package ivan.franjkovic.weatherfranjkovic.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import ivan.franjkovic.weatherfranjkovic.R;
import ivan.franjkovic.weatherfranjkovic.mvp.current_weather_data.ContractCurrent;
import ivan.franjkovic.weatherfranjkovic.mvp.current_weather_data.PresenterCurrent;
import ivan.franjkovic.weatherfranjkovic.mvp.five_day_weather_forecast.ContractFiveDay;
import ivan.franjkovic.weatherfranjkovic.mvp.five_day_weather_forecast.PresenterFiveDay;
import ivan.franjkovic.weatherfranjkovic.tools.Tools;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.REQUEST_LOCATION_PERMISSION;


public class MainActivity extends BaseActivity implements ContractCurrent.CurrentWeatherView, ContractFiveDay.FiveDayView {

    @BindView(R.id.tv_current_temp)
    TextView tvTempCurrent;

    @BindView(R.id.iv_weather_image)
    ImageView ivWeatherImage;

    @BindView(R.id.tv_temp_max)
    TextView tvTempMax;

    @BindView(R.id.tv_temp_min)
    TextView tvTempMin;

    @BindView(R.id.tv_weather_description)
    TextView tvWeatherDescription;

    @BindView(R.id.tv_humidity)
    TextView tvHumidity;

    @BindView(R.id.tv_wind_speed)
    TextView tvWindSpeed;

    @BindView(R.id.tv_sunrise_sunset)
    TextView tvSunriseSunset;

    @BindView(R.id.tv_day_of_week)
    TextView tvDayOfWeek;

    @BindView(R.id.iv_daily_forecast_icon)
    ImageView ivDailyForecastIcon;

    @BindView(R.id.tv_daily_temp_range)
    TextView tvDailyTempRange;

    @BindView(R.id.tv_day_of_week1)
    TextView tvDayOfWeek1;

    @BindView(R.id.iv_daily_forecast_icon1)
    ImageView ivDailyForecastIcon1;

    @BindView(R.id.tv_daily_temp_range1)
    TextView tvDailyTempRange1;

    @BindView(R.id.tv_day_of_week2)
    TextView tvDayOfWeek2;

    @BindView(R.id.iv_daily_forecast_icon2)
    ImageView ivDailyForecastIcon2;

    @BindView(R.id.tv_daily_temp_range2)
    TextView tvDailyTempRange2;

    private FusedLocationProviderClient client;
    private PresenterCurrent presenterCurrent;
    private PresenterFiveDay presenterFiveDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        ButterKnife.bind(this, contentView);
        contentFrame.addView(contentView, 0);

        presenterCurrent = new PresenterCurrent(this);
        presenterFiveDay = new PresenterFiveDay(this);

        client = LocationServices.getFusedLocationProviderClient(this);
        getLastKnownLocation();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastKnownLocation();
                }
            }
        }
    }

    @Override
    public void setLocationName(String locationName) {
        tvLocationBase.setText(locationName);
    }

    @Override
    public void setTempRange(String[] tempRange) {
        tvTempCurrent.setText(tempRange[0]);
        tvTempMax.setText(tempRange[1]);
        tvTempMin.setText(tempRange[2]);
    }

    @Override
    public void setIcon(String iconUrl) {
        Picasso.get().load(iconUrl).into(ivWeatherImage);
    }

    @Override
    public void setWeatherDescription(String weatherDescription) {
        tvWeatherDescription.setText(weatherDescription);
    }

    @Override
    public void setHumidity(String humidity) {
        tvHumidity.setText(humidity);
    }

    @Override
    public void setWindSpeed(String windSpeed) {
        tvWindSpeed.setText(windSpeed);
    }

    @Override
    public void setSunriseSunset(String sunriseSunset) {
        tvSunriseSunset.setText(sunriseSunset);
    }

    @Override
    public void onResponseMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseFailure(String failureMessage) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterCurrent.onDestroy();
        presenterFiveDay.onDestroyFiveDay();
    }

    private void getLastKnownLocation() {
        if (client != null) {
            checkPermission();
            client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location == null) {
                        return;
                    }
                    presenterCurrent.requestDataFromServer(location.getLatitude(), location.getLongitude(), null);
                    presenterFiveDay.requestDataFromServerFiveDay(location.getLatitude(), location.getLongitude(), null);
                }
            });
        }
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void setDayOfWeek(String[] dayOfWeek) {
        tvDayOfWeek.setText(dayOfWeek[0]);
        tvDayOfWeek1.setText(dayOfWeek[1]);
        tvDayOfWeek2.setText(dayOfWeek[2]);
    }

    @Override
    public void setIconFiveDay(String[] iconUrl) {
        Picasso.get().load(iconUrl[0]).into(ivDailyForecastIcon);
        Picasso.get().load(iconUrl[1]).into(ivDailyForecastIcon1);
        Picasso.get().load(iconUrl[2]).into(ivDailyForecastIcon2);
    }

    @Override
    public void setTempRangeFiveDay(String[] tempRange) {
        tvDailyTempRange.setText(tempRange[0]);
        tvDailyTempRange1.setText(tempRange[1]);
        tvDailyTempRange2.setText(tempRange[2]);
    }

    @Override
    public void onResponseMessageFiveDay(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseFailureFiveDay(String failureMessage) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_SHORT).show();
    }

    public void getWeather(String cityName) {
        presenterCurrent.requestDataFromServer(0, 0, cityName);
        presenterFiveDay.requestDataFromServerFiveDay(0, 0, cityName);
    }
}
