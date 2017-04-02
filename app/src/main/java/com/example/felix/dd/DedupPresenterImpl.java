package com.example.felix.dd;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by felix on 26/08/2016.
 * MITM for presentation and model
 */
public class DedupPresenterImpl implements DedupPresenter {

    private DedupView dedupView;
    private DedupInteractor dedupInteractor;

    public DedupPresenterImpl(DedupView dedupView) {
        this.dedupView = dedupView;
        this.dedupInteractor = new DedupInteractorImpl();
    }

    @Override
    public void createPhotos(ContentResolver contentResolver) {

        if (dedupView != null) {
            dedupView.showMessage("Processing " + "createPhotos");
            dedupInteractor.createPhotos(contentResolver);
        }
    }

    @Override
    public void deleteFiles() {
        if(dedupView !=null){
            dedupView.showMessage("Processing " + "deleteFiles");
            dedupInteractor.deleteFiles();
        }
    }

    @Override
    public Boolean permissions(Context context, Activity activity) {

        if (dedupView != null) {
            dedupView.showMessage("Processing " + "permissions");

            PermissionMgr permission = new PermissionMgr(context, activity);

            permission.set(new Permission(Manifest.permission.READ_CALL_LOG, "Read Call LOG"));
            permission.set(new Permission(Manifest.permission.READ_CONTACTS, "Read Contacts"));
            permission.set(new Permission(Manifest.permission.READ_SMS, "READ SMS"));
            permission.set(new Permission(Manifest.permission.READ_CALENDAR, "READ Calendar"));
            permission.set(new Permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, "WRITE External Storage"));

            boolean check = dedupInteractor.check(permission);

            if (!check) {
                //Ask for permissions

                System.out.println("Access Denied");
                System.out.println("Requesting Access");

                dedupInteractor.request(permission);

                return false;

            } else {
                return true;
            }
        }

        return true;

    }

    @Override public void onItemClicked() {

        //call dedup interactor for method you wish to invoke

        if (dedupView != null) {
            dedupView.showMessage(String.format("Button clicked"));
        }
    }
}