package com.playdeez.hamquest;

import java.io.InputStream;

import org.w3c.dom.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Game extends Activity {
	Document document;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d("HQ", "1");
        InputStream reader = getResources().openRawResource(R.xml.config_propertygroups);
        Log.d("HQ", "2");
    }
}