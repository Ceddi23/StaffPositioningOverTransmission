package com.example.developer.staffpositioningovertransmission.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.developer.staffpositioningovertransmission.R;

import java.util.List;

/**
 * Created by developer on 2017/12/15.
 */

public class TasklistAdapter extends ArrayAdapter<String>{

    List<String> tasks;
    List<String> status;
    Context c;
    LayoutInflater inflater;


    public TasklistAdapter(Context context, List<String> tasks, List<String> status) {
        super(context, R.layout.custom_tasklist_row, tasks);

        this.c = context;
        this.tasks = tasks;
        this.status = status;

    }

    public class ViewHolder{

        TextView task;
        TextView stats;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_tasklist_row, null);

        }

        final ViewHolder holder = new ViewHolder();

        holder.task = (TextView) convertView.findViewById(R.id.custom_tasklist_task);
        holder.stats = (TextView) convertView.findViewById(R.id.custom_tasklist_status);

        holder.task.setText(tasks.get(position));
        holder.stats.setText(String.format("Status: %s", status.get(position)));
        return convertView;
    }


}
