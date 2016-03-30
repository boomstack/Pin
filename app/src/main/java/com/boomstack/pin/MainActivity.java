package com.boomstack.pin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.boomstack.pyutil.PyUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        char c = '王';
        System.out.println(PyUtil.isChinese(c));
    }
}
