package org.databene.feed4junit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.databene.benerator.Generator;
import org.databene.benerator.anno.AnnotationMapper;
import org.databene.benerator.anno.DefaultPathResolver;
import org.databene.benerator.anno.PathResolver;
import org.databene.benerator.anno.ThreadPoolSize;
import org.databene.benerator.engine.BeneratorContext;
import org.databene.benerator.engine.DefaultBeneratorContext;
import org.databene.benerator.factory.EquivalenceGeneratorFactory;
import org.databene.benerator.wrapper.ProductWrapper;
import org.databene.commons.ConfigurationError;
import org.databene.commons.IOUtil;
import org.databene.commons.Period;
import org.databene.commons.StringUtil;
import org.databene.commons.converter.AnyConverter;
import org.databene.feed4junit.ChildRunner;
import org.databene.feed4junit.FrameworkMethodWithParameters;
import org.databene.feed4junit.Scheduler;
import org.databene.feed4junit.scheduler.DefaultFeedScheduler;
import org.databene.model.data.DataModel;
import org.databene.platform.java.BeanDescriptorProvider;
import org.databene.platform.java.Entity2JavaConverter;
import org.databene.script.DatabeneScriptParser;
import org.databene.script.Expression;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.unitils.UnitilsJUnitBlockRunner;

/**
 * 
 * 
 * Feeder整合Unitils.
 * 
 * <pre><b>描述：</b>
 *    结合Unitils对容器的管理和Feed4JUnit的冒烟测试
 * </pre>
 * 
 * <pre><b>修改记录：</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">蔡源</a>
 * @since 2013-8-12
 * @version 1.0
 *
 */
public class Feeder extends UnitilsJUnitBlockRunner
{
	public static final String CONFIG_FILENAME_PROPERTY = "feed4junit.properties";
	private static final String DEFAULT_CONFIG_FILENAME = "feed4junit.properties";
	private static final String FEED4JUNIT_BASE_PATH = "feed4junit.basepath";

	private static final long DEFAULT_TIMEOUT = Period.WEEK.getMillis();

	static {
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
	}
	
	private BeneratorContext context;
	private PathResolver pathResolver;
	private AnnotationMapper annotationMapper;
	private List<FrameworkMethod> children;
	private RunnerScheduler scheduler;

	public Feeder(Class<?> aClass) throws InitializationError
	{
		super(aClass);
		this.children = null;
	}
	
	@Override
	protected String testName(FrameworkMethod method) {
		return (method instanceof FrameworkMethodWithParameters ? method.toString() : super.testName(method));
	}
	
	@Override
	public void setScheduler(RunnerScheduler scheduler) {
		this.scheduler = scheduler;
		super.setScheduler(scheduler);
	}
	
