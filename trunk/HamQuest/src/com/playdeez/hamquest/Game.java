package com.playdeez.hamquest;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity {
	private com.playdeez.hamquest.engine.Game theGame;
	public com.playdeez.hamquest.engine.Game getGame(){
		return theGame;
	}
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        theGame = new com.playdeez.hamquest.engine.Game(getAssets());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
}