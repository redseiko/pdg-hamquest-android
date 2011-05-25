package com.playdeez.hamquest.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.playdeez.boardgames.WeightedGenerator;

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
	private static List<PropertyValuePair> processTemplateAttribute(Node theNode, AssetManager theAssetManager, GameConstants<Game> theGameConstants) throws ParserConfigurationException, IOException, SAXException{
		Log.d("PropertyValuePair","processTemplateAttribute");
		String[] attributes = theNode.getNodeValue().split(",");
		for(String attribute:attributes){
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream reader = theAssetManager.open(attribute);
			Document document = builder.parse(reader);
			Node rootNode = document.getFirstChild();
			return loadPropertyValuesFromXmlNode(rootNode,theAssetManager,theGameConstants);
		}
		return null;
	}
	private static List<PropertyValuePair> processAttributes(Node theNode, AssetManager theAssetManager,GameConstants<Game> theGameConstants) throws ParserConfigurationException, IOException, SAXException{
		Log.d("PropertyValuePair","processAttributes");
		Node templateAttributeNode = DomUtilities.getAttributeNode(theNode,theGameConstants.getTagNames().Template);
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
	private static WeightedGenerator<Boolean> loadBooleanGenerator(Node theNode, GameConstants<Game> theGameConstants){
		WeightedGenerator<Boolean> result = new WeightedGenerator<Boolean>();
		for(Node node = theNode.getFirstChild();node!=null;node=node.getNextSibling()){
			if(node.getNodeType()==Node.ELEMENT_NODE && node.getNodeName().compareTo(theGameConstants.getTagNames().Entry)==0){
				Boolean theValue = false;
				Integer theWeight = 0;
				for(Node subNode=node.getFirstChild();subNode!=null;subNode = subNode.getNextSibling()){
					if(subNode.getNodeType()==Node.ELEMENT_NODE){
						if(subNode.getNodeName().compareTo(theGameConstants.getTagNames().Value)==0){
							theValue = new Boolean(subNode.getFirstChild().getNodeValue());
						}else if(subNode.getNodeName().compareTo(theGameConstants.getTagNames().Weight)==0){
							theWeight = new Integer(subNode.getFirstChild().getNodeValue());
						}
					}
				}
				result.setWeight(theValue, theWeight);
			}
		}
		return result;
	}
	private static WeightedGenerator<Integer> loadIntegerGenerator(Node theNode, GameConstants<Game> theGameConstants){
		WeightedGenerator<Integer> result = new WeightedGenerator<Integer>();
		for(Node node = theNode.getFirstChild();node!=null;node=node.getNextSibling()){
			if(node.getNodeType()==Node.ELEMENT_NODE && node.getNodeName().compareTo(theGameConstants.getTagNames().Entry)==0){
				Integer theValue = 0;
				Integer theWeight = 0;
				for(Node subNode=node.getFirstChild();subNode!=null;subNode = subNode.getNextSibling()){
					if(subNode.getNodeType()==Node.ELEMENT_NODE){
						if(subNode.getNodeName().compareTo(theGameConstants.getTagNames().Value)==0){
							theValue = new Integer(subNode.getFirstChild().getNodeValue());
						}else if(subNode.getNodeName().compareTo(theGameConstants.getTagNames().Weight)==0){
							theWeight = new Integer(subNode.getFirstChild().getNodeValue());
						}
					}
				}
				result.setWeight(theValue, theWeight);
			}
		}
		return result;
	}
	private static WeightedGenerator<String> loadStringGenerator(Node theNode, GameConstants<Game> theGameConstants){
		WeightedGenerator<String> result = new WeightedGenerator<String>();
		for(Node node = theNode.getFirstChild();node!=null;node=node.getNextSibling()){
			if(node.getNodeType()==Node.ELEMENT_NODE && node.getNodeName().compareTo(theGameConstants.getTagNames().Entry)==0){
				String theValue = "";
				Integer theWeight = 0;
				for(Node subNode=node.getFirstChild();subNode!=null;subNode = subNode.getNextSibling()){
					if(subNode.getNodeType()==Node.ELEMENT_NODE){
						if(subNode.getNodeName().compareTo(theGameConstants.getTagNames().Value)==0){
							if(subNode.getFirstChild()!=null){
								theValue = subNode.getFirstChild().getNodeValue();
							}
						}else if(subNode.getNodeName().compareTo(theGameConstants.getTagNames().Weight)==0){
							theWeight = new Integer(subNode.getFirstChild().getNodeValue());
						}
					}
				}
				result.setWeight(theValue, theWeight);
			}
		}
		return result;
	}
	private static PropertyValuePair processEntityNode(Node theNode, AssetManager theAssetManager, GameConstants<Game> theGameConstants){
		Log.d("PropertyValuePair","processEntityNode");
		String theKey = theNode.getNodeName();
		Node typeAttributeNode = DomUtilities.getAttributeNode(theNode,theGameConstants.getTagNames().Type);
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
		}else if(typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().BooleanGenerator)==0){
			WeightedGenerator<Boolean> theValue = loadBooleanGenerator(theNode,theGameConstants);
			return new PropertyValuePair(theKey,theValue);
		}else if(typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().IntegerGenerator)==0){
			WeightedGenerator<Integer> theValue = loadIntegerGenerator(theNode,theGameConstants);
			return new PropertyValuePair(theKey,theValue);
		}else if(typeAttributeNode.getNodeValue().compareTo(theGameConstants.getTypes().StringGenerator)==0){
			WeightedGenerator<String> theValue = loadStringGenerator(theNode,theGameConstants);
			return new PropertyValuePair(theKey,theValue);
		}else{
			return null;
		}
	}
	public static List<PropertyValuePair> loadPropertyValuesFromXmlNode(Node theNode,AssetManager theAssetManager,GameConstants<Game> theGameConstants) throws ParserConfigurationException, IOException, SAXException{
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
