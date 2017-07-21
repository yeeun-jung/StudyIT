package com.example.moyeit.moyeitapp.Network;

import android.content.Context;

/**
 * Created by alice on 2017-07-21.
 */

public class MoyeITServerClient {

    public MoyeITServerClient() {
        super();
    }

    public MoyeITServerClient(Context context){
        this.context = context;
        init();
    }

    private Context context;
    private String BASE_URL;
    private MoyeITServerService apiService;

    public void init(){
        BASE_URL = context.getResources().getString("http://13.124.67.227:22/moyeit/");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(MoyeITServerService.class);

    }

    public MoyeITServerService MoyeITService(){
        return apiService;
    }
}