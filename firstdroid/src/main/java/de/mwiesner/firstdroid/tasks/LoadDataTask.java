package de.mwiesner.firstdroid.tasks;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.okrest.http.HTTPClient;
import com.okrest.http.HTTPMethod;
import com.okrest.json.JSONClient;
import com.okrest.utils.QueryParamsBuilder;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import de.mwiesner.firstdroid.R;
import de.mwiesner.firstdroid.model.Weeks;
import de.mwiesner.firstdroid.model.Week;


public class LoadDataTask extends AsyncTask<Void, Void, Weeks> {

    Activity activity;
    SimpleDateFormat heroDateFormat;
    ListView listView;


    public LoadDataTask(Activity a){
        activity = a;
        heroDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    protected Weeks doInBackground(Void... parameter) {
        String url = "https://hero.cloudcontrolled.com";
        try {
            Map<String, Object> params = new QueryParamsBuilder()
                .set("fetch_type", "next")
                .set("init_date", heroDateFormat.format(new Date()))
                .build();
            JSONClient<Weeks> client = new JSONClient<Weeks>(url, Weeks.class);
            return client.send(HTTPMethod.GET, "/calendar/load", params);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Weeks weeks) {
        listView = (ListView) activity.findViewById(R.id.listview);
        WeekArrayAdapter adapter = new WeekArrayAdapter(activity, weeks.weeks);
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Week week = (Week) parent.getItemAtPosition(position);

                final CharSequence[] items = {week.person.phone, week.person.email};

                AlertDialog.Builder ad = new AlertDialog.Builder(activity);
                ad.setTitle(week.person.name);
                ad.setItems(items, new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog,int id){
                        if (id == 0){
                            // Phone
                            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + (String)items[id]));
                            activity.startActivity(callIntent);
                        }
                        if (id == 1){
                            // Email
                            String address = (String)items[1];
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", address, null));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mail from opsdroid");
                            activity.startActivity(Intent.createChooser(emailIntent, "Send email to " + address));
                        }
                    }
                });
                ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick (DialogInterface dialog,int id){
                    dialog.cancel();
                }});
                ad.show();
                }
            });
    }
}