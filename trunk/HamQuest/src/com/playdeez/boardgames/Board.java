package com.playdeez.boardgames;

import java.util.ArrayList;
import java.util.List;

public class Board<T> implements INestedObject<T> {
	private T parent = null;
	public T getParent(){
		return parent;
	}
	public void putParent(T theParent){
		parent = theParent;
	}
	private List<BoardColumn<T>> columns;
	public int getColumns(){
		return columns.size();
	}
	public int getRows(){
		return columns.get(0).getRows();
	}
	public BoardColumn<T> getColumn(int theColumn){
		return columns.get(theColumn);
	}
	public Board(T theParent,Integer theColumns, Integer theRows,BoardCellContentsFactoryBase theFactory){
		putParent(theParent);
		columns = new ArrayList<BoardColumn<T>>();
		while(columns.size()<theColumns){
			columns.add(new BoardColumn<T>(this,columns.size(),theRows,theFactory));
		}
		clear();
	}
	public void clear(){
		for(BoardColumn<T> column:columns){
			column.clear();
		}
	}
}