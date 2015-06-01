package com.freelancer.tvguide.Remote;

import com.freelancer.tvguide.Model.ChannelModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android 18 on 6/1/2015.
 * <p/>
 * Serialize the return of the API and parse it to an object.
 * <p/>
 * * @author marty hernandez
 *
 * @see com.google.gson.Gson
 */
public class GetChannelsResponse {

    @SerializedName("results")
    public List<ChannelModel> channels = new ArrayList<>();

    @SerializedName("count")
    public int count;

}
