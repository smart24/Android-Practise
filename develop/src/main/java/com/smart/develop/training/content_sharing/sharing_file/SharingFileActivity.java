package com.smart.develop.training.content_sharing.sharing_file;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smart.develop.R;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * FileName: SharingFileActivity
 *
 * Des: Training
 *
 *      --Building App with Content Sharing
 *
 *      --Sharing File
 *
 * Time: 2017/1/11 下午7:39
 */
public class SharingFileActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mDesIV;
    private TextView mDesName,mDesType,mDesSize,mDesContent;
    private Button mRetrieveFile;
    private final static int REQUST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_file);
        initView();
    }

    private void initView(){
        mDesIV = (ImageView)findViewById(R.id.des_iv);
        mDesName = (TextView) findViewById(R.id.des_name);
        mDesType = (TextView) findViewById(R.id.des_type);
        mDesSize = (TextView) findViewById(R.id.des_size);
        mDesContent = (TextView) findViewById(R.id.des_content);
        mRetrieveFile = (Button) findViewById(R.id.retrieve_file);
        mRetrieveFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.retrieve_file:
                retrieveFile();
            break;
        }
    }

    private void retrieveFile(){
        Intent requestFileIntent = new Intent(Intent.ACTION_PICK);
//        requestFileIntent.setType("text/plain");
//        requestFileIntent.setType("image/*");
        //此处的 Type 是可以自定义的，只要在目标组建的 <data android:mimeType="smart/*"/> 声明相同的类型即可
        //既然 Type 可以自定义，那么其他的可以自定吗？例如 Action
        requestFileIntent.setType("smart/*");
        startActivityForResult(requestFileIntent,REQUST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);
        if (REQUST_CODE == requestCode){
            if (RESULT_OK == resultCode){

                Uri returnUri = returnIntent.getData();
                ContentResolver contentResolver = getContentResolver();
                Cursor returnCursor = contentResolver.query(returnUri, null, null, null, null);
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                returnCursor.moveToFirst();
                mDesName.setText(returnCursor.getString(nameIndex));
                mDesSize.setText((Float.toString(returnCursor.getLong(sizeIndex)/1024/1024f)) + "M");
                String mimeType = contentResolver.getType(returnUri);
                mDesType.setText(mimeType);
                try {
                    ParcelFileDescriptor inputPFD = contentResolver.openFileDescriptor(returnUri, "r");
                    FileDescriptor fd = inputPFD.getFileDescriptor();
                    if(mimeType.equals("text/plain")){
                        FileInputStream fis = new FileInputStream(fd);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        fis.close();
                        mDesContent.setText(sb.toString());
                    }else if (mimeType.equals("image/png")){
                        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd);
                        mDesIV.setImageBitmap(bitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}
