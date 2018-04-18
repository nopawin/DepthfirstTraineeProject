package com.earthsilen.depthfirsttraineeproject.Models.NewsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("tag")
    @Expose
    private Object tag;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("platform")
    @Expose
    private Object platform;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("newsDateLabel")
    @Expose
    private String newsDateLabel;
    @SerializedName("createdDateLabel")
    @Expose
    private String createdDateLabel;
    @SerializedName("lastUpdBy")
    @Expose
    private String lastUpdBy;
    @SerializedName("createdName")
    @Expose
    private String createdName;
    @SerializedName("provinceCode")
    @Expose
    private String provinceCode;
    @SerializedName("provinceName")
    @Expose
    private String provinceName;
    @SerializedName("imageList")
    @Expose
    private List<ImageList> imageList = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getPlatform() {
        return platform;
    }

    public void setPlatform(Object platform) {
        this.platform = platform;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getNewsDateLabel() {
        return newsDateLabel;
    }

    public void setNewsDateLabel(String newsDateLabel) {
        this.newsDateLabel = newsDateLabel;
    }

    public String getCreatedDateLabel() {
        return createdDateLabel;
    }

    public void setCreatedDateLabel(String createdDateLabel) {
        this.createdDateLabel = createdDateLabel;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<ImageList> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageList> imageList) {
        this.imageList = imageList;
    }

}
