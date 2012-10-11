package com.github.gujiaxi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button btn_ok = (Button) findViewById(R.id.btn_ok);
        OnClickListener ocl_AboutActivity = new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        	}
        };
        btn_ok.setOnClickListener(ocl_AboutActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_about, menu);
        return true;
    }
}
