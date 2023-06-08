package com.emransac.emaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.emransac.emaapp.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar  chipNavigationBar;

    RelativeLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
    public void mercapp(View view) {
        startActivity(new Intent(getApplicationContext(),MercAppActivity.class));
    }

    private void CreatepopUpwindow() {
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView=inflater.inflate(R.layout.mainpopup,null);

        int width= ViewGroup.LayoutParams.MATCH_PARENT;
        int height=ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable=true;
        PopupWindow popupWindow=new PopupWindow(popUpView,width,height,focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);

            }
        });
        TextView Skip ,Gotit;
        Skip=popUpView.findViewById(R.id.Skip);
        Gotit=popUpView.findViewById(R.id.Gotit);
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write code anything you want
            }
        });
        // and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}