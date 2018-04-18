package com.earthsilen.depthfirsttraineeproject.Models;

import java.io.Serializable;

public class LeaveModels implements Serializable {
    private String reason;
    private String details;
    private String fromDate;
    private String toDate;


    /**public DepthfirstNewsModels(String imgurl, String desc) {
     this.imgurl = imgurl;
     this.desc = desc;

     }**/

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}