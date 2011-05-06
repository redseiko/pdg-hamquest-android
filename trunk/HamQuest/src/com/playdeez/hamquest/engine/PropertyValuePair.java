package com.playdeez.hamquest.engine;

import java.util.*;
import org.w3c.dom.*;

public class PropertyValuePair {
	private String property;
	private Object value;
	public String getProperty(){
		return property;
	}
	public Object getValue(){
		return value;
	}
	public PropertyValuePair(String theProperty, Object theValue){
		property = theProperty;
		value = theValue;
	}
	public static List<PropertyValuePair> loadPropertyValuesFromXmlNode(Node node, final String identifier){
		List<PropertyValuePair> properties = new ArrayList<PropertyValuePair>();
		
		if(node.getAttributes().getNamedItem(GameConstants.TagNames.Template)!=null){
			String[] filenames = node.getAttributes().getNamedItem(GameConstants.TagNames.Template).getNodeValue().split(",");
			for(String filename:filenames){
				
			}
		}
		
		return properties;
	}
}
