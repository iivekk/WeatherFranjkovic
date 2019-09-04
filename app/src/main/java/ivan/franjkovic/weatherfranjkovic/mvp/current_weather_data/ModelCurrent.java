package ivan.franjkovic.weatherfranjkovic.mvp.current_weather_data;

import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.current_weather_data.ResultForCurrentWeatherData;
import ivan.franjkovic.weatherfranjkovic.restapi.WeatherApiClient;
import ivan.franjkovic.weatherfranjkovic.restapi.WeatherApiService;
import ivan.franjkovic.weatherfranjkovic.tools.Tools;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.API_KEY;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.BASE_URL;

public class ModelCurrent implements ContractCurrent.CurrentWeatherModel {

    WeatherApiService service = WeatherApiClient.getClient(BASE_URL).create(WeatherApiService.class);

    @Override
    public void getResult(final OnFinishedListener onFinishedListener, double lat, double lon, String cityName) {

        Call<ResultForCurrentWeatherData> call;

        if (cityName != null) {
            call = service.CURRENT_WEATHER_BY_CITY_NAME_CALL(cityName, API_KEY);
        } else {
            call = service.CURRENT_WEATHER_BY_LOCATION_CALL(lat, lon, API_KEY);
        }

        if (call != null) {
            call.enqueue(new Callback<ResultForCurrentWeatherData>() {
                @Override
                public void onResponse(Call<ResultForCurrentWeatherData> call, Response<ResultForCurrentWeatherData> response) {
                    if (response.body() != null) {
                        onFinishedListener.onFinished(response.body());
                    } else {
                        onFinishedListener.onResponseMessage(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResultForCurrentWeatherData> call, Throwable t) {
                    onFinishedListener.onFailure(t);
                }
            });
        }
    }
}