	/**
	 * Instantiates a test class and initializes attributes 
	 * which have been marked with a @Source annotation.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Object createTest() throws Exception {
		Object testObject = super.createTest();
		for (FrameworkField attribute : getTestClass().getAnnotatedFields(org.databene.benerator.anno.Source.class)) {
			if ((attribute.getField().getModifiers() & Modifier.PUBLIC) == 0)
				throw new ConfigurationError("Attribute '" + attribute.getField().getName() + "' must be public");
			Generator<?> generator = getAnnotationMapper().createAndInitAttributeGenerator(attribute.getField(), getContext());
			if (generator != null) {
				ProductWrapper wrapper = new ProductWrapper();
				wrapper = generator.generate(wrapper);
				if (wrapper != null)
					attribute.getField().set(testObject, wrapper.unwrap());
			}
		}
		return testObject;
	}
	
	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		if (children == null) {
			children = new ArrayList<FrameworkMethod>();
			TestClass testClass = getTestClass();
			BeneratorContext context = getContext();
			context.setGeneratorFactory(new EquivalenceGeneratorFactory());
			getAnnotationMapper().parseClassAnnotations(testClass.getAnnotations(), context);
			for (FrameworkMethod method : testClass.getAnnotatedMethods(Test.class)) {
				if (method.getMethod().getParameterTypes().length == 0) {
					// standard JUnit test method
					children.add(method);
					continue;
				} else {
					// parameterized Feed4JUnit test method
					List<? extends FrameworkMethod> parameterizedTestMethods;
					parameterizedTestMethods = computeParameterizedTestMethods(method.getMethod(), context);
					children.addAll(parameterizedTestMethods);
				}
			}
		}
		return children;
	}

	@Override
    protected void validateTestMethods(List<Throwable> errors) {
		validatePublicVoidMethods(Test.class, false, errors);
	}

	// test execution --------------------------------------------------------------------------------------------------
	
	protected Statement childrenInvoker(final RunNotifier notifier) {
		return new Statement() {
			@Override
			public void evaluate() {
				runChildren(notifier);
			}
		};
	}

	private void runChildren(final RunNotifier notifier) {
		RunnerScheduler scheduler = getScheduler();
		for (FrameworkMethod method : getChildren())
 			scheduler.schedule(new ChildRunner(this, method, notifier));
		scheduler.finished();
	}

	public RunnerScheduler getScheduler() {
		if (scheduler == null)
			scheduler = createDefaultScheduler();
		return scheduler;
	}
	
	protected RunnerScheduler createDefaultScheduler() {
		TestClass testClass = getTestClass();
		Scheduler annotation = testClass.getJavaClass().getAnnotation(Scheduler.class);
		if (annotation != null) {
			String spec = annotation.value();
			Expression<?> bean = DatabeneScriptParser.parseBeanSpec(spec);
			return (RunnerScheduler) bean.evaluate(null);
		} else {
			return new DefaultFeedScheduler(1, DEFAULT_TIMEOUT);
		}
	}

	@Override
	public void runChild(FrameworkMethod method, RunNotifier notifier) {
		super.runChild(method, notifier);
	}
	
	// helpers ---------------------------------------------------------------------------------------------------------

	private PathResolver configuredPathResolver() {
		if (pathResolver != null)
			return pathResolver;
		String configuredConfigFileName = System.getProperty(CONFIG_FILENAME_PROPERTY);
		String configFileName = configuredConfigFileName;
		if (StringUtil.isEmpty(configFileName))
			configFileName = DEFAULT_CONFIG_FILENAME;
		if (IOUtil.isURIAvailable(configFileName)) {
			// load individual or configured config file
			return configuredPathResolver(configFileName);
		} else if (StringUtil.isEmpty(configuredConfigFileName)) {
			// if no explicit config file was configured, then use defaults...
			return createDefaultResolver();
		} else {
			// ...otherwise raise an exception
			throw new ConfigurationError("Feed4JUnit configuration file not found: " + configuredConfigFileName);
		}
	}

	private PathResolver createDefaultResolver() {
		return applyBasePath(new DefaultPathResolver());
	}

	private PathResolver configuredPathResolver(String configFileName) {
		try {
			Map<String, String> properties = IOUtil.readProperties(configFileName);
			String pathResolverSpec = properties.get("pathResolver");
			if (pathResolverSpec != null) {
				PathResolver resolver;
				resolver =  (PathResolver) DatabeneScriptParser.parseBeanSpec(pathResolverSpec).evaluate(getContext());
				return applyBasePath(resolver);
			} else
				return createDefaultResolver();
		} catch (IOException e) {
			throw new ConfigurationError("Error reading config file '" + configFileName + "'", e);
		}
	}

	private PathResolver applyBasePath(PathResolver resolver) {
		String confdBasePath = System.getProperty(FEED4JUNIT_BASE_PATH);
		if (confdBasePath != null)
			resolver.setBasePath(confdBasePath);
		return resolver;
	}

	private void validatePublicVoidMethods(Class<? extends Annotation> annotation, boolean isStatic, List<Throwable> errors) {
		List<FrameworkMethod> methods = getTestClass().getAnnotatedMethods(annotation);
		for (FrameworkMethod eachTestMethod : methods)
			eachTestMethod.validatePublicVoid(isStatic, errors);
	}

	private List<FrameworkMethodWithParameters> computeParameterizedTestMethods(Method method, BeneratorContext context) {
		Integer threads = getThreadCount(method);
		long timeout = getTimeout(method);
		List<FrameworkMethodWithParameters> result = new ArrayList<FrameworkMethodWithParameters>();
		Class<?>[] parameterTypes = method.getParameterTypes();
		Generator<Object[]> paramGenerator = getAnnotationMapper().createAndInitMethodParamsGenerator(method, context);
		Class<?>[] expectedTypes = parameterTypes;
		ProductWrapper<Object[]> wrapper = new ProductWrapper<Object[]>();
		int count = 0;
		while ((wrapper = paramGenerator.generate(wrapper)) != null) {
			Object[] generatedParams = wrapper.unwrap();
			if (generatedParams.length > expectedTypes.length) // imported data may have more columns than the method parameters, ...
				generatedParams = Arrays.copyOfRange(generatedParams, 0, expectedTypes.length); // ...so cut them
			for (int i = 0; i < generatedParams.length; i++) {
				generatedParams[i] = Entity2JavaConverter.convertAny(generatedParams[i]);
				generatedParams[i] = AnyConverter.convert(generatedParams[i], parameterTypes[i]);
			}
			// generated params may be to few, e.g. if an XLS row was imported with trailing nulls, 
			// so create an array of appropriate size
			Object[] usedParams = new Object[parameterTypes.length];
			System.arraycopy(generatedParams, 0, usedParams, 0, Math.min(generatedParams.length, usedParams.length));
			result.add(new FrameworkMethodWithParameters(method, usedParams, threads, timeout));
			count++;
		}
		if (count == 0)
			throw new RuntimeException("No parameter values available for method: " + method);
		return result;
	}

	private Integer getThreadCount(Method method) {
		ThreadPoolSize methodAnnotation = method.getAnnotation(ThreadPoolSize.class);
		if (methodAnnotation != null)
			return methodAnnotation.value();
		Class<?> testClass = method.getDeclaringClass();
		ThreadPoolSize classAnnotation = testClass.getAnnotation(ThreadPoolSize.class);
		if (classAnnotation != null)
			return classAnnotation.value();
		return null;
	}

	private long getTimeout(Method method) {
		return DEFAULT_TIMEOUT;
	}

	private AnnotationMapper getAnnotationMapper() { 
		// lazy initialization is necessary since the constructor is not executed by JUnit
		if (annotationMapper == null) {
			PathResolver pathResolver = configuredPathResolver();
			annotationMapper = new AnnotationMapper(new EquivalenceGeneratorFactory(), getDataModel(), pathResolver);
		}
		return annotationMapper;
	}

	private BeneratorContext getContext() { 
		// lazy initialization is necessary since the constructor is not executed by JUnit
		if (context == null) {
			context = new DefaultBeneratorContext();
			DataModel dataModel = context.getDataModel();
			new BeanDescriptorProvider(dataModel);
		}
		return context;
	}

	private DataModel getDataModel() {
		return getContext().getDataModel();
	}


}
