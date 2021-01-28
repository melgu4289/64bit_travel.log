package com.melgu.travellog;

import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    ArrayList<LatLng> points;
    double lat,longitude;
    String newtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        points = new ArrayList<LatLng>();
        points.add(new LatLng(21.114369, 79.049423));
        points.add(new LatLng(21.113913, 79.049203));
        points.add(new LatLng(21.113478, 79.048736));
        points.add(new LatLng(21.113002, 79.048592));
        points.add(new LatLng(21.112857, 79.047315));
        points.add(new LatLng(21.112997, 79.046741));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("dd-MM-yy HH:mm:ss", Locale.US);
        newtime = sdfDateTime.format(new Date(System.currentTimeMillis()));
    }

    View marker_root_view;
    TextView tv_marker;
    private void setCustomMarkerView(){
        marker_root_view = LayoutInflater.from(this).inflate(R.layout.marker_custom,null);
        tv_marker = (TextView) marker_root_view.findViewById(R.id.marker_custom);
    }

    private Bitmap createDrawableFromView(Context context, View view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels,displayMetrics.heightPixels);
        view.layout(0,0,displayMetrics.widthPixels,displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),view.getMeasuredHeight(),Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    private void drawMarker(ArrayList<LatLng> l) {
        // Creating an instance of MarkerOptions

        PolylineOptions options = new PolylineOptions();
        options.color(Color.parseColor("#CDCDCD"));

        for (int i = 0; i < l.size(); i++) {
            options.add(l.get(i));
            MarkerOptions marker = new MarkerOptions().position(l.get(i)).title("Bus")
                    .snippet(newtime);
            marker.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker_root_view)));

            // Adding marker on the Google Map
            mMap.addMarker(marker);
        }

        mMap.addPolyline(options);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(l.get(0).latitude, l.get(0).longitude)).zoom(18).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setCustomMarkerView();
        // Add a marker in Sydney and move the camera
        drawMarker(points);
    }
}