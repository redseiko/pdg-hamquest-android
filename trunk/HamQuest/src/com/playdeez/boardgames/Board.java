package com.playdeez.boardgames;

import java.util.ArrayList;
import java.util.List;

public class Board<T extends IBoardCellContents> {
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
	public Board(int theColumns, int theRows){
		columns = new ArrayList<BoardColumn<T>>();
		while(columns.size()<theColumns){
			columns.add(new BoardColumn<T>(columns.size(),theRows));
		}
		clear();
	}
	public void clear(){
		for(BoardColumn<T> column:columns){
			column.clear();
		}
	}
}