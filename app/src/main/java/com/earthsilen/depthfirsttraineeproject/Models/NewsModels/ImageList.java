
package com.earthsilen.depthfirsttraineeproject.Models.NewsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImageList implements Serializable {

    @SerializedName("attachFileName")
    @Expose
    private String attachFileName;

    public String getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName;
    }

}
