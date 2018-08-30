package com.example.student.notifcationapptask.activitys;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.student.notifcationapptask.R;
import com.example.student.notifcationapptask.adapters.AdapterViewPager;
import com.example.student.notifcationapptask.models.ViewPagerModel;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private final List<ViewPagerModel> list = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


        list.add(new ViewPagerModel(R.drawable.java));
        list.add(new ViewPagerModel(R.drawable.ios));
        list.add(new ViewPagerModel(R.drawable.android));

        final AdapterViewPager adapter = new AdapterViewPager(this, list);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        final TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);

    }
}
