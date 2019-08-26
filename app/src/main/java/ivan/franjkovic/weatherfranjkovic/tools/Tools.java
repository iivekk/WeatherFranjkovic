package ivan.franjkovic.weatherfranjkovic.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static class Methods {

        public static String convertKelvinToCelsius(double kelvin) {
            double celsius = kelvin - 273.15f;
            return Math.round(celsius) + "°";
        }

        public static String integerToTime(int time) {
            Date d = new Date();
            d.setTime((long) time * 1000);
            return new SimpleDateFormat("HH:mm").format(d);
        }

        public static String returnDayOfWeek(int time) {
            Date d = new Date();
            d.setTime((long) time * 1000);
            return new SimpleDateFormat("EEEE").format(d);
        }

    }

    public static class Constants {
        public static final String BASE_URL = "https://api.openweathermap.org";
        public static final String API_KEY = "1864201178f67faa020bc4669244ae06";
        public static final int REQUEST_LOCATION_PERMISSION = 101;

        public static final String ICON_URL = "https://openweathermap.org/img/w/";
    }
}
