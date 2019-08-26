package ivan.franjkovic.weatherfranjkovic.mvp.current_weather_data;

import java.io.IOException;

import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.current_weather_data.ResultForCurrentWeatherData;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.*;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Methods.convertKelvinToCelsius;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Methods.integerToTime;

public class PresenterCurrent implements ContractCurrent.CurrentWeatherPresenter, ContractCurrent.CurrentWeatherModel.OnFinishedListener {

    private ContractCurrent.CurrentWeatherView view;
    private ContractCurrent.CurrentWeatherModel model;

    public PresenterCurrent(ContractCurrent.CurrentWeatherView view) {
        this.view = view;
        model = new ModelCurrent();
    }

    @Override
    public void onFinished(ResultForCurrentWeatherData result) {

        String locationName = result.getName();
        view.setLocationName(locationName);

        String temp = convertKelvinToCelsius(result.getMain().getTemp());
        String tempMax = convertKelvinToCelsius(result.getMain().getTempMax());
        String tempMin = convertKelvinToCelsius(result.getMain().getTempMin());
        view.setTempRange(new String[]{temp, tempMax, tempMin});

        String iconUrl = ICON_URL + result.getWeather().get(0).getIcon() + ".png";
        view.setIcon(iconUrl);

        String weatherDescription = result.getWeather().get(0).getDescription();
        view.setWeatherDescription(weatherDescription);

        String humidity = result.getMain().getHumidity() + " %";
        view.setHumidity(humidity);

        String windSpeed = result.getWind().getSpeed() + " m/s";
        view.setWindSpeed(windSpeed);

        String sunrise = integerToTime(result.getSys().getSunrise());
        String sunset = integerToTime(result.getSys().getSunset());
        String sunriseSunset = sunrise + " / " + sunset;
        view.setSunriseSunset(sunriseSunset);

    }

    @Override
    public void onFailure(Throwable t) {
        String failureMessage = "";
        if (t instanceof IOException){
            failureMessage = "No internet connection";
        } else {
            failureMessage = t.getMessage();
        }
        view.onResponseFailure(failureMessage);
    }

    @Override
    public void onResponseMessage(String message) {
        view.onResponseMessage(message);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void requestDataFromServer(double lat, double lon, String cityName) {
        model.getResult(this, lat, lon, cityName);
    }
}
