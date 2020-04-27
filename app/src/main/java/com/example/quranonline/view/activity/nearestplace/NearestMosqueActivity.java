package com.example.quranonline.view.activity.nearestplace;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quranonline.R;
import com.example.quranonline.data.model.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.quranonline.data.local.Constants.KEY;
import static com.example.quranonline.data.local.Constants.NAME;
import static com.example.quranonline.data.local.Constants.TYPE;

public class NearestMosqueActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng mylocation;
    NearestMosqueViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_mosque);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        viewModel = ViewModelProviders.of(this).get(NearestMosqueViewModel.class);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        CheckUserPermsions();
//        setMapStyle( googleMap);



    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void disaplyMyLocationOnMap() {


        LocationManager lm = (LocationManager) NearestMosqueActivity.this.getSystemService(LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager. NETWORK_PROVIDER, 1000, 100, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mylocation = new LatLng(location.getLatitude(),location.getLongitude());
                Log.d("LOCATION",mylocation.toString());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(mylocation)
                        .title("")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                mMap.addCircle(new CircleOptions().radius(300)
                        .center(mylocation).strokeColor(Color.BLUE)
                        .fillColor(Color.parseColor("#500084d3"))
                        .strokeWidth(2));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 15.0f));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 10.0f));
                fetNearest(location.getLatitude()+","+location.getLongitude() , 5000 ,TYPE , NAME , KEY);


            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });


    }
    public void setMapStyle(GoogleMap map)
    {
        try{
            boolean success = map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,R.raw.style));
        }
        catch (Exception e)
        {

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    void CheckUserPermsions(){
        if ( Build.VERSION.SDK_INT >= 23){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED  ){
                requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return ;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            disaplyMyLocationOnMap();
        }

    }
    //get acces to location permsion
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    disaplyMyLocationOnMap();
                } else {
                    // Permission Denied
                    Toast.makeText( this,"your message" , Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void  fetNearest(String latLng ,int r , String type , String name , String key)
    {
        try {
                viewModel.getNearestData(
                        latLng , r ,type , name , key
                );
                viewModel.data.observe(this, new Observer<Places>() {
                    @Override
                    public void onChanged(Places places) {
                        for (int i = 0; i < places.getResults().size(); i++) {
                            double lat = places.getResults().get(i).getGeometry().getLocation().getLat();
                            double lang = places.getResults().get(i).getGeometry().getLocation().getLng();
                            LatLng latLng = new LatLng(lat , lang);
                            mMap.addMarker(new MarkerOptions().position(latLng)
                                    .title("")
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                            //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 15.0f));
                            Toast.makeText(NearestMosqueActivity.this, "added"+latLng, Toast.LENGTH_SHORT).show();


                        }
                    }
                });


        }
        catch (Exception e)
        {

        }
    }


}
