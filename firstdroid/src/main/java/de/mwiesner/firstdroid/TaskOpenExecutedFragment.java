package de.mwiesner.firstdroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.mwiesner.firstdroid.R;


public class TaskOpenExecutedFragment  extends Fragment {
    public static final String ARG_SECTION_NUMBER = "TaskOpenExecutedFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.taskopenexecuted, container, false);
        return view;
    }
}
