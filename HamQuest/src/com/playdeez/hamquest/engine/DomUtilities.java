package com.playdeez.hamquest.engine;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import android.util.Log;

public class DomUtilities {
	public static Node getAttributeNode(Node theNode, String theAttributeName){
		Log.d("DomUtilities","getAttributeNode");
		NamedNodeMap nodeMap = theNode.getAttributes();
		for(Integer index=0;index<nodeMap.getLength();++index){
			Node node = nodeMap.item(index);
			if(node.getNodeName().compareTo(theAttributeName)==0){
				return node;
			}
		}
		return null;
	}

}
