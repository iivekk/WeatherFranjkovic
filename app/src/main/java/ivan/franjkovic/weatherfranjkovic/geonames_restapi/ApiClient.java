package ivan.franjkovic.weatherfranjkovic.geonames_restapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.GEONAMES_BASE_URL;

public class ApiClient {

    private static Retrofit mRetrofit = null;

    public static Retrofit getClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(GEONAMES_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
