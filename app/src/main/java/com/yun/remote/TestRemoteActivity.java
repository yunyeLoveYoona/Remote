package com.yun.remote;

import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by dell on 2016/12/14.
 */

public class TestRemoteActivity extends RemoteActivity<TestActivity> implements ITest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setITest(this);
    }

    @Override
    public void toDo() {
        getActivity().finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}
