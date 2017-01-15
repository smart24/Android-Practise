package com.smart.develop.training.content_sharing.sharing_file_nfc;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.smart.develop.R;

import java.io.File;

/**
 * FileName: SharingFileNFCActivity
 *
 * Des: Training
 *
 *      --Building App with Content Sharing
 *
 *      --Sharing File with NFC
 *
 * Time: 2017/1/14 上午12:15
 */

public class SharingFileNFCActivity extends AppCompatActivity implements NfcAdapter.CreateBeamUrisCallback {

    private NfcAdapter mNfcAdapter;
    private Uri[] mFileUris = new Uri[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_file_nfc);
        verifyNFC();
    }

    private void initView() {

    }

    /**
     * Des: 验证当前设备是否支持 NFC 功能
     *
     * Time: 2017/1/14 上午12:26
     */
    private boolean verifyNFC() {
        if (!this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {
            // Android Beam file transfer isn't supported
            showMsg(getResources().getString(R.string.not_support_nfc));
            return false;
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Android Beam file transfer isn't supported
            showMsg(getResources().getString(R.string.not_support_nfc));
            return false;
        } else {
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
            mNfcAdapter.setBeamPushUrisCallback(this, this);
            showMsg(getResources().getString(R.string.support_nfc));
            return true;
        }
    }

    /**
     * Des: Show Message
     *
     * Time: 2017/1/14 上午12:26
     */
    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Des: 当设备监测到用户想通过 NFC 传输文件时的回调函数
     * <p>
     * Time: 2017/1/14 上午12:31
     */
    @Override
    public Uri[] createBeamUris(NfcEvent event) {
        String transferFile = "transfer_image.jpg";
        File extDir = getExternalFilesDir(null);
        File requestFile = new File(extDir, transferFile);
        requestFile.setReadable(true, false);
        // Get a URI for the File and add it to the list of URIs
        Uri fileUri = Uri.fromFile(requestFile);
        if (fileUri != null) {
            mFileUris[0] = fileUri;
        } else {
            Log.e("NFC Activity", "No File URI available for file.");
        }
        return new Uri[0];
    }
}
