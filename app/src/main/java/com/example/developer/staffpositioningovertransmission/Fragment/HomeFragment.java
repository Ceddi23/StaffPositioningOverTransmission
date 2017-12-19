package com.example.developer.staffpositioningovertransmission.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.developer.staffpositioningovertransmission.R;

import  android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;


public class HomeFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final Button home_button = (Button) view.findViewById(R.id.home_button);
        final MaterialSpinner home_dropdown_task = (MaterialSpinner) view.findViewById(R.id.home_dropdown);
        final TextView home_text_timestarted = (TextView) view.findViewById(R.id.home_timestarted);
        final TextView home_text_location = (TextView) view.findViewById(R.id.home_location);
        final TextView home_text_longitude = (TextView) view.findViewById(R.id.home_longitude);
        final TextView home_text_latitude = (TextView) view.findViewById(R.id.home_latitude);
        final TextView home_text_client_name = view.findViewById(R.id.textView9);
        final TextView home_text_contact = view.findViewById(R.id.textView10);

        final List<String> taskList = new ArrayList<String>();
        taskList.add("DELIVER 1 (1km away)");
        taskList.add("DELIVER 2 (1.5km away)");
        taskList.add("DELIVER 3 (2km away)");
        taskList.add("DELIVER 4 (2.6km away)");
        taskList.add("DELIVER 5 (4km away)");

        final ArrayAdapter<String> taskAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, taskList);
        taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        home_dropdown_task.setAdapter(taskAdapter);

        home_text_timestarted.setVisibility(View.INVISIBLE);
        home_text_latitude.setVisibility(View.INVISIBLE);
        home_text_location.setVisibility(View.INVISIBLE);
        home_text_longitude.setVisibility(View.INVISIBLE);
        home_text_client_name.setVisibility(View.INVISIBLE);
        home_text_contact.setVisibility(View.INVISIBLE);

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delivery start
                if(taskList.size() >= 1) {
                    if(home_button.getText().equals("START")) {

                        home_button.setText("DELIVERED");
                        home_dropdown_task.setEnabled(false);
                        home_text_timestarted.setVisibility(View.VISIBLE);
                        home_text_timestarted.setText(String.format("Time Started: %s", getTime()));
                        home_text_latitude.setVisibility(View.VISIBLE);
                        home_text_location.setVisibility(View.VISIBLE);
                        home_text_longitude.setVisibility(View.VISIBLE);
                        home_text_client_name.setVisibility(View.VISIBLE);
                        home_text_contact.setVisibility(View.VISIBLE);

                        //Delivery Done
                    } else {
                        home_button.setText("START");
                        home_dropdown_task.setEnabled(true);
                        home_text_timestarted.setVisibility(View.INVISIBLE);
                        home_text_latitude.setVisibility(View.INVISIBLE);
                        home_text_location.setVisibility(View.INVISIBLE);
                        home_text_longitude.setVisibility(View.INVISIBLE);
                        home_text_client_name.setVisibility(View.INVISIBLE);
                        home_text_contact.setVisibility(View.INVISIBLE);
                        taskList.remove(home_dropdown_task.getSelectedItemPosition());
                        home_dropdown_task.setAdapter(taskAdapter);

                    }
                }

                if(taskList.size() < 1  ) {
                    if(!home_button.getText().equals("END RECORDING")) {
                        home_button.setText("END RECORDING");
                        home_dropdown_task.setEnabled(true);
                        home_text_timestarted.setVisibility(View.INVISIBLE);
                        home_text_latitude.setVisibility(View.INVISIBLE);
                        home_text_location.setVisibility(View.INVISIBLE);
                        home_text_longitude.setVisibility(View.INVISIBLE);
                        home_text_client_name.setVisibility(View.INVISIBLE);
                        home_text_contact.setVisibility(View.INVISIBLE);
                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                        dialog.setTitle("Congratulations");
                        dialog.setMessage("You finished all your deliveries for today, you can take rest now!");
                        dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });
                        dialog.show();
                        home_button.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        return view;
    }

    private String getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }
}
