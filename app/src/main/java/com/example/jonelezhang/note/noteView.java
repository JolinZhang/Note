package com.example.jonelezhang.note;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class noteView extends AppCompatActivity {
    private TextView title;
    private TextView content;
    private TextView createTime;
    String temp1;
    String temp2;
    String temp3;
    int temp4;
    private ImageButton delete;
    NotesDatabaseHelper  noteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        createTime = (TextView) findViewById(R.id.createTime);
        // show title content and create time
        final Intent intent = getIntent();
        temp1 = intent.getStringExtra("title");
        temp2 = intent.getStringExtra("content");
        temp3 = intent.getStringExtra("createTime");
        temp4 = intent.getIntExtra("id", 0);
        title.setText(temp1);
        content.setText(temp2);
        createTime.setText(temp3);
        //delete function
        delete = (ImageButton) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteHelper = new NotesDatabaseHelper(noteView.this);
                noteHelper.deleteNote(temp4);
                startActivity(new Intent(noteView.this, MainActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_view, menu);
        return true;
    }

}
