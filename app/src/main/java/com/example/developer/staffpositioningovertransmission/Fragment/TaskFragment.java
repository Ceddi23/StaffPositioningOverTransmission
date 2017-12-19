package com.example.developer.staffpositioningovertransmission.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View taskView = inflater.inflate(R.layout.fragment_task, container, false);

        List<String> tasks = new ArrayList<String>();
        tasks.add("DELIVER 1");
        tasks.add("DELIVER 2");
        tasks.add("DELIVER 3");
        tasks.add("DELIVER 4");
        tasks.add("DELIVER 5");
        List<String> status = new ArrayList<String>();
        status.add("Pending");
        status.add("Pending");
        status.add("Pending");
        status.add("Pending");
        status.add("Pending");

        ArrayAdapter adapter = new TasklistAdapter(this.getContext(),tasks,status);
        ListView listView = (ListView) taskView.findViewById(R.id.taskListView);
        listView.setAdapter(adapter);

        return  taskView;
    }


}
