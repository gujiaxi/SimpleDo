package com.github.gujiaxi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_add = (Button)findViewById(R.id.btn_add);
        OnClickListener ocl_AddActivity = new OnClickListener() {
        	public void onClick(View v) {
        		Intent intent1 = new Intent(MainActivity.this, AddActivity.class);
        		startActivity(intent1);
        	}
        };
        btn_add.setOnClickListener(ocl_AddActivity);
        Button btn_del = (Button)findViewById(R.id.btn_del);
        OnClickListener ocl_DelActivity = new OnClickListener() {
        	public void onClick(View v) {
        		//TODO
        	}
        };
        btn_del.setOnClickListener(ocl_DelActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuItem itm_about = menu.add(0,1,1,"About");
    	itm_about.setIntent(new Intent(this,AboutActivity.class));
    	return super.onCreateOptionsMenu(menu);
    }
}
