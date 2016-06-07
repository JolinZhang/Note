package com.example.jonelezhang.note;

/**
 * Created by Jonelezhang on 5/14/16.
 */
public class Note {
        private int id;
        private String title;
        private String content;
        private String imageResourcesId;
        private String createTime;
    //constructor
        public Note(String s){
        }
    //set methods
        public void setId(Integer id){ this.id = id; }
        public void setTitle(String title){
            this.title = title;
        }
        public void setContent(String content){
            this.content = content;
        }
        public void setImageResourceId(String imageResourceId){
            this.imageResourcesId = imageResourceId;
        }
        public void setCreateTime(String createTime){
            this.createTime = createTime;
        }
    // get methods
        public int   getId(){return id;}
        public String getTitle(){
            return title;
        }
        public String getContent(){
            return content;
        }
        public String getImageResourceId(){
            return imageResourcesId;
        }
        public String getCreateTime(){
            return createTime;
        }
}
