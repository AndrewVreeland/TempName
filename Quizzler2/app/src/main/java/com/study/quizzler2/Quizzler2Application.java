package com.study.quizzler2;

import android.app.Application;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

public class Quizzler2Application extends Application {
    public static final String TAG = "quizzler2application";

    @Override
    public void onCreate() {
        super.onCreate();

        try {

            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());


            Amplify.configure(getApplicationContext());
            Log.i(TAG, "Initialized Amplify");
        } catch (AmplifyException ae) {
            Log.e(TAG, "Error initializing Amplify: " + ae.getMessage(), ae);
        }
    }
}