package com.zxn.titleviewdemo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.zxn.titleview.TitleView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title4)
    TitleView title4;
    @BindView(R.id.title5)
    TitleView title5;
    private boolean isShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        title5.setTitleLeftIconId(R.mipmap.jiantou_bottom);

//        title4.setTitleRightIconId(R.mipmap.jiantou_bottom);

        title4.setOnTitleClickListener(new TitleView.OnTitleClickListener() {
            @Override
            public void onClickTitle(View view) {
                isShow = !isShow;
                title4.showTitleRightIcon(isShow);
            }
        });

        //title4.showTitleRightIcon(false);

    }
}
