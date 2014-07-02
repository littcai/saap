package com.litt.saap.core.module.dynamicsearch;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litt.core.util.StringUtils;
import com.litt.saap.core.module.dynamicform.model.Field;
import com.litt.saap.core.module.dynamicsearch.model.Column;
import com.litt.saap.core.module.dynamicsearch.model.Condition;
import com.litt.saap.core.module.dynamicsearch.model.ConditionGroup;
import com.litt.saap.core.module.dynamicsearch.model.DynamicSearch;
import com.litt.saap.core.module.dynamicsearch.model.DynamicSearchResult;
import com.litt.saap.core.module.dynamicsearch.model.FieldGroup;
import com.litt.saap.core.module.dynamicsearch.model.Table;

/**
 * DynamicSearchManager.
 * 
 * <pre><b>Descr:</b>
 *    
 * </pre>
 * 
 * <pre><b>Changelog:</b>
 *    
 * </pre>
 * 
 * @author <a href="mailto:littcai@hotmail.com">Caiyuan</a>
 * @since 2014-6-13
 * @version 1.0
 */
public class DynamicSearchGenerator {
	
	/** Logger. */
    private static final Logger logger = LoggerFactory.getLogger(DynamicSearchGenerator.class);
	
	private DynamicSearch dynamicSearch;	

	private Map<String, ConditionGroup> conditionGroups = new HashMap<String, ConditionGroup>();
	
	/**
	 * @param dynamicSearch
	 */
	public DynamicSearchGenerator(DynamicSearch dynamicSearch) {
		super();
		this.dynamicSearch = dynamicSearch;
	}
	
	public void addConditionGroup(ConditionGroup conditionGroup)
	{
		this.conditionGroups.put(conditionGroup.getFieldName(), conditionGroup);
	}
	
	public DynamicSearchResult generate()
	{
		
		DynamicSearchResult result = new DynamicSearchResult();
		
		List<FieldGroup> fieldGroupList = dynamicSearch.getFieldGroupList();
		this.genBaseFieldGroup(result, fieldGroupList.get(0));
		for (int i=1; i<fieldGroupList.size(); i++) {
			FieldGroup fieldGroup = fieldGroupList.get(i);
			this.genAdditionFieldGroup(result, fieldGroup);
		}
		
		return result;
	}

	/**
	 * @param result
	 */
	public void genBaseFieldGroup(DynamicSearchResult result, FieldGroup baseFieldGroup) {
		
		Table table = baseFieldGroup.getTable();
		result.addFrom(table.getName()+" AS "+baseFieldGroup.getName());
		genTable(result, baseFieldGroup);
	}
	
	/**
	 * @param result
	 */
	public void genAdditionFieldGroup(DynamicSearchResult result, FieldGroup fieldGroup) {
		
		if(fieldGroup.isDynamic())
		{
			boolean isExist = false;
		
			List<Field> fieldList = fieldGroup.getFieldList();
			for (Field field : fieldList) 
			{
				String key = fieldGroup.getName()+"."+field.getKey();
				
				if(conditionGroups.containsKey(key))
				{
					isExist = true;
					break;
				}
			}
			if(!isExist)
				return;
		}
		
		Table table = fieldGroup.getTable();
		result.addFrom(table.getJoinType()+" "+table.getName()+" AS "+fieldGroup.getName()+" ON "+table.getJoinOn());
		
		this.genTable(result, fieldGroup);
	}
	
