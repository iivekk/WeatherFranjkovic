package ivan.franjkovic.weatherfranjkovic.mvp.five_day_weather_forecast;

import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.five_day_weather_forecast.ResultForFiveDayForecast;
import ivan.franjkovic.weatherfranjkovic.restapi.WeatherApiClient;
import ivan.franjkovic.weatherfranjkovic.restapi.WeatherApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.API_KEY;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.BASE_URL;

public class ModelFiveDay implements ContractFiveDay.FiveDayModel {

    WeatherApiService service = WeatherApiClient.getClient(BASE_URL).create(WeatherApiService.class);

    @Override
    public void getResult(final OnFinishedListener onFinishedListener, double lat, double lon, String cityName) {

        Call<ResultForFiveDayForecast> call;

        if (cityName != null) {
            call = service.FIVE_DAY_FORECAST_BY_CITY_NAME_CALL(cityName, API_KEY);
        } else {
            call = service.FIVE_DAY_FORECAST_BY_LOCATION_CALL(lat, lon, API_KEY);
        }

        if (call != null) {
            call.enqueue(new Callback<ResultForFiveDayForecast>() {
                @Override
                public void onResponse(Call<ResultForFiveDayForecast> call, Response<ResultForFiveDayForecast> response) {
                    if (response.body() != null) {
                        onFinishedListener.onFinished(response.body());
                    } else {
                        onFinishedListener.onResponseMessage(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResultForFiveDayForecast> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }
    }
}
