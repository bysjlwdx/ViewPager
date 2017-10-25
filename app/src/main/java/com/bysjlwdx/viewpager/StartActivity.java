package com.bysjlwdx.viewpager;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private List<View> mViews = new ArrayList<View>();
    private LocalActivityManager manager;
    private Intent intentMain, intentCircle, intentMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_start);
        this.getSupportActionBar().hide();//隐藏标题栏
        ViewPager viewPager = (ViewPager) findViewById(R.id.id_ViewPager);
        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);
        intentMain = new Intent(StartActivity.this, MainActivity.class);
        View tab01 = manager.startActivity("viewID", intentMain).getDecorView();
        intentCircle = new Intent(StartActivity.this, CircleActivity.class);
        View tab02 = manager.startActivity("viewID", intentCircle).getDecorView();
        intentMy = new Intent(StartActivity.this, MyActivity.class);
        View tab03 = manager.startActivity("viewID", intentMy).getDecorView();

        mViews.add(tab01);//将页面添加到View集合
        mViews.add(tab02);
        mViews.add(tab03);

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mViews.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(mViews.get(position));


                return mViews.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
        // 配置适配器
    }
}
