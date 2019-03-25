package com.ongo.ongologinlibrary.login;


import android.content.Context;
import android.util.Pair;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.ongo.ongologinlibrary.baseclasses.BasePresenter;
import com.ongo.ongologinlibrary.baseclasses.BaseView;
import com.ongo.ongologinlibrary.services.WSResponnse;
import com.ongo.ongologinlibrary.services.WebServices;
import com.ongo.ongologinlibrary.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter extends BasePresenter<LoginView.View> implements LoginView.Presenter {

    Context mContext;
    public LoginPresenter(Context mContext) {
        this.mContext = mContext;

    }
    // login api
    @Override
    public void callLoginApi(String url, int mallId, String mobilenumberStr, String passwordrStr) {

        Utils.showProgress(mContext, false, "Loading");
        String baseUrl = url + "/MobileAPIs/loginConsumerForOrg?";

        WebServices ws = new WebServices(mContext);
        RequestParams params = new RequestParams();
        params.put("orgId", mallId);
        if (Utils.validateEmailAddress(mobilenumberStr)) {
            params.put("email", mobilenumberStr);
        } else {
            params.put("mobileNo", mobilenumberStr);
        }
        params.put("password", passwordrStr);

        ws.invokeWebService(params, baseUrl, new WSResponnse() {
            @Override
            public void onResponse(boolean success, String response) {
                Utils.hideProgress(mContext);
                if (success) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.has("status")) {
                            if (jsonObject.getString("status").equalsIgnoreCase("1")) {
                                Gson gson = new Gson();
                                LoginResponse data = gson.fromJson(response, LoginResponse.class);
                                getView().loginResponce(data);

                            } else if (jsonObject.getString("status").equalsIgnoreCase("-1")) {
                                Utils.toast(jsonObject.getString("msg"), mContext);

                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    Utils.toast("Cannot process the request. Please try after some time", mContext);
                }
            }
        });


    }

    @Override
    public void callSocialLoginsApi(String url,int mailId, final String mobilenumberStr,final String nameStr) {
        String baseUrl = url + "/MobileAPIs/loginConsumerForOrg?";
        RequestParams params1 = new RequestParams();
        params1.put("email", mobilenumberStr);
        params1.put("password", "");
        params1.put("isLoginWithFB", "true");
        params1.put("orgId", mailId);

        WebServices ws = new WebServices(mContext);
        ws.invokeWebService(params1, baseUrl, new WSResponnse() {
            @Override
            public void onResponse(boolean success, String response) {
                if (!response.isEmpty()) {
                    try {
                        JSONObject jObj = new JSONObject(response);
                        if (jObj.getString("status").equals("1")) {
                            Gson gson = new Gson();
                            LoginResponse data = gson.fromJson(response, LoginResponse.class);
                            getView().loginResponce(data);
                        } else if (jObj.getString("status").equalsIgnoreCase("-1")) {
                            Utils.toast(jObj.getString("msg"), mContext);
                            getView().errorLoginResult();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Utils.toast("Cannot process the request. Please try after some time", mContext);

                }
            }
        });


    }



}
