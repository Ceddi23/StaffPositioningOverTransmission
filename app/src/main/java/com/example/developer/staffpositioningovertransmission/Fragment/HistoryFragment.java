package com.example.developer.staffpositioningovertransmission.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.developer.staffpositioningovertransmission.R;

import java.util.Calendar;

public class HistoryFragment extends Fragment {

    int timePickFlg;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_history, container, false);

        final DatePicker datePicker = view.findViewById(R.id.dpDatePicker);
        final Button historyButton = view.findViewById(R.id.btnOk);
        final Button okDateTimeButton = view.findViewById(R.id.btnOkTimeDate);
        final EditText dateText = view.findViewById(R.id.dateText);
        final EditText timeFrom = view.findViewById(R.id.timeFromText);
        final EditText timeTo = view.findViewById(R.id.timeToText);
        final TimePicker timePick = view.findViewById(R.id.tpTimePick);

        final CalendarDates calDate = new CalendarDates();
        final Calendar cal = Calendar.getInstance();

        final String[] monthList = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        final int timeFromFlg = 1;
        final int timeToFlg = 2;
        final int dateFlg = 3;

        datePicker.setVisibility(View.INVISIBLE);
        timePick.setVisibility(View.INVISIBLE);
        okDateTimeButton.setVisibility(View.INVISIBLE);
        dateText.setInputType(InputType.TYPE_NULL);
        timeFrom.setInputType(InputType.TYPE_NULL);

        dateText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickFlg = dateFlg;
                datePicker.setVisibility(View.VISIBLE);
                okDateTimeButton.setVisibility(View.VISIBLE);
                hidePrimaryControls(dateText, timeFrom, timeTo, historyButton);
            }
        });

        timeFrom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickFlg = timeFromFlg;
                timePick.setVisibility(View.VISIBLE);
                okDateTimeButton.setVisibility(View.VISIBLE);
                hidePrimaryControls(dateText, timeFrom, timeTo, historyButton);
            }
        });

        timeTo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickFlg = timeToFlg;
                timePick.setVisibility(View.VISIBLE);
                okDateTimeButton.setVisibility(View.VISIBLE);
                hidePrimaryControls(dateText, timeFrom, timeTo, historyButton);
            }
        });

        okDateTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String period = timePick.getCurrentHour() < 12 ? "AM" : "PM";
                int timePeriod = timePick.getCurrentHour();
                if (timePeriod > 12) {
                    timePeriod -= 12;
                }
                if (timePickFlg == timeFromFlg) {
                    timeFrom.setText(timePeriod + ":" + String.format("%02d", timePick.getCurrentMinute()) + " " + period);
                    timePick.setVisibility(View.INVISIBLE);
                    okDateTimeButton.setVisibility(View.INVISIBLE);
                    showPrimaryControls(dateText, timeFrom, timeTo, historyButton);
                } else if (timePickFlg == timeToFlg) {
                    timeTo.setText(timePeriod + ":" + String.format("%02d", timePick.getCurrentMinute()) + " " + period);
                    timePick.setVisibility(View.INVISIBLE);
                    okDateTimeButton.setVisibility(View.INVISIBLE);
                    showPrimaryControls(dateText, timeFrom, timeTo, historyButton);
                } else if (timePickFlg == dateFlg) {
                    dateText.setText(monthList[datePicker.getMonth()] + " " + datePicker.getDayOfMonth() + ", " + datePicker.getYear());
                    calDate.mDay = datePicker.getDayOfMonth();
                    calDate.mYear = datePicker.getYear();
                    calDate.mMonth = datePicker.getMonth();
                    datePicker.setVisibility(View.INVISIBLE);
                    okDateTimeButton.setVisibility(View.INVISIBLE);
                    showPrimaryControls(dateText, timeFrom, timeTo, historyButton);
                }
                timePick.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
                timePick.setCurrentMinute(cal.get(Calendar.MINUTE));
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(view.getContext());

                alertadd.setTitle("Route history (" + monthList[calDate.mMonth] + " " + calDate.mDay + "," + calDate.mYear + ")");
                LayoutInflater factory = LayoutInflater.from(view.getContext());
                if (!dateText.getText().toString().isEmpty()) {
                    if (calDate.mDay % 2 == 0) {
                        final View view = factory.inflate(R.layout.bluestaff_image_dialog, null);
                        alertadd.setView(view);
                    } else {
                        final View view = factory.inflate(R.layout.redstaff_image_dialog, null);
                        alertadd.setView(view);
                    }
                } else {
                    alertadd.setTitle("Date not set");
                    alertadd.setMessage("Please select date first");
                }
                alertadd.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertadd.show();
            }
        });
        return view;
    }

    void hidePrimaryControls(EditText dateText, EditText timeFrom, EditText timeTo, Button historyButton) {
        dateText.setVisibility(View.INVISIBLE);
        historyButton.setVisibility(View.INVISIBLE);
        timeFrom.setVisibility(View.INVISIBLE);
        timeTo.setVisibility(View.INVISIBLE);
    }

    void showPrimaryControls(EditText dateText, EditText timeFrom, EditText timeTo, Button historyButton) {
        dateText.setVisibility(View.VISIBLE);
        historyButton.setVisibility(View.VISIBLE);
        timeFrom.setVisibility(View.VISIBLE);
        timeTo.setVisibility(View.VISIBLE);
    }

    private class CalendarDates {
        int mYear;
        int mMonth;
        int mDay;
    }


}
