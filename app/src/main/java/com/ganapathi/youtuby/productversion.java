package com.ganapathi.youtuby;

import android.graphics.Bitmap;

/**
 * Created by Ganapathi on 11-03-2018.
 */

public class productversion {

    private String android_version_name, image_path;
    private Bitmap android_image_url, userprofilepic;

    public String getAndroid_version_name() {
        return android_version_name;
    }

    public void setAndroid_version_name(String android_version_name) {
        this.android_version_name = android_version_name;
    }

    public String getAndroid_imagePath() {
        return image_path;
    }

    public void setAndroid_imagePath(String image_path) {
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
