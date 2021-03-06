package com.handsignature.secuve.secuvehandsignature;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by quddn on 2016-08-14.
 */
public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        name 문자열 컬럼, sign1, 2, 3 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE USER (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, sign1 TEXT, sign2 TEXT, sign3 TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists USER;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(String name, String sign1, String sign2, String sign3) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO USER VALUES(null, '" + name + "', '" + sign1 + "', '" +  sign2 + "', '" + sign3 + "');");
        db.close();
    }

    public void update(String name, String sign1, String sign2, String sign3) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 sign information update
        db.execSQL("UPDATE USER SET sign1='" + sign1 + "', sign2='" + sign2 + "', sign3='" + sign3 + "' WHERE name='" + name + "';");
        db.close();
    }

    public void update1(String name, String sign1) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 sign information update
        db.execSQL("UPDATE USER SET sign1='" + sign1 + "' WHERE name='" + name + "';");
        db.close();
    }

    public void update2(String name, String sign2) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 sign information update
        db.execSQL("UPDATE USER SET sign2='" + sign2 + "' WHERE name='" + name + "';");
        db.close();
    }

    public void update3(String name, String sign3) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 sign information update
        db.execSQL("UPDATE USER SET sign3='" + sign3 + "' WHERE name='" + name + "';");
        db.close();
    }

    public void delete(String name) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM USER WHERE name='" + name + "';");
        db.close();
    }

    public String selectNames() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(1) + ",";
        }
        return result;
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " - "
                    + cursor.getString(1)
                    + " : "
                    + cursor.getString(2)
                    + " | "
                    + cursor.getString(3)
                    + " | "
                    + cursor.getString(4)
                    + ";";
        }

        return result;
    }
}