	/**
	 * Gen table.
	 *
	 * @param result the result
	 * @param fieldGroup the field group
	 */
	public void genTable(DynamicSearchResult result, FieldGroup fieldGroup) {
		Table table = fieldGroup.getTable();
		//where
		if(!StringUtils.isEmpty(table.getWhere()))
		{
			result.addWhere(fieldGroup.getName()+"."+table.getWhere());
		}
		List<Column> columnList = table.getColumnList();
		for (Column column : columnList) {
			String select = fieldGroup.getName()+"."+column.getName();
			if(!StringUtils.isEmpty(column.getAlias()))
			{
				select += " AS "+column.getAlias();
			}
			result.addSelect(select);
		}
		//
		List<Field> fieldList = fieldGroup.getFieldList();
		for (Field field : fieldList) {
			String key = fieldGroup.getName()+"."+field.getKey();
			String dataType = field.getDataType();
			if(conditionGroups.containsKey(key))
			{
				logger.debug("find conditionGroup:{}", new Object[]{key});
				
				StringBuilder whereBuilder = new StringBuilder();
				whereBuilder.append("(");
				ConditionGroup conditionGroup = conditionGroups.get(key);
				Condition<?>[] conditions = conditionGroup.getConditions();
				
				for (int i=0; i<conditions.length; i++) {
					Condition<?> condition = conditions[i];
					if(i>0)
					{
						whereBuilder.append(" OR ");
					}
					whereBuilder.append(key).append(condition.getRealSymbol());
					
					String symbol = condition.getSymbol();
					if(Condition.LIKE.equals(symbol))
					{
						result.addParam("%"+condition.getValue()+"%");
					}
					else if(Condition.START_WITH.equals(symbol))
					{
						result.addParam(condition.getValue()+"%");
					}
					else if(Condition.END_WITH.equals(symbol))
					{
						result.addParam("%"+condition.getValue());
					}
					else if(Condition.BETWEEN.equals(symbol))
					{
						Object[] values = (Object[])condition.getValue();
						result.addParam(values[0]);
						result.addParam(values[1]);
					}
					else 
					{
						result.addParam(condition.getValue());
					}
				}
				whereBuilder.append(")");
				result.addWhere(whereBuilder.toString());
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DynamicSearch dynamicSearch = DynamicSearchManager.getInstance().getDynamicSearch("assetInfo-dynamicsearch");
		
		DynamicSearchGenerator generator = new DynamicSearchGenerator(dynamicSearch);
		{
			ConditionGroup conditionGroup = new ConditionGroup("chargeUser.LASTNAME");
			{
				Condition<String> condition = new Condition<String>();
				condition.setSymbol(Condition.LIKE);
				condition.setValue("aaa");
				conditionGroup.addCondition(condition);
			}
			{
				Condition<String> condition = new Condition<String>();
				condition.setSymbol(Condition.EQ);
				condition.setValue("bbb");
				conditionGroup.addCondition(condition);
			}
			generator.addConditionGroup(conditionGroup);
		}
		{	
			ConditionGroup conditionGroup = new ConditionGroup("commonName.ATTR_VALUE");
			{
				Condition<String> condition = new Condition<String>();
				condition.setSymbol(Condition.LIKE);
				condition.setValue("ccc");
				conditionGroup.addCondition(condition);
			}
			generator.addConditionGroup(conditionGroup);
		}
		{
			ConditionGroup conditionGroup = new ConditionGroup("age");
			{
				Condition<Integer> condition = new Condition<Integer>();
				condition.setSymbol(Condition.EQ);
				condition.setValue(1);
				conditionGroup.addCondition(condition);
			}
			{
				Condition<Integer> condition = new Condition<Integer>();
				condition.setSymbol(Condition.GT);
				condition.setValue(100);
				conditionGroup.addCondition(condition);
			}
			generator.addConditionGroup(conditionGroup);
		}
		{
			ConditionGroup conditionGroup = new ConditionGroup("storageDate.ATTR_VALUE_DATE");
			{
				Condition<Date> condition = new Condition<Date>();
				condition.setSymbol(Condition.EQ);
				condition.setValue(new Date());
				conditionGroup.addCondition(condition);
			}
			{
				Date[] dates = new Date[]{new Date(), new Date()};
				Condition<Date[]> condition = new Condition<Date[]>();
				condition.setSymbol(Condition.BETWEEN);
				condition.setValue(dates);
				conditionGroup.addCondition(condition);
			}
			generator.addConditionGroup(conditionGroup);
		}
		
		DynamicSearchResult result = generator.generate();
		System.out.println(result.genSQL());
		List<Object> paramsList = result.getParams();
		for (Object object : paramsList) {
			System.out.println(object);
		}
	}

}
