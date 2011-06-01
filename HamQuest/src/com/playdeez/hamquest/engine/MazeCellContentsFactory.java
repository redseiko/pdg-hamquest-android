package com.playdeez.hamquest.engine;

import com.playdeez.boardgames.BoardCellContentsBase;
import com.playdeez.boardgames.BoardCellContentsFactoryBase;

public class MazeCellContentsFactory extends BoardCellContentsFactoryBase {
	public BoardCellContentsBase createBoardCellContents(){
		return new MazeCellContents();
	}
}
