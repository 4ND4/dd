package com.example.felix.dd;

import android.content.ContentResolver;

/**
 * Created by felix on 26/08/2016.
 */
public interface DedupInteractor {

    void createPhotos(ContentResolver contentResolver);
    boolean check(PermissionMgr permissionMgr);
    boolean request(PermissionMgr permissionMgr);
    void deleteFiles();

}