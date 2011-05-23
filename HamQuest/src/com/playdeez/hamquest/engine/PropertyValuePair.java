package com.playdeez.hamquest.engine;

import java.util.*;

import org.w3c.dom.*;
import android.content.res.AssetManager;
import android.util.Log;

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
	private static Node getAttributeNode(Node theNode, String theAttributeName){
		Log.d("PropertyValuePair","getAttributeNode");
		NamedNodeMap nodeMap = theNode.getAttributes();
		for(Integer index=0;index<nodeMap.getLength();++index){
			Node node = nodeMap.item(index);
			if(node.getNodeName().compareTo(theAttributeName)==0){
				return node;
			}
		}
		return null;
	}
	private static List<PropertyValuePair> processTemplateAttribute(Node theNode, AssetManager theAssetManager, GameConstants<Game> theGameConstants){
		Log.d("PropertyValuePair","processTemplateAttribute");
		List<PropertyValuePair> properties = new ArrayList<PropertyValuePair>();
		//TODO finish me
		return properties;
	}
	private static List<PropertyValuePair> processAttributes(Node theNode, AssetManager theAssetManager,GameConstants<Game> theGameConstants){
		Log.d("PropertyValuePair","processAttributes");
		Node templateAttributeNode = getAttributeNode(theNode,theGameConstants.getTagNames().Template);
		if(templateAttributeNode!=null){
			return processTemplateAttribute(templateAttributeNode,theAssetManager,theGameConstants);
		}
		return new ArrayList<PropertyValuePair>();
	}
	private static Set<String> loadTagSet(Node theNode, GameConstants<Game> theGameConstants){
		Set<String> result = new HashSet<String>();
		NodeList nodes = theNode.getChildNodes();
		for(Integer index = 0;index<nodes.getLength();++index){
			Node node = nodes.item(index);
			if(node.getNodeName().compareTo(theGameConstants.getTagNames().Tag)==0){
				Node firstChild = node.getFirstChild();
				if(firstChild!=null){
					result.add(firstChild.getNodeValue());
				}
			}
		}
		return result;
	}
	private static PropertyValuePair processEntityNode(Node theNode, AssetManager theAssetManager, GameConstants<Game> theGameConstants){
		Log.d("PropertyValuePair","processEntityNode");
		String theKey = theNode.getNodeName();
		Node typeAttributeNode = getAttributeNode(theNode,theGameConstants.getTagNames().Type);
		if(typeAttributeNode==null || typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().String)==0){
			Node firstChild = theNode.getFirstChild();
			if(firstChild!=null){
				String theValue = theNode.getFirstChild().getNodeValue();
				return new PropertyValuePair(theKey,theValue);
			}else{
				return new PropertyValuePair(theKey,"");
			}
		}else if(typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().Integer)==0){
			String theValue = theNode.getFirstChild().getNodeValue();
			return new PropertyValuePair(theKey,new Integer(theValue));
		}else if(typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().Double)==0){
			String theValue = theNode.getFirstChild().getNodeValue();
			return new PropertyValuePair(theKey,new Double(theValue));
		}else if(typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().Boolean)==0){
			String theValue = theNode.getFirstChild().getNodeValue();
			return new PropertyValuePair(theKey,new Boolean(theValue));
		}else if(typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().TagSet)==0){
			Set<String> theValue = loadTagSet(theNode,theGameConstants);
			return new PropertyValuePair(theKey,theValue);
		}else{
			return null;
		}
	}
	public static List<PropertyValuePair> loadPropertyValuesFromXmlNode(Node theNode,AssetManager theAssetManager,GameConstants<Game> theGameConstants){
		Log.d("PropertyValuePair","loadPropertyValuesFromXmlNode");
		List<PropertyValuePair> properties = processAttributes(theNode,theAssetManager,theGameConstants);
		NodeList nodes = theNode.getChildNodes();
		for(Integer index=0;index<nodes.getLength();++index){
			Node node = nodes.item(index);
			if(node.getNodeType()==Node.ELEMENT_NODE){
				PropertyValuePair thePair = processEntityNode(node,theAssetManager,theGameConstants);
				if(thePair!=null){
					properties.add(thePair);
				}
			}
		}
		return properties;
	}
}
