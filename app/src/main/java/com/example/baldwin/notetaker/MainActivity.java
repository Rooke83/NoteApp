package com.example.baldwin.notetaker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.baldwin.notetaker.MESSAGE";

    Context context;
    private List<String> storeNotes;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText editText;
    private String filename = "savedNotes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        storeNotes = new ArrayList<>();
        editText = (EditText) findViewById(R.id.enter_text);


        adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1, storeNotes);

        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int index, long id) {
                startEdit(view, index);
                //Toast.makeText(getApplicationContext(), "List Item Clicked: " + notes.get(index), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    public void startEdit(View view, int index) {
        Intent intent = new Intent(this, EditActivity.class);
        String note = storeNotes.get(index);
        //Toast.makeText(getApplicationContext(), "List Item Clicked: " + note, Toast.LENGTH_LONG).show();
        intent.putExtra(EXTRA_MESSAGE, note);
        if (note != null) startActivity(intent);
    }

    public void saveNote(View view) {
        String input = editText.getText().toString();
        if (input.length() > 0) {
            adapter.add(input);
            editText.setText("");
        }
    }

    public void populateArray() {
        try
        {
            FileInputStream input = openFileInput(filename);
            DataInputStream din = new DataInputStream(input);
            int size = din.readInt();
            for (int i = 0; i < size; i++) {
                String line = din.readUTF();

                storeNotes.add(line);

            }
            din.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        populateArray();
        adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1, storeNotes);
        listView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();

        try {
            FileOutputStream output = openFileOutput(filename, this.MODE_PRIVATE);
            DataOutputStream out = new DataOutputStream(output);
            out.writeInt(storeNotes.size());
            for (String line : storeNotes) {
                out.writeUTF(line);
            }
            out.flush();
            out.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        storeNotes.clear();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
