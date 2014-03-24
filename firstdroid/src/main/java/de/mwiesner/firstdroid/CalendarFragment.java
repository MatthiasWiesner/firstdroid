package de.mwiesner.firstdroid;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.mwiesner.firstdroid.tasks.LoadDataTask;


public class CalendarFragment extends Fragment {
    public static final String ARG_SECTION_NUMBER = "CalendarFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar, container, false);
        new LoadDataTask(getActivity()).execute();
        return view;
    }
}
