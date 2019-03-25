package com.ongo.loginlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.ongo.ongologinlibrary.login.LoginActivity;
import com.ongo.ongologinlibrary.utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_login;
    Context mContext;
    private static final int LOGIN_CODE = 100;
    String baseUrl = "http://149.129.137.162:8081";
    ImageView im_facebook, im_google;

    LoginActivity loginActivity;
    private static final int FB_SIGN_IN = 300;
    private static final int G_SIGN_IN = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        im_facebook = findViewById(R.id.im_facebook);
        im_google = findViewById(R.id.im_google);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        im_facebook.setOnClickListener(this);
        im_google.setOnClickListener(this);
        splashMethode();
    }

    private void splashMethode() {
        final boolean _active = true;
        final int _splashTime = 1000;

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {

                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if (Utils.isNetworkAvailable(mContext)) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                callNextScreens();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                // Utils.sacaSnackbar(mContext,v,"Internet Connection Not Available");
                                // Utils.snackBarRelative(relativeLayout,v,getString(R.string.network_not_enabled));
                                Utils.toast(mContext.getResources().getString(R.string.network_not_available), mContext);
                            }
                        });
                    }
                }
            }
        };
        splashTread.start();

    }

    private void callNextScreens() {


        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra("baseUrl", baseUrl);
        startActivityForResult(intent, LOGIN_CODE);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.im_facebook:

                callFaceBookLogin();
                break;
            case R.id.im_google:
                callGoogleLogin();
                break;


        }
    }

    private void callGoogleLogin() {

        Intent intent = new Intent(mContext, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("isClicked", "google");
        bundle.putString("baseUrl", baseUrl);
        intent.putExtras(bundle);
        startActivityForResult(intent, LOGIN_CODE);


    }

    private void callFaceBookLogin() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("isClicked", "facebook");
        bundle.putString("baseUrl", baseUrl);
        intent.putExtras(bundle);
        startActivityForResult(intent, LOGIN_CODE);


    }

    private void login() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra("baseUrl", baseUrl);
        startActivityForResult(intent, LOGIN_CODE);

    }
}
