package com.playdeez.boardgames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeBase<T> extends Board<T>  {
	private IDirection[] directions;
	private List<MazePortalBase> portals;
	public IDirection[] getDirections(){
		return directions;
	}
	public List<MazePortalBase> getPortals(){
		return portals;
	}
	public MazeBase(T theParent,Integer theColumns, Integer theRows,IDirection[] theDirections,MazePortalFactoryBase thePortalFactory, BoardCellContentsFactoryBase theContentsFactory){
		super(theParent,theColumns,theRows,theContentsFactory);//initialize board base class
		directions = theDirections;//store list of possible directions
		portals = new ArrayList<MazePortalBase>();
		for(Integer theColumn=0;theColumn<theColumns;++theColumn){//loop through columns
			for(Integer theRow=0;theRow<theRows;++theRow){//loop through rows
				BoardCell<T> cell = this.getColumn(theColumn).getRow(theRow);//grab out the board cell
				MazeCell<T> mazeCell = new MazeCell<T>(cell,theContentsFactory);//create a new maze cell
				cell.putContents(mazeCell);//put maze cell into board cell
				for(IDirection direction : getDirections()){//loop through directions
					Integer theNextColumn = direction.getNextColumn(theColumn, theRow);//move to adjacent column
					Integer theNextRow = direction.getNextRow(theColumn, theRow);//move to adjacent row
					BoardCell<T> theNextCell = this.getColumn(theNextColumn).getRow(theNextRow);//get the next board cell
					if(theNextCell!=null){//check for null
						@SuppressWarnings("unchecked")
						MazeCell<T> theNextMazeCell = (MazeCell<T>)theNextCell.getContents();//grab the next maze cell
						MazePortalBase thePortal = thePortalFactory.createMazePortal();//duplicate portal template
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
	public void clear(){
		super.clear();
		for(MazePortalBase portal:portals){
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
	@SuppressWarnings("unchecked")
	public void generate(){
		Map<MazeCell<T>,GeneratorState> generatorStates = new HashMap<MazeCell<T>,GeneratorState>();
		List<MazeCell<T>> frontierCells = new ArrayList<MazeCell<T>>();
		MazeCell<T> cell;
		MazeCell<T> neighborCell;
		Integer column;
		Integer row;

		clear();
		for(column=0;column<getColumns();column++){
			for(row=0;row<getRows();++row){
				generatorStates.put((MazeCell<T>)getColumn(column).getRow(row).getContents(),GeneratorState.OUTSIDE);
			}
		}
		cell = (MazeCell<T>)getColumn(RandomNumberGenerator.Next(getColumns())).getRow(RandomNumberGenerator.Next(getRows())).getContents();
		generatorStates.put(cell,GeneratorState.INSIDE);
		for(IDirection direction:getDirections()){
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
				IDirection direction = getDirections()[RandomNumberGenerator.Next(getDirections().length)];
				neighborCell = cell.getNeighbor(direction);
				if(neighborCell!=null && generatorStates.get(neighborCell)==GeneratorState.INSIDE){
					cell.getPortal(direction).putOpen(true);
				}
			}while(neighborCell==null || generatorStates.get(neighborCell)!=GeneratorState.INSIDE);
			for(IDirection direction:getDirections()){
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
