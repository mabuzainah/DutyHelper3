package com.example.mohammedabu.dutyhelper;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mohammedabu.dutyhelper.Authentication.Login;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends ActionBarActivity {

    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Login.class);
        /**
         * The code below is to create the Bottom Navigation Bar, which appears in all the fragments.
         */

        bottomBar = BottomBar.attach(this,savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.menu_bottombar, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottom_bar_icon_calender){
                    CalenderFragment calenderFragment = new CalenderFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,calenderFragment).commit();
                } else if (menuItemId == R.id.bottom_bar_icon_search){
                    SearchFragment searchFragment = new SearchFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,searchFragment).commit();
                } else if(menuItemId == R.id.bottom_bar_icon_create){
                    CreateFragment createFragment = new CreateFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,createFragment).commit();
                } else if(menuItemId == R.id.bottom_bar_icon_tasks){
                    TasksFragment tasksFragment = new TasksFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,tasksFragment).commit();
                } else if(menuItemId == R.id.bottom_bar_icon_profile){
                    PeopleFragment peopleFragment = new PeopleFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,peopleFragment).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        //Setting the colours for the bottom navigation bar on each fragment page.
        bottomBar.mapColorForTab(0, "#ffc800");
        bottomBar.mapColorForTab(1, "#ffc800");
        bottomBar.mapColorForTab(2, "#ffc800");
        bottomBar.mapColorForTab(3, "#ffc800");
        bottomBar.mapColorForTab(4, "#ffc800");
    }


    @Override
    public void onBackPressed() {
        // Disable going back to the Login Screen from within the application
        moveTaskToBack(true);
    }
}
