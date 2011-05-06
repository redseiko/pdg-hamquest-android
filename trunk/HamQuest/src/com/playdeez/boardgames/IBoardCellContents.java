package com.playdeez.boardgames;

public interface IBoardCellContents<T> {
	public void clear();
	public T duplicate();
}
