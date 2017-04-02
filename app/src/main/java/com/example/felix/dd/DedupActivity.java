package com.example.felix.dd;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by felix on 26/08/2016.
 */
public class DedupActivity extends Activity implements DedupView {

    private DedupPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);

        btn.setText("TEST");

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //invoke functions

                presenter.onItemClicked();

            }
        });

        presenter = new DedupPresenterImpl(this);

    }

    @Override public void showMessage(String message) {
        System.out.println(message);
    }
}