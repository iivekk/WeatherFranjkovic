package ivan.franjkovic.weatherfranjkovic.mvp.geonames_api;

import ivan.franjkovic.weatherfranjkovic.geonames_api_models.SearchResult;
import ivan.franjkovic.weatherfranjkovic.geonames_restapi.ApiClient;
import ivan.franjkovic.weatherfranjkovic.geonames_restapi.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.LANGUAGE;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.MAX_ROWS;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.USERNAME;

public class Model implements Contract.Model {

    ApiService service = ApiClient.getClient().create(ApiService.class);

    @Override
    public void getResult(OnFinishedListener onFinishedListener, String query) {
        Call<SearchResult> resultCall = service.SEARCH_RESULT_CALL(query, MAX_ROWS, LANGUAGE, USERNAME);
        resultCall.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.body() != null) {
                    onFinishedListener.onFinished(response.body());
                    onFinishedListener.message(response.code() + " - " + response.message()
                            + "\n" + response.raw().request().url());
                } else {
                    onFinishedListener.message(response.code() + response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                onFinishedListener.onFailure(t);
                onFinishedListener.message(t.getMessage());
            }
        });
    }
}
