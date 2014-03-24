package de.mwiesner.firstdroid;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import de.mwiesner.firstdroid.CalendarFragment;
import de.mwiesner.firstdroid.TaskOpenExecutedFragment;


class TabsListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment fragment;
    private Activity activity;
    private String tag;
    private Class<T> clazz;

    /*
     * @param activity  The host Activity, used to instantiate the fragment
     * @param tag  The identifier tag for the fragment
     * @param clz  The fragment's Class, used to instantiate the fragment
     */
    public TabsListener(Activity a, String t, Class<T> clz) {
        activity = a;
        tag = t;
        clazz = clz;
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // Check if the fragment is already initialized
        if (fragment == null) {
            // If not, instantiate and add it to the activity
            fragment = Fragment.instantiate(activity, clazz.getName());
            ft.add(android.R.id.content, fragment, tag);
        } else {
            // If it exists, simply attach it in order to show it
            ft.attach(fragment);
        }
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragment != null) {
            // Detach the fragment, because another one is being attached
            ft.detach(fragment);
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // User selected the already selected tab. Usually do nothing.
    }
}


public class MainActivity extends FragmentActivity {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current tab position.
     */

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private CalendarFragment calendarFragment;
    private TaskOpenExecutedFragment taskOpenExecutedFragment;

    public MainActivity(){
        calendarFragment = new CalendarFragment();
        taskOpenExecutedFragment = new TaskOpenExecutedFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar to show tabs.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // for each of the sections in the app, add a tab to the action bar.
        actionBar.addTab(actionBar.newTab().setText("Calendar").setTag(calendarFragment.ARG_SECTION_NUMBER)
            .setTabListener(new TabsListener<CalendarFragment>(
                    this,
                    CalendarFragment.ARG_SECTION_NUMBER,
                    CalendarFragment.class)));

        actionBar.addTab(actionBar.newTab().setText("TaskOpen/Executed").setTag(taskOpenExecutedFragment.ARG_SECTION_NUMBER)
            .setTabListener(new TabsListener<TaskOpenExecutedFragment>(
                    this,
                    TaskOpenExecutedFragment.ARG_SECTION_NUMBER,
                    TaskOpenExecutedFragment.class)));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current tab position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current tab position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
            .getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
