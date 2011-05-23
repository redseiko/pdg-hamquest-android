package com.playdeez.boardgames;

public class BoardCell<T> {
	private BoardColumn<T> parent = null;
	public BoardColumn<T> getParent(){
		return parent;
	}
	public void putParent(BoardColumn<T> theParent){
		parent = theParent;
	}
	private Integer column;
	private Integer row;
	private BoardCellContentsBase contents;
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
	public BoardCellContentsBase getContents(){
		return contents;
	}
	public void putContents(BoardCellContentsBase theContents){
		contents = theContents;
	}
	private void initializeBoardCell(BoardColumn<T> theParent,Integer theColumn, Integer theRow, BoardCellContentsBase theContents){
		putParent(theParent);
		column = theColumn;
		row = theRow;
		putContents(theContents);
	}
	public BoardCell(BoardColumn<T> theParent,Integer theColumn, Integer theRow, BoardCellContentsFactoryBase theFactory){
		initializeBoardCell(theParent,theColumn,theRow,theFactory.createBoardCellContents());
	}
}
