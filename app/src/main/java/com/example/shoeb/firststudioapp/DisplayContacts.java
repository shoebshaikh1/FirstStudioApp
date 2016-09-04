package com.example.shoeb.firststudioapp;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayContacts extends AppCompatActivity {
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contacts);

        list = (ListView) findViewById(R.id.listView1);
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();
    }
}

class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<String>> {
//    ProgressDialog pd;
    Context context;
    DisplayContacts dc = new DisplayContacts();
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();

        /*pd = ProgressDialog.show(this, "Loading Contacts",
                "Please Wait");*/
    }

    @Override
    protected ArrayList<String> doInBackground(Void... params) {
        // TODO Auto-generated method stub
        ArrayList<String> contacts = new ArrayList<String>();

        Cursor c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null, null, null);
        while (c.moveToNext()) {

            String contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contacts.add(contactName + ":" + phNumber);

        }
        c.close();

        return contacts;
    }

    @Override
    protected void onPostExecute(ArrayList<String> contacts) {
        // TODO Auto-generated method stub
        super.onPostExecute(contacts);

//        pd.cancel();

//        ll.removeView(loadBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context.getApplicationContext(), R.layout.contact_row, contacts);
        ListView list = (ListView) dc.findViewById(R.id.listView1);
        list.setAdapter(adapter);

    }


}
