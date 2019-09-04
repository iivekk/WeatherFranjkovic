package ivan.franjkovic.weatherfranjkovic.restapi;

import ivan.franjkovic.weatherfranjkovic.youtubeapi_models.YouTubeResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeApiService {

    @GET("youtube/v3/search")
    Call<YouTubeResult> YOU_TUBE_RESULT_CALL(@Query("part") String part, @Query("q") String keyword, @Query("key") String apiKey);

}
