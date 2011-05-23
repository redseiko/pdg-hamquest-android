package com.playdeez.hamquest.engine;

import com.playdeez.boardgames.INestedObject;

public class GameChild implements INestedObject<Game>{
	private Game parent;
	public Game getParent(){
		return parent;
	}
	public void putParent(Game theParent){
		parent = theParent;
	}
	public GameChild(Game theParent){
		putParent(theParent);
	}
}
