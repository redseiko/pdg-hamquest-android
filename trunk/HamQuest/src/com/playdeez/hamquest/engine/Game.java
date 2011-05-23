package com.playdeez.hamquest.engine;

import android.content.res.AssetManager;

public class Game {
	private String theme = "default";
	public String getTheme(){
		return theme;
	}
	private GameConstants<Game> gameConstants;
	public GameConstants<Game> getGameConstants(){
		return gameConstants;
	}
	private DescriptorTable themeTable;
	public DescriptorTable getThemeTable(){
		return themeTable;
	}
	private DescriptorTable propertyGroupsTable;
	public DescriptorTable getPropertyGroupsTable(){
		return propertyGroupsTable;
	}
	private DescriptorTable messagesTable;
	public DescriptorTable getMessagesTable(){
		return messagesTable;
	}
	private DescriptorTable terrainsTable;
	public DescriptorTable getTerrainsTable(){
		return terrainsTable;
	}
	public void loadTablesForTheme(AssetManager theAssetManager){
		Descriptor themeDescriptor = themeTable.getDescriptor(getTheme());
		propertyGroupsTable = new DescriptorTable(this,themeDescriptor.<String>getProperty(getGameConstants().getTagNames().PropertyGroups),theAssetManager, getGameConstants().getTagNames().PropertyGroups, getGameConstants().getTagNames().PropertyGroup);
		messagesTable = new DescriptorTable(this,themeDescriptor.<String>getProperty(getGameConstants().getTagNames().Messages),theAssetManager, getGameConstants().getTagNames().Messages, getGameConstants().getTagNames().Message);
		terrainsTable = new DescriptorTable(this,themeDescriptor.<String>getProperty(getGameConstants().getTagNames().Terrains),theAssetManager, getGameConstants().getTagNames().Terrains, getGameConstants().getTagNames().Terrain);
	}
	public Game(AssetManager theAssetManager){
		gameConstants = new GameConstants<Game>(this);
		themeTable = new DescriptorTable(this,getGameConstants().getAssetLocations().Themes,theAssetManager,getGameConstants().getTagNames().Themes,getGameConstants().getTagNames().Theme);
		loadTablesForTheme(theAssetManager);
	}
}
