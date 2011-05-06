package com.playdeez.boardgames;

public interface IDirection<T> {
	public boolean isFirst();
	public boolean isLast();
	public T opposite();
	public T next();
	public T previous();
	public Integer getNextColumn(Integer startColumn,Integer startRow);
	public Integer getNextRow(Integer startColumn,Integer startRow);
}
