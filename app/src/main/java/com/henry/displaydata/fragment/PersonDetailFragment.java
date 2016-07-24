package com.henry.displaydata.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henry.displaydata.R;


public class PersonDetailFragment extends Fragment
{
    public static String FAV_COLOR = "color";
    public static String AGE = "age";
    public static String FIRST_NAME = "firstname";
    public static String LAST_NAME = "lastname";

    TextView txtColorValue,txtAgeValue,txtLastNameValue,txtFirstNameValue;
    int age;
    String color,firstName,lastName;

    public PersonDetailFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        color = getArguments().getString(FAV_COLOR);
        age = getArguments().getInt(AGE);
        firstName = getArguments().getString(FIRST_NAME);
        lastName = getArguments().getString(LAST_NAME);

        View rootView = inflater.inflate(R.layout.person_detail, container, false);
        txtColorValue =(TextView) rootView.findViewById(R.id.txtColorValue);
        txtAgeValue =(TextView) rootView.findViewById(R.id.txtAgeValue);
        txtLastNameValue =(TextView) rootView.findViewById(R.id.txtLastNameValue);
        txtFirstNameValue =(TextView) rootView.findViewById(R.id.txtFirstNameValue);


        txtColorValue.setText(color);
        txtAgeValue.setText(String.valueOf(age));
        txtLastNameValue.setText(lastName);
        txtFirstNameValue.setText(firstName);

        return rootView;
    }
}
