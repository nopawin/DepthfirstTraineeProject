
package com.earthsilen.depthfirsttraineeproject.Models.DoListModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Row implements Serializable {

    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("ORG_NAME")
    @Expose
    private String oRGNAME;
    @SerializedName("POSITION")
    @Expose
    private String pOSITION;
    @SerializedName("TEL")
    @Expose
    private String tEL;
    @SerializedName("IMAGE")
    @Expose
    private String iMAGE;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;

    public String getNAME() {
        return nAME;
    }

    public void setNAME(String nAME) {
        this.nAME = nAME;
    }

    public String getORGNAME() {
        return oRGNAME;
    }

    public void setORGNAME(String oRGNAME) {
        this.oRGNAME = oRGNAME;
    }

    public String getPOSITION() {
        return pOSITION;
    }

    public void setPOSITION(String pOSITION) {
        this.pOSITION = pOSITION;
    }

    public String getTEL() {
        return tEL;
    }

    public void setTEL(String tEL) {
        this.tEL = tEL;
    }

    public String getIMAGE() {
        return iMAGE;
    }

    public void setIMAGE(String iMAGE) {
        this.iMAGE = iMAGE;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

}
