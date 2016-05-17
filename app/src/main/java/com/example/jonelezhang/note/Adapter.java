package com.example.jonelezhang.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jonelezhang on 5/16/16.
 */
public class Adapter extends BaseAdapter{
    Context context;
    List<Note> noteList;
    private static LayoutInflater inflater = null;

    //constructor
    public Adapter(Context context, List<Note> noteList){
        this.context = context;
        this.noteList = noteList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_note,null);
        }
        TextView title =(TextView) convertView.findViewById(R.id.noteTitle);
        Note note;
        note = noteList.get(position);
        title.setText(note.getTitle());
        return convertView;
    }
}
