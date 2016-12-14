package com.yun.remote;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestRemoteActivity.class);
                intent.putExtra("remote", "com.yun.remote.TestActivity");
                intent.putExtra("sendMsg", ((EditText) findViewById(R.id.send_msg)).getText()
                        .toString());
                startActivity(intent);
            }
        });
    }
}
