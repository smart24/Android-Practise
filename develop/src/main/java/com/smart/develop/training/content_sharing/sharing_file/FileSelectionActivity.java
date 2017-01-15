package com.smart.develop.training.content_sharing.sharing_file;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smart.develop.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * FileName: FileSelectionActivity
 *
 * Des: Training
 *
 *      --Building App with Content Sharing
 *
 *      --Sharing File
 *
 *      文件选择
 *
 * Time: 2017/1/11 下午9:44
 */
public class FileSelectionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mLV;
    private File mPrivateRootDir;
    private File mDesDir;
    private File mDesTextFile, mDesImageFile;
    private String[] mDesFilesName;
    private File[] mDesFiles;
    private ArrayAdapter<String> mAdapter;
    private Uri mFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_selection);
        initView();
        initData();
    }

    private void initView() {
        mLV = (ListView) findViewById(R.id.file_selection_lv);
        mLV.setOnItemClickListener(this);
    }

    private void initData() {
        mPrivateRootDir = getFilesDir();
        mDesDir = new File(mPrivateRootDir, "des_dir");
        if (!mDesDir.exists()) {
            mDesDir.mkdir();
        }
        mDesTextFile = new File(mDesDir, "des_text.txt");
        mDesImageFile = new File(mDesDir, "des_image.png");
        try {
            if (!mDesTextFile.exists()) {
                mDesTextFile.createNewFile();
                mDesImageFile.createNewFile();
            }

            AssetManager am = getAssets();
            InputStream is = am.open("des_text_file");
            FileOutputStream textFOS = new FileOutputStream(mDesTextFile);
            byte[] bytes = new byte[1024];
            int temp = 0;
            while ((temp = is.read(bytes)) != -1) {
                textFOS.write(bytes, 0, temp);
            }
            textFOS.flush();
            textFOS.close();

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.baojiaozi);
            FileOutputStream imageFOS = new FileOutputStream(mDesImageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, imageFOS);
            imageFOS.flush();
            imageFOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mDesFilesName = mDesDir.list();
        mDesFiles = mDesDir.listFiles();
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDesFilesName);
        mLV.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            mFileUri = FileProvider.getUriForFile(this, "com.smart.develop.fileprovider", mDesFiles[position]);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (mFileUri != null) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(mFileUri, getContentResolver().getType(mFileUri));
            // Set the result
            this.setResult(Activity.RESULT_OK, intent);
            this.finish();
        }
    }
}
