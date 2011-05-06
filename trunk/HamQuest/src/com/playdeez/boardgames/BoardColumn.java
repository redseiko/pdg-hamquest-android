package com.playdeez.boardgames;

import java.util.ArrayList;
import java.util.List;

public class BoardColumn<T extends IBoardCellContents<T>> {
	private Integer column;
	private List<BoardCell<T>> cells;
	public Integer getColumn(){
		return column;
	}
	public void putColumn(Integer theColumn){
		column = theColumn;
		for(BoardCell<T> cell:cells){
			cell.putColumn(theColumn);
		}
	}
	public int getRows(){
		return cells.size();
	}
	public BoardCell<T> getRow(Integer theRow){
		return cells.get(theRow);
	}
	public BoardColumn(int theColumn, int theRows){
		column = theColumn;
		cells = new ArrayList<BoardCell<T>>();
		while(cells.size()<theRows){
			cells.add(new BoardCell<T>(column,cells.size()));
		}
	}
	public void clear(){
		for(BoardCell<T> cell:cells){
			if(cell!=null){
				cell.clear();
			}
		}
	}
}
