package com.example.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.List;

public class MyLocationProvider {
    public static final long MINIMUM_TIME_BETWEEN_UPDATE = 5 * 1000;
    public static final long MINIMUM_DISTANCE_BETWEEN_UPDATE = 10;
    LocationManager locationManager;
    Location location;

    public MyLocationProvider(Context context) {
        locationManager = ((LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE));
        location = null;
    }

    public boolean canGetLocation() {
        boolean GPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return GPSEnable || networkEnable;
    }

    @SuppressLint("MissingPermission")
    public Location getCurrentLocation(LocationListener locationListener) {
        if (!canGetLocation()) {
            return null;
        }
        String provider = LocationManager.GPS_PROVIDER;
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            provider = LocationManager.NETWORK_PROVIDER;
        location = locationManager.getLastKnownLocation(provider);
        if (location == null) {
            location = getBestLastKnownLocation();
        }
        if (locationListener != null) {
            locationManager.requestLocationUpdates(provider,
                    MINIMUM_TIME_BETWEEN_UPDATE, MINIMUM_DISTANCE_BETWEEN_UPDATE, locationListener);
        }
        return location;
    }

    @SuppressLint("MissingPermission")
    private Location getBestLastKnownLocation() {
        List<String> providers = locationManager.getAllProviders();
        Location bestLocation = null;
        for (String provider : providers) {
            Location temp = locationManager.getLastKnownLocation(provider);
            if (temp == null) continue;
            if (bestLocation == null)
                bestLocation = temp;
            else {
                if (temp.getAccuracy() > bestLocation.getAccuracy())
                    bestLocation = temp;
            }
        }
        return bestLocation;
    }
}
