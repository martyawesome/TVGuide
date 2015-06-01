package com.freelancer.tvguide.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.freelancer.tvguide.Model.ChannelModel;
import com.freelancer.tvguide.R;

import java.util.List;

/**
 * Created by Android 18 on 6/1/2015.
 * <p/>
 * Responsible for displaying the values of the ChannelModels to the ListView.
 *
 * @author marty hernandez
 */
public class ChannelAdapter extends BaseAdapter {

    private List<ChannelModel> channelModels;
    private Context context;

    /**
     * Public constructor for the ChannelAdapter. Initializes the global channelModels and context.
     *
     * @param context
     * @param channelModels
     */
    public ChannelAdapter(Context context, List<ChannelModel> channelModels) {
        this.channelModels = channelModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return channelModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_channel, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView start = (TextView) convertView.findViewById(R.id.start);
        TextView end = (TextView) convertView.findViewById(R.id.end);
        TextView channel = (TextView) convertView.findViewById(R.id.channel);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);

        name.setText("Name: " + channelModels.get(position).name);
        start.setText("Start: " + channelModels.get(position).startTime);
        end.setText("End: " + channelModels.get(position).endTime);
        channel.setText("Channel: " + channelModels.get(position).channel);
        rating.setText("Rating: " + channelModels.get(position).rating);

        return convertView;
    }
}
