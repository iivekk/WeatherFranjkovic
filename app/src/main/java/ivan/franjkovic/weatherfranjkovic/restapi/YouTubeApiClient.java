package ivan.franjkovic.weatherfranjkovic.restapi;

import ivan.franjkovic.weatherfranjkovic.tools.Tools;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YouTubeApiClient {
    private static Retrofit mRetrofit = null;

    public static Retrofit getClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Tools.Constants.YT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}

