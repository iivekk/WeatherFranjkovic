package ivan.franjkovic.weatherfranjkovic.mvp.current_weather_data;

import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.current_weather_data.ResultForCurrentWeatherData;

public interface ContractCurrent {

    interface CurrentWeatherModel {

        interface OnFinishedListener {

            void onFinished(ResultForCurrentWeatherData result);

            void onFailure(Throwable t);

            void onResponseMessage(String message);

        }

        void getResult(OnFinishedListener onFinishedListener, double lat, double lon, String cityName);

    }

    interface CurrentWeatherView {

        void setLocationName(String locationName);

        void setTempRange(String[] tempRange);

        void setIcon(String iconUrl);

        void setWeatherDescription(String weatherDescription);

        void setHumidity(String humidity);

        void setWindSpeed(String windSpeed);

        void setSunriseSunset(String sunriseSunset);

        void onResponseMessage(String message);

        void onResponseFailure(String failureMessage);

    }

    interface CurrentWeatherPresenter {

        void onDestroy();

        void requestDataFromServer(double lat, double lon, String cityName);

    }

}
