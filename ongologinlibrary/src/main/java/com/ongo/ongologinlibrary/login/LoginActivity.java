package com.ongo.ongologinlibrary.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.ongo.ongologinlibrary.R;
import com.ongo.ongologinlibrary.baseclasses.BaseActivity;
import com.ongo.ongologinlibrary.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends BaseActivity implements LoginView.View {
    private static final String TAG = "LoginActivity.";
    private static final int FB_SIGN_IN = 300;
    private static final int G_SIGN_IN = 400;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;

    EditText ed_mobile_number, ed_password;
    Button btn_submit;
    SignInButton ivGoogle;
    LoginPresenter loginPresenter;
    LoginButton mLoginButton;
    private String accessToken;
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private boolean isSocialLogin = false;
    String baseUrl;
    String isClicked;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        FacebookSdk.sdkInitialize(context, FB_SIGN_IN);
        AppEventsLogger.activateApp(this);
        googleSignInIntialisation();
        setContentView(R.layout.activity_login);
        baseUrl = getIntent().getStringExtra("baseUrl");
        printHashKey(this);
        intialiViews();

      /*  Bundle bundle = getIntent().getExtras();
        baseUrl = bundle.getString("baseUrl");
        isClicked = bundle.getString("isClicked");

        if (isClicked != null && !isClicked.isEmpty()) {

            if (isClicked.equalsIgnoreCase("google")) {
                googleLogin();

            } else if (isClicked.equalsIgnoreCase("facebook")) {
                fbLogin();

            }

        }
*/

    }

    public void googleSignInIntialisation() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(LoginActivity.this.getResources().getString(R.string.server_client_id))
                .requestEmail()
                .build();

        try {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    //.enableAutoManage(this /* FragmentActivity */, (GoogleApiClient.OnConnectionFailedListener) this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void printHashKey(Context pContext) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.e("KeyHash: ", "" + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void intialiViews() {
        inputLayoutEmail = findViewById(R.id.input_layout_email);
        inputLayoutPassword = findViewById(R.id.input_layout_password);

        ed_mobile_number = findViewById(R.id.ed_mobile_number);
        ed_password = findViewById(R.id.ed_password);

        mLoginButton = findViewById(R.id.login_button);
        ivGoogle = findViewById(R.id.ivGoogle);
        btn_submit = findViewById(R.id.btn_submit);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fbLogin();


            }
        });


        ivGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogin();


            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();


            }
        });

    }

    public void googleLogin() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, G_SIGN_IN);


    }

    public void fbLogin() {

        mLoginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, (Arrays.asList("user_birthday", "public_profile", "user_friends", "user_about_me", "email")));
        mLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                accessToken = loginResult.getAccessToken().getToken();
                                // Get facebook data from login
                                Bundle bFacebookData = getFacebookData(object);
                                Log.e("bfbdata", "" + bFacebookData);
                                assert bFacebookData != null;
                                String id = bFacebookData.getString("idFacebook");
                                String profilePic = bFacebookData.getString("profile_pic");
                                String email = bFacebookData.getString("email");
                                String gender = bFacebookData.getString("gender");
                                String fullName = bFacebookData.getString("first_name") + bFacebookData.getString("last_name");
                                String birthday = bFacebookData.getString("user_birthday");

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name,gender, email,age_range,birthday"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private boolean validatePassword() {

        if (ed_password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("Enter Password");
            requestFocus(ed_password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;

    }

    private boolean validateNumberOrEmail() {
        if (ed_mobile_number.getText().toString().trim().isEmpty()) {
            inputLayoutEmail.setError("Enter Mobile number or Email");
            requestFocus(ed_mobile_number);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;

    }

    private void requestFocus(View view) {

        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private Bundle getFacebookData(JSONObject object) {

        String email = "", name = "";

        Bundle bundle = new Bundle();
        try {
            String id = object.getString("id");
            Log.e("object", "" + object);
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=400");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
            bundle.putString("idFacebook", id);
            if (object.has("first_name")) {
                bundle.putString("first_name", object.getString("first_name"));
                name = object.getString("first_name");
            }

            if (object.has("last_name")) {
                bundle.putString("last_name", object.getString("last_name"));
                name = name + "" + object.getString("last_name");
            }


            if (object.has("email")) {
                bundle.putString("email", object.getString("email"));
                email = object.getString("email");
            }
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("user_location"))
                bundle.putString("user_location", object.getString("user_location"));

            // login("FaceBok", email, "");

            loginPresenter.callSocialLoginsApi(baseUrl, 2, email, name);
            isSocialLogin = true;

        } catch (JSONException e) {
            Log.e("123", "" + e.getLocalizedMessage());
        }
        return bundle;
    }

    private boolean validateLogin(String mobilenumberStr, String passwordrStr) {
        if (ed_mobile_number.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Mobile is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ed_password.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    @Override
    protected void initializePresenter() {
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setView(this);
        setBasePresenter(loginPresenter);

    }

    @Override
    protected void onCreateView(Bundle saveInstanceState) {

    }

    @Override
    public void loginResponce(LoginResponse loginResponse) {

        if (loginResponse.getStatus().equals("1")) {
            Toast.makeText(this, loginResponse.getEmailId(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("LoginDto", loginResponse);
            setResult(Activity.RESULT_OK, intent);
            finish();

        } else {
            Toast.makeText(this, "invalid user name or password", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void errorLoginResult() {

        Intent intent = new Intent();
        intent.putExtra("LoginDto", "LoginDto");
        setResult(Activity.RESULT_OK, intent);
        finish();



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == G_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else if (requestCode == FB_SIGN_IN) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            fbLogout(context);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            assert acct != null;
            Log.e(TAG, "display name: " + acct.getDisplayName());


            String personName = "";
            String personPhotoUrl = "";
            String email = "";
            try {
                personName = acct.getDisplayName();
                personPhotoUrl = acct.getPhotoUrl().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                email = acct.getEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String personId = acct.getId();
            Log.e(TAG, "Name: " + personName + ", email: " + email + ", Image: " + personPhotoUrl + ", personId: " + personId);
            // login("GooglePlus", email, "");

            loginPresenter.callSocialLoginsApi(baseUrl, 2, email, personName);
            isSocialLogin = true;
            signOut();

        }
    }

    public void signOut() {
        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {

                // FirebaseAuth.getInstance().signOut();
                if (mGoogleApiClient.isConnected()) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.d(TAG, "User Logged out");
                            }
                        }
                    });
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.d(TAG, "Google API Client Connection Suspended");
            }
        });
    }

    private static void fbLogout(Context mContext) {

        try {

//            FacebookSdk.sdkInitialize(mContext);
            new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                    .Callback() {
                @Override
                public void onCompleted(GraphResponse graphResponse) {

                    LoginManager.getInstance().logOut();

                }
            }).executeAsync();
        } catch (Exception e) {
            Log.e("exp", "" + e.getLocalizedMessage());
        }
    }


    private void login() {

        if (!validateNumberOrEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        if (Utils.isNetworkAvailable(context)) {
            loginPresenter.callLoginApi(baseUrl, 2, ed_mobile_number.getText().toString(), ed_password.getText().toString());
        } else {
            Utils.toast(context.getResources().getString(R.string.network_not_available), context);
        }

               /* if (validateLogin(ed_mobile_number.getText().toString(), ed_password.getText().toString())) {
                    // todo change below  base url , mall id
                    if (Utils.isNetworkAvailable(context)) {
                        loginPresenter.callLoginApi(baseUrl, 2, ed_mobile_number.getText().toString(), ed_password.getText().toString());
                    } else {
                        Utils.toast(context.getResources().getString(R.string.network_not_available), context);
                    }

                }*/

    }
}
