package org.howcompany.mytown;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/codes/")
    Call<Mytown> inputCode(@Query("city_code") String city, @Query("shop_code") String shop, @Query("phone") String phone, @Query("code") String code);
}
