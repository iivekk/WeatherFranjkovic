package ivan.franjkovic.weatherfranjkovic.mvp.five_day_weather_forecast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ivan.franjkovic.weatherfranjkovic.R;
import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.five_day_weather_forecast.FiveDayForecastList;
import ivan.franjkovic.weatherfranjkovic.open_weather_api_models.five_day_weather_forecast.ResultForFiveDayForecast;
import ivan.franjkovic.weatherfranjkovic.tools.Tools;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.ICON_URL;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Methods.convertKelvinToCelsius;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Methods.integerToTime;
import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Methods.returnDayOfWeek;

public class PresenterFiveDay implements ContractFiveDay.FiveDayPresenter, ContractFiveDay.FiveDayModel.OnFinishedListener {

    private ContractFiveDay.FiveDayView view;
    private ContractFiveDay.FiveDayModel model;

    List<FiveDayForecastList> todaysForecast = new ArrayList<>();
    List<FiveDayForecastList> firstDayForecast = new ArrayList<>();
    List<FiveDayForecastList> secondDayForecast = new ArrayList<>();
    List<FiveDayForecastList> thirdDayForecast = new ArrayList<>();
    List<FiveDayForecastList> fourthDayForecast = new ArrayList<>();

    public PresenterFiveDay(ContractFiveDay.FiveDayView view) {
        this.view = view;
        model = new ModelFiveDay();
    }

    @Override
    public void onFinished(ResultForFiveDayForecast result) {

        setForecastByDay(result);

        String dayOfWeek = returnDayOfWeek(firstDayForecast.get(0).getDt());
        String dayOfWeek1 = returnDayOfWeek(secondDayForecast.get(0).getDt());
        String dayOfWeek2 = returnDayOfWeek(thirdDayForecast.get(0).getDt());
        view.setDayOfWeek(new String[]{dayOfWeek, dayOfWeek1, dayOfWeek2});

        String iconUrl = getIconForFiveDayForecast(firstDayForecast);
        String iconUrl1 = getIconForFiveDayForecast(secondDayForecast);
        String iconUrl2 = getIconForFiveDayForecast(thirdDayForecast);
        view.setIconFiveDay(new String[]{iconUrl, iconUrl1, iconUrl2});

        view.setTempRangeFiveDay(new String[]{setTempMinMax(firstDayForecast), setTempMinMax(secondDayForecast), setTempMinMax(thirdDayForecast)});
    }

    @Override
    public void onFailure(Throwable t) {
        String failureMessage = "";
        if (t instanceof IOException){
            failureMessage = "No internet connection";
        } else {
            failureMessage = t.getMessage();
        }
        view.onResponseFailureFiveDay(failureMessage);
    }

    @Override
    public void onResponseMessage(String message) {
        view.onResponseMessageFiveDay(message);
    }

    @Override
    public void onDestroyFiveDay() {
        this.view = null;
    }

    @Override
    public void requestDataFromServerFiveDay(double lat, double lon, String cityName) {
        model.getResult(this, lat, lon, cityName);
    }

    private void setForecastByDay(ResultForFiveDayForecast result) {
        if (result.getCnt() == 40) {
            for (int i = 0; i < 40; i++) {
                if (i < 8) {
                    todaysForecast.add(result.getList().get(i));
                }
                if (i >= 8 && i < 16) {
                    firstDayForecast.add(result.getList().get(i));
                }
                if (i >= 16 && i < 24) {
                    secondDayForecast.add(result.getList().get(i));
                }
                if (i >= 24 && i < 32) {
                    thirdDayForecast.add(result.getList().get(i));
                }
                if (i > 32) {
                    fourthDayForecast.add(result.getList().get(i));
                }
            }
        }
    }

    private String setTempMinMax(List<FiveDayForecastList> list) {

        List<Double> min = new ArrayList<>();
        List<Double> max = new ArrayList<>();

        for (FiveDayForecastList fl : list) {
            min.add(fl.getMain().getTempMin());
            max.add(fl.getMain().getTempMax());
        }

        String tempMax = convertKelvinToCelsius(Math.round(Collections.max(max))) + " / ";
        String tempMin = convertKelvinToCelsius(Math.round(Collections.min(min))) + "";
        return tempMax + tempMin;
    }

    private String getIconForFiveDayForecast(List<FiveDayForecastList> list) {
        String iconUrl = "";
        for (FiveDayForecastList f : list) {
            String[] s = f.getDtTxt().split("\\s+");
            if (s[1].equals("15:00:00")) {
                iconUrl = ICON_URL + f.getWeather().get(0).getIcon() + ".png";
            }
        }
        return iconUrl;
    }

}
