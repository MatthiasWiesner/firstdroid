package de.mwiesner.firstdroid.tasks;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.mwiesner.firstdroid.R;
import de.mwiesner.firstdroid.model.Week;


public class WeekArrayAdapter extends ArrayAdapter<Week> {
    private final Context context;
    private final List<Week> weeks;

    public WeekArrayAdapter(Context context, List<Week> weeks) {
        super(context, R.id.week, weeks);
        this.context = context;
        this.weeks = weeks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View weekView = convertView;

        if(weekView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            weekView = inflater.inflate(R.layout.listelement, parent, false);
        }

        TextView firstline = (TextView) weekView.findViewById(R.id.firstLine);
        TextView secondline = (TextView) weekView.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView) weekView.findViewById(R.id.icon);

        Week week = weeks.get(position);

        firstline.setText(week.person.name);
        secondline.setText(week.week_nr + " " + week.start_date + " - " + week.end_date);
        Picasso.with(context)
            .load(week.person.picture)
            .fit()
            .into(imageView);

        return weekView;
    }
}
