package com.example.mohammedabu.dutyhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.mohammedabu.dutyhelper.Authentication.Login;
import com.example.mohammedabu.dutyhelper.Authentication.RegisterActivity;

/**
 * Created by Mohammed on 25/09/2017.
 */

public class CalenderFragment extends Fragment {

    CalendarView calendarView;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calender, container, false);
        return view;
    }

}
