package com.freelancer.tvguide.Utils;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.freelancer.tvguide.Activity.DashboardActivity;
import com.freelancer.tvguide.Remote.WebserviceCall;

/**
 * Created by Android 18 on 6/1/2015.
 * <p/>
 * Custom listener for the Scroll. Checks if the first visible item is equal to the
 * difference between the total items and the visible item count. If the the position is
 * at the bottom of the ListView, retrieve the next programs from the API.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {

    private int previousFirstVisibleItem = 0;
    private int previousVisibleItemCount = 0;
    private Context context;
    private int counter = 0;
    private ProgressBar progressBar;

    public EndlessScrollListener(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem == totalItemCount - visibleItemCount) {

            counter++;
            previousFirstVisibleItem = firstVisibleItem;
            previousVisibleItemCount = visibleItemCount;

            if (counter == 1 && DashboardActivity.mRemainingChannels > 0) {
                // End of list\
                if (Utils.isNetworkAvailable(context)) {
                    DashboardActivity.mLastPosition = firstVisibleItem;
                    progressBar.setVisibility(View.VISIBLE);
                    new WebserviceCall(context, progressBar).getChannels(DashboardActivity.mStart);
                } else {
                    Toast.makeText(context, "Not connected to the Internet", Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            counter = 0;
        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}