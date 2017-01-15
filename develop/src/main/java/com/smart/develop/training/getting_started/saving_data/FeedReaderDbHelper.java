package com.smart.develop.training.getting_started.saving_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.smart.develop.training.getting_started.saving_data.FeedReaderContract.FeedEntry;

/**
 * FileName: FeedReaderDbHelper
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Saving Data
 *
 *      数据库存储帮助类
 *
 * Time: 2017/1/7 下午5:32
 */
public class FeedReaderDbHelper extends SQLiteOpenHelper {
    
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    /**
     * Des: 构造函数，主要用于创建数据库
     *
     * Time: 2017/1/7 下午5:36
     */
    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Des: 创建表
     *
     * Time: 2017/1/7 下午5:35
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Des: 更新数据（升级）
     *
     * Time: 2017/1/7 下午5:36
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Des: 降级更新
     *
     * Time: 2017/1/7 下午5:38
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
