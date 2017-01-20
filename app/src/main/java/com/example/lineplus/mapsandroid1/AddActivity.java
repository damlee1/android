package com.example.lineplus.mapsandroid1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lineplus on 2017. 1. 19..
 */

public class AddActivity extends AppCompatActivity{

    String[] mode_list = {"NONE","소리","진동","무음"};
    String[] wifi_list = {"NONE","wifi ON","wifi OFF"};
    String[] blue_list = {"NONE","bluetooth ON", "bluetooth OFF"};

    boolean init_s_in_mode;
    boolean init_s_in_wifi;
    boolean init_s_in_blue;
    boolean init_s_out_mode;
    boolean init_s_out_wifi;
    boolean init_s_out_blue;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        TextView t_in_mode = (TextView)findViewById(R.id.t_in_mode_a);
        TextView t_in_wifi = (TextView)findViewById(R.id.t_in_wifi_a);
        TextView t_in_blue = (TextView)findViewById(R.id.t_in_blue_a);
        TextView t_out_mode = (TextView)findViewById(R.id.t_out_mode_a);
        TextView t_out_wifi = (TextView)findViewById(R.id.t_out_wifi_a);
        TextView t_out_blue = (TextView)findViewById(R.id.t_out_blue_a);

        Spinner s_in_mode = (Spinner)findViewById(R.id.s_in_mode_a);
        Spinner s_in_wifi = (Spinner)findViewById(R.id.s_in_wifi_a);
        Spinner s_in_blue = (Spinner)findViewById(R.id.s_in_blue_a);
        Spinner s_out_mode = (Spinner)findViewById(R.id.s_out_mode_a);
        Spinner s_out_wifi = (Spinner)findViewById(R.id.s_out_wifi_a);
        Spinner s_out_blue = (Spinner)findViewById(R.id.s_out_blue_a);

        final ArrayAdapter<String> a_in_mode = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, mode_list);
        a_in_mode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_in_mode.setAdapter(a_in_mode);
        s_in_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(init_s_in_mode == false){//초기화 시의 선택 제외시
                    init_s_in_mode = true;
                    return;
                }

                String toastMessage = String.valueOf(a_in_mode.getItem(position));
                Toast.makeText(AddActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
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
                    init_s_in_wifi = true;
                    return;
                }

                String toastMessage = String.valueOf(a_in_wifi.getItem(position));
                Toast.makeText(AddActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
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
                    init_s_in_blue = true;
                    return;
                }

                String toastMessage = String.valueOf(a_in_blue.getItem(position));
                Toast.makeText(AddActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
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
                    init_s_out_mode = true;
                    return;
                }

                String toastMessage = String.valueOf(a_out_mode.getItem(position));
                Toast.makeText(AddActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
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
                    init_s_out_wifi = true;
                    return;
                }

                String toastMessage = String.valueOf(a_out_wifi.getItem(position));
                Toast.makeText(AddActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
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
                    init_s_out_blue = true;
                    return;
                }

                String toastMessage = String.valueOf(a_out_blue.getItem(position));
                Toast.makeText(AddActivity.this,
                        toastMessage,
                        Toast.LENGTH_SHORT
                ).show();
            }
            public void onNothingSelected(AdapterView<?> parent){

            }
        });


    }

}
