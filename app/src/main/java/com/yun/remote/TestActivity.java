package com.yun.remote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dell on 2016/8/1.
 */
public class TestActivity extends AppCompatActivity {
    private ITest iTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back();
                iTest.toDo();
            }
        });
        ((TextView) findViewById(R.id.send_msg)).setText(getIntent().getStringExtra("sendMsg"));
    }


    public void setITest(ITest iTest) {
        this.iTest = iTest;
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Toast.makeText(this, "test1", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy1", Toast.LENGTH_SHORT).show();
    }
}
