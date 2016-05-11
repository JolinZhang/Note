package com.example.jonelezhang.note;

/**
 * Created by Jonelezhang on 5/9/16.
 */
public class notes {
    private String title;
    private String content;
    private int imageResourcesId;

    private notes(String title, String content, int imageResourceId){
        this.title = title;
        this.content = content;
        this.imageResourcesId = imageResourceId;
    }
    public String gettitle(){
        return title;
    }
    public  String getContent(){
        return content;
    }
    public int getImageResourceId(){
        return imageResourcesId;
    }
    //notes is an array of note
    public static final notes[] note={
            new notes("Happy","I am happy 222222",R.drawable.happy),
            new notes("Angry","I am angry 44444",R.drawable.angry),
            new notes("sad","I am sad 44444",R.drawable.sad),

    };

}
