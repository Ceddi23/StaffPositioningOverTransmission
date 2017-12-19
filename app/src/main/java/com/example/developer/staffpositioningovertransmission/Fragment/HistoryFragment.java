package com.example.developer.staffpositioningovertransmission.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.developer.staffpositioningovertransmission.R;

import java.util.Calendar;



public class HistoryFragment extends Fragment {
    int mYear;
    int mMonth;
    int mDay;

    public HistoryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_history, container, false);

        final Button historyButton = view.findViewById(R.id.btnOk);
        final DatePicker datePicker = view.findViewById(R.id.dpDatePicker);
        final EditText dateText = view.findViewById(R.id.dateText);
        final TextView tvLabel = view.findViewById(R.id.tvLabel);
        final EditText timeFrom = view.findViewById(R.id.timeFromText);
        final EditText timeTo = view.findViewById(R.id.timeToText);
        final Calendar cal = Calendar.getInstance();

        datePicker.setVisibility(View.INVISIBLE);

        dateText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                dateText.setVisibility(View.INVISIBLE);
                historyButton.setVisibility(View.INVISIBLE);
                tvLabel.setVisibility(View.INVISIBLE);
                timeFrom.setVisibility(View.INVISIBLE);
                timeTo.setVisibility(View.INVISIBLE);
            }
        });

        cal.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                dateText.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                mDay = dayOfMonth;
                mYear = year;
                mMonth = month + 1;
                datePicker.setVisibility(View.INVISIBLE);
                dateText.setVisibility(View.VISIBLE);
                historyButton.setVisibility(View.VISIBLE);
                tvLabel.setVisibility(View.VISIBLE);
                timeFrom.setVisibility(View.VISIBLE);
                timeTo.setVisibility(View.VISIBLE);


            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder alertadd = new AlertDialog.Builder(view.getContext());

                alertadd.setTitle("Route history (" + mYear + "/" + mMonth + "/" + mDay + ")");
                LayoutInflater factory = LayoutInflater.from(view.getContext());
                if (!dateText.getText().toString().isEmpty()) {
                    if (cal.get(Calendar.DAY_OF_MONTH) - 3 > mDay) {
                        final View view = factory.inflate(R.layout.bluestaff_image_dialog, null);
                        alertadd.setView(view);
                    } else {
                        final View view = factory.inflate(R.layout.greenstaff_image_dialog, null);
                        alertadd.setView(view);
                    }
                    alertadd.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertadd.show();
                } else {
                    alertadd.setTitle("Date not set");
                    alertadd.setMessage("Please select date first");
                    alertadd.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertadd.show();
                }
            }
        });

        return view;
    }
}
