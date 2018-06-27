package com.zarmada.zarmadatest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceConnectionAdministrator {

    APIServices api;
    Retrofit retrofit;

    public ServiceConnectionAdministrator() {
        this.initializeRetrofitInstance();
    }

    private void initializeRetrofitInstance() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        retrofit = (new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build());


        this.api = this.retrofit.create(APIServices.class);

    }


    public void addSurvey(Survey survey, final ResponseListener listener) {

        Call<Void> call = this.api.addSurveys(survey);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.throwResponse(true);
                } else {
                    listener.throwError(false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.throwError(false);
            }
        });

    }


    public void getSurveys(final ResponseListener listener) {

        Call<List<Survey>> call = this.api.getSurveys();
        call.enqueue(new Callback<List<Survey>>() {
            @Override
            public void onResponse(Call<List<Survey>> call, Response<List<Survey>> response) {

                if(response.isSuccessful()) {
                    listener.throwResponse(response.body());
                } else {
                    listener.throwError(false);
                }
            }

            @Override
            public void onFailure(Call<List<Survey>> call, Throwable t) {
                listener.throwError(false);
            }
        });
    }


    interface ResponseListener {
        void throwResponse(Object obj);
        void throwError(Object obj);
    }
}
