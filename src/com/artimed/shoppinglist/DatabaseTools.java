package com.artimed.shoppinglist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DatabaseTools extends SQLiteOpenHelper {
	
	public DatabaseTools(Context applicationContext){
		
		super(applicationContext, "shoppinglist.db", null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String queryLists = "CREATE TABLE lists ( listId INTEGER PRIMARY KEY, listName TEXT, " +
		"listDate TEXT)";
		
		String queryItems = "CREATE TABLE items ( itemId INTEGER PRIMARY KEY, itemName TEXT, " +
		"itemAmount TEXT, itemBought TEXT, listId INTEGER, listName TEXT)";
		
		database.execSQL(queryLists);
		database.execSQL(queryItems);
		
		String firstListName = "My Shopping List";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();

			

		
		
		String insertTemplateList = "INSERT INTO lists (listName, " +
				"listDate) VALUES('" + firstListName + "', '" + dateFormat.format(date)+"')";
		database.execSQL(insertTemplateList);


	}
	

	
	

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		
		String query = "DROP TABLE IF EXISTS items";
		String queryLists = "DROP TABLE IF EXISTS lists";

		
		database.execSQL(queryLists);	
		database.execSQL(query);
		onCreate(database);
		
	}
	
	
	public void insertList(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("listName", queryValues.get("listName"));

		values.put("listDate", queryValues.get("listDate"));
		
		database.insert("lists", null, values);
		
		database.close();
		
	}
	
	
	public void insertItem(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("itemName", queryValues.get("itemName"));
		values.put("itemAmount", queryValues.get("itemAmount"));
		values.put("itemBought", queryValues.get("itemBought"));
		
		values.put("listId", queryValues.get("listId"));
		values.put("listName", queryValues.get("listName"));

		
		database.insert("items", null, values);
		
		database.close();
		
	}
	
	
	
	
	
	
	public int updateList(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		
		values.put("listName", queryValues.get("listName"));
		values.put("listId", queryValues.get("listId"));



		
		return database.update("lists", values, 
				"listId" + " = ?", new String[] {queryValues.get("listId") });
		
	}
	
	
	public int updateItems(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		
		values.put("listName", queryValues.get("listName"));
		values.put("listId", queryValues.get("listId"));


		
		return database.update("items", values, 
				"listId" + " = ?", new String[] {queryValues.get("listId") });

		
	}
	
	
	
	
	public int updateItem(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		
		values.put("itemName", queryValues.get("itemName"));
		values.put("itemAmount", queryValues.get("itemAmount"));
		if(queryValues.get("itemBought")!=null){
			values.put("itemBought", queryValues.get("itemBought"));
	
		}

		return database.update("items", values, 
				"itemId" + " = ? AND listId" + " = ?", new String[] {queryValues.get("itemId"), queryValues.get("listId") });
		
	}
	
	public int updateItemFlag(HashMap<String, String> queryValues){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put("itemBought", queryValues.get("itemBought"));
	
		return database.update("items", values, 
				"itemId" + " = ? AND listId" + " = ?", new String[] {queryValues.get("itemId"), queryValues.get("listId") });
		
	}
	

	public void deleteList(String listId){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		String deleteListQuery = "DELETE FROM lists WHERE listId='" + listId + "'";
		
		database.execSQL(deleteListQuery);
		
		String deleteItemsQuery = "DELETE FROM items WHERE listId='" + listId + "'";
		
		database.execSQL(deleteItemsQuery);
		
	}
	
	
	public void deleteItem(String itemId, String listId){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		String deleteQuery = "DELETE FROM items WHERE itemId='" + itemId + "' AND listId='" + listId + "'";
		
		database.execSQL(deleteQuery);
		
	}
	
	public ArrayList<HashMap<String, String>> getAllItems(String listId){
		
		ArrayList<HashMap<String, String>> itemArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT * FROM items WHERE listId ='" + listId +"' ORDER BY itemId";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				HashMap<String, String> itemMap = new HashMap<String, String>();
				
				itemMap.put("itemId", cursor.getString(0));
				itemMap.put("itemName", cursor.getString(1));
				itemMap.put("itemAmount", cursor.getString(2));
				itemMap.put("itemBought", cursor.getString(3));
				itemMap.put("listId", cursor.getString(4));
				itemMap.put("listName", cursor.getString(5));

	
				
				itemArrayList.add(itemMap);
				
			} while(cursor.moveToNext());
			
		}
		
		return itemArrayList;
		
	}
	
	
	public ArrayList<HashMap<String, String>> getAllLists(){
	//public Cursor getAllLists(){
		
		ArrayList<HashMap<String, String>> itemArrayList = new ArrayList<HashMap<String, String>>();
		
		//String selectQuery = "SELECT * FROM lists ORDER BY listId DESC";
		
		
		String selectQuery = "SELECT distinct a.listId, a.listName, a.listDate,  " +
				"  ifnull((select count(*) itemTotalCount from items b where a.listId = b.listId group by b.listId), '0'),  " +
				"  ifnull((select count(*) itemBoughtsCount from items c where a.listId = c.listId and c.itemBought = '1' group by c.listId), '0')  " +
				" FROM lists a " +
				"  ORDER BY a.listId ASC";
		
		
		
		 
		//(select listId, ifnull(count(*), 0) itemsBoughtCount from items group by listId where itemBought = 1) b 

			//	select listId, ifnull(count(*), 0) itemsTotalCount from items where listId = listId group by listId

				
				
				
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				HashMap<String, String> itemMap = new HashMap<String, String>();
				
				itemMap.put("listId", cursor.getString(0));
				itemMap.put("listName", cursor.getString(1));
				itemMap.put("listDate", cursor.getString(2));
				itemMap.put("itemCount", cursor.getString(4)+"/"+cursor.getString(3));


	
				
				itemArrayList.add(itemMap);
				
			} while(cursor.moveToNext());
			
		}
		
		return itemArrayList;
		//return cursor;
		
	}

	
	
	
	
	
	public HashMap<String, String> getListInfo(String listId){
		
		HashMap<String, String> itemMap = new HashMap<String, String>();
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM lists WHERE listId='" + listId + "'";
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				itemMap.put("listId", cursor.getString(0));
				itemMap.put("listName", cursor.getString(1));
				itemMap.put("listDate", cursor.getString(2));



				
			} while(cursor.moveToNext());
			
		}
		
		return itemMap;
		
	}
	
	
	
	
