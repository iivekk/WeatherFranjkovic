package ivan.franjkovic.weatherfranjkovic.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherApiClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
