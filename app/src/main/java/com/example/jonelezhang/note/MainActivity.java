package com.example.jonelezhang.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageButton write;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private List<Note> noteList;
    private Note note;
    private Adapter adapter;
    private GridView gridView;
    NotesDatabaseHelper noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get getAllNote function in  NotesDatabaseHelper
        noteHelper = new NotesDatabaseHelper(MainActivity.this);
        noteList = new ArrayList< Note >();
        noteList = noteHelper.getAllNote();
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new Adapter(MainActivity.this,noteList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 note = new Note("");
                 note = noteHelper.getNote(position);
                 Intent i = new Intent(MainActivity.this, noteView.class);
                 i.putExtra("title",note.getTitle());
                 i.putExtra("content",note.getContent());
                 i.putExtra("pictureId",note.getImageResourceId());
                 i.putExtra("createTime",note.getCreateTime());
                 startActivity(i);
            }
        });


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

}
