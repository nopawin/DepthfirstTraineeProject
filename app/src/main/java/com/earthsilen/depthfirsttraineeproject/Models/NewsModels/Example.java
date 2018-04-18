
package com.earthsilen.depthfirsttraineeproject.Models.NewsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

}
