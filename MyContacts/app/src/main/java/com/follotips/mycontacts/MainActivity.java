package com.follotips.mycontacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ShowAllContactsActivity";

    DBHelper mdbHelper;

    private ListView mListView;
    private ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
      //  mListView = (ListView) findViewById(R.id.listView);
        mdbHelper = new DBHelper(this);








        populateListView();

    }

    protected void onResume() {


        adapter.notifyDataSetChanged();

        populateListView();

        super.onResume();
    }





    private void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in ListView. ");

        //get data and append to a list
        final ArrayList <String> listData = new ArrayList<>();


        //Cursor
        final Cursor data = mdbHelper.getDataAll();

        //for listview view elements
        while (data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the Arraylist
            listData.add(data.getString(1));

        }

        //create the list adapter and set the adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);


        mListView.setAdapter(adapter);



        //set an onItemClicklistener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                if(name.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Name is empty",Toast.LENGTH_LONG);
                }


                Log.d(TAG, "onItemClick you Clicked on " + name);

                //getting itemID
                Cursor data = mdbHelper.getItemID(name);
                int itemID = -1;

                //for item id listview
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }


                System.out.println("itemID at POSITION :" + itemID);

                Cursor data1 = mdbHelper.getDataParam(itemID,"surname" , "phoneNumber" , "birthday");
                //getting data from cursor data1
                String surname = data1.getString(0);
                String phoneNumber = data1.getString(1);
                String birthday = data1.getString(2);




                if (itemID > -1) {
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(MainActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id", itemID);
                    editScreenIntent.putExtra("name", name);
                    editScreenIntent.putExtra("surname",surname);
                    editScreenIntent.putExtra("phoneNumber", phoneNumber);
                    editScreenIntent.putExtra("birthday", birthday);

                    startActivity(editScreenIntent);
                    finish();
                } else {
                    toastMessage("No ID associated with that name");
                }

                // adapter.notifyDataSetChanged();

            }

        });
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void Onclick(View view){

        switch (view.getId()){

            case R.id.floatingActionButton:

                break;


        }

    }

}
