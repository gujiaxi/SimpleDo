package com.github.gujiaxi;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AddActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        
        Button btn_ok = (Button)findViewById(R.id.btn_ok);
        OnClickListener ocl_ok = new OnClickListener() {
        	public void onClick(View v) {
        		//TODO
        		finish();
        	}
        };
        btn_ok.setOnClickListener(ocl_ok);
        
        Button btn_cl = (Button)findViewById(R.id.btn_cl);
        OnClickListener ocl_cl = new OnClickListener() {
        	public void onClick(View v) {
        		finish();
        	}
        };
        btn_cl.setOnClickListener(ocl_cl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add, menu);
        return true;
    }
}
