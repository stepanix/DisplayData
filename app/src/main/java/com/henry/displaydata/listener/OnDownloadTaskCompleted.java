package com.henry.displaydata.listener;

import com.henry.displaydata.model.Person;

import java.util.ArrayList;

/**
 * Created by Henry.Oforeh on 2016/07/24.
 */
public interface OnDownloadTaskCompleted {
    void onPersonDownloadTaskCompleted(ArrayList<Person> response);
}
