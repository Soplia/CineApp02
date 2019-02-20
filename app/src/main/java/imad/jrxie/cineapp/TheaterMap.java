package imad.jrxie.cineapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class TheaterMap extends FragmentActivity
{
    // Googleplex经纬度
    private final LatLng googleLatLng = new LatLng(45.7567654, 3.1441095);
    public String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.theaterMap);

        if(mapFragment == null)
        {
            Log.d(TAG, "mapFragment is null");

        }
        else
        {
            Log.d(TAG, "mapFragment is not null");
            mapFragment.getMapAsync(onMapReadyCallback);
        }


    }

    protected OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback()
    {
        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            if (ContextCompat.checkSelfPermission(TheaterMap.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(TheaterMap.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0x01);
            }
            else
            {
                googleMap.addMarker(new MarkerOptions().position(googleLatLng)
                        .title(String.valueOf(R.string.TopTitle)));
                //移动摄像头
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(googleLatLng, 13));

                googleMap.setMyLocationEnabled(true);

            }
        }

    };


}