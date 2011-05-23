package com.playdeez.boardgames;

public interface IDirection {
	public boolean isFirst();
	public boolean isLast();
	public IDirection opposite();
	public IDirection next();
	public IDirection previous();
	public Integer getNextColumn(Integer startColumn,Integer startRow);
	public Integer getNextRow(Integer startColumn,Integer startRow);
}
