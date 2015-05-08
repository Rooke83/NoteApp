package com.example.baldwin.notetaker;

import android.content.Intent;
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
        String note = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        editText = (EditText) findViewById(R.id.edit_text);
        editText.setText(note);
        arrPos = intent.getIntExtra("arrPos", 0);

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

    public void onPause() {
        super.onPause();

    }

    public void saveEdit(View view){
        String toSend = editText.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("note", toSend);
        intent.putExtra("arrPos", arrPos);
        startActivity(intent);
    }
    
    public void cancelEdit(View view) {
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
