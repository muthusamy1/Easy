

package com.student.admin.easycalls.map;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.BuildConfig;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.student.admin.easycalls.MainActivity;
import com.student.admin.easycalls.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackerService extends Service implements LocationListener {
    private static final String TAG = TrackerService.class.getSimpleName();
    public static final String STATUS_INTENT = "status";
    public static final String LOCATION_INTENT = "location";
    private static final int NOTIFICATION_ID = 1803151152;
    private static final int FOREGROUND_SERVICE_ID = 1803151152;
    public static final String PRIMARY_NOTIF_CHANNEL = "default";
    public static final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1001;
    private GoogleApiClient mGoogleApiClient;
    private List<Double> mTransportStatuses = new ArrayList<Double>();
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mNotificationBuilder;
    private PowerManager.WakeLock mWakelock;


    public TrackerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        buildNotification();
        setStatusMessage(R.string.connecting);

        startLocationTracking();

    }


    @Override
    public void onDestroy() {
        // Set activity title to not tracking.
        setStatusMessage(R.string.not_tracking);
        // Stop the persistent notification.
        mNotificationManager.cancel(NOTIFICATION_ID);
        // Stop receiving location updates.
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,
                    TrackerService.this);
        }

        if (mWakelock != null) {
            mWakelock.release();
        }
        super.onDestroy();
    }



    private void fetchRemoteConfig() {

    }



    private GoogleApiClient.ConnectionCallbacks mLocationRequestCallback = new GoogleApiClient
            .ConnectionCallbacks() {

        @SuppressLint({"MissingPermission", "InvalidWakeLockTag"})
        @Override
        public void onConnected(Bundle bundle) {
            LocationRequest request = new LocationRequest();
            request.setInterval( 1000);
            request.setFastestInterval( 1000);
            request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    request, TrackerService.this);
            setStatusMessage(R.string.tracking);

            // Hold a partial wake lock to keep CPU awake when the we're tracking location.
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            mWakelock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyWakelockTag");
            mWakelock.acquire();
        }

        @Override
        public void onConnectionSuspended(int reason) {
            // TODO: Handle gracefully
        }
    };

    /**
     * Starts location tracking by creating a Google API client, and
     * requesting location updates.
     */
    private void startLocationTracking() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(mLocationRequestCallback)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    /**
     * Determines if the current location is approximately the same as the location
     * for a particular status. Used to check if we'll add a new status, or
     * update the most recent status of we're stationary.
     */


    private float getBatteryLevel() {
        Intent batteryStatus = registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int batteryLevel = -1;
        int batteryScale = 1;
        if (batteryStatus != null) {
            batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, batteryLevel);
            batteryScale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, batteryScale);
        }
        return batteryLevel / (float) batteryScale * 100;
    }

///

    private void shutdownAndScheduleStartup(int when) {


        stopSelf();
    }

    /**
     * Pushes a new status to Firebase when location changes.
     */
    @Override
    public void onLocationChanged(Location location) {

        fetchRemoteConfig();

        long hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        Map<String, Object> transportStatus = new HashMap<>();
        transportStatus.put("lat", location.getLatitude());
        transportStatus.put("long", location.getLongitude());
        transportStatus.put("time", new Date().getTime());
        transportStatus.put("power", getBatteryLevel());

        System.out.println("connectiondfgsssssssssssssssssssssssssssssssssssssssssssss");
              System.out.println(location);

        setLocationMessage(mTransportStatuses);
        if (BuildConfig.DEBUG) {
//            logStatusToStorage(transportStatus);
        }

        NetworkInfo info = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        boolean connected = info != null && info.isConnectedOrConnecting();
        setStatusMessage(connected ? R.string.tracking : R.string.not_tracking);
    }

    private void buildNotification() {
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                .setContentTitle(getString(R.string.app_name))
                .setOngoing(true)
                .setContentIntent(resultPendingIntent);
        startForeground(FOREGROUND_SERVICE_ID, mNotificationBuilder.build());

        startForeground(1, mNotificationBuilder.build());
       // startForeground(1, mNotificationBuilder.build());
        Notification notification = new NotificationCompat.Builder(this, PRIMARY_NOTIF_CHANNEL)
                .setSmallIcon( R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .build();

        startForeground(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);

    }

    /**
     * Sets the current status message (connecting/tracking/not tracking).
     */
    private void setStatusMessage(int stringId) {

        mNotificationBuilder.setContentText(getString(stringId));
        mNotificationManager.notify(NOTIFICATION_ID, mNotificationBuilder.build());

        // Also display the status message in the activity.
        Intent intent = new Intent(STATUS_INTENT);
        intent.putExtra(getString(R.string.status), stringId);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void setLocationMessage(List<Double> location) {
        // Also display the status message in the activity.
        Intent intent = new Intent(LOCATION_INTENT);
        intent.putExtra("lat",  "7876");
        intent.putExtra( "long", "tyutu");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}
