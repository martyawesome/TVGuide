package com.freelancer.tvguide.Activity;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.freelancer.tvguide.Adapter.ChannelAdapter;
import com.freelancer.tvguide.Model.ChannelModel;
import com.freelancer.tvguide.R;
import com.freelancer.tvguide.Remote.GetChannelsInterface;
import com.freelancer.tvguide.Remote.GetChannelsResponse;
import com.freelancer.tvguide.Remote.WebserviceCall;
import com.freelancer.tvguide.Utils.Constants;
import com.freelancer.tvguide.Utils.EndlessScrollListener;
import com.freelancer.tvguide.Utils.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * The dashboard activity is responsible for getting the list of channels from an API,
 * and displays it.
 *
 * @author marty hernandez
 */
@EActivity(R.layout.activity_dashboard)
public class DashboardActivity extends ActionBarActivity implements GetChannelsInterface {

    @ViewById
    ListView channelsListView;
    @ViewById
    ProgressBar progressBar;
    @ViewById
    EditText start;
    @ViewById
    Button submit;

    public static int mStart = 0;
    private int mPreviousStart = 0;
    public static int mRemainingChannels;
    private List<ChannelModel> mCurrentChannels = new ArrayList<>();
    private ChannelAdapter mChannelAdapter;
    private static final String TAG = DashboardActivity.class.getSimpleName();

    @AfterViews
    void initialize() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Click
    void submit() {

        if (Utils.isNetworkAvailable(this)) {

            progressBar.setVisibility(View.VISIBLE);
            mCurrentChannels.clear();
            mStart = 0;
            mRemainingChannels = 0;

            try {
                mStart = Integer.parseInt(start.getText().toString());
                mPreviousStart = mStart;
            } catch (Exception e) {
                e.printStackTrace();
            }

            new WebserviceCall(this, progressBar).getChannels(mStart);

        } else {
            populateOfflineChannels();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            if (Utils.isNetworkAvailable(this)) {

                progressBar.setVisibility(View.VISIBLE);
                mCurrentChannels.clear();
                mRemainingChannels = 0;

                new WebserviceCall(this, progressBar).getChannels(mPreviousStart);

            } else {
                populateOfflineChannels();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Populate the database and the ListView with the channels retrieved from the web service.
     * This is for on getting of channels on startup.
     *
     * @param response
     */
    @Override
    public void getChannels(GetChannelsResponse response) {

        progressBar.setVisibility(View.GONE);

        mCurrentChannels.addAll(response.channels);

        mRemainingChannels = response.count - mStart;
        mStart += Constants.LIST_VISIBLE_ITEMS;

        ChannelModel.updateChannels(mCurrentChannels);

        Log.d(TAG, "Count: " + response.count);

        mChannelAdapter = new ChannelAdapter(this, mCurrentChannels);
        channelsListView.setAdapter(mChannelAdapter);
        channelsListView.setOnScrollListener(new EndlessScrollListener(this, progressBar));

    }

    /**
     * Populate the ListView with channels saved from the database.
     */
    public void populateOfflineChannels() {
        mCurrentChannels.clear();
        progressBar.setVisibility(View.GONE);
        mChannelAdapter = new ChannelAdapter(this, ChannelModel.getAll());
        channelsListView.setAdapter(mChannelAdapter);
        channelsListView.setOnScrollListener(new EndlessScrollListener(this, progressBar));
    }

}
