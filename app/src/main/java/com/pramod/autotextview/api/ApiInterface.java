package com.pramod.autotextview.api;




import com.pramod.autotextview.RegionRes;
import com.pramod.autotextview.districtRes.DistrictRes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @GET("https://ferari.ifdc.org/apicontroller/GetRegionName")
    Call<RegionRes> getRegion();





    @POST("GetDistrictName")
    @FormUrlEncoded
    Call<DistrictRes> getdistrict(@Field("region_id") String region_id);
}

