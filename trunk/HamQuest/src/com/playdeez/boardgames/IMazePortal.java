package com.playdeez.boardgames;

public interface IMazePortal<T> {
	public boolean getOpen();
	public void putOpen(boolean theOpen);
	public void clear();
	public T duplicate();
}
