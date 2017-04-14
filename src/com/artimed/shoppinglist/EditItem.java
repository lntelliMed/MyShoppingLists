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

public class EditItem extends Activity{
	
	EditText itemName;
	EditText itemAmount;

	String listId;
	String listName;
	
	DatabaseTools dbTools = new DatabaseTools(this);
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_item);
		itemName = (EditText) findViewById(R.id.itemName);
		itemAmount = (EditText) findViewById(R.id.itemAmount);

		listId=getIntent().getExtras().getString("listId");
		listName=getIntent().getExtras().getString("listName");
		
		Intent theIntent = getIntent();
		
		String itemId = theIntent.getStringExtra("itemId");
		
		HashMap<String, String> itemList = dbTools.getItemInfo(itemId, listId);
		
		if(itemList.size() != 0){
			
			itemName.setText(itemList.get("itemName"));
			itemAmount.setText(itemList.get("itemAmount"));

			
		}
	}
	
	public void saveItemChanges(View view){
		
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		itemName = (EditText) findViewById(R.id.itemName);
		itemAmount = (EditText) findViewById(R.id.itemAmount);

		
		Intent theIntent = getIntent();
		
		String itemId = theIntent.getStringExtra("itemId");
		
		queryValuesMap.put("itemId", itemId);
		queryValuesMap.put("itemName", itemName.getText().toString());
		queryValuesMap.put("itemAmount", itemAmount.getText().toString());
		
		queryValuesMap.put("listId", listId);
		queryValuesMap.put("listName", listName);

		///////queryValuesMap.put("itemBought", itemAmount.getText().toString());////////xxxxxxxxxxxxx

		dbTools.updateItem(queryValuesMap);
		this.callMainActivity(view);
		
	}
	
	
	public void cancelItemChanges(View view){
		this.callMainActivity(view);
		
	}
	
	public void removeItem(View view){
		
		Intent theIntent = getIntent();
		
		String itemId = theIntent.getStringExtra("itemId");
		
		dbTools.deleteItem(itemId, listId);
		
		this.callMainActivity(view);
		
	}
	
	
	public void removeItem(View view, String itemIdToDelete){///sonradan ekledim
		
		
		dbTools.deleteItem(itemIdToDelete, listId);
		finish();
	}
	
	public void callMainActivity(View view){
		
//		Intent objIntent = new Intent(getApplication(), MainActivity.class);
//		
//		startActivity(objIntent);
		finish();
		
	}
	

	

}