/*	public HashMap<String, String> getItemsBoughtCount(String listId){
		
		HashMap<String, String> itemMap = new HashMap<String, String>();
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT IFNULL(COUNT(*), 0) FROM items WHERE listId='" + listId + "'";
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				itemMap.put("totalItemCount", cursor.getString(0));			
			} while(cursor.moveToNext());
			
		}
		
		
		
		String selectQuery2 = "SELECT IFNULL(COUNT(*), 0) FROM items WHERE listId='" + listId + "' AND itemBought='1'";
		
		Cursor cursor2 = database.rawQuery(selectQuery2, null);
		
		if(cursor2.moveToFirst()){
			
			do{
				
				itemMap.put("totalItemBought", cursor2.getString(0));			
			} while(cursor2.moveToNext());
			
		}
		
		
		return itemMap;
		
	}*/
	
	
	public HashMap<String, String> getItemInfo(String itemId, String listId){
		
		HashMap<String, String> itemMap = new HashMap<String, String>();
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM items WHERE itemId='" + itemId + "' AND listId='" + listId + "'";
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()){
			
			do{
				
				itemMap.put("itemId", cursor.getString(0));
				itemMap.put("itemName", cursor.getString(1));
				itemMap.put("itemAmount", cursor.getString(2));
				itemMap.put("itemBought", cursor.getString(3));
				
				itemMap.put("listId", cursor.getString(4));
				itemMap.put("listName", cursor.getString(5));


				
			} while(cursor.moveToNext());
			
		}
		
		return itemMap;
		
	}
	
	
	public void deleteAllList(){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		String deleteListQuery = "DELETE FROM lists";
		
		database.execSQL(deleteListQuery);
		
	}
	
	public void deleteAllItems(){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		String deleteItemsQuery = "DELETE FROM items";
		
		database.execSQL(deleteItemsQuery);
		
	}
	
	public void deleteAllItemsInList(String listId){
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		String deleteQuery = "DELETE FROM items WHERE listId='" + listId + "'";
		
		database.execSQL(deleteQuery);
		
	}
	
}








