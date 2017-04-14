package com.artimed.shoppinglist;

import java.util.ArrayList;
import java.util.HashMap;
import com.artimed.shoppinglist.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ShoppingListAdapter extends ArrayAdapter<HashMap<String, String>> {
	//ShoppingListHolder shoppingListHolder = null;////////buraya naklettim
	private static Context context;
	private static int layoutResourceId;
	private static ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

	//////HashMap<String, String> user;///buraya koydum


	
	private static class ShoppingListHolder {
		TextView itemId;
		TextView itemName;
		TextView itemAmount;
		ImageButton removeButton;
		ImageButton editButton;
		CheckBox boughtButton;
		
		LinearLayout linearLayout;
		
		
		
		int position;
	}
	
	public ShoppingListAdapter(Context context, int layoutResourceId,
			ArrayList<HashMap<String, String>> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ShoppingListHolder shoppingListHolder;
		//View row = convertView;
		/////user = data.get(position);

		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(layoutResourceId, parent, false);
			shoppingListHolder = new ShoppingListHolder();
			
			shoppingListHolder.itemId = (TextView) convertView.findViewById(R.id.itemId);

			shoppingListHolder.itemName = (TextView) convertView.findViewById(R.id.itemName);
		



			shoppingListHolder.itemAmount = (TextView) convertView.findViewById(R.id.itemAmount);
			

		
			

			shoppingListHolder.removeButton = (ImageButton) convertView.findViewById(R.id.removeButton);
			shoppingListHolder.editButton = (ImageButton) convertView.findViewById(R.id.editButton);
			shoppingListHolder.boughtButton = (CheckBox) convertView.findViewById(R.id.itemBought);
			
			shoppingListHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.theItemLayout);


			
		////////

					
		////////	
					
					
					convertView.setTag(shoppingListHolder);
		} else {
			shoppingListHolder = (ShoppingListHolder) convertView.getTag();
		}
		
		shoppingListHolder.itemId.setText(data.get(position).get("itemId"));

		shoppingListHolder.itemName.setText(data.get(position).get("itemName"));
		shoppingListHolder.itemAmount.setText(data.get(position).get("itemAmount"));
		
		

		if(data.get(position).get("itemBought").equals("1") ){
			shoppingListHolder.itemName.setPaintFlags(shoppingListHolder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			shoppingListHolder.itemAmount.setPaintFlags(shoppingListHolder.itemAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			shoppingListHolder.boughtButton.setChecked(true);
			shoppingListHolder.itemName.setTextColor(Color.GRAY);//
			shoppingListHolder.itemAmount.setTextColor(Color.GRAY);//

		}else if(!data.get(position).get("itemBought").equals("1")){
			
			
			shoppingListHolder.itemName.setPaintFlags(shoppingListHolder.itemAmount.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
			shoppingListHolder.itemAmount.setPaintFlags(shoppingListHolder.itemAmount.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
			shoppingListHolder.boughtButton.setChecked(false);
			shoppingListHolder.itemName.setTextColor(Color.BLACK);//
			shoppingListHolder.itemAmount.setTextColor(Color.BLACK);//
			


		}



shoppingListHolder.position = position;


//////////ddddddddddddddddddddddddddddddddsetRemoveOnClickListener(shoppingListHolder, user);
shoppingListHolder.removeButton.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(final View v) {


AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
		context);

	alertDialogBuilder.setTitle("Delete Item");

	alertDialogBuilder
		.setMessage("Are you sure you want to delete this item?\n-" + data.get(shoppingListHolder.position).get("itemName"))
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				remove(data.get(shoppingListHolder.position));
				
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
})		;


/////////////sssssssssssssssssetEditOnClickListener(shoppingListHolder, user);
shoppingListHolder.editButton.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		Intent  theIntent = new Intent(v.getContext(),EditItem.class);
							
		theIntent.putExtra("itemId", data.get(shoppingListHolder.position).get("itemId"));
		
		theIntent.putExtra("listId", data.get(shoppingListHolder.position).get("listId"));
		theIntent.putExtra("listName", data.get(shoppingListHolder.position).get("listName"));
		
		
		context.startActivity(theIntent); 

		

	}
})		;


