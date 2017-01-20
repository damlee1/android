package com.example.lineplus.mapsandroid1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by lineplus on 2017. 1. 19..
 */

public class DetailActivity extends AppCompatActivity {

    String[] mode_list = {"NONE","소리","진동","무음"};
    String[] wifi_list = {"NONE","wifi ON","wifi OFF"};
    String[] blue_list = {"NONE","bluetooth ON", "bluetooth OFF"};

    boolean init_s_in_mode;
    boolean init_s_in_wifi;
    boolean init_s_in_blue;
    boolean init_s_out_mode;
    boolean init_s_out_wifi;
    boolean init_s_out_blue;
    GeofenceDBHelper mHelper_d;
    SQLiteDatabase db_d;
    private Context context;


    String name;
    double lat;
    double lon;
    int radius;
    int in_mode;
    int in_wifi;
    int in_blue;
    int out_mode;
    int out_wifi;
    int out_blue;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        this.context = this;
        setContentView(R.layout.detail_activity);

        final TextView get_id = (TextView)findViewById(R.id.get_id);

        TextView t_in_mode = (TextView)findViewById(R.id.t_in_mode);
        TextView t_in_wifi = (TextView)findViewById(R.id.t_in_wifi);
        TextView t_in_blue = (TextView)findViewById(R.id.t_in_blue);
        TextView t_out_mode = (TextView)findViewById(R.id.t_out_mode);
        TextView t_out_wifi = (TextView)findViewById(R.id.t_out_wifi);
        TextView t_out_blue = (TextView)findViewById(R.id.t_out_blue);

        final EditText edit_name = (EditText)findViewById(R.id.edit_name);

        Button save_button = (Button)findViewById(R.id.save_button);
        Button delete_button = (Button)findViewById(R.id.delete_button);
        Button cancel_button = (Button)findViewById(R.id.cancel_button);

        ImageButton map_button = (ImageButton)findViewById(R.id.map_button);

        final Spinner s_in_mode = (Spinner)findViewById(R.id.s_in_mode);
        final Spinner s_in_wifi = (Spinner)findViewById(R.id.s_in_wifi);
        final Spinner s_in_blue = (Spinner)findViewById(R.id.s_in_blue);
        final Spinner s_out_mode = (Spinner)findViewById(R.id.s_out_mode);
        final Spinner s_out_wifi = (Spinner)findViewById(R.id.s_out_wifi);
        final Spinner s_out_blue = (Spinner)findViewById(R.id.s_out_blue);

        //db.execSQL("CREATE TABLE geo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +

        mHelper_d = new GeofenceDBHelper(this);
        db_d = mHelper_d.getWritableDatabase();

        //get_id.setText(getIntent().getStringExtra("id"));
        final String id = ""+getIntent().getStringExtra("id");
        final String[] args = {id};





        Cursor cursor_d;

        cursor_d = db_d.rawQuery("SELECT * FROM geo WHERE _id = ?",args);
        //cursor.moveToFirst();
        for(int i=0; i<cursor_d.getCount();i++){
            cursor_d.moveToNext();
            name = cursor_d.getString(1);
            lat = cursor_d.getDouble(2);
            lon = cursor_d.getDouble(3);
            radius = cursor_d.getInt(4);
            in_mode = cursor_d.getInt(5);
            in_wifi = cursor_d.getInt(6);
            in_blue = cursor_d.getInt(7);
            out_mode = cursor_d.getInt(8);
            out_wifi = cursor_d.getInt(9);
            out_blue = cursor_d.getInt(10);
        }

        lat = getIntent().getExtras().getDouble("latitude");
        lon = getIntent().getExtras().getDouble("longitude");
        radius = getIntent().getExtras().getInt("radius");

        get_id.setText(lat+" "+lon+" "+radius);

        //get_id.setText(name);
        edit_name.setText(name);
        cursor_d.close();

