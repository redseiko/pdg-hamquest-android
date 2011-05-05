package com.playdeez.boardgames;

public enum Direction {
	NORTH(0,0,-1),
	EAST(1,1,0),
	SOUTH(2,0,1),
	WEST(3,-1,0);
	
	private Integer ordinal;
	public Integer getOrdinal(){
		return ordinal;
	}
	private Integer deltaX;
	private Integer getDeltaX(){
		return deltaX;
	}
	private Integer deltaY;
	private Integer getDeltaY(){
		return deltaY;
	}
	private Direction(Integer theOrdinal,Integer theDeltaX,Integer theDeltaY){
		ordinal =theOrdinal;
		deltaX = theDeltaX;
		deltaY = theDeltaY;
	}
	
	public static Direction first(){
		return NORTH;
	}
	public static Direction last(){
		return WEST;
	}
	public static Integer count(){
		return Direction.values().length;
	}
	private static Direction lookUpByOrdinal(Integer theOrdinal){
		for(Direction direction:Direction.values()){
			if(direction.getOrdinal()==theOrdinal){
				return direction;
			}
		}
		return null;
	}
	public static Direction opposite(Direction theDirection){
		Integer oppositeOrdinal = (theDirection.getOrdinal() + count()/2)%count();
		return lookUpByOrdinal(oppositeOrdinal);
	}
	public static Direction next(Direction theDirection){
		Integer nextOrdinal = theDirection.getOrdinal()+1;
		return lookUpByOrdinal(nextOrdinal);
	}
	public static Direction previous(Direction theDirection){
		Integer previousOrdinal = theDirection.getOrdinal()+count()-1;
		return lookUpByOrdinal(previousOrdinal);
	}
	public static Integer getNextColumn(Integer startColumn, Integer startRow,Direction theDirection){
		return startColumn+theDirection.getDeltaX();
	}
	public static Integer getNextRow(Integer startColumn,Integer startRow, Direction theDirection){
		return startRow+theDirection.getDeltaY();
	}
}
