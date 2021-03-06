package com.freelancer.tvguide.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.freelancer.tvguide.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * SplashActivity. Display the app icon before starting the DashboardActivity.
 * <p/>
 * * @author marty hernandez
 */
@EActivity(R.layout.activity_splash)
public class SplashActivity extends ActionBarActivity {

    private Context mContext = SplashActivity.this;

    /**
     * Created a Handler to wait for 3 seconds before starting to the next Activity.
     * * @author marty hernandez
     */
    @AfterViews
    void initialize() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent intent = new Intent(mContext, DashboardActivity_.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                // close this activity
                finish();
            }
        }, 3000);


    }

}
