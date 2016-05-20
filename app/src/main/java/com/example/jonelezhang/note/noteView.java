package com.example.jonelezhang.note;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class noteView extends AppCompatActivity {
    private TextView title;
    private TextView content;
    private TextView createTime;
    String temp1;
    String temp2;
    String temp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        createTime = (TextView) findViewById(R.id.createTime);

        Intent intent = getIntent();
        temp1 = intent.getStringExtra("title");
        temp2 = intent.getStringExtra("content");
        temp3 = intent.getStringExtra("createTime");
        title.setText(temp1);
        content.setText(temp2);
        createTime.setText(temp3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_view, menu);
        return true;
    }

}
