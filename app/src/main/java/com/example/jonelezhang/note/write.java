package com.example.jonelezhang.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.preference.DialogPreference;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class write extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private String imageResourcesId;
    private ImageButton takePhoto;
    private ImageView addPhoto;
    private ImageButton submit;
    private NotesDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        title = (EditText) findViewById( R.id.title );
        content = (EditText) findViewById( R.id.content );
        dbHelper = new NotesDatabaseHelper(this);
//      photo button click, show alertDialog
        takePhoto = (ImageButton) findViewById(R.id.photo);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        submit = (ImageButton) findViewById(R.id.submit);
        submit.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                 saveNote();
            }
        });
    }
    // save title, content and image_id into note
    public void saveNote(){
        Note note = new Note("");
        note.setTitle(title.getText().toString());
        note.setContent(content.getText().toString());
        note.setImageResourceId(imageResourcesId);
        //use function addNote in class NotesDatabaseHelper to insert data
        dbHelper.addNote(note);
    }
//  design alertDialog
    private void selectImage(){
        final CharSequence photoOptions[] = new CharSequence[]{"Take Photo", "Choose Photo"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo");
        builder.setItems(photoOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(photoOptions[which].equals("Take Photo")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,1);
                }else if(photoOptions[which].equals("Choose Photo")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            }
        });
        builder.show();
    }
//  save image into external folder
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
       if(resultCode == RESULT_OK) {
           addPhoto = (ImageView) findViewById(R.id.addPhoto);
           // take photo
           if (requestCode == 1) {
           //  show Thumbnail of picture as bg
               Bundle extras = data.getExtras();
               Bitmap imageBitmap = (Bitmap) extras.get("data");
               //show in ImageView
               addPhoto.setImageBitmap(imageBitmap);
               //save picture
               savePicture(imageBitmap);
           }else if(requestCode == 2){
               Uri selectedImage = data.getData();
               //addPhoto.setImageURI(selectedImage);
               String[] filePath = {MediaStore.Images.Media.DATA};
               Cursor c = getContentResolver().query(selectedImage, filePath,null,null,null);
               c.moveToFirst();
               int columnIndex = c.getColumnIndex(filePath[0]);
               String picturePath=c.getString(columnIndex);
               c.close();
               File imgFile = new  File(picturePath);
               //rotate picture
               Bitmap bitmap = rotatePicture(imgFile);
               //show in ImageView
               addPhoto.setImageBitmap(bitmap);
               //save picture
               savePicture(bitmap);
           }
       }
    }
    //define save path and save file name for taking photo and choose photo
    public void savePicture(Bitmap imageBitmap){
        //define dir folder
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/notes_images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        //  define picture name as current time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String timeStamp = dateFormat.format(date);
        imageResourcesId = timeStamp+".jpg";
        File file = new File(myDir, imageResourcesId);
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // rotate picture
    public Bitmap rotatePicture(File imgFile){
        ExifInterface exif = null;
        int rotate = 0;
        //show Thumbnail of picture as bg
        Bitmap thumbnail = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        try{
            exif = new ExifInterface(imgFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;}

        }catch(IOException e){
            e.printStackTrace();
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        Bitmap bitmap = Bitmap.createBitmap(thumbnail , 0, 0, thumbnail.getWidth(), thumbnail.getHeight(), matrix, true);
        return bitmap;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write, menu);
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
