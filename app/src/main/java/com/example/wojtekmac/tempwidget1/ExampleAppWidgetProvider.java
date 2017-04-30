package com.example.wojtekmac.tempwidget1;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import com.example.wojtekmac.tempwidget1.MainActivity;
import com.example.wojtekmac.tempwidget1.R;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static android.os.Build.VERSION_CODES.N;
import static java.security.AccessController.getContext;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
    private WeatherData weatherData = new WeatherData();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Log.i("AAA", "Updating widget now");
            weatherData.updateWeatherDataSync();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), ExampleAppWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            Log.i("aaa","entered once " + i);
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
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.actionButton, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);

        }
    }
}