package com.example.matt.termproject;

import android.annotation.TargetApi;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Build;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // custom logic for determining sandbox status
        int confidence = 0; // the level of confidence that we are in a sandbox
        Log.d(TAG, "Beginning API scan for emulation.");
        // Build properties
        // looking for armeabi or unknown; likely
        //for( int i = 0; i < Build.SUPPORTED_ABIS.length; i++) {
        //    Log.d(TAG, "Supported ABI: " + Build.SUPPORTED_ABIS[i]);
        //    if(Build.SUPPORTED_ABIS[i].equals("armeabi") || Build.SUPPORTED_ABIS[i].equals("unknown"))
        //        confidence += 40;
        //}
        // looking for unknown board; very certain
        if(Build.BOARD.equals("unknown"))
            confidence += 50;
        // generic brand; very certain
        if(Build.BRAND.equals("generic"))
            confidence += 50;
        // generic device; very certain
        if(Build.DEVICE.equals("generic"))
            confidence += 50;
        // generic prefix fingerprint; very certain
        if(Build.FINGERPRINT.startsWith("generic"))
            confidence += 50;
        // goldfish hardware; very certain
        if(Build.HARDWARE.equals("goldfish"))
            confidence += 50;
        // android-test prefix host; likely
        if(Build.HOST.startsWith("android-test"))
            confidence += 40;
        // FRF91 ID; very certain
        if(Build.ID.equals("FRF91"))
            confidence += 50;
        // unknown manufacturer; very certain
        if(Build.MANUFACTURER.equals("unknown"))
            confidence += 50;
        // sdk model; very certain
        if(Build.MODEL.equals("sdk"))
            confidence += 50;
        // sdk product; very certain
        if(Build.PRODUCT.equals("sdk"))
            confidence += 50;
        // unknown radio; very certain
        if(Build.RADIO.equals("unknown"))
            confidence += 50;
        // null serial; very certain
        if(Build.SERIAL.equals("null"))
            confidence += 50;
        // test-keys tags; very certain
        if(Build.TAGS.equals("test-keys"))
            confidence += 50;
        // android-build user; very certain
        if(Build.USER.equals("android-builder"))
            confidence += 50;
        // TelephonyManager methods
        TelephonyManager telMan = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        // regular expression matching a string of all zeroes
        if(telMan.getDeviceId().matches("^[0]+$"))
            confidence += 50;
        // check the telephone number prefix
        if(telMan.getLine1Number().startsWith("155552155"))
            confidence += 50;
        // check the network country ISO
        if(telMan.getNetworkCountryIso().equals("us"))
            confidence += 10;
        if(telMan.getNetworkType() == 3) // edge
            confidence += 10;
        if(telMan.getNetworkOperator().startsWith("310"))
            confidence += 10;
        if(telMan.getNetworkOperator().substring(3).startsWith("260"))
            confidence += 10;
        if(telMan.getPhoneType() == 1) // gsm
            confidence += 10;
        if(telMan.getSimCountryIso().equals("us"))
            confidence += 10;
        if(telMan.getSimSerialNumber().equals("89014103211118510720"))
            confidence += 15;
        if(telMan.getSubscriberId().startsWith("310260000000000"))
            confidence += 50;
        if(telMan.getVoiceMailNumber().equals("15552175049"))
            confidence += 50;

        Log.d(TAG, "Total confidence: " + confidence);





        setContentView(R.layout.activity_main);

        TextView txtView = (TextView)findViewById(R.id.hello_world);
        txtView.setText("Total confidence: " + Integer.toString(confidence));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
