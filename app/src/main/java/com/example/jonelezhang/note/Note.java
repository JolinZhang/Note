package com.example.jonelezhang.note;

/**
 * Created by Jonelezhang on 5/14/16.
 */
public class Note {
        private String title;
        private String content;
        private String imageResourcesId;
    //constructor
        public Note(String s){
        }
    //set methods
        public void setTitle(String title){
            this.title = title;
        }
        public void setContent(String content){
            this.content = content;
        }
        public void setImageResourceId(String imageResourceId){
            this.imageResourcesId = imageResourceId;
        }
    // get methods
        public String getTitle(){
            return title;
        }
        public  String getContent(){
            return content;
        }
        public String getImageResourceId(){
            return imageResourcesId;
        }

}
