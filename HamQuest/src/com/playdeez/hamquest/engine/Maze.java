package com.playdeez.hamquest.engine;

import com.playdeez.boardgames.IDirection;
import com.playdeez.boardgames.MazeBase;

public class Maze extends MazeBase<Game> {
	public Maze(Game theParent){
		super(theParent,
				theParent.getGameConstants().getMaze().Width,
				theParent.getGameConstants().getMaze().Height,
				new IDirection[]{Direction.NORTH,Direction.EAST,Direction.SOUTH,Direction.WEST},
				new MazePortalFactory(),
				new MazeCellContentsFactory()
				);
	}
}
