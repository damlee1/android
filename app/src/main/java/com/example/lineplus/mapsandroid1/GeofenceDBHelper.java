package com.example.lineplus.mapsandroid1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lineplus on 2017. 1. 19..
 */

public class GeofenceDBHelper extends SQLiteOpenHelper {

    public GeofenceDBHelper(Context context){
        //생성자에서 DB 이름과 버전을 정의
        super(context, "GeoFence.db", null, 1);
    }

    // 나중에 하드코딩 변경하기
    // 두번째로 이 앱을 돌릴때 level이 똑같으니까 onCreate와 onUpgrade가 실행되지 않는거!!!!!!!!!!
    public void onCreate(SQLiteDatabase db){
        // geo table 생성
        db.execSQL("CREATE TABLE geo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, lat DOUBLE, long DOUBLE, radius INTEGER, " +
                "in_mode INTEGER, in_wifi INTEGER, in_bluetooth INTEGER, " +
                "out_mode INTEGER, out_wifi INTEGER, out_bluetooth INTEGER);");
        db.execSQL("INSERT INTO geo VALUES (null,'work',null,null,5,0,0,0,0,0,0);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST geo");
        onCreate(db);

        //Column.id.toString();
        //Column.id.ordinal();
    }

    //하드코딩 하지 않는 방법!!!!!!!!!!
    enum Column {
        id, name_, lat, lon;

        public String type;
        public void setType(String type){
            this.type = type;
        }
    }
}
