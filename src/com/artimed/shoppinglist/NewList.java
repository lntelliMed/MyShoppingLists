package com.artimed.shoppinglist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.artimed.shoppinglist.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class NewList extends Activity{

	TextView listId;
	EditText listName;


	DatabaseTools dbTools = new DatabaseTools(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.add_new_list);
		
		listName = (EditText) findViewById(R.id.listName);


	}
	public void addNewList(View view) {
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();

		
		HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();
		
		queryValuesMap.put("listName", listName.getText().toString());
		queryValuesMap.put("listDate", dateFormat.format(date));



		
		dbTools.insertList(queryValuesMap);
		this.callMainListActivity(view);
	}
	
	public void cancelListAdd(View view){
		this.callMainListActivity(view);
		
	}
	public void callMainListActivity(View view) {
//		Intent theIntent = new Intent(getApplicationContext(), MainActivity.class);
//		startActivity(theIntent);
		////finish();
		
		Intent main = new Intent(NewList.this, MainListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
startActivity(main);
	}	
	


	
}
