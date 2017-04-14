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

public class EditList extends Activity{
	
	EditText listName;
	String  listId;

	
	DatabaseTools dbTools = new DatabaseTools(this);
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_list);
		listName = (EditText) findViewById(R.id.listName);

		listId = getIntent().getExtras().getString("listId");
		
		Intent theIntent = getIntent();
		
		
		HashMap<String, String> itemList = dbTools.getListInfo(listId);
		
		if(itemList.size() != 0){
			
			listName.setText(itemList.get("listName"));

			
		}
	}
	
	public void saveItemChanges(View view){
		
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		listName = (EditText) findViewById(R.id.listName);

		
		Intent theIntent = getIntent();
		
		String itemId = theIntent.getStringExtra("listId");
		
		queryValuesMap.put("listId", listId);
		queryValuesMap.put("listName", listName.getText().toString());
		


		///////queryValuesMap.put("itemBought", itemAmount.getText().toString());////////xxxxxxxxxxxxx

		dbTools.updateList(queryValuesMap);
		dbTools.updateItems(queryValuesMap);
		this.callMainListActivity(view);
		
	}
	
	
	public void cancelItemChanges(View view){
		this.callMainListActivity(view);
		
	}
	
/*	public void removeItem(View view){
		
		Intent theIntent = getIntent();
		
		String itemId = theIntent.getStringExtra("itemId");
		
		dbTools.deleteItem(itemId, listId);
		
		this.callMainListActivity(view);
		
	}*/
	
	
/*	public void removeItem(View view, String itemIdToDelete){///sonradan ekledim
		
		
		dbTools.deleteItem(itemIdToDelete, listId);
		finish();
	}*/
	
	public void callMainListActivity(View view){
		
//		Intent objIntent = new Intent(getApplication(), MainActivity.class);
//		
//		startActivity(objIntent);
		finish();
		
	}
	

	

}






