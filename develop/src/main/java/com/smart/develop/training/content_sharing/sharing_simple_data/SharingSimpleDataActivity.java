package com.smart.develop.training.content_sharing.sharing_simple_data;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.smart.develop.R;

import java.io.InputStream;

/**
 * FileName: SharingSimpleDataActivity
 *
 * Des: Training
 *
 *      --Building App with Content Sharing
 *
 *      --Sharing Simple Data
 *
 * Time: 2017/1/9 下午11:53
 */
public class SharingSimpleDataActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mImageView,mReceiveImageView;
    private Button mGetImage,mSendImage,mShareAction;
    private final static int REQUEST_CODE = 0x1;
    private Uri mUri;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_simple_data);
        initView();
        initData();
    }

    private void initView(){
        mImageView = (ImageView)findViewById(R.id.simple_data_show);
        mGetImage = (Button)findViewById(R.id.get_image);
        mSendImage = (Button)findViewById(R.id.send_image);
        mGetImage.setOnClickListener(this);
        mSendImage.setOnClickListener(this);
        mReceiveImageView = (ImageView)findViewById(R.id.simple_data_receive_show);
        mShareAction = (Button)findViewById(R.id.share_action_provider);
        mShareAction.setOnClickListener(this);
    }

    private void initData(){
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                // Handle text being sent
            } else if (type.startsWith("image/")) {
                // Handle single image being sent
                handleReceivedImage(intent);
            }
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                // Handle multiple images being sent
            }
        }
    }

    /**
     * Des: 处理接收到的图片数据
     *
     * Time: 2017/1/10 上午1:13
     */
    private void handleReceivedImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                mReceiveImageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.get_image:
                getImage();
                break;
            case R.id.send_image:
                setImage();
                break;
            case R.id.share_action_provider:
                triggerShareActionProvider();
                break;
        }
    }

    /**
     * Des: 从图库获取图片资源
     *
     * Time: 2017/1/10 上午12:14
     */
    private void getImage(){
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_CODE);
    }

    private void setImage(){
        if(mUri != null){
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, mUri);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.sharing_simple_data))); 
        }else{
            Toast.makeText(this, getResources().getString(R.string.please_choose_a_picture), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(REQUEST_CODE == requestCode){
            if(RESULT_OK == resultCode){
                try {
                    mUri = data.getData();
                    InputStream inputStream = getContentResolver().openInputStream(mUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    mImageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.share_intent, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.item_share_intent);

        // Fetch and store ShareActionProvider
        mShareActionProvider =  (ShareActionProvider)MenuItemCompat.getActionProvider(item);
        // Return true to display menu
        return(super.onCreateOptionsMenu(menu));
    }

    private void triggerShareActionProvider(){
        if(mUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, mUri);
            shareIntent.setType("image/*");
            mShareActionProvider.setShareIntent(shareIntent);
        }else{
            Toast.makeText(this, getResources().getString(R.string.please_choose_a_picture), Toast.LENGTH_SHORT).show();
        }
    }
}


















































