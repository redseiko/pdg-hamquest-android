package com.playdeez.boardgames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeBase<S extends IDirection<S>,  T extends IMazePortal<T>,U extends IBoardCellContents<U>> extends Board<MazeCell<S,T,U>>  {
	private S[] directions;
	private List<T> portals;
	public S[] getDirections(){
		return directions;
	}
	public List<T> getPortals(){
		return portals;
	}
	public MazeBase(Integer theColumns, Integer theRows,S[] theDirections,T thePortalTemplate, U theCellContentsTemplate){
		super(theColumns,theRows);//initialize board base class
		directions = theDirections;//store list of possible directions
		portals = new ArrayList<T>();
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
							portals.add(thePortal);
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
	public void clear(){
		super.clear();
		for(T portal:portals){
			portal.clear();
		}
	}
	protected void onPostGenerate(){
	}
	private enum GeneratorState{
		OUTSIDE,
		FRONTIER,
		INSIDE
	}
	public void generate(){
		Map<MazeCell<S,T,U>,GeneratorState> generatorStates = new HashMap<MazeCell<S,T,U>,GeneratorState>();
		List<MazeCell<S,T,U>> frontierCells = new ArrayList<MazeCell<S,T,U>>();
		MazeCell<S,T,U> cell;
		MazeCell<S,T,U> neighborCell;
		Integer column;
		Integer row;

		clear();
		for(column=0;column<getColumns();column++){
			for(row=0;row<getRows();++row){
				generatorStates.put(getColumn(column).getRow(row).getContents(),GeneratorState.OUTSIDE);
			}
		}
		cell = getColumn(RandomNumberGenerator.Next(getColumns())).getRow(RandomNumberGenerator.Next(getRows())).getContents();
		generatorStates.put(cell,GeneratorState.INSIDE);
		for(S direction:getDirections()){
			neighborCell = cell.getNeighbor(direction);
			if(neighborCell!=null){
				frontierCells.add(neighborCell);
				generatorStates.put(neighborCell, GeneratorState.FRONTIER);
			}
		}
		while(frontierCells.size()>0){
			cell = frontierCells.get(RandomNumberGenerator.Next(frontierCells.size()));
			frontierCells.remove(cell);
			generatorStates.put(cell, GeneratorState.INSIDE);
			do{
				S direction = getDirections()[RandomNumberGenerator.Next(getDirections().length)];
				neighborCell = cell.getNeighbor(direction);
				if(neighborCell!=null && generatorStates.get(neighborCell)==GeneratorState.INSIDE){
					cell.getPortal(direction).putOpen(true);
				}
			}while(neighborCell==null || generatorStates.get(neighborCell)!=GeneratorState.INSIDE);
			for(S direction:getDirections()){
				neighborCell = cell.getNeighbor(direction);
				if(neighborCell!=null && generatorStates.get(neighborCell)==GeneratorState.OUTSIDE){
					frontierCells.add(neighborCell);
					generatorStates.put(neighborCell, GeneratorState.FRONTIER);
				}
			}
		}
		onPostGenerate();
	}
}
