package imad.jrxie.cineapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
            //添加标记
            googleMap.addMarker(new MarkerOptions().position(googleLatLng)
                    .title(String.valueOf(R.string.TopTitle)));
            //移动摄像头
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(googleLatLng, 13));

            googleMap.setMyLocationEnabled(true);
        }
    };
}