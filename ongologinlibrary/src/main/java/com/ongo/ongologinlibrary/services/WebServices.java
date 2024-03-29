package com.ongo.ongologinlibrary.services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class WebServices extends Activity {

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */

    Context context;
    List<String> data;

    public WebServices(Context c) {
        this.context = c;
        data = new ArrayList<String>();
    }

    public void invokeWebService(RequestParams params, final String tag, final WSResponnse wsResponnse
    ) {
        // Make RESTful webservice call using AsyncHttpClient object
        final AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(30, 100000);
        client.setConnectTimeout(5 * 100000);
        client.setResponseTimeout(7 * 100000);

        //showProgress();

        client.get(tag, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {

                hideProgress();
                try {
                    String str = new String(responseBody, "UTF-8");
//                        Log.i("TAG", "onSuccess: >>>>>>>>  "+str);
//                        Log.i("TAG", "onSuccess: >>>>>>>>  "+"    "+ IOUtils.toString(responseBody, "UTF-8"));
                    wsResponnse.onResponse(true, str);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                hideProgress();
                Log.e("TAG", "onFailure: >>>>>>>>  " + responseBody);
                wsResponnse.onResponse(false, "");
            }
        });
    }



    private ProgressDialog mProgressDialog;

    void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {
                mProgressDialog.dismiss();
            }
        }
    }

    void showProgress() {
        mProgressDialog = new ProgressDialog(context);
        //  mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Fetching data..");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        try {
            if (mProgressDialog != null && !mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            Log.e("exp in feedspinr", "" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


}
