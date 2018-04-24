package com.earthsilen.depthfirsttraineeproject.Models;


import java.io.Serializable;

public class TraineeTeamModels implements Serializable {
    private String imgurl;
    private String name;
    private String rank;

//    public TraineeTeamModels(String imgurl, String name, String rank) {
//        this.imgurl = imgurl;
//        this.name = name;
//        this.rank = rank;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}