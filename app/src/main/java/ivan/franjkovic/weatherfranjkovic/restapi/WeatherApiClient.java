package ivan.franjkovic.weatherfranjkovic.restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.BASE_URL;

public class WeatherApiClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
