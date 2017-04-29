package com.example.wojtekmac.tempwidget1;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import com.example.wojtekmac.tempwidget1.MainActivity;
import com.example.wojtekmac.tempwidget1.R;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static android.os.Build.VERSION_CODES.N;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
    private WeatherData weatherData = new WeatherData();


    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            Log.i("aaa","entered once");
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch ExampleActivity

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            weatherData.updateWeatherDataSync();
            Log.i("AAA", weatherData.getOutTemp());
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);
            views.setTextViewText(R.id.textView, weatherData.getOutTemp());
            views.setTextViewText(R.id.textViewDate, weatherData.getDate());


            Intent intent = new Intent(context, ExampleAppWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);

        }
    }
}