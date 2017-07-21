package com.example.moyeit.moyeitapp.Service;

/**
 * Created by alice on 2017-07-21.
 */

public interface MoyeITServerService {

    @FormUrlEncoded
    @POST("user/login")
    Call<UserDto> login(@Field("email") String id, @Field("pw") String password);

}
