package com.azad.lib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "books.db";
    private static final String TB_NAME_BOOK = "book_info";
    private static final String TB_NAME_USER = "user";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user" +
                "(id VARCHAR PRIMARY KEY NOT NULL ," +
                "username VARCHAR, " +
                "password VARCHAR)");
        ContentValues cn = new ContentValues();
        cn.put("id", "1");
        cn.put("username", "admin");
        cn.put("password", "12345");
        db.insert(TB_NAME_USER, null, cn);


        db.execSQL("CREATE TABLE book_info" +
                "(id VARCHAR PRIMARY KEY NOT NULL ," +
                "title VARCHAR, " +
                "publishDate VARCHAR, " +
                "writer VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_BOOK);
        onCreate(db);
    }

    public boolean bookInsert(String id, String title, String publishDate, String writer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put("id", id);
        cn.put("title", title);
        cn.put("publishDate", publishDate);
        cn.put("writer", writer);

        long res = db.insert(TB_NAME_BOOK, null, cn);
        if (res == -1)
            return false;
        return true;
    }

    public boolean bookDelete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TB_NAME_BOOK, "id=?"
                , new String[]{id});
        if (res == 0)
            return false;
        return true;
    }

    public boolean bookUpdate(String id, String title, String publishDate, String writer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put("id", id);
        cn.put("title", title);
        cn.put("publishDate", publishDate);
        cn.put("writer", writer);

        long res = db.update(TB_NAME_BOOK, cn, "id=?"
                , new String[]{id});
        if (res < 1)
            return false;
        return true;

    }

    public Cursor bookGetAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TB_NAME_BOOK, null);
        return result;
    }

    public Cursor userGet(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TB_NAME_USER + " where username = '" + username + "'", null);
        return result;
    }
}