///////////////////ffffffffffffffffffsetBoughtOnClickListener(shoppingListHolder, user);
shoppingListHolder.boughtButton.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		
		if(shoppingListHolder.boughtButton.isChecked()){
			Toast.makeText(context, "Item " + data.get(shoppingListHolder.position).get("itemName") + " has been bought!", Toast.LENGTH_SHORT).show();
			//data.get(shoppingListHolder.position).get("itemBought").replace("0", "1");
			shoppingListHolder.itemName.setText(data.get(shoppingListHolder.position).get("itemName"));

			shoppingListHolder.itemAmount.setText(data.get(shoppingListHolder.position).get("itemAmount"));
			

			shoppingListHolder.itemAmount.setPaintFlags(shoppingListHolder.itemAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			shoppingListHolder.itemName.setPaintFlags(shoppingListHolder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			
			
			shoppingListHolder.itemName.setTextColor(Color.GRAY);//
			shoppingListHolder.itemAmount.setTextColor(Color.GRAY);//
			
			/////////////////////////
			
			DatabaseTools dbTools = new DatabaseTools(context);
			
			HashMap<String, String> queryValuesMap = new HashMap<String, String>();

						
			queryValuesMap.put("itemId", data.get(shoppingListHolder.position).get("itemId"));
			queryValuesMap.put("itemBought", "1");////////xxxxxxxxxxxxx item checked boolean true

			queryValuesMap.put("listId", data.get(shoppingListHolder.position).get("listId"));
			queryValuesMap.put("listName", data.get(shoppingListHolder.position).get("listName"));
			
			
			dbTools.updateItemFlag(queryValuesMap);
			data =  dbTools.getAllItems(data.get(shoppingListHolder.position).get("listId"));//sssssssssssssssssssssss olur mu yine yav
			dbTools.close();
			//setNotifyOnChange(true);
			//notifyDataSetChanged();

			///////
			
		} else if(!shoppingListHolder.boughtButton.isChecked()){

			Toast.makeText(context, "Item " + data.get(shoppingListHolder.position).get("itemName") + " has NOT been bought!", Toast.LENGTH_SHORT).show();
			//data.get(shoppingListHolder.position).get("itemBought").replace("1", "0");
			shoppingListHolder.itemAmount.setPaintFlags(shoppingListHolder.itemAmount.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
			shoppingListHolder.itemName.setPaintFlags(shoppingListHolder.itemName.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

			shoppingListHolder.itemName.setTextColor(Color.BLACK);//
			shoppingListHolder.itemAmount.setTextColor(Color.BLACK);//
			
			DatabaseTools dbTools = new DatabaseTools(context);
			
			HashMap<String, String> queryValuesMap = new HashMap<String, String>();

						
			queryValuesMap.put("itemId", data.get(shoppingListHolder.position).get("itemId"));
			queryValuesMap.put("itemBought", "0");////////xxxxxxxxxxxxx item checked boolean true
			
			queryValuesMap.put("listId", data.get(shoppingListHolder.position).get("listId"));
			queryValuesMap.put("listName", data.get(shoppingListHolder.position).get("listName"));
					

			dbTools.updateItemFlag(queryValuesMap);
			data =  dbTools.getAllItems(data.get(shoppingListHolder.position).get("listId"));//sssssssssssssssssssssss olur mu yine yav
			dbTools.close();
			//setNotifyOnChange(true);
			//notifyDataSetChanged();

		}



		
		

	}
})		;





////////////////ssssssssssss
shoppingListHolder.linearLayout.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		
		if(!data.get(shoppingListHolder.position).get("itemBought").equals("1")){
			Toast.makeText(context, "Item " + data.get(shoppingListHolder.position).get("itemName") + " has been bought!", Toast.LENGTH_SHORT).show();
			
			shoppingListHolder.boughtButton.setChecked(true);

			
			//data.get(shoppingListHolder.position).get("itemBought").replace("0", "1");
			shoppingListHolder.itemName.setText(data.get(shoppingListHolder.position).get("itemName"));

			shoppingListHolder.itemAmount.setText(data.get(shoppingListHolder.position).get("itemAmount"));
			

			shoppingListHolder.itemAmount.setPaintFlags(shoppingListHolder.itemAmount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			shoppingListHolder.itemName.setPaintFlags(shoppingListHolder.itemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			
			
			shoppingListHolder.itemName.setTextColor(Color.GRAY);//
			shoppingListHolder.itemAmount.setTextColor(Color.GRAY);//
			
			/////////////////////////
			
			DatabaseTools dbTools = new DatabaseTools(context);
			
			HashMap<String, String> queryValuesMap = new HashMap<String, String>();

						
			queryValuesMap.put("itemId", data.get(shoppingListHolder.position).get("itemId"));
			queryValuesMap.put("itemBought", "1");////////xxxxxxxxxxxxx item checked boolean true

			queryValuesMap.put("listId", data.get(shoppingListHolder.position).get("listId"));
			queryValuesMap.put("listName", data.get(shoppingListHolder.position).get("listName"));
			
			
			dbTools.updateItemFlag(queryValuesMap);
			data =  dbTools.getAllItems(data.get(shoppingListHolder.position).get("listId"));//sssssssssssssssssssssss olur mu yine yav
			dbTools.close();
			//setNotifyOnChange(true);
			//notifyDataSetChanged();

			///////
			
		} else if(data.get(shoppingListHolder.position).get("itemBought").equals("1")){

			Toast.makeText(context, "Item " + data.get(shoppingListHolder.position).get("itemName") + " has NOT been bought!", Toast.LENGTH_SHORT).show();
			
			shoppingListHolder.boughtButton.setChecked(false);

			
			//data.get(shoppingListHolder.position).get("itemBought").replace("1", "0");
			shoppingListHolder.itemAmount.setPaintFlags(shoppingListHolder.itemAmount.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
			shoppingListHolder.itemName.setPaintFlags(shoppingListHolder.itemName.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));

			shoppingListHolder.itemName.setTextColor(Color.BLACK);//
			shoppingListHolder.itemAmount.setTextColor(Color.BLACK);//
			
			DatabaseTools dbTools = new DatabaseTools(context);
			
			HashMap<String, String> queryValuesMap = new HashMap<String, String>();

						
			queryValuesMap.put("itemId", data.get(shoppingListHolder.position).get("itemId"));
			queryValuesMap.put("itemBought", "0");////////xxxxxxxxxxxxx item checked boolean true
			
			queryValuesMap.put("listId", data.get(shoppingListHolder.position).get("listId"));
			queryValuesMap.put("listName", data.get(shoppingListHolder.position).get("listName"));
					

			dbTools.updateItemFlag(queryValuesMap);
			data =  dbTools.getAllItems(data.get(shoppingListHolder.position).get("listId"));//sssssssssssssssssssssss olur mu yine yav
			dbTools.close();
			//setNotifyOnChange(true);
			//notifyDataSetChanged();

		}



		
		

	}
})		;





//////////////////eeeeeeeeeeeeeee
		return convertView;

	}



	@Override
	public void remove(HashMap<String, String> object) {
		//data.remove(object);////ben ekledim
		super.remove(object);
		DatabaseTools dbTools = new DatabaseTools(context);
		dbTools.deleteItem(object.get("itemId"), object.get("listId"));
		
		//data =  dbTools.getAllItems();
		//data =  dbTools.getAllItems();//sssssssssssssssssssssss olur mu yine yav

		dbTools.close();
		//setNotifyOnChange(true);
		//notifyDataSetChanged();

		
		Intent intent = ((Activity) context).getIntent();
		((Activity) context).overridePendingTransition(0, 0);
		((Activity) context).finish();
		((Activity) context).overridePendingTransition(0, 0);
		((Activity) context).startActivity(intent);
		


	}

	private void setRemoveOnClickListener(ShoppingListHolder holder, final HashMap<String, String> object) {
	}
	
	
	private void setEditOnClickListener(ShoppingListHolder holder, final HashMap<String, String> object) {
	}
	
	

	private void setBoughtOnClickListener(final ShoppingListHolder holder, final HashMap<String, String> object) {
	}
	

	}


	


