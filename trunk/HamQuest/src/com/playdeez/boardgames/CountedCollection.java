package com.playdeez.boardgames;
import java.util.*;

public class CountedCollection<T extends Comparable<T>> {
	private HashMap<T,Integer> counts = new HashMap<T,Integer>();
	public Set<T> getIdentifiers()
	{
		return counts.keySet();
	}
	public T getMinimalValue()
	{
		T result = null;
		for(T theIdentifier:getIdentifiers())
		{
			if(result==null || result.compareTo(theIdentifier)>0)
			{
				result = theIdentifier;
			}
		}
		return result;
	}
	public T getMaximalValue()
	{
		T result = null;
		for(T theIdentifier:getIdentifiers())
		{
			if(result==null || result.compareTo(theIdentifier)>0)
			{
				result = theIdentifier;
			}
		}
		return result;
	}
	public void add(T theIdentifier, Integer count)
	{
		if(counts.containsKey(theIdentifier))
		{
			counts.put(theIdentifier, count+counts.get(theIdentifier));
		}
		else
		{
			counts.put(theIdentifier, count);
		}
	}
	public void add(T theIdentifier)
	{
		add(theIdentifier,1);
	}
	public boolean has(T theIdentifier)
	{
		return counts.containsKey(theIdentifier) && counts.get(theIdentifier)>0;
	}
	public Integer get(T theIdentifier)
	{
		if(has(theIdentifier))
		{
			return counts.get(theIdentifier);
		}
		else
		{
			return 0;
		}
	}
	public void put(T theIdentifier, Integer theCount)
	{
		counts.put(theIdentifier,theCount);
	}
	public Integer Remove(T theIdentifier, Integer theCount)
	{
		if(counts.containsKey(theIdentifier))
		{
			if(theCount>counts.get(theIdentifier))
			{
				theCount = counts.get(theIdentifier);
				counts.remove(theIdentifier);
				return theCount;
			}
			else
			{
				counts.put(theIdentifier, counts.get(theIdentifier)-theCount);
				return theCount;
			}
		}
		else
		{
			return 0;
		}
	}
	public boolean Remove(T theIdentifier)
	{
		return Remove(theIdentifier,1)>0;
	}
}
