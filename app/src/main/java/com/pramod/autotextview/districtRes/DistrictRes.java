
package com.pramod.autotextview.districtRes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictRes {

    @SerializedName("check")
    @Expose
    private Integer check;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
