package com.example.shoeb.firststudioapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class UserList extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> listAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

       /* Intent intent = getIntent();
        String listMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);*/

        listView = (ListView) findViewById(R.id.listView);

/*        String[] planets = new String[] { listMessage , "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );*/

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
//        listAdapter.add( listMessage );
        /*listAdapter.add( "Pluto" );
        listAdapter.add( "Haumea" );
        listAdapter.add( "Makemake" );
        listAdapter.add( "Eris" );*/

        // Set the ArrayAdapter as the ListView's adapter.
//        listView.setAdapter( listAdapter );

    }

    public void addMessages(View view){
        TextView editText = (TextView) findViewById(R.id.editText);
        String newMessage = editText.getText().toString().trim();
        String minn = "";
        Log.d("Message",newMessage);
        long size = newMessage.length();
        if (size > 0){ //Checks if the message is not empty
            Date date = new Date();
            int hour = date.getHours();
            int min = date.getMinutes();
            if (min>=0 && min <=9){// adds zero to the single digit minute value
                minn = "0" + min;
            }
            else { // keeps the minute value as it is, if it is not single digit
                minn = min+"";
            }
            newMessage += " " + hour + ":" + minn;
            editText.setText("");
            listAdapter.add(newMessage);
            listView.setAdapter( listAdapter );
            listView.setSelection(listView.getAdapter().getCount()-1 ); //scroll to the last item of the listview
            listAdapter.notifyDataSetChanged();
//        return newMessage;
        }
        /*else {
            editText.setText("");
        }*/
    }
}
