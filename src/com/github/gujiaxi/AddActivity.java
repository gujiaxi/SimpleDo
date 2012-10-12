package com.github.gujiaxi;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;

public class AddActivity extends Activity {

	private int mYear;
	private int mMonth;
	private int mDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	final Calendar c = Calendar.getInstance();
    	
    	mYear = c.get(Calendar.YEAR);
    	mMonth = c.get(Calendar.MONDAY);
    	mDay = c.get(Calendar.DAY_OF_MONTH);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        
        final DatePicker dp = (DatePicker) this.findViewById(R.id.NoteDate);
        dp.init(mYear, mMonth, mDay, null);
        
        Button btn_ok = (Button)findViewById(R.id.btn_ok);
        OnClickListener ocl_ok = new OnClickListener() {
        	public void onClick(View v) {
        		AutoCompleteTextView nc = (AutoCompleteTextView) findViewById(R.id.NoteContent);
        		
        		Bundle bundle = new Bundle();
        		bundle.putString("note", nc.getText()+" on "+dp.getMonth()+"/"+dp.getDayOfMonth());
        		Intent mIntent = new Intent();
        		mIntent.putExtras(bundle);
        		setResult(RESULT_OK, mIntent);
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
