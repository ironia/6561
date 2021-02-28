package org.howcompany.mytown;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FindUserApiService {
    @GET("/users/")
    Call<FindUser> findUser(@Query("phone") String phone);
}
