package com.playdeez.hamquest.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Descriptor {
	private Map<String,Object> properties = new HashMap<String,Object>();
	public boolean hasProperty(String property){
		return properties.containsKey(property);
	}
	protected void putProperty(String property, Object value){
		if(value==null){
			if(hasProperty(property)){
				properties.remove(property);
			}
		}else{
			properties.put(property, value);
		}
	}
	@SuppressWarnings("unchecked")
	public <T> T getProperty(String property){
		return (T)properties.get(property);
	}
	protected void addPropertyValues(List<PropertyValuePair> thePropertyValues){
		for(PropertyValuePair propertyValue:thePropertyValues){
			putProperty(propertyValue.getProperty(),propertyValue.getValue());
		}
	}
	public Descriptor(List<PropertyValuePair> thePropertyValues){
		addPropertyValues(thePropertyValues);
	}
}
