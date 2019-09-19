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
        public static final String API_KEY = "[your api key]";
        public static final int REQUEST_LOCATION_PERMISSION = 101;

        public static final String ICON_URL = "https://openweathermap.org/img/w/";

        public static final String YT_BASE_URL = "https://www.googleapis.com";
        public static final String YT_API_KEY = "[your api key]";

        public static final String VIDEO_CODE = "VIDEO_CODE";

        // autocomplete search
        public static final String USERNAME = "iivekk";
        public static final String GEONAMES_BASE_URL = "http://api.geonames.org";
        public static final String PATH_TO_URL_CONTENT = "searchJSON";
        public static final int MAX_ROWS = 20;
        public static final String LANGUAGE = "hr";

        public static final String SELECTED_CITY = "SELECTED_CITY";

    }
}
