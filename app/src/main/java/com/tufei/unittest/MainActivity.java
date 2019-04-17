package com.tufei.unittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tufei.unittest.di.ActivityComponent;
import com.tufei.unittest.di.DaggerActivityComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    public MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv_hello);
        textView.setText("Hello,tufei!");

        Button button = findViewById(R.id.btn_jump);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


       ActivityComponent activityComponent = DaggerActivityComponent.builder()
               .appComponent(ComponentHolder.getAppComponent())
                .build();
       activityComponent.inject(this);

       presenter.println();
    }
}
