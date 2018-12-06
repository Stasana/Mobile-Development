package com.melnyk.stanislav.melnyk.Retrofit;

public class ApiUtil {
    public static final String BASE_URL = "https://api.itbook.store/1.0/";

    public static APIServise getSOService() {
        return ApiClient.getClient(BASE_URL).create(APIServise.class);
    }
}