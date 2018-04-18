package com.earthsilen.depthfirsttraineeproject.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class TraineeNewsModels implements Serializable {
    private ArrayList<String> imgurl = new ArrayList<String>();
    private String desc;




    /**public DepthfirstNewsModels(String imgurl, String desc) {
     this.imgurl = imgurl;
     this.desc = desc;

     }**/

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public  ArrayList<String> getImgurl() {
        return imgurl;
    }



    public void setImgurl(ArrayList<String> imgurl) {
        this.imgurl = imgurl;
    }

    public void addToArray(String imgurl){
        this.imgurl.add(imgurl);
    }


}