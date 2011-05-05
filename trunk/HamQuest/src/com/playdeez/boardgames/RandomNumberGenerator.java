package com.playdeez.boardgames;
import java.util.*;

public class RandomNumberGenerator {
	private static Random random = new Random();
	public static Integer Next(Integer maximum)
	{
		return random.nextInt(maximum);
	}
	public static Integer Next(Integer minimum, Integer maximum)
	{
		return minimum+Next(maximum-minimum);
	}
}
