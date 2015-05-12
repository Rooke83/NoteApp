package com.example.baldwin.notetaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import org.w3c.dom.Text;


public class EditActivity extends ActionBarActivity {

    private EditText editText;
    private int arrPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        SharedPreferences sharedPref = getSharedPreferences("test", 0);

        String note = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        editText = (EditText) findViewById(R.id.edit_text);
        editText.setText(note);
        arrPos = intent.getIntExtra("arrPos", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
      //  editor.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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

    @Override
    public void onPause() {
        super.onPause();

            String save_string_key = "test";
            SharedPreferences sharedPref = getSharedPreferences("test", 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            EditText text = (EditText) findViewById(R.id.edit_text);
            editor.putString(save_string_key, text.getText().toString());
            editor.commit();

    }

    @Override
    public void onResume() {

        super.onResume();

        SharedPreferences sharedPref = getSharedPreferences("test", 0);

        String save_string_key = "test";

        String stuff = sharedPref.getString(
               save_string_key, "");
        if(stuff!=null && stuff!="") {
            EditText text =  (EditText) findViewById(R.id.edit_text);
            text.setText(stuff);
        } /*else {
            Intent intent = getIntent();


            String note = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            editText = (EditText) findViewById(R.id.edit_text);
            editText.setText(note);
            arrPos = intent.getIntExtra("arrPos", 0);
            SharedPreferences.Editor editor = sharedPref.edit();
            //  editor.clear();
        }*/

    }
    @Override
    public void onStop(){

        SharedPreferences sharedPref = getSharedPreferences("test", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        super.onStop();
    }

   @Override
    public void onBackPressed(){

       SharedPreferences sharedPref = getSharedPreferences("test", 0);
       SharedPreferences.Editor editor = sharedPref.edit();
       editor.remove("test");
       editor.commit();
       super.onBackPressed();
    }

    @Override
    public boolean onNavigateUp(){

        SharedPreferences sharedPref = getSharedPreferences("test", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("test");
        editor.commit();
        return super.onNavigateUp();

    }

    public void saveEdit(View view){
        String toSend = editText.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("note", toSend);
        intent.putExtra("arrPos", arrPos);
        startActivity(intent);
    }
    
    public void cancelEdit(View view) {

        SharedPreferences sharedPref = getSharedPreferences("test", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("test");
        editor.commit();
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void deleteNote(View view) {
        String toSend = editText.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("delete", true);
        intent.putExtra("arrPos", arrPos);
        startActivity(intent);
    }
}
