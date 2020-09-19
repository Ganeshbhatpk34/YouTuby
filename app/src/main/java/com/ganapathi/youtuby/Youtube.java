package com.ganapathi.youtuby;

/**
 * Created by Ganapathi on 19-09-2020.
 */

import android.graphics.Bitmap;

public class Youtube {

    private String android_version_name, image_path;
    private Bitmap android_image_url, userprofilepic;

    public String getAndroid_youtube_name() {
        return android_version_name;
    }

    public void setAndroid_youtube_name(String android_version_name) {
        this.android_version_name = android_version_name;
    }

    public String getAndroid_youtubePath() {
        return image_path;
    }

    public void setAndroid_youtubePath(String image_path) {
        this.image_path = image_path;
    }

    public Bitmap getAndroid_image_url() {
        return android_image_url;
    }

    public void setAndroid_image_url(Bitmap android_image_url) {
        this.android_image_url = android_image_url;
    }

    public Bitmap getProfileUserpic() {
        return userprofilepic;
    }

    public void setProfileUserpic(Bitmap userprofilepic) {
        this.userprofilepic = userprofilepic;
    }


}