        final ArrayAdapter<String> a_in_mode = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, mode_list);
        a_in_mode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_in_mode.setAdapter(a_in_mode);
        s_in_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(init_s_in_mode == false){//초기화 시의 선택 제외시
                    s_in_mode.setSelection(in_mode);
                    init_s_in_mode = true;
                    return;
                }
                /*
                String toastMessage = String.valueOf(a_in_mode.getItem(position));
                Toast.makeText(DetailActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
                */
                in_mode = position;
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        final ArrayAdapter<String> a_in_wifi = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, wifi_list);
        a_in_mode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_in_wifi.setAdapter(a_in_wifi);
        s_in_wifi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(init_s_in_wifi == false){//초기화 시의 선택 제외시
                    s_in_wifi.setSelection(in_wifi);
                    init_s_in_wifi = true;
                    return;
                }
                /*
                String toastMessage = String.valueOf(a_in_wifi.getItem(position));
                Toast.makeText(DetailActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
                */
                in_wifi = position;
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        final ArrayAdapter<String> a_in_blue = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, blue_list);
        a_in_mode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_in_blue.setAdapter(a_in_blue);
        s_in_blue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(init_s_in_blue == false){//초기화 시의 선택 제외시
                    s_in_blue.setSelection(in_blue);
                    init_s_in_blue = true;
                    return;
                }
                /*
                String toastMessage = String.valueOf(a_in_blue.getItem(position));
                Toast.makeText(DetailActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
                */
                in_blue = position;
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });


        final ArrayAdapter<String> a_out_mode = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, mode_list);
        a_out_mode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_out_mode.setAdapter(a_out_mode);
        s_out_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(init_s_out_mode == false){//초기화 시의 선택 제외시
                    s_out_mode.setSelection(out_mode);
                    init_s_out_mode = true;
                    return;
                }
                /*
                String toastMessage = String.valueOf(a_out_mode.getItem(position));
                Toast.makeText(DetailActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
                */
                out_mode = position;
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        final ArrayAdapter<String> a_out_wifi = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, wifi_list);
        a_out_wifi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_out_wifi.setAdapter(a_out_wifi);
        s_out_wifi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(init_s_out_wifi == false){//초기화 시의 선택 제외시
                    s_out_wifi.setSelection(out_wifi);
                    init_s_out_wifi = true;
                    return;
                }
                /*
                String toastMessage = String.valueOf(a_out_wifi.getItem(position));
                Toast.makeText(DetailActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
                */
                out_wifi = position;
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        final ArrayAdapter<String> a_out_blue = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, blue_list);
        a_out_blue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_out_blue.setAdapter(a_out_blue);
        s_out_blue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(init_s_out_blue == false){//초기화 시의 선택 제외시
                    s_out_blue.setSelection(out_blue);
                    init_s_out_blue = true;
                    return;
                }
                /*
                String toastMessage = String.valueOf(a_out_blue.getItem(position));
                Toast.makeText(DetailActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
                */
                out_blue = position;
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edit_name.getText().toString();


                ContentValues recordValues = new ContentValues();
                recordValues.put("name",name);
                recordValues.put("lat", lat);
                recordValues.put("long",lon);
                recordValues.put("radius",radius);
                recordValues.put("in_mode", in_mode);
                recordValues.put("in_wifi", in_wifi);
                recordValues.put("in_bluetooth", in_blue);
                recordValues.put("out_mode", out_mode);
                recordValues.put("out_wifi", out_wifi);
                recordValues.put("out_bluetooth", out_blue);
                //db_d.insert("geo",null,recordValues);
                db_d.update("geo",recordValues,"_id = ?",args);

                /*
                왜 안되는지 모르겠다(위 다른 방법으로 되니까...!)
                db_d.execSQL("UPDATE geo SET name='"+name+"',lat="+lat+",long="+lon+",radius="+radius+
                        ",in_mode="+in_mode+",in_wifi="+in_wifi+",in_bluetooth="+in_blue+
                        ",out_mode="+out_mode+",out_wifi="+out_wifi+",out_bluetooth"+out_blue+
                    " where _id="+id+";");
                */
                /*
                이것도 됨
                insert
                db_d.execSQL("INSERT INTO geo VALUES(null,'"+name+"',"+lat+","+lon+","+radius+","+
                in_mode+","+in_wifi+","+in_blue+","+out_mode+","+out_wifi+","+out_blue+");");
                */

                db_d.close();
                Intent mainIntent = new Intent(context, MainActivity.class);
                // context 공부!! (요소에 따라 할수 있는것이 다르다)
                startActivity(mainIntent);
                //cursor_d = db_d.rawQuery("SELECT * FROM geo WHERE _id = ?",args);


            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edit_name.getText().toString();

                //db_d.execSQL("DELETE FROM geo WHERE _id = "+String.valueOf(Integer.valueOf(id)-1));
                db_d.execSQL("DELETE FROM geo WHERE _id = "+id);
                Intent mainIntent = new Intent(context, MainActivity.class);
                // context 공부!! (요소에 따라 할수 있는것이 다르다)
                startActivity(mainIntent);
                //cursor_d = db_d.rawQuery("SELECT * FROM geo WHERE _id = ?",args);


            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mapIntent = new Intent(context, MapActivity.class);
                // context 공부!! (요소에 따라 할수 있는것이 다르다)
                startActivity(mapIntent);

            }
        });

    }



}
