package com.example.bpn18.cosmos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.version;

/**
 * Created by bpn18 on 12/6/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static int version= 1 ;
    static String name = "cosmosroutinedb7";
    String CreateTableRoutine= "CREATE TABLE IF NOT EXISTS `routine123` (\n" +
            "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`faculty`\tINTEGER,\n" +
            "\t`day`\tINTEGER,\n" +
            "\t`subject1`\tINTEGER,\n" +
            "\t`subject2`\tINTEGER,\n" +
            "\t`subject3`\tINTEGER,\n" +
            "\t`subject4`\tINTEGER,\n" +
            "\t`teacher1`\tINTEGER,\n" +
            "\t`teacher2`\tINTEGER,\n" +
            "\t`teacher3`\tINTEGER,\n" +
            "\t`teacher4`\tINTEGER,\n" +
            "\t`room1`\tINTEGER,\n" +
            "\t`room2`\tINTEGER,\n" +
            "\t`room3`\tINTEGER,\n" +
            "\t`room4`\tINTEGER,\n" +
            "\t`starts1`\tINTEGER,\n" +
            "\t`starts2`\tINTEGER,\n" +
            "\t`starts3`\tINTEGER,\n" +
            "\t`starts4`\tINTEGER,\n" +
            "\t`ends1`\tINTEGER,\n" +
            "\t`ends2`\tINTEGER,\n" +
            "\t`ends3`\tINTEGER,\n" +
            "\t`ends4`\tINTEGER\n" +
            ");";

    String CreateTableEvents = "CREATE TABLE IF NOT EXISTS `events` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`date`\tINTEGER,\n" +
            "\t`detail`\tINTEGER\n" +
            "); ";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(CreateTableRoutine);
        getWritableDatabase().execSQL(CreateTableEvents);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    public void insertRoutine(ContentValues cv){
        getWritableDatabase().insert("routine123 ", "" ,cv);
    }

    public void insertEvents(ContentValues cv){
        getWritableDatabase().insert("events ", "" ,cv);
    }

    public ArrayList<UserInfo> getUserList(){
        String sql = " select * from routine123 where faculty= 'it' ";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        while(c.moveToNext()){
            UserInfo info = new UserInfo();
            info.id=c.getString(c.getColumnIndex("id"));
            info.faculty=c.getString(c.getColumnIndex("faculty"));
            info.day=c.getString(c.getColumnIndex("day"));
            info.subject1=c.getString(c.getColumnIndex("subject1"));
            info.subject2=c.getString(c.getColumnIndex("subject2"));
            info.subject3=c.getString(c.getColumnIndex("subject3"));
            info.subject4=c.getString(c.getColumnIndex("subject4"));
            info.teacher1=c.getString(c.getColumnIndex("teacher1"));
            info.teacher2=c.getString(c.getColumnIndex("teacher2"));
            info.teacher3=c.getString(c.getColumnIndex("teacher3"));
            info.teacher4=c.getString(c.getColumnIndex("teacher4"));
            info.room1=c.getString(c.getColumnIndex("room1"));
            info.room2=c.getString(c.getColumnIndex("room2"));
            info.room3=c.getString(c.getColumnIndex("room3"));
            info.room4=c.getString(c.getColumnIndex("room4"));
            info.starts1=c.getString(c.getColumnIndex("starts1"));
            info.starts2=c.getString(c.getColumnIndex("starts2"));
            info.starts3=c.getString(c.getColumnIndex("starts3"));
            info.starts4=c.getString(c.getColumnIndex("starts4"));
            info.ends1=c.getString(c.getColumnIndex("ends1"));
            info.ends2=c.getString(c.getColumnIndex("ends2"));
            info.ends3=c.getString(c.getColumnIndex("ends3"));
            info.ends4=c.getString(c.getColumnIndex("ends4"));
            list.add(info);
        }
        c.close();
        return list;
    }

    public ArrayList<EventInfos> getEventList(){
        String sql = " select * from events order by id desc";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        ArrayList<EventInfos> list = new ArrayList<EventInfos>();
        while(c.moveToNext()){
            EventInfos info = new EventInfos();
            info.id=c.getString(c.getColumnIndex("id"));
            info.date=c.getString(c.getColumnIndex("date"));
            info.detail=c.getString(c.getColumnIndex("detail"));

            list.add(info);
        }
        c.close();
        return list;
    }


    public UserInfo getUserInfo(String id){
        String sql = " select * from routine123 where id=" + id;
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        UserInfo info = new UserInfo();
        while(c.moveToNext()){
            info.id=c.getString(c.getColumnIndex("id"));
            info.faculty=c.getString(c.getColumnIndex("faculty"));
            info.day=c.getString(c.getColumnIndex("day"));
            info.subject1=c.getString(c.getColumnIndex("subject1"));
            info.subject2=c.getString(c.getColumnIndex("subject2"));
            info.subject3=c.getString(c.getColumnIndex("subject3"));
            info.subject4=c.getString(c.getColumnIndex("subject4"));
            info.teacher1=c.getString(c.getColumnIndex("teacher1"));
            info.teacher2=c.getString(c.getColumnIndex("teacher2"));
            info.teacher3=c.getString(c.getColumnIndex("teacher3"));
            info.teacher4=c.getString(c.getColumnIndex("teacher4"));
            info.room1=c.getString(c.getColumnIndex("room1"));
            info.room2=c.getString(c.getColumnIndex("room2"));
            info.room3=c.getString(c.getColumnIndex("room3"));
            info.room4=c.getString(c.getColumnIndex("room4"));
            info.starts1=c.getString(c.getColumnIndex("starts1"));
            info.starts2=c.getString(c.getColumnIndex("starts2"));
            info.starts3=c.getString(c.getColumnIndex("starts3"));
            info.starts4=c.getString(c.getColumnIndex("starts4"));
            info.ends1=c.getString(c.getColumnIndex("ends1"));
            info.ends2=c.getString(c.getColumnIndex("ends2"));
            info.ends3=c.getString(c.getColumnIndex("ends3"));
            info.ends4=c.getString(c.getColumnIndex("ends4"));
        }
        c.close();
        return info;
    }
    public void updateUser(String id, ContentValues cv){
        getWritableDatabase().update( " routine123 " , cv , "id=" + id , null);
    }
    public void deleteUserfrag(){
        getWritableDatabase().delete("routine123",null,null);
    }

    public void deleteUser(String id) {
        getWritableDatabase().delete("routine123", "id=" + id, null);
    }

    public void deleteEvents(){
        getWritableDatabase().delete("events",null,null);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public EventInfos getEvents(){
        String sql = "select * from routine123 where day='sunday'";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        EventInfos info = new EventInfos();
        while(c.moveToNext()){
            info.id=c.getString(c.getColumnIndex("id"));
            info.date=c.getString(c.getColumnIndex("date"));
            info.detail=c.getString(c.getColumnIndex("detail"));

        }
        c.close();
        return info;
    }

    public UserInfo getRoutine(String sql){
        //      String sql = "select * from routine123 where day='sunday'";
        Cursor c = getReadableDatabase().rawQuery(sql,null);
        UserInfo info = new UserInfo();
        while(c.moveToNext()){
            info.id=c.getString(c.getColumnIndex("id"));
            info.faculty=c.getString(c.getColumnIndex("faculty"));
            info.day=c.getString(c.getColumnIndex("day"));
            info.subject1=c.getString(c.getColumnIndex("subject1"));
            info.subject2=c.getString(c.getColumnIndex("subject2"));
            info.subject3=c.getString(c.getColumnIndex("subject3"));
            info.subject4=c.getString(c.getColumnIndex("subject4"));
            info.teacher1=c.getString(c.getColumnIndex("teacher1"));
            info.teacher2=c.getString(c.getColumnIndex("teacher2"));
            info.teacher3=c.getString(c.getColumnIndex("teacher3"));
            info.teacher4=c.getString(c.getColumnIndex("teacher4"));
            info.room1=c.getString(c.getColumnIndex("room1"));
            info.room2=c.getString(c.getColumnIndex("room2"));
            info.room3=c.getString(c.getColumnIndex("room3"));
            info.room4=c.getString(c.getColumnIndex("room4"));
            info.starts1=c.getString(c.getColumnIndex("starts1"));
            info.starts2=c.getString(c.getColumnIndex("starts2"));
            info.starts3=c.getString(c.getColumnIndex("starts3"));
            info.starts4=c.getString(c.getColumnIndex("starts4"));
            info.ends1=c.getString(c.getColumnIndex("ends1"));
            info.ends2=c.getString(c.getColumnIndex("ends2"));
            info.ends3=c.getString(c.getColumnIndex("ends3"));
            info.ends4=c.getString(c.getColumnIndex("ends4"));
        }
        c.close();
        return info;
    }
}
