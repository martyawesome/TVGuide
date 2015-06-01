package com.freelancer.tvguide.Model;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android 18 on 6/1/2015.
 * <p/>
 * Model for the channel. This model serializes the keys from a JSON object
 * to create their object. Also, this class extends to a Model - a class from
 * ActiveAndroid - to easily create, retrieve, update and delete data from the
 * database using SQlite.
 *
 * @author marty hernandez
 * @see com.google.gson.Gson
 * @see serialization
 * @see Model
 */
@Table(name = "channel")
public class ChannelModel extends Model {

    @Column(name = "name")
    @SerializedName("name")
    public String name;

    @Column(name = "start_time")
    @SerializedName("start_time")
    public String startTime;

    @Column(name = "end_time")
    @SerializedName("end_time")
    public String endTime;

    @Column(name = "channel")
    @SerializedName("channel")
    public String channel;

    @Column(name = "rating")
    @SerializedName("rating")
    public String rating;

    /**
     * Update the channels in the database
     *
     * @param channelModels
     */
    public static void updateChannels(List<ChannelModel> channelModels) {

        ActiveAndroid.beginTransaction();
        try {

            ChannelModel.deleteAll();

            for (ChannelModel channelModel : channelModels) {
                channelModel.save();
            }

            ActiveAndroid.setTransactionSuccessful();

        } finally {
            ActiveAndroid.endTransaction();
        }

    }

    /**
     * Deletes all ChannelModels in the database.
     */
    public static void deleteAll() {
        new Delete().from(ChannelModel.class).execute();
    }

    /**
     * Returns all of the ChannelModels in the database.
     */
    public static List<ChannelModel> getAll() {
        return new Select().from(ChannelModel.class).execute();
    }

}
