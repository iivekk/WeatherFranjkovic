package ivan.franjkovic.weatherfranjkovic.mvp.you_tube_api;

import ivan.franjkovic.weatherfranjkovic.restapi.YouTubeApiClient;
import ivan.franjkovic.weatherfranjkovic.restapi.YouTubeApiService;
import ivan.franjkovic.weatherfranjkovic.youtubeapi_models.YouTubeResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.*;

public class ModelYTApi implements ContractYTApi.ModelYTA {

    YouTubeApiService service = YouTubeApiClient.getClient().create(YouTubeApiService.class);

    @Override
    public void getResult(OnFinishedListener listener, String keyWord) {
        Call<YouTubeResult> call = service.YOU_TUBE_RESULT_CALL("id", keyWord, YT_API_KEY);
        call.enqueue(new Callback<YouTubeResult>() {
            @Override
            public void onResponse(Call<YouTubeResult> call, Response<YouTubeResult> response) {
                if (response.body() != null) {
                    listener.onFinished(response.body());
                }
            }

            @Override
            public void onFailure(Call<YouTubeResult> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
