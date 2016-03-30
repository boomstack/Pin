package com.boomstack.pin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.boomstack.pyutil.PyUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str="我是中国人";
        String str2="我ye是中国人A";
        System.out.println("hola "+PyUtil.strToPinyinLower(str));
        System.out.println("hola "+PyUtil.strToPinyinLower(str2));
        System.out.println("hola "+PyUtil.strToPinyinLowerAll(str));
        System.out.println("hola "+PyUtil.strToPinyinLowerAll(str2));
        System.out.println("hola "+PyUtil.strToPinyinUpper(str));
        System.out.println("hola "+PyUtil.strToPinyinUpper(str2));
        System.out.println("hola "+PyUtil.strToPinyinUpperAll(str));
        System.out.println("hola "+PyUtil.strToPinyinUpperAll(str2));

    }
}
