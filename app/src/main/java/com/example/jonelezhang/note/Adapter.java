package com.example.jonelezhang.note;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
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
        ImageView photo = (ImageView) convertView.findViewById(R.id.noteImage);
        TextView createTime = (TextView) convertView.findViewById(R.id.noteCreateTime);

        Note note;
        note = noteList.get(position);
        int aa = note.getId();
        title.setText(note.getTitle());
        createTime.setText(note.getCreateTime());
//      if no photo not show ImageView block
        if(note.getImageResourceId() == null)
             {
                 int id = context.getResources().getIdentifier("com.example.jonelezhang.note:drawable/" + "def", null, null);
                 photo.setImageResource(id);
             } else{
            //Photo file path and show photo
                String path = Environment.getExternalStorageDirectory().toString() + "/notes_images/";
                String photoPath = path + note.getImageResourceId();
                Bitmap myBitmap = BitmapFactory.decodeFile(photoPath);
                photo.setImageBitmap(Bitmap.createScaledBitmap(myBitmap, 220, 270, false));
        }
        return convertView;
    }
}
