package com.playdeez.boardgames;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MazeCell<T> extends BoardCellContentsBase {
	private BoardCell<T> parent = null;
	public Object getParent(){
		return parent;
	}
	public void putParent(BoardCell<T> theParent){
		parent = theParent;
	}
	private Map<IDirection,MazePortalBase> portals;
	private Map<IDirection,MazeCell<T>> neighbors;
	private BoardCellContentsBase contents;
	public MazeCell<T> getNeighbor(IDirection theDirection){
		if(!hasNeighbor(theDirection)) return null;
		return neighbors.get(theDirection);
	}
	public void putNeighbor(IDirection theDirection, MazeCell<T> theNeighbor){
		neighbors.put(theDirection,theNeighbor);
	}
	public boolean hasNeighbor(IDirection theDirection){
		return(neighbors.containsKey(theDirection));
	}
	public Set<IDirection> getNeighborDirections(){
		return neighbors.keySet();
	}
	public MazePortalBase getPortal(IDirection theDirection){
		if(!hasPortal(theDirection)) return null;
		return portals.get(theDirection);
	}
	public boolean hasPortal(IDirection theDirection){
		return portals.containsKey(theDirection);
	}
	public Set<IDirection> getPortalDirections(){
		return portals.keySet();
	}
	public void putPortal(IDirection theDirection, MazePortalBase thePortal){
		portals.put(theDirection, thePortal);
	}
	public BoardCellContentsBase getContents(){
		return contents;
	}
	public void putContents(BoardCellContentsBase theContents){
		contents = theContents;
	}
	public void clear(){
		if(getContents()!=null){
			getContents().clear();
		}
	}
	public MazeCell(BoardCell<T> theParent,BoardCellContentsFactoryBase theFactory){
		putParent(theParent);
		putContents(theFactory.createBoardCellContents());
		portals = new HashMap<IDirection,MazePortalBase>();
		neighbors = new HashMap<IDirection,MazeCell<T>>();
	}
}
