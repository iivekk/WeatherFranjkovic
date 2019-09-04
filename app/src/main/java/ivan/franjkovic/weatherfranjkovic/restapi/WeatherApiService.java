package ivan.franjkovic.weatherfranjkovic.restapi;

import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.current_weather_data.ResultForCurrentWeatherData;
import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.five_day_weather_forecast.ResultForFiveDayForecast;
import ivan.franjkovic.weatherfranjkovic.youtubeapi_models.YouTubeResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("data/2.5/weather")
    Call<ResultForCurrentWeatherData> CURRENT_WEATHER_BY_LOCATION_CALL(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);

    @GET("data/2.5/weather")
    Call<ResultForCurrentWeatherData> CURRENT_WEATHER_BY_CITY_NAME_CALL(@Query("q") String cityName, @Query("appid") String apiKey);

    @GET("data/2.5/forecast")
    Call<ResultForFiveDayForecast> FIVE_DAY_FORECAST_BY_LOCATION_CALL(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);

    @GET("data/2.5/forecast")
    Call<ResultForFiveDayForecast> FIVE_DAY_FORECAST_BY_CITY_NAME_CALL(@Query("q") String cityName, @Query("appid") String apiKey);


}
