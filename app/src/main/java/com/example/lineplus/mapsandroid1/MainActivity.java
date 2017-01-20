package com.example.lineplus.mapsandroid1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener{
    private static final String TAG = "MainActivity";

    public static final String ROW_ID = "row_id";

    private Context context;
    GeofenceDBHelper mHelper;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private static final int REQUEST_CODE_LOCATION = 2000;//임의의 정수로 정의

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.activity_main);
         Log.d(TAG, "1");

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Bundle update 필요한가?
        //updateValuesFromBundle(savedInstanceState); onSaveInstanceState(Bundle savedInstanceState);

        buildGoogleApiClient();


        ListView list = (ListView)findViewById(R.id.list);
        mHelper = new GeofenceDBHelper(this);
        Cursor cursor;
        SQLiteDatabase db = mHelper.getWritableDatabase();

        //db.execSQL("INSERT INTO geo VALUES (null,'area1',null,null,null,null,null,null,null,null,null);");
        //db.execSQL("INSERT INTO geo VALUES (null,'area2',null,null,null,null,null,null,null,null,null);");

        cursor = db.rawQuery("SELECT * FROM geo",null);
        //cursor.moveToFirst();
        Log.d(TAG, cursor.getCount()+"");

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.list_item,
                cursor, new String[] {"name"},
                new int[] {R.id.geo_name}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        list.setAdapter(adapter);
        list.setOnItemClickListener(listViewItemClickListener);

    }

    private OnItemClickListener listViewItemClickListener = new OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> parentView, View clickedView, int position, long id)
        {
            Bundle extras = new Bundle();
            extras.putString("id",String.valueOf(id));

            /*
            String toastMessage = "yeeeh";

            Toast.makeText(
                    context,
                    toastMessage,
                    Toast.LENGTH_SHORT
            ).show();
            */


            Intent detailIntent = new Intent(context, DetailActivity.class);
            // context 공부!! (요소에 따라 할수 있는것이 다르다)
            detailIntent.putExtras(extras);
            startActivity(detailIntent);

        }
    };

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;//callback으로 받아온 map을 저장!!!!!!!!!!

        Log.d(TAG, "4");
    }

    protected synchronized void buildGoogleApiClient() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            //createLocationRequest();
            Log.d(TAG, "2");
        }
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
        Log.d(TAG, "3");
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
        Log.d(TAG, "stop");
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        // 권한 및 식별자(Developer > Permissions & Identifiers)
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION);
        } else{
            // Location permission has been granted, continue as usual.
            getCurrentLocation();
        }

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        //check return value is true or not
        switch (requestCode) {
            case REQUEST_CODE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    getCurrentLocation();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    private void getCurrentLocation(){

        try {//file 접근처럼 try, catch 구조로
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        }
        catch (SecurityException e){
            e.printStackTrace();
        }

        if (mLastLocation != null) {

            showCurrentLocation();
        }

    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    private void showCurrentLocation() {
        Log.d(TAG, "13");

        LatLng curPoint = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
        Log.d(TAG, String.valueOf(mLastLocation.getLatitude()));
        Log.d(TAG, String.valueOf(mLastLocation.getLongitude()));
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(curPoint, 15);
        map.animateCamera(update);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //마커 달기
        Marker home = map.addMarker(new MarkerOptions()
                .position(curPoint)
                .title("HOME")
                .draggable(true));

        home.showInfoWindow();

        Log.d(TAG, "14");


    }




}
