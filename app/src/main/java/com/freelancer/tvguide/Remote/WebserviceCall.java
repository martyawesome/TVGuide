package com.freelancer.tvguide.Remote;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.freelancer.tvguide.Activity.DashboardActivity;
import com.freelancer.tvguide.Utils.Constants;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Android 18 on 6/1/2015.
 * <p/>
 * Handles the method/s for communicating with the API.
 */
public class WebserviceCall {

    private Context context;
    private ProgressBar progressBar;

    /**
     * Public constructor. Initializes the context and the progress bar.
     *
     * @param context
     * @param progressBar
     */
    public WebserviceCall(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    /**
     * Connect to the API using the asynchronous method from AndroidQuery to get the
     * list of programs. The JSON wil then be converted to an object using the library GSON.
     *
     * @param start
     */
    public void getChannels(int start) {

        final AQuery aq = new AQuery(context);

        aq.ajax(Constants.API + "start=" + start, JSONObject.class, new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {

                Log.d("GET CHANNELS: ", "URL: " + url);
                if (json != null) {
                    GetChannelsResponse response = new Gson().fromJson(json.toString(), GetChannelsResponse.class);
                    ((DashboardActivity) context).getChannels(response);
                } else {
                    progressBar.setVisibility(View.GONE);
                    //ajax error, show error code
                    Toast.makeText(context, "Something unexpectedly went wrong. Try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
