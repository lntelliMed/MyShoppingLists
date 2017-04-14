package com.artimed.shoppinglist;

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


public class NewItem extends Activity{

	TextView itemId;
	EditText itemName;
	EditText itemAmount;

	String listId;
	String listName;

	DatabaseTools dbTools = new DatabaseTools(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.add_new_item);
		
		itemName = (EditText) findViewById(R.id.itemName);
		itemAmount = (EditText) findViewById(R.id.itemAmount);

		listId=getIntent().getExtras().getString("listId");
		listName=getIntent().getExtras().getString("listName");

	}
	public void addNewItem(View view) {
				
		HashMap<String, String> queryValuesMap =  new  HashMap<String, String>();
		
		queryValuesMap.put("itemName", itemName.getText().toString());
		queryValuesMap.put("itemAmount", itemAmount.getText().toString());
		queryValuesMap.put("itemBought", itemAmount.getText().toString());///////////xxxxxxxxxxx
		
		queryValuesMap.put("listId", listId);
		queryValuesMap.put("listName", listName);


		
		dbTools.insertItem(queryValuesMap);
		this.callMainActivity(view);
	}
	
	public void cancelItemAdd(View view){
		this.callMainActivity(view);
		
	}
	public void callMainActivity(View view) {
//		Intent theIntent = new Intent(getApplicationContext(), MainActivity.class);
//		startActivity(theIntent);
		finish();
	}	
	


	
}
