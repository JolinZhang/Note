package com.example.jonelezhang.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton write;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private CardView mCardView;
    private ImageView noteImage;
    private TextView noteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteImage = (ImageView) findViewById(R.id.noteImage);
        noteTitle = (TextView) findViewById(R.id.noteTitle);
        // get getAllNote function in  NotesDatabaseHelper
        NotesDatabaseHelper noteHelper = new NotesDatabaseHelper(getBaseContext());
        List<Note> noteList = noteHelper.getAllNote();
        for (Note note : noteList){

        }






        //click button, connect to another activity
        write = (ImageButton) findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                startActivity(new Intent(MainActivity.this,write.class));
            }
        });

    }
//  press back button from main activity close
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
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
