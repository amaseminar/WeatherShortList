package ro.marianperca.weathershortlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    ArrayList<WeatherDay> forecast = new ArrayList<>();
    LinearLayout forecastContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forecastContainer = findViewById(R.id.forecast_container);

        populateForecast();

        SimpleDateFormat formatDate = new SimpleDateFormat("EEEE", Locale.ENGLISH);

        for (WeatherDay weatherDay : forecast) {
            View weatherView = LayoutInflater.from(this).inflate(R.layout.weather_day, forecastContainer, false);
            TextView day = weatherView.findViewById(R.id.day);
            TextView tempLow = weatherView.findViewById(R.id.temp_low);
            TextView tempHigh = weatherView.findViewById(R.id.temp_high);
            ImageView icon = weatherView.findViewById(R.id.icon);

            day.setText(formatDate.format(weatherDay.date));
            tempLow.setText(weatherDay.min + " \u00B0");
            tempHigh.setText(weatherDay.max + " \u00B0");

            Glide.with(this).load(weatherDay.icon).into(icon);

            forecastContainer.addView(weatherView);
        }
    }

    private void populateForecast() {
        Calendar c = Calendar.getInstance();
        forecast.add(new WeatherDay(c.getTime(), 8, 18, "https://www.weatherbit.io/static/img/icons/r03d.png"));

        c.add(Calendar.DATE, 1);
        forecast.add(new WeatherDay(c.getTime(), 9, 16, "https://www.weatherbit.io/static/img/icons/r05d.png"));

        c.add(Calendar.DATE, 1);
        forecast.add(new WeatherDay(c.getTime(), 10, 17, "https://www.weatherbit.io/static/img/icons/r03d.png"));

        c.add(Calendar.DATE, 1);
        forecast.add(new WeatherDay(c.getTime(), 12, 22, "https://www.weatherbit.io/static/img/icons/c02d.png"));

        c.add(Calendar.DATE, 1);
        forecast.add(new WeatherDay(c.getTime(), 8, 19, "https://www.weatherbit.io/static/img/icons/c03d.png"));
    }
}
