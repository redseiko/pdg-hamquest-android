package com.playdeez.boardgames;

public class Maze<S extends IDirection<S>,  T extends IMazePortal<T>,U extends IBoardCellContents<U>> extends Board<MazeCell<S,T,U>>  {
	private S[] directions;
	public S[] getDirections(){
		return directions;
	}
	public Maze(Integer theColumns, Integer theRows,S[] theDirections,T thePortalTemplate, U theCellContentsTemplate){
		super(theColumns,theRows);//initialize board base class
		directions = theDirections;//store list of possible directions
		for(Integer theColumn=0;theColumn<theColumns;++theColumn){//loop through columns
			for(Integer theRow=0;theRow<theRows;++theRow){//loop through rows
				BoardCell<MazeCell<S,T,U>> cell = this.getColumn(theColumn).getRow(theRow);//grab out the board cell
				MazeCell<S,T,U> mazeCell = new MazeCell<S,T,U>(cell);//create a new maze cell
				mazeCell.putContents(theCellContentsTemplate.duplicate());//put new contents in the maze cell
				cell.putContents(mazeCell);//put maze cell into board cell
				for(S direction : getDirections()){//loop through directions
					Integer theNextColumn = direction.getNextColumn(theColumn, theRow);//move to adjacent column
					Integer theNextRow = direction.getNextRow(theColumn, theRow);//move to adjacent row
					BoardCell<MazeCell<S,T,U>> theNextCell = this.getColumn(theNextColumn).getRow(theNextRow);//get the next board cell
					if(theNextCell!=null){//check for null
						MazeCell<S,T,U> theNextMazeCell = theNextCell.getContents();//grab the next maze cell
						if(theNextMazeCell!=null){//check for null
							T thePortal = thePortalTemplate.duplicate();//duplicate portal template
							mazeCell.putPortal(direction, thePortal);//set portal leaving current maze cell
							mazeCell.putNeighbor(direction, theNextMazeCell);//set next maze cell as neighbor
							theNextMazeCell.putPortal(direction.opposite(), thePortal);//set portal entering next maze cell
							theNextMazeCell.putNeighbor(direction.opposite(),mazeCell);//set next maze cell's neighbor
						}
					}
				}	
			}
		}
	}
}
