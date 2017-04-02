package com.example.felix.dd;

import android.content.ContentResolver;
import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;;
import java.io.OutputStreamWriter;

/**
 * Created by felix on 26/08/2016.
 */
public class DedupInteractorImpl implements DedupInteractor {

    @Override
    public void createPhotos(ContentResolver contentResolver) {

    }

    private void writeFile(String message, String filename) {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {

            File directory = Environment.getExternalStoragePublicDirectory("forensics");

            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file;

            try {

                file = new File(directory, filename);
                if (!file.isFile())
                    file.createNewFile();

                FileOutputStream stream = new FileOutputStream(file, true);
                OutputStreamWriter writer = new OutputStreamWriter(stream);

                writer.write(message);
                writer.append("\n");    //append a new line

                writer.close();

            } catch (Exception e) {

                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean check(PermissionMgr permissionMgr) {
        return permissionMgr.check();
    }

    @Override
    public boolean request(PermissionMgr permissionMgr) {

        return permissionMgr.request();

    }

    public void deleteFiles() {

        try {

            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {

                File directory = Environment.getExternalStoragePublicDirectory("forensics");

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                if(directory.listFiles()!=null) {
                    for (File child : directory.listFiles()) {
                        child.delete();
                    }
                }

                System.out.println("forensics folder emptied...");

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}