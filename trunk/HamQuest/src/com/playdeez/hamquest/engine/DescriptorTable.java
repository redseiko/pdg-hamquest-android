package com.playdeez.hamquest.engine;

import java.util.*;

import android.content.res.AssetManager;

public class DescriptorTable extends GameChild {
	private Map<String,Descriptor> table = null;
	public Set<String> getIdentifiers(){
		return table.keySet();
	}
	public Descriptor getDescriptor(String theIdentifier){
		return table.get(theIdentifier);
	}
	public DescriptorTable(Game theParent,String theFileName,AssetManager theAssetManager, String theRootNodeName, String theDescriptorNodeName){
		super(theParent);
		table = Descriptor.loadDescriptorTableFromXmlFile(theFileName, theAssetManager, getParent().getGameConstants(), theRootNodeName, theDescriptorNodeName);
	}
}
