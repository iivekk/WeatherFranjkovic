
package ivan.franjkovic.weatherfranjkovic.open_weather_api_models.five_day_weather_forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultForFiveDayForecast {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<FiveDayForecastList> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<FiveDayForecastList> getList() {
        return list;
    }

    public void setList(java.util.List<FiveDayForecastList> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
