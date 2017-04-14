package com.artimed.shoppinglist;

import java.util.ArrayList;
import java.util.Arrays;
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
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;

import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MainListActivity extends Activity {
	//Intent intent;
	//TextView itemId;
	//ShoppingListAdapter adapter;

	  private String[] Countries = {"ss","aa"};//ddddddddddddddddddddddddssssssssssssssssss
	ListView listView;

	DatabaseTools dbTools = new DatabaseTools(this);





	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		
		 
		    Arrays.sort(Countries);
		    
		
		setContentView(R.layout.activity_lists_main);


		listView = (ListView) findViewById(R.id.memberList_id);


		ArrayList<HashMap<String, String>> itemList = dbTools.getAllLists();
		
		//Cursor itemList = dbTools.getAllLists();
		

		
/*		String[] from = new String[] { "_id", "listName", "listDate" };
		int[] to = new int[] { R.id.member_id, R.id.member_name, R.id.member_date };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				MainListActivity.this, R.layout.view_member_entry, itemList, from, to);
		
		adapter.notifyDataSetChanged();

		listView.setAdapter(adapter);*/
		

				if(itemList.size()!=0) {
						

			//ListView listView = getListView();
			
			

		       
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					
					TextView listId = (TextView) view.findViewById(R.id.listId);
										
					String listIdValue = listId.getText().toString();	

					TextView memberName = (TextView) view.findViewById(R.id.listName);
					
					

					
					
					String listNameValue = memberName.getText().toString();	
					
					
					Intent  theIntent = new Intent(getApplication(),MainActivity.class);
										
					theIntent.putExtra("listId", listIdValue);
					theIntent.putExtra("listName", listNameValue);
					
					//Toast.makeText(getApplicationContext(), "ddddddddd ", Toast.LENGTH_LONG).show();

					startActivity(theIntent); 
				}
			});
			
			
			
			
			
/*			listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			    @Override
			    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			        //registerForContextMenu(view);
			        openContextMenu(view);
					Toast.makeText(getApplicationContext(), "editItemButtonClick ", Toast.LENGTH_LONG).show();

			        return true;
			    }
			});*/
			
			

			
	    	registerForContextMenu(listView);
			
			
			ListAdapter adapter = new SimpleAdapter( MainListActivity.this,itemList, R.layout.view_member_entry, new String[] { "listId","listName", "listDate", "itemCount"}, new int[] {R.id.listId, R.id.listName, R.id.listDate, R.id.itemsBoughtCount});
		
			//adapter = new ShoppingListAdapter( MainListActivity.this,R.layout.item_entry, itemList);//, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});

			((BaseAdapter) adapter).notifyDataSetChanged();

			listView.setAdapter(adapter);
			

			
			//TextView itemsBought = (TextView) findViewById(R.id.duration);
			//HashMap<String, String> itemsBoughtCount = dbTools.getItemsBoughtCount(listIdValue);
			//itemsBought.setText(itemsBoughtCount.get("totalItemBought") + "/" + itemsBoughtCount.get("totalItemCount"));
			//itemsBought.setText("xx");

			
		}
	}

	

