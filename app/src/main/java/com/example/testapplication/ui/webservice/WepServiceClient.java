package com.example.testapplication.ui.webservice;

import com.example.testapplication.ui.Utitle.Constants;
import com.example.testapplication.ui.Utitle.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.testapplication.ui.Utitle.Constants.CONNECTION_TIMEOUT;
import static com.example.testapplication.ui.Utitle.Constants.READ_TIMEOUT;
import static com.example.testapplication.ui.Utitle.Constants.WRITE_TIMEOUT;

public class WepServiceClient {
    private static OkHttpClient client = new OkHttpClient.Builder()

            // establish connection to server
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte read from the server
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte sent to server
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

            .retryOnConnectionFailure(false)

            .build();


    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ApiService apiService = retrofit.create(ApiService.class);

    public static ApiService getRecipeApi(){
        return apiService;
    }

}
