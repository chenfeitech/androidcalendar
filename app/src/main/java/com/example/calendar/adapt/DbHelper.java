package com.example.calendar.adapt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "xnotebook.db";
    public static final String TABLE_NAME = "notebook";

    public DbHelper(Context context)
    {
        // super(context,"sy_10.db3",null,1);
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 调用时刻：当数据库第1次创建时调用
     * 作用：创建数据库 表 & 初始化数据
     * SQLite数据库创建支持的数据类型： 整型数据、字符串类型、日期类型、二进制
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //        sqLiteDatabase.execSQL("INSERT INTO note values('2022-12-10','notemsg')");
        String createTbStr = "CREATE TABLE " + TABLE_NAME
                +"(_id integer primary key, date VARCHAR(8), title VARCHAR(128), content VARCHAR(2048), type VARCHAR(8))";
        sqLiteDatabase.execSQL(createTbStr);
    }

    /**
     * 调用时刻：当数据库升级时则自动调用（即 数据库版本 发生变化时）
     * 作用：更新数据库表结构
     * 注：创建SQLiteOpenHelper子类对象时,必须传入一个version参数，该参数 = 当前数据库版本, 若该版本高于之前版本, 就调用onUpgrade()
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        //可重新建表
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);


        //也可在表中新增列元素， 使用 SQL的ALTER语句
        // String sql = "alter table "+TABLE_NAME+" add name varchar";
        // sqLiteDatabase.execSQL(sql);
    }
}
