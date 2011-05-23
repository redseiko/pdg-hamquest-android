package com.playdeez.boardgames;

public interface INestedObject<T> {
	T getParent();
	void putParent(T theParent);
}
