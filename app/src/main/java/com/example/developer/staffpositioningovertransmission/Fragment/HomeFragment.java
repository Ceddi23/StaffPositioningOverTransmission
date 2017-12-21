package com.example.developer.staffpositioningovertransmission.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.developer.staffpositioningovertransmission.R;

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
        final TextView home_text_client_name = view.findViewById(R.id.home_client_name);
        final TextView home_text_contact = view.findViewById(R.id.home_client_number);
        final TextView home_text_queue = view.findViewById(R.id.home_queue);

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
        home_text_queue.setVisibility(View.INVISIBLE);

        home_text_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home_button.setText("START");
                home_dropdown_task.setEnabled(true);
                home_text_timestarted.setVisibility(View.INVISIBLE);
                home_text_latitude.setVisibility(View.INVISIBLE);
                home_text_location.setVisibility(View.INVISIBLE);
                home_text_longitude.setVisibility(View.INVISIBLE);
                home_text_client_name.setVisibility(View.INVISIBLE);
                home_text_contact.setVisibility(View.INVISIBLE);
                home_text_queue.setVisibility(View.INVISIBLE);
            }
        });

        home_text_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Contact Client");
                builder.setMessage("Do you want to Call the Client?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (Build.VERSION.SDK_INT >= 23) {
                            if (ActivityCompat.checkSelfPermission(getContext(),
                                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                            } else {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + home_text_contact.getText()));
                                startActivity(callIntent);
                            }

                        } else {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + home_text_contact.getText()));
                            startActivity(callIntent);
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskList.size() >= 1) {
                    if (home_dropdown_task.getSelectedItemPosition() != 0) {
                        if (home_button.getText().equals("START")) {

                            home_button.setText("DELIVERED");
                            home_dropdown_task.setEnabled(false);
                            home_text_timestarted.setVisibility(View.VISIBLE);
                            home_text_timestarted.setText(String.format("Time Started: %s", getTime()));
                            home_text_latitude.setVisibility(View.VISIBLE);
                            home_text_location.setVisibility(View.VISIBLE);
                            home_text_longitude.setVisibility(View.VISIBLE);
                            home_text_client_name.setVisibility(View.VISIBLE);
                            home_text_contact.setVisibility(View.VISIBLE);
                            home_text_queue.setVisibility(View.VISIBLE);

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
                            home_text_queue.setVisibility(View.INVISIBLE);

                            taskList.remove(home_dropdown_task.getSelectedItemPosition() - 1);
                            home_dropdown_task.setAdapter(taskAdapter);
                            if (taskList.size() != 0) {
                                home_dropdown_task.setSelection(1);
                            } else {
                                home_dropdown_task.setHint("");
                            }
                        }
                    } else {
                        AlertDialog.Builder alert_select_task = new AlertDialog.Builder(view.getContext());
                        alert_select_task.setMessage("Please Select Task First before pressing START button");
                        alert_select_task.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alert_select_task.show();
                    }

                }

                if (taskList.size() < 1) {
                    if (!home_button.getText().equals("END RECORDING")) {
                        home_button.setText("END RECORDING");
                        home_dropdown_task.setEnabled(true);
                        home_text_timestarted.setVisibility(View.INVISIBLE);
                        home_text_latitude.setVisibility(View.INVISIBLE);
                        home_text_location.setVisibility(View.INVISIBLE);
                        home_text_longitude.setVisibility(View.INVISIBLE);
                        home_text_client_name.setVisibility(View.INVISIBLE);
                        home_text_contact.setVisibility(View.INVISIBLE);
                        home_text_queue.setVisibility(View.INVISIBLE);

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
