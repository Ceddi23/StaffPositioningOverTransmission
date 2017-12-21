package com.example.developer.staffpositioningovertransmission.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.developer.staffpositioningovertransmission.R;

import java.util.List;

/**
 * Created by developer on 2017/12/15.
 */

public class TasklistAdapter extends ArrayAdapter<String>{

    List<String> tasks;

    Context c;
    LayoutInflater inflater;


    public TasklistAdapter(Context context, List<String> tasks) {
        super(context, R.layout.custom_tasklist_row, tasks);

        this.c = context;
        this.tasks = tasks;

    }

    public class ViewHolder{

        TextView task;
        TextView stats;
        ImageView stats_img;
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
        holder.stats_img = (ImageView) convertView.findViewById(R.id.custom_tasklist_status_icon);

        switch (position) {
            case 0:
                holder.task.setText(tasks.get(position));
                holder.stats.setText(String.format("Delivered"));
                holder.stats_img.setImageResource(R.drawable.task_status_delivered);
                break;
            case 1:
                holder.task.setText(tasks.get(position));
                holder.stats.setText(String.format("Delivering..."));
                holder.stats_img.setImageResource(R.drawable.task_status_ondelivery);
                break;
            default:
                holder.task.setText(tasks.get(position));
                holder.stats.setText(String.format("Pending"));
                holder.stats_img.setImageResource(R.drawable.task_status_pending);
                break;
        }
        return convertView;
    }
}
