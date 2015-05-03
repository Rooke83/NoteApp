package com.example.baldwin.notetaker;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    Context context;
    List<String> notes;
    ArrayAdapter<String> adapter;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        notes = new ArrayList<>();

        Button enter = (Button) findViewById(R.id.enter_button);
        editText = (EditText) findViewById(R.id.edit_text);

        enter.setOnClickListener(this);

        adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1, notes);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int index, long id) {
                Toast.makeText(getApplicationContext(), "List Item Clicked: " + notes.get(index), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {

        String input = editText.getText().toString();
        if (input.length() > 0) {
            adapter.add(input);
        }

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
