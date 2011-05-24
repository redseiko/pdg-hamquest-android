package com.playdeez.hamquest.engine;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.res.AssetManager;
import android.util.Log;

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
	private static Descriptor loadDescriptorFromNode(Node theNode, AssetManager theAssetManager,GameConstants<Game> theGameConstants) throws ParserConfigurationException, IOException, SAXException{
		Log.d("Descriptor","loadDescriptorFromNode");
		List<PropertyValuePair> properties = PropertyValuePair.loadPropertyValuesFromXmlNode(theNode,theAssetManager,theGameConstants);
		Descriptor descriptor = new Descriptor(properties);
		return descriptor;	
	}
	private static List<Descriptor> loadDescriptorsFromRootNode(Node theNode,AssetManager theAssetManager,GameConstants<Game> theGameConstants, String theDescriptorNodeName) throws ParserConfigurationException, IOException, SAXException{
		List<Descriptor> result=new ArrayList<Descriptor>();
		Log.d("Descriptor","loadDescriptorsFromRootNode");
		NodeList nodes = theNode.getChildNodes();
		for(Integer index=0;index<nodes.getLength();++index){
			Node node = nodes.item(index);
			Log.d("Descriptor",node.getNodeName());
			if(node.getNodeName().compareTo(theDescriptorNodeName)==0){
				result.add(loadDescriptorFromNode(node,theAssetManager,theGameConstants));
			}
		}
		return result;
	}
	private static List<Descriptor> loadDescriptorsFromXmlDocument(Document theDocument,AssetManager theAssetManager,GameConstants<Game> theGameConstants, String theRootNodeName,String theDescriptorNodeName) throws ParserConfigurationException, IOException, SAXException{
		Log.d("Descriptor","loadDescriptorsFromXmlDocument");
		NodeList nodes = theDocument.getChildNodes();
		for(Integer index=0;index<nodes.getLength();++index){
			Node node = nodes.item(index);
			Log.d("Descriptor",node.getNodeName());
			if(node.getNodeName().compareTo(theRootNodeName)==0){
				return loadDescriptorsFromRootNode(node,theAssetManager,theGameConstants,theDescriptorNodeName);
			}
		}
		return null;
	}
	public static Map<String,Descriptor> loadDescriptorTableFromXmlFile(String theFileName,AssetManager theAssetManager,GameConstants<Game> theGameConstants, String theRootNodeName,String theDescriptorNodeName){
		try {
			Map<String,Descriptor> table = new HashMap<String,Descriptor>();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream reader = theAssetManager.open(theFileName);
			Document document = builder.parse(reader);
			List<Descriptor> descriptors = Descriptor.loadDescriptorsFromXmlDocument(document, theAssetManager, theGameConstants, theRootNodeName, theDescriptorNodeName);
			for(Descriptor descriptor:descriptors){
				String theIdentifier = descriptor.<String>getProperty(theGameConstants.getTagNames().Identifier);
				table.put(theIdentifier, descriptor);
			}
			return table;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}
}
