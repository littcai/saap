package org.unitils;

import java.util.ArrayList;
import java.util.List;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;
import org.unitils.core.TestListener;
import org.unitils.core.Unitils;

/** 
 * 
 * Unitils基于BlockJUnit4ClassRunner的改写.
 * 
 * <pre><b>描述：</b>
 *    升级为BlockJUnit4ClassRunner
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
public class UnitilsJUnitBlockRunner extends BlockJUnit4ClassRunner
{
	TestListener listener;

    public UnitilsJUnitBlockRunner(Class<?> aClass) throws InitializationError {
        super(aClass);
        listener = Unitils.getInstance().getTestListener();
    }

    @Override
    protected Statement classBlock(RunNotifier runNotifier) {
        listener.beforeTestClass(getTestClass().getClass());
        return super.classBlock(runNotifier);
    }

    @Override
    protected Object createTest() throws Exception {
        Object o = super.createTest();
        listener.afterCreateTestObject(o);
        return o;
    }

    @Override
    protected Statement methodInvoker(final FrameworkMethod frameworkMethod, final Object o) {
        return new InvokeMethod(frameworkMethod, o) {
            @Override
            public void evaluate() throws Throwable {
                listener.beforeTestMethod(o, frameworkMethod.getMethod());
                Throwable threw = null;
                try {
                    super.evaluate();
                } catch (Throwable t) {
                    threw = t;
                } finally {
                    listener.afterTestMethod(o, frameworkMethod.getMethod(), threw);
                }
                if (threw != null) {
                    throw threw;
                }
            }
        };
    }

    @Override
    protected Statement withBeforeClasses(Statement statement) {
        final Statement junitBeforeClasses = super.withBeforeClasses(statement);

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                listener.beforeTestClass(getTestClass().getClass());
                junitBeforeClasses.evaluate();
            }
        };
    }
    
    @Override
    protected Statement withBefores(final FrameworkMethod method, final Object target, Statement statement) {
        final Statement junitBefores = super.withBefores(method, target, statement);
        
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                listener.beforeTestSetUp(target, method.getMethod());
                junitBefores.evaluate();
            }
        };
    }
    
    @Override
    protected Statement withAfters(final FrameworkMethod method, final Object target, final Statement statement) {
        final Statement junitAfters = super.withAfters(method, target, statement);
        
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                List<Throwable> errors = new ArrayList<Throwable>();
                try {
                    junitAfters.evaluate();
                }
                catch (Throwable e) {
                    errors.add(e);
                }

                try {
                    listener.afterTestTearDown(target, method.getMethod());
                }
                catch (Exception e) {
                    errors.add(e);
                }

                if (errors.isEmpty()) {
                    return;
                }
                if (errors.size() == 1) {
                    throw errors.get(0);
                }
                throw new MultipleFailureException(errors);
            }
        };
    }

}
