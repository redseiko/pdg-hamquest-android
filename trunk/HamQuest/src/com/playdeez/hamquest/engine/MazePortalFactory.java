package com.playdeez.hamquest.engine;

import com.playdeez.boardgames.MazePortalBase;
import com.playdeez.boardgames.MazePortalFactoryBase;

public class MazePortalFactory extends MazePortalFactoryBase {
	public MazePortalBase createMazePortal(){
		return new MazePortal();
	}
}
