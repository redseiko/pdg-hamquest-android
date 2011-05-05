package com.playdeez.boardgames;

import java.util.ArrayList;
import java.util.List;

public class MazeCell<T extends IMazePortal,U extends IBoardCellContents> implements IBoardCellContents {
	private List<T> portals;
	private List<MazeCell<T,U>> neighbors;
	private U contents;
	public MazeCell<T,U> getNeighbor(Direction theDirection){
		if(!hasNeighbor(theDirection)) return null;
		return neighbors.get(theDirection.getOrdinal());
	}
	public boolean hasNeighbor(Direction theDirection){
		return (theDirection.getOrdinal()>=0&&theDirection.getOrdinal()<neighbors.size());
	}
	public T getPortal(Direction theDirection){
		if(!hasPortal(theDirection)) return null;
		return portals.get(theDirection.getOrdinal());
	}
	public boolean hasPortal(Direction theDirection){
		return(theDirection.ordinal()>=0 && theDirection.getOrdinal()<portals.size());
	}
	public U getContents(){
		return contents;
	}
	public void putContents(U theContents){
		contents = theContents;
	}
	public void clear(){
		
	}
	public MazeCell(){
		portals = new ArrayList<T>();
		while(portals.size()<Direction.count()){
			portals.add(null);
		}
		neighbors = new ArrayList<MazeCell<T,U>>();
		while(neighbors.size()<Direction.count()){
			neighbors.add(null);
		}
	}
}