/*

	public void showAddItem(View view) {
		Intent theIntent = new Intent(getApplicationContext(), NewItem.class);
		startActivity(theIntent);
	}
	
	public void editItemButtonClick(View view){

				
				itemId = (TextView) findViewById(R.id.itemId);
									
				String itemIdValue = itemId.getText().toString();	

				
				Intent  theIntent = new Intent(getApplication(),EditItem.class);
									
				theIntent.putExtra("itemId", itemIdValue); 
				
				
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

		ArrayList<HashMap<String, String>> itemList =  dbTools.getAllItems();
		


		if(itemList.size()!=0) {
						

			ListView listView = getListView();
			
			

			
			
			adapter = new ShoppingListAdapter( MainListActivity.this,R.layout.item_entry, itemList);//, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});

adapter.notifyDataSetChanged();
			setListAdapter(adapter);
			
			
			adapter.notifyDataSetChanged();

		}
	}


	*/
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }
	
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
        ContextMenuInfo menuInfo) {
      if (v.getId()==R.id.memberList_id) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        TextView listName = (TextView) info.targetView.findViewById(R.id.listName);
        menu.setHeaderTitle(listName.getText().toString());
        String[] menuItems = getResources().getStringArray(R.array.list_context_menu);
        for (int i = 0; i<menuItems.length; i++) {
          menu.add(Menu.NONE, i, i, menuItems[i]);
        }
      }
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
      AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
      
      TextView listName = (TextView) info.targetView.findViewById(R.id.listName);
      final TextView listId = (TextView) info.targetView.findViewById(R.id.listId);
    		  
      
      
      int menuItemIndex = item.getItemId();
      String[] menuItems = getResources().getStringArray(R.array.list_context_menu);
      String menuItemName = menuItems[menuItemIndex];
      //String listItemName = Countries[info.position];

      //TextView text = (TextView)findViewById(R.id.footer);
      //text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
      
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();

		queryValuesMap.put("listName", listName.getText().toString());
		queryValuesMap.put("listId", listId.getText().toString());


      
      if(menuItemName.equalsIgnoreCase("Edit")){
  		Toast.makeText(getApplicationContext(), "You want to Edit  "+listName.getText().toString() +"    "+listId.getText().toString(), Toast.LENGTH_LONG).show();
  		
  		//dbTools.updateList(queryValuesMap);
  		//dbTools.updateItems(queryValuesMap);
  		
  		
		Intent  theIntent = new Intent(getApplication(), EditList.class);
		
		theIntent.putExtra("listId", listId.getText().toString());
		theIntent.putExtra("listName", listName.getText().toString()); 
		

		startActivity(theIntent); 
		

      } else if (menuItemName.equalsIgnoreCase("Delete")){
    	  
    	  
    	  
    	  
    	  
	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    			MainListActivity.this);

	    		alertDialogBuilder.setTitle("Delete List");

	    		alertDialogBuilder
	    			.setMessage("Are you sure you want to delete the list " + listName.getText().toString() + "?")
	    			.setCancelable(false)
	    			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog,int id) {

	    			  		dbTools.deleteList(listId.getText().toString());
	    			        callMainListActivity();
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

      return true;
    }
    
	
	@Override
	protected void onResume() {
		super.onResume();
		listView = (ListView) findViewById(R.id.memberList_id);

		
		ArrayList<HashMap<String, String>> itemList = dbTools.getAllLists();
		
		
		ListAdapter adapter = new SimpleAdapter( MainListActivity.this,itemList, R.layout.view_member_entry, new String[] { "listId","listName", "listDate", "itemCount"}, new int[] {R.id.listId, R.id.listName, R.id.listDate, R.id.itemsBoughtCount});
		
		//adapter = new ShoppingListAdapter( MainListActivity.this,R.layout.item_entry, itemList);//, new String[] { "itemId","itemName", "itemAmount"}, new int[] {R.id.itemId, R.id.itemName, R.id.itemAmount});

		((BaseAdapter) adapter).notifyDataSetChanged();

		listView.setAdapter(adapter);
	}
	
	
	public void callMainListActivity() {
//		Intent theIntent = new Intent(getApplicationContext(), MainActivity.class);
//		startActivity(theIntent);
		////finish();
		
		Intent main = new Intent(MainListActivity.this, MainListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
startActivity(main);
	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
//	    case R.id.add_list:
//	    	Intent add_mem = new Intent(MainListActivity.this, NewList.class);
//			startActivity(add_mem);
//			return true;
	    case R.id.delete_all_lists:
	    	
	    	
	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    			MainListActivity.this);

	    		alertDialogBuilder.setTitle("Delete All Lists");

	    		alertDialogBuilder
	    			.setMessage("Are you sure you want to delete all lists with their items?")
	    			.setCancelable(false)
	    			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog,int id) {
	    			    	dbTools.deleteAllList();
	    			    	dbTools.deleteAllItems();
	    			    	
//	    					Intent  theIntent = new Intent(getApplicationContext(),MainListActivity.class);
//	
//	    					startActivity(theIntent); 
	    			    	callMainListActivity();
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
	    case R.id.help:
	    	
	    	
	    	AlertDialog.Builder helpAlertDialogBuilder = new AlertDialog.Builder(
	    			MainListActivity.this);

	    	helpAlertDialogBuilder.setTitle("Application Help...");

	    	helpAlertDialogBuilder
	    			.setMessage("First, you need to create a list from the menu (+). Then the items can be added to it!")
	    			.setCancelable(false)
	    			.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
	    				public void onClick(DialogInterface dialog,int id) {
	    					dialog.cancel();

	    				}
	    			  });
	    		AlertDialog helpAlertDialog = helpAlertDialogBuilder.create();

	    		helpAlertDialog.show();
	    		

			return true;
			
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	public void showAddList(View view) {
    	Intent add_mem = new Intent(MainListActivity.this, NewList.class);
		startActivity(add_mem);
	}
	
	public void deleteAllLists(View view) {
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    			MainListActivity.this);

    		alertDialogBuilder.setTitle("Delete All Lists");

    		alertDialogBuilder
    			.setMessage("Are you sure you want to delete all lists with their items?")
    			.setCancelable(false)
    			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog,int id) {
    			    	dbTools.deleteAllList();
    			    	dbTools.deleteAllItems();
    			    	
    			    	callMainListActivity();
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
	
	
	public void exitList(View view) {
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    			MainListActivity.this);

    		alertDialogBuilder.setTitle("Exit Application");

    		alertDialogBuilder
    			.setMessage("Are you sure you want to exit the application?")
    			.setCancelable(false)
    			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog,int id) {
    					finish();
    				}
    			  })
    			.setNegativeButton("No",new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog,int id) {
    					dialog.cancel();
    				}
    			});
    		AlertDialog alertDialog = alertDialogBuilder.create();

    		alertDialog.show();	}
	

	public void showMoreMenu(View view) {
		

		int currentApiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentApiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB){
		    PopupMenu popupMenu = new PopupMenu(MainListActivity.this, view);
		    
		    
		    MenuInflater inflater = popupMenu.getMenuInflater();
		    inflater.inflate(R.menu.main, popupMenu.getMenu());
		    
		    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
		    	   
		    	   @Override
		    	   public boolean onMenuItemClick(MenuItem item) {
		    	    Toast.makeText(MainListActivity.this,
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
