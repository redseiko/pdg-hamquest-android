package com.playdeez.boardgames;

public class MazePortalBase {
	private boolean open = false;
	public boolean getOpen(){
		return open;
	}
	public void putOpen(boolean theOpen){
		open = theOpen;
	}
	public void clear(){
		putOpen(false);
	}
}
