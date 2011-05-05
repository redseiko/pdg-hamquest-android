package com.playdeez.boardgames;

public class BoardCell<T extends IBoardCellContents> {
	private Integer column;
	private Integer row;
	private T contents;
	public Integer getColumn(){
		return column;
	}
	public Integer getRow(){
		return row;
	}
	public void putColumn(Integer theColumn){
		column = theColumn;
	}
	public void putRow(Integer theRow){
		row = theRow;
	}
	public void clear(){
		getContents().clear();
	}
	public T getContents(){
		return contents;
	}
	public void putContents(T theContents){
		contents = theContents;
	}
	private void initializeBoardCell(Integer theColumn, Integer theRow, T theContents){
		column = theColumn;
		row = theRow;
		putContents(theContents);
	}
	public BoardCell(Integer theColumn, Integer theRow, T theContents){
		initializeBoardCell(theColumn,theRow,theContents);
	}
	public BoardCell(Integer theColumn, Integer theRow){
		initializeBoardCell(theColumn,theRow,null);
	}
}
