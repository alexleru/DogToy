package ru.alexleru.dogtoy.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private static final String baseUrl = "http://www.alexleru.h1n.ru/dogtoy/";
    private Retrofit retrofit;
    private APIService API;

    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API = retrofit.create(APIService.class);
    }

    public static NetworkService getNetworkService(){
        if(networkService == null){
            networkService = new NetworkService();
        }
        return networkService;
    }

    public APIService getAPI(){
        return API;
    }
}
