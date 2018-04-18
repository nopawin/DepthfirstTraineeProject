package com.earthsilen.depthfirsttraineeproject.Models;

import java.io.Serializable;

public class DolistModels implements Serializable {
    private String title;
    private String note;
    private String date;


    /**public DepthfirstNewsModels(String imgurl, String desc) {
     this.imgurl = imgurl;
     this.desc = desc;

     }**/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}