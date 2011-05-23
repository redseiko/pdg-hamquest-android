package com.playdeez.hamquest.engine;

import com.playdeez.boardgames.IDirection;

public enum Direction implements IDirection {
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
	
	public boolean isFirst(){
		return first().getOrdinal()==this.getOrdinal();
	}
	public static Direction first(){
		return NORTH;
	}
	public boolean isLast(){
		return last().getOrdinal()==this.getOrdinal();
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
	public Direction opposite()	{
		return opposite(this);
	}
	public static Direction opposite(Direction theDirection){
		Integer oppositeOrdinal = (theDirection.getOrdinal() + count()/2)%count();
		return lookUpByOrdinal(oppositeOrdinal);
	}
	public Direction next(){
		return next(this);
	}
	public static Direction next(Direction theDirection){
		Integer nextOrdinal = theDirection.getOrdinal()+1;
		return lookUpByOrdinal(nextOrdinal);
	}
	public Direction previous(){
		return previous(this);
	}
	public static Direction previous(Direction theDirection){
		Integer previousOrdinal = theDirection.getOrdinal()+count()-1;
		return lookUpByOrdinal(previousOrdinal);
	}
	public Integer getNextColumn(Integer startColumn,Integer startRow){
		return getNextColumn(startColumn,startRow,this);
	}
	public static Integer getNextColumn(Integer startColumn, Integer startRow,Direction theDirection){
		return startColumn+theDirection.getDeltaX();
	}
	public static Integer getNextRow(Integer startColumn,Integer startRow, Direction theDirection){
		return startRow+theDirection.getDeltaY();
	}
	public Integer getNextRow(Integer startColumn,Integer startRow){
		return getNextRow(startColumn,startRow,this);
	}
}
