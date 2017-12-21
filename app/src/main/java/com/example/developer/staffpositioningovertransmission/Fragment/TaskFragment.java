package com.example.developer.staffpositioningovertransmission.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.developer.staffpositioningovertransmission.Adapter.TasklistAdapter;
import com.example.developer.staffpositioningovertransmission.MainActivity;
import com.example.developer.staffpositioningovertransmission.R;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View taskView = inflater.inflate(R.layout.fragment_task, container, false);

        final List<String> tasks = new ArrayList<String>();
        tasks.add("DELIVER 1");
        tasks.add("DELIVER 2");
        tasks.add("DELIVER 3");
        tasks.add("DELIVER 4");
        tasks.add("DELIVER 5");

        ArrayAdapter adapter = new TasklistAdapter(this.getContext(),tasks);
        ListView listView = (ListView) taskView.findViewById(R.id.taskListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog.Builder welcomeDialog = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LayoutInflater.from(taskView.getContext());
                final View dialogView = inflater.inflate(R.layout.custom_tasklist_row_item, null);
                welcomeDialog.setView(dialogView);

                welcomeDialog.setCancelable(true);
                welcomeDialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                welcomeDialog.setTitle(tasks.get(position));
                welcomeDialog.show();
            }
        });

        return  taskView;
    }
}
