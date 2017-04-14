package com.artimed.shoppinglist;

import java.util.ArrayList;
import java.util.HashMap;



import com.artimed.shoppinglist.DatabaseTools;
import com.artimed.shoppinglist.NewItem;
import com.artimed.shoppinglist.R;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MainActivity extends ListActivity {
	Intent intent;
	TextView itemId;
	ShoppingListAdapter adapter;
	
	String listId;
	String listName;

	//ImageButton imageButton;
	//////private ShoppingListAdapter adapter;//


	DatabaseTools dbTools = new DatabaseTools(this);





	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		
		setContentView(R.layout.activity_items_main);

listId=getIntent().getExtras().getString("listId");
listName=getIntent().getExtras().getString("listName");

TextView itemsListNameTextView = (TextView) findViewById(R.id.listsTitleTextView);
itemsListNameTextView.setText(listName);

TextView listNameTextViews = (TextView) findViewById(R.id.listNameTextViews);
listNameTextViews.setText(listName);


		ArrayList<HashMap<String, String>> itemList =  dbTools.getAllItems(listId);
		


		if(itemList.size()!=0) {
						

			ListView listView = getListView();
			
			

		       
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					
					itemId = (TextView) view.findViewById(R.id.itemId);
										
					String itemIdValue = itemId.getText().toString();	

					
					Intent  theIntent = new Intent(getApplication(),EditItem.class);
										
					theIntent.putExtra("itemId", itemIdValue); 
					
					Toast.makeText(getApplicationContext(), "ddddddddd ", Toast.LENGTH_LONG).show();

					startActivity(theIntent); 
				}
			}); 
			
			
			
			//adapter = new ShoppingListAdapter( MainActivity.this,R.layout.item_entry,itemList);

			
			//ListAdapter adapter = new SimpleAdapter( MainActivity.this,itemList, R.layout.item_entry, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});

			///////////ffffShoppingListAdapter adapter = new ShoppingListAdapter( MainActivity.this,itemList, R.layout.item_entry, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});
			adapter = new ShoppingListAdapter( MainActivity.this,R.layout.item_entry, itemList);//, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});
//adapter.setNotifyOnChange(true);
//adapter.notifyDataSetChanged();
			setListAdapter(adapter);
			
			
			//adapter.notifyDataSetChanged();
			
			
			///////
			//imageButton = (ImageButton) findViewById(R.layout.item_entry).findViewById(R.id.imageButton1);

			////////
			


			
		}
	}

	



	public void showAddItem(View view) {
		Intent theIntent = new Intent(getApplicationContext(), NewItem.class);
		
		theIntent.putExtra("listId", listId); 
		theIntent.putExtra("listName", listName); 
		startActivity(theIntent);
	}
	


	
	public void deleteAllItems(View view) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				MainActivity.this);
	
			alertDialogBuilder.setTitle("Delete All Items");
	
			alertDialogBuilder
				.setMessage("Are you sure you want to delete all items?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
				    	dbTools.deleteAllItemsInList(listId);
				    	
				    	
				    	

						
						
						Intent  theIntent = new Intent(getApplicationContext(), MainActivity.class);
						
						
						theIntent.putExtra("listId", listId);
						theIntent.putExtra("listName", listName);
						
						overridePendingTransition(0, 0);
						finish();
						overridePendingTransition(0, 0);
						
						startActivity(theIntent); 
						
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});
			AlertDialog alertDialog = alertDialogBuilder.create();
	
			alertDialog.show();
	}	
	
	
	public void goBack(View view) {
		finish();
	}
	
	
	public void editItemButtonClick(View view){

				
				itemId = (TextView) findViewById(R.id.itemId);
									
				String itemIdValue = itemId.getText().toString();	

				
				Intent  theIntent = new Intent(getApplication(),EditItem.class);
									
				theIntent.putExtra("itemId", itemIdValue); 
				
				theIntent.putExtra("listId", listId); 
				theIntent.putExtra("listName", listName); 

				
				
				startActivity(theIntent); 
	

		
		Toast.makeText(getApplicationContext(), "editItemButtonClick "+itemIdValue, Toast.LENGTH_LONG).show();
		
	}
	
	public void removeItemButtonClick(View view){
		ImageButton imageButton=(ImageButton) view.getTag();


		Toast.makeText(getApplicationContext(), "removeItemButtonClick ", Toast.LENGTH_LONG).show();

}
	

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		///////listId=getIntent().getExtras().getString("listId");

		ArrayList<HashMap<String, String>> itemList =  dbTools.getAllItems(listId);
		


		if(itemList.size()!=0) {
						

			ListView listView = getListView();
			
			

			
			//adapter = new ShoppingListAdapter( MainActivity.this,R.layout.item_entry,itemList);

			
			//ListAdapter adapter = new SimpleAdapter( MainActivity.this,itemList, R.layout.item_entry, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});

			///////////ffffShoppingListAdapter adapter = new ShoppingListAdapter( MainActivity.this,itemList, R.layout.item_entry, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});
			adapter = new ShoppingListAdapter( MainActivity.this,R.layout.item_entry, itemList);//, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});

adapter.notifyDataSetChanged();
			setListAdapter(adapter);
			
			
			adapter.notifyDataSetChanged();
			
			
			///////
			//imageButton = (ImageButton) findViewById(R.layout.item_entry).findViewById(R.id.imageButton1);

			////////
		}
	}


	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);

        return super.onCreateOptionsMenu(menu);
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
//	    case R.id.add_item:
//			Intent theIntent = new Intent(getApplicationContext(), NewItem.class);
//			
//			theIntent.putExtra("listId", listId); 
//			theIntent.putExtra("listName", listName); 
//			startActivity(theIntent);
//			return true;
//	    case R.id.go_back:
//	    	finish();
//	    	return true;
	    case R.id.delete_all_items:
	    	
	    	
	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    			MainActivity.this);

	    		alertDialogBuilder.setTitle("Delete All Items");

	    		alertDialogBuilder
	    			.setMessage("Are you sure you want to delete all items?")
	    			.setCancelable(false)
	    			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog,int id) {
	    			    	dbTools.deleteAllItemsInList(listId);
	    			    	
	    					Intent  theIntent = new Intent(getApplicationContext(), MainActivity.class);
	    					theIntent.putExtra("listId", listId);
	    					theIntent.putExtra("listName", listName);
	    					
							overridePendingTransition(0, 0);
							finish();
							overridePendingTransition(0, 0);
							
	    					startActivity(theIntent); 
	    					
	    				}
	    			  })
	    			.setNegativeButton("No",new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog,int id) {
	    					dialog.cancel();
	    				}
	    			});
	    		AlertDialog alertDialog = alertDialogBuilder.create();

	    		alertDialog.show();
	    		

			return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	

	public void showItemsMoreMenu(View view) {
		

		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentApiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
		    PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
		    
		    
		    MenuInflater inflater = popupMenu.getMenuInflater();
		    inflater.inflate(R.menu.menu_items, popupMenu.getMenu());
		    
		    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
		    	   
				@Override
		    	   public boolean onMenuItemClick(MenuItem item) {
		    	    Toast.makeText(MainActivity.this,
		    	      item.toString(),
		    	      Toast.LENGTH_LONG).show();
		    	    
		    	    ////////////////sssssssssss
		    	    onOptionsItemSelected(item);
		    	    ////////////////eeeeeeee
		    	    return true;
		    	   }
		    	  });
		    
		    
		    popupMenu.show();
		} else{
			this.openOptionsMenu();
		}
		
		
	}
	
}
