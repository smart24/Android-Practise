package com.smart.develop.training.getting_started.saving_data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smart.develop.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * FileName: SavingDataActivity
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Saving Data
 *
 * Time: 2017/1/6 下午11:21
 */
public class SavingDataActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSPInput, mIFInput, mEFInput,mDBInput;
    private Button mSPInputConfirm, mSPReadConfirm,
                   mIFInputConfirm, mIFReadConfirm,
                   mEFInputConfirm, mEFReadConfirm,
                   mDBInsertConfirm, mDBDeleteConfirm,mDBUpdateConfirm,mDBQueryConfirm;
    private TextView mSPReadShow, mIFReadShow, mEFReadShow, mDBReadShow;
    private SharedPreferences mSP;
    private SharedPreferences.Editor mEditor;
    public final static String SAVING_DATA_KEY = "SAVING_DATA_KEY";
    private final static String TEXT_FILE = "text_file";
    private File mExternalFile = null;
    public final static String TYPE_KEY = "TYPE_KEY";
    public final static int INSERT = 0x1;
    public final static int DELETE = 0x2;
    public final static int UPDATE = 0x3;
    public final static int QUERY = 0x4;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int type = msg.what;
            switch (type){
                case INSERT:
                    Toast.makeText(SavingDataActivity.this, "INSERT", Toast.LENGTH_SHORT).show();
                    break;
                case DELETE:
                    Toast.makeText(SavingDataActivity.this, "DELETE", Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE:
                    Toast.makeText(SavingDataActivity.this, "UPDATE", Toast.LENGTH_SHORT).show();
                    break;
                case QUERY:
                    Toast.makeText(SavingDataActivity.this, "QUERY", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_data);
        initView();
    }

    private void initView() {
        //SharedPreference
        mSPInput = (EditText) this.findViewById(R.id.shared_preference_input);
        mSPInputConfirm = (Button) this.findViewById(R.id.shared_preference_input_confirm);
        mSPReadShow = (TextView) this.findViewById(R.id.shared_preference_read_show);
        mSPReadConfirm = (Button) this.findViewById(R.id.shared_preference_read_confirm);
        mSPInputConfirm.setOnClickListener(this);
        mSPReadConfirm.setOnClickListener(this);
        //Internal File
        mIFInput = (EditText) this.findViewById(R.id.internal_file_input);
        mIFInputConfirm = (Button) this.findViewById(R.id.internal_file_input_confirm);
        mIFReadShow = (TextView) this.findViewById(R.id.internal_file_read_show);
        mIFReadConfirm = (Button) this.findViewById(R.id.internal_file_read_confirm);
        mIFInputConfirm.setOnClickListener(this);
        mIFReadConfirm.setOnClickListener(this);
        //External File
        mEFInput = (EditText) this.findViewById(R.id.external_file_input);
        mEFInputConfirm = (Button) this.findViewById(R.id.external_file_input_confirm);
        mEFReadShow = (TextView) this.findViewById(R.id.external_file_read_show);
        mEFReadConfirm = (Button) this.findViewById(R.id.external_file_read_confirm);
        mEFInputConfirm.setOnClickListener(this);
        mEFReadConfirm.setOnClickListener(this);
        //Database
        mDBInput = (EditText) this.findViewById(R.id.db_input);
        mDBReadShow = (TextView) this.findViewById(R.id.db_read_show);
        mDBInsertConfirm = (Button) this.findViewById(R.id.db_insert_confirm);
        mDBDeleteConfirm = (Button) this.findViewById(R.id.db_delete_confirm);
        mDBUpdateConfirm = (Button) this.findViewById(R.id.db_update_confirm);
        mDBQueryConfirm = (Button) this.findViewById(R.id.db_query_confirm);
        mDBInsertConfirm.setOnClickListener(this);
        mDBDeleteConfirm.setOnClickListener(this);
        mDBUpdateConfirm.setOnClickListener(this);
        mDBQueryConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.shared_preference_input_confirm:
                writeToSharedPreferences();
                break;
            case R.id.shared_preference_read_confirm:
                readFromSharedPreferences();
                break;
            case R.id.internal_file_input_confirm:
                writeToInternalFile();
                break;
            case R.id.internal_file_read_confirm:
                readFromInternalFile();
                break;
            case R.id.external_file_input_confirm:
                writeToExternalFile();
                break;
            case R.id.external_file_read_confirm:
                readFromExternalFile();
                break;
            case R.id.db_insert_confirm:
                operateDatabase(INSERT);
                break;
            case R.id.db_delete_confirm:
                operateDatabase(DELETE);
                break;
            case R.id.db_update_confirm:
                operateDatabase(UPDATE);
                break;
            case R.id.db_query_confirm:
                operateDatabase(QUERY);
                break;
        }
    }

    /**
     * Des: 向共享参数中写数据
     *
     * Time: 2017/1/7 下午1:22
     */
    private void writeToSharedPreferences() {
        if (mEditor == null) {
            mSP = getPreferences(MODE_PRIVATE);
            mEditor = mSP.edit();
        }
        mEditor.putString(SAVING_DATA_KEY, mSPInput.getText().toString());
        mEditor.commit();
    }

    /**
     * Des: 从共享参数中读数据
     *
     * Time: 2017/1/7 下午1:23
     */
    private void readFromSharedPreferences() {
        if (mSP == null) {
            mSP = getPreferences(MODE_PRIVATE);
        }
        mSPReadShow.setText(mSP.getString(SAVING_DATA_KEY, getResources().getString(R.string.nothing)));
    }

    /**
     * Des: 向内存中写数据
     *
     * Time: 2017/1/7 下午1:38
     */
    private void writeToInternalFile() {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(TEXT_FILE, Context.MODE_PRIVATE);
            outputStream.write(mIFInput.getText().toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Des: 从内存中读数据
     *
     * Time: 2017/1/7 下午1:39
     */
    private void readFromInternalFile() {
        FileInputStream inputStream;
        try {
            byte[] bytes = new byte[1024];
            inputStream = openFileInput(TEXT_FILE);
            int temp = inputStream.read(bytes);
            String result = new String(bytes, 0, temp);
            mIFReadShow.setText(result);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Des: 向扩展存储中写数据
     *
     * Time: 2017/1/7 下午1:38
     */
    private void writeToExternalFile() {
        try {
            if (isExternalStorageWritable()) {
                mExternalFile = new File(getExternalFilesDir(null), TEXT_FILE);
                OutputStream os = new FileOutputStream(mExternalFile);
                os.write(mEFInput.getText().toString().getBytes());
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Des: 从扩展存储中读数据
     *
     * Time: 2017/1/7 下午1:39
     */
    private void readFromExternalFile() {
        try {
            if (isExternalStorageWritable()) {
                if(mExternalFile ==null ){
                    mExternalFile = new File(getExternalFilesDir(null), TEXT_FILE);
                }
                byte[] bytes = new byte[1024];
                InputStream is = new FileInputStream(mExternalFile);
                int temp = is.read(bytes);
                String result = new String(bytes, 0, temp);
                mEFReadShow.setText(result);
                is.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Des: 检查扩展存储是否可用
     *
     * Time: 2017/1/7 下午2:29
     */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Des: 数据库增、删、改、查
     *
     * Time: 2017/1/7 下午6:24
     */
    private void operateDatabase(int type){
        Intent intent = new Intent(this,FeedReaderService.class);
        switch (type){
            case INSERT:
                intent.putExtra(TYPE_KEY,INSERT);
                intent.putExtra(SAVING_DATA_KEY,mDBInput.getText().toString());
            break;
            case DELETE:
                intent.putExtra(TYPE_KEY,DELETE);
                intent.putExtra(SAVING_DATA_KEY,mDBInput.getText().toString());
            break;
            case UPDATE:
                intent.putExtra(TYPE_KEY,UPDATE);
                intent.putExtra(SAVING_DATA_KEY,mDBInput.getText().toString());
            break;
            case QUERY:
                intent.putExtra(TYPE_KEY,QUERY);
                intent.putExtra(SAVING_DATA_KEY,mDBInput.getText().toString());
            break;
        }
        startService(intent);
    }

}
