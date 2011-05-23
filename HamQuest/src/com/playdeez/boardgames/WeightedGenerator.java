package com.playdeez.boardgames;
import java.util.*;

public class WeightedGenerator<T extends Comparable<T>> {
	private Map<T,Integer> generationTable = new HashMap<T,Integer>();
	private Integer weightTotal = 0;
	public T getMinimalValue(){
		T result = null;
		for(T theIdentifier:generationTable.keySet())
		{
			if(result==null || result.compareTo(theIdentifier)>0)
			{
				result = theIdentifier;
			}
		}
		return result;
	}
	public T getMaximalValue(){
		T result = null;
		for(T theIdentifier:generationTable.keySet())
		{
			if(result==null || result.compareTo(theIdentifier)>0)
			{
				result = theIdentifier;
			}
		}
		return result;
	}
	public void clear(){
		generationTable.clear();
	}
	public void setWeight(T theValue,Integer theWeight)
	{
		if(generationTable.containsKey(theValue))
		{
			weightTotal-=generationTable.get(theValue);
		}
		generationTable.put(theValue,theWeight);
		weightTotal+=generationTable.get(theValue);
	}
	public Integer getWeight(T theValue){
		if(generationTable.containsKey(theValue))
		{
			return generationTable.get(theValue);
		}
		else
		{
			return 0;
		}
	}
	public T generate(){
		if(weightTotal>0)
		{
			Integer randomValue = RandomNumberGenerator.Next(weightTotal);
			for(T theValue: generationTable.keySet())
			{
				if(randomValue<generationTable.get(theValue))
				{
					return theValue;
				}
				else
				{
					randomValue -= generationTable.get(theValue);
				}
			}
		}
		return null;
	}
	public WeightedGenerator(){
	}
}
