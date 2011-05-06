package com.playdeez.boardgames;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MazeCell<S extends IDirection<S>, T extends IMazePortal<T>,U extends IBoardCellContents<U>> implements IBoardCellContents<MazeCell<S,T,U>> {
	private Map<S,T> portals;
	private Map<S,MazeCell<S,T,U>> neighbors;
	private U contents;
	private BoardCell<MazeCell<S,T,U>> boardCell;
	public BoardCell<MazeCell<S,T,U>> getBoardCell(){
		return boardCell;
	}
	public MazeCell<S,T,U> getNeighbor(S theDirection){
		if(!hasNeighbor(theDirection)) return null;
		return neighbors.get(theDirection);
	}
	public void putNeighbor(S theDirection, MazeCell<S,T,U> theNeighbor){
		neighbors.put(theDirection,theNeighbor);
	}
	public boolean hasNeighbor(S theDirection){
		return(neighbors.containsKey(theDirection));
	}
	public Set<S> getNeighborDirections(){
		return neighbors.keySet();
	}
	public T getPortal(S theDirection){
		if(!hasPortal(theDirection)) return null;
		return portals.get(theDirection);
	}
	public boolean hasPortal(S theDirection){
		return portals.containsKey(theDirection);
	}
	public Set<S> getPortalDirections(){
		return portals.keySet();
	}
	public void putPortal(S theDirection, T thePortal){
		portals.put(theDirection, thePortal);
	}
	public U getContents(){
		return contents;
	}
	public void putContents(U theContents){
		contents = theContents;
	}
	public void clear(){
		if(getContents()!=null){
			getContents().clear();
		}
	}
	public MazeCell(BoardCell<MazeCell<S,T,U>> theBoardCell){
		boardCell = theBoardCell;
		portals = new HashMap<S,T>();
		neighbors = new HashMap<S,MazeCell<S,T,U>>();
	}
	public MazeCell<S,T,U> duplicate(){
		return null;
	}
}
