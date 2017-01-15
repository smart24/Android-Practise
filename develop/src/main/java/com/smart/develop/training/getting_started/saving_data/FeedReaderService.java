package com.smart.develop.training.getting_started.saving_data;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.smart.develop.training.getting_started.saving_data.FeedReaderContract.FeedEntry;

import static com.smart.develop.training.getting_started.saving_data.SavingDataActivity.DELETE;
import static com.smart.develop.training.getting_started.saving_data.SavingDataActivity.INSERT;
import static com.smart.develop.training.getting_started.saving_data.SavingDataActivity.QUERY;
import static com.smart.develop.training.getting_started.saving_data.SavingDataActivity.SAVING_DATA_KEY;
import static com.smart.develop.training.getting_started.saving_data.SavingDataActivity.TYPE_KEY;
import static com.smart.develop.training.getting_started.saving_data.SavingDataActivity.UPDATE;

/**
 * FileName: FeedReadService
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Saving Data
 *
 *      数据库存储后台任务类
 *
 * Time: 2017/1/7 下午5:54
 */
public class FeedReaderService extends IntentService {

    private FeedReaderDbHelper mDbHelper = null;
    private SQLiteDatabase mSQLite = null;
    private String TAG = "FeedReaderService";

    public FeedReaderService() {
        super("FeedReaderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        initSQLiteDatabase();
        int type = intent.getIntExtra(TYPE_KEY,0);
        switch(type){
            case INSERT:
                insert(intent.getStringExtra(SAVING_DATA_KEY));
                break;
            case DELETE:
                delete(intent.getStringExtra(SAVING_DATA_KEY));
                break;
            case UPDATE:
                update(intent.getStringExtra(SAVING_DATA_KEY));
                break;
            case QUERY:
                query(intent.getStringExtra(SAVING_DATA_KEY));
                break;
        }
    }

    /**
     * Des: 初始化数据库帮助类
     *
     * Time: 2017/1/7 下午6:29
     */
    private void initSQLiteDatabase(){
        if(mDbHelper == null){
            mDbHelper = new FeedReaderDbHelper(this);
            mSQLite = mDbHelper.getWritableDatabase();
        }
    }

    /**
     * Des: 向数据库中插入数据
     *
     * Time: 2017/1/7 下午6:30
     */
    private void insert(String title){
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
        mSQLite.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        Log.d(TAG, "INSERT SUCCEED");
    }

    /**
     * Des: 删除数据库中的数据
     *
     * Time: 2017/1/7 下午6:30
     */
    private void delete(String ... selectionArgs){

        String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";

        if(selectionArgs == null){
            mSQLite.delete(FeedEntry.TABLE_NAME, null, null);
        }else{
            mSQLite.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);
        }
        Log.d(TAG, "DELETE SUCCEED");
    }

    /**
     * Des: 更新数据库中的指定数据
     *
     * Time: 2017/1/7 下午6:30
     */
    private void update(String title){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE, title);

        String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = {"update"};

        mSQLite.update(FeedEntry.TABLE_NAME,values,selection,selectionArgs);

        Log.d(TAG, "UPDATE SUCCEED");
    }

    /**
     * Des: 从数据库查数据
     *
     * Time: 2017/1/7 下午6:30
     */
    private void query(String ... selectionArgs){
        String[] projection = {FeedEntry._ID,FeedEntry.COLUMN_NAME_TITLE};
        String sortOrder = FeedEntry.COLUMN_NAME_TITLE + " DESC";
        Cursor cursor = null;
        if(selectionArgs == null){
            cursor = mSQLite.query(
                     FeedEntry.TABLE_NAME,                     // The table to query
                     projection,                               // The columns to return
                     null,                                     // The columns for the WHERE clause
                     null,                                     // The values for the WHERE clause
                     null,                                     // don't group the rows
                     null,                                     // don't filter by row groups
                     sortOrder                                 // The sort order
            );
        }else{
            String selection = FeedEntry.COLUMN_NAME_TITLE + " = ?";
            cursor = mSQLite.query(
                     FeedEntry.TABLE_NAME,                     // The table to query
                     projection,                               // The columns to return
                     selection,                                // The columns for the WHERE clause
                     selectionArgs,                            // The values for the WHERE clause
                     null,                                     // don't group the rows
                     null,                                     // don't filter by row groups
                     sortOrder                                 // The sort order
            );
        }
        while(cursor.moveToNext()) {
            Log.d(TAG, "QUERY SUCCEED  " + cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_TITLE)));
        }
        cursor.close();
    }

}
