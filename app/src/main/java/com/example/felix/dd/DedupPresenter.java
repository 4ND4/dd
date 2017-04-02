package com.example.felix.dd;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by felix on 26/08/2016.
 */
public interface DedupPresenter {

    void createPhotos(ContentResolver contentResolver);
    void deleteFiles();
    Boolean permissions(Context context, Activity activity);
    void onItemClicked();
}