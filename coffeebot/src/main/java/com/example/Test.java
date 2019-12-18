package com.example;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ciwbm on 5/14/17.
 */

public class Test {

   public static  String tomorrowWeather;
    public static void main(String[]args)
    {
        String speechText = requestMakeCoffee();
        System.out.println(speechText);
    }

    private static String requestMakeCoffee() {
        String coffeeStatus;

        RetrofitClient client = new RetrofitClient();

        Call<String> call = client.getApi().makeCoffee();
        Response<String> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isSuccessful()) {
            // request successful (status code 200, 201)

            coffeeStatus="Your coffee is ready";
            // publish the post added event

        } else {
            // request not successful (like 400,401,403 etc and 5xx)
            coffeeStatus="There is currently some problem with Coffee Machine ,Please try again later";

        }

        return coffeeStatus;
    }


}
