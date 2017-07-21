package com.example.moyeit.moyeitapp.activity;

/**
 * Created by alice on 2017-07-21.
 */

public class ExampleActivity {
        /*
            server api 호출
         */
        //테스트
    Call<RestaurantDetailDto> callRestaurantDetailInfo = kindPickyEactingService.restaurantDetailInfo(restaurantId);

        callRestaurantDetailInfo.enqueue(new Callback<RestaurantDetailDto>() {
        @Override
        public void onResponse(Call<RestaurantDetailDto> call, Response<RestaurantDetailDto> response) {
            Log.i("test1", response.body().getName());
            restaurantName.setText(response.body().getName());
            restaurantTel.setText(response.body().getTel());
            restaurantAddress.setText(response.body().getAddress());
            Glide.with(getApplicationContext()).load(response.body().getImage()).into(restaurantImage);
            setupAdapter(response.body());

        }

        @Override
        public void onFailure(Call<RestaurantDetailDto> call, Throwable t) {

        }
    });
}
