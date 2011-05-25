package com.playdeez.hamquest.engine;

import com.playdeez.boardgames.INestedObject;

public final class GameConstants<T> implements INestedObject<T> {
	public final class AssetLocations {
		public final String Themes = "config/themes.xml";
	}
	private AssetLocations assetLocations = new AssetLocations();
	public AssetLocations getAssetLocations(){
		return assetLocations;
	}
	public final class TagNames {
		public final String Creature = "creature";
		public final String Creatures = "creatures";
		public final String Entry = "entry";
		public final String Identifier = "identifier";
		public final String Message = "message";
		public final String Messages = "messages";
		public final String PropertyGroup = "property-group";
		public final String PropertyGroups = "property-groups";
		public final String Tag = "tag";
		public final String Tags = "tags";
		public final String Template="template";
		public final String Terrain = "terrain";
		public final String Terrains = "terrains";
		public final String Text = "text";
		public final String Theme = "theme";
		public final String Themes = "themes";
		public final String TutorialDescriptionText = "tutorial-description-text";
		public final String TutorialImagePath = "tutorial-image-path";
		public final String TutorialTitleText = "tutorial-title-text";
		public final String Type = "type";
		public final String Value = "value";
		public final String Weight = "weight";
	}
	private TagNames tagNames = new TagNames();
	public TagNames getTagNames(){
		return tagNames;
	}
	public final class Types{
		public final String String = "string";
		public final String Integer = "integer";
		public final String Double = "double";
		public final String Boolean = "boolean";
		public final String TagSet = "tag-set";
		public final String CreatureDescriptor = "creature-descriptor";
		public final String PlayerDescriptor = "player-descriptor";
		public final String BooleanGenerator = "boolean-generator";
		public final String IntegerGenerator = "integer-generator";
		public final String StringGenerator = "string-generator";
	}
	private Types types = new Types();
	public Types getTypes(){
		return types;
	}
	private T parent;
	public T getParent() {
		return parent;
	}
	public void putParent(T theParent) {
		parent = theParent;
		
	}
	public GameConstants(T theParent){
		putParent(theParent);
	}
}
