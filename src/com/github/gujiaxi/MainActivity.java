package com.github.gujiaxi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
	
	private ListView myListView;
	private NoteDatabase myDatabase;
	private Cursor myCursor;
	private SimpleCursorAdapter myAdapter;
	private int _id;
	
	static final int REQUEST_CODE = 1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myListView = (ListView) findViewById(R.id.myListView);
        myDatabase = new NoteDatabase(this);
        myCursor = myDatabase.select();
        
        myAdapter = new SimpleCursorAdapter(
        	this,
        	R.layout.list_view,
        	myCursor,
        	new String[] { NoteDatabase.FIELD_TEXT },
        	new int[] { R.id.cb }
        );
       
        myListView.setAdapter(myAdapter);
        
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        		myCursor.moveToPosition(arg2);
        		_id = myCursor.getInt(0);
        	}
        });
        
    	myListView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
    		public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
            	menu.setHeaderTitle("Menu");
                menu.add(0, 1, 1, "Delete");
                menu.add(0, 2, 2, "Cancel");
            }
        });
        
        Button btn_add = (Button)findViewById(R.id.btn_add);
        OnClickListener ocl_AddActivity = new OnClickListener() {
        	public void onClick(View v) {
        		Intent intent1 = new Intent(MainActivity.this, AddActivity.class);
        		startActivityForResult(intent1, REQUEST_CODE);
        	}
        };
        btn_add.setOnClickListener(ocl_AddActivity);
    }
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case 1:
				delItem();
				break;
			case 2:
				break;
		}
	    return super.onContextItemSelected(item);
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == REQUEST_CODE) {
    		if (resultCode == RESULT_OK) {
    			Bundle bundle = data.getExtras();
    			String info = bundle.getString("note");
    			addItem(info);
    		}
    	}
    }
    
    private void addItem(String s) {
    	myDatabase.insert(s);
    	myCursor.requery();
    	myListView.invalidateViews();
    	_id = 0;
    }
    
    private void editItem(String s) {
    	myDatabase.update(_id, s);
    	myCursor.requery();
    	myListView.invalidateViews();
    	_id = 0;
    }
    
    private void delItem() {
    	myDatabase.delete(_id);
    	myCursor.requery();
    	myListView.invalidateViews();
    	_id = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuItem itm_about = menu.add(0,1,1,"About");
    	itm_about.setIntent(new Intent(this,AboutActivity.class));
    	return super.onCreateOptionsMenu(menu);
    }
}
