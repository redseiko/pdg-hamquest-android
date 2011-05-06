package com.playdeez.hamquest.client;

import org.w3c.dom.*;

import com.playdeez.hamquest.*;
import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity {
	Document document;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);   
    }
}