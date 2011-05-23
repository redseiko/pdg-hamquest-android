package com.playdeez.boardgames;
import java.util.*;

public class RandomNumberGenerator {
	private static List<Random> stack = new ArrayList<Random>();
	public static void pushRandom(Random theRandom){
		stack.add(theRandom);
	}
	public static Random popRandom(){
		if(stack.size()==0){
			return null;
		}else{
			Random result = stack.get(stack.size()-1);
			stack.remove(stack.size()-1);
			return result;
		}
	}
	public static Integer Next(Integer maximum)
	{
		if(stack.size()==0){
			pushRandom(new Random());
		}
		return stack.get(stack.size()-1).nextInt(maximum);
	}
	public static Integer Next(Integer minimum, Integer maximum)
	{
		return minimum+Next(maximum-minimum);
	}
}
