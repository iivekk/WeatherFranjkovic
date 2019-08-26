package ivan.franjkovic.weatherfranjkovic.mvp.five_day_weather_forecast;

import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.five_day_weather_forecast.ResultForFiveDayForecast;

public interface ContractFiveDay {

    interface FiveDayModel {

        interface OnFinishedListener {

            void onFinished(ResultForFiveDayForecast result);

            void onFailure(Throwable t);

            void onResponseMessage(String message);

        }

        void getResult(OnFinishedListener onFinishedListener, double lat, double lon, String cityName);

    }

    interface FiveDayView {

        void setDayOfWeek(String[] dayOfWeek);

        void setIconFiveDay(String[] iconUrl);

        void setTempRangeFiveDay(String[] tempRange);

        void onResponseMessageFiveDay(String message);

        void onResponseFailureFiveDay(String failureMessage);

    }

    interface FiveDayPresenter {

        void onDestroyFiveDay();

        void requestDataFromServerFiveDay(double lat, double lon, String cityName);

    }

}
