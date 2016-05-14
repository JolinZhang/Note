package com.example.jonelezhang.note;

/**
 * Created by Jonelezhang on 5/14/16.
 */
public class Note {
        private String title;
        private String content;
        private String imageResourcesId;
    //constructor
        private Note( String title, String content, String imageResourceId){
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
        public String getImageResourceId(){
            return imageResourcesId;
        }

}
