package com.zxn.titleviewdemo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zxn on 2019-4-11 23:23:40.
 */
public class Main3Activity extends AppCompatActivity {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    public static void jumpTo(Context context, String param1) {
        Intent intent = new Intent(context, Main3Activity.class);
        intent.putExtra(ARG_PARAM1, param1);
        context.startActivity(intent);
    }

    public static void jumpTo(Context context) {
        Intent intent = new Intent(context, Main3Activity.class);
        context.startActivity(intent);
    }

    protected int getLayoutResId() {
        return R.layout.activity_main3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam1 = getIntent().getStringExtra(ARG_PARAM1);
    }
}
