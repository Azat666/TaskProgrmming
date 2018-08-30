package com.example.student.notifcationapptask.fragments;

import android.app.DatePickerDialog;
import android.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Build;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.student.notifcationapptask.R;
import com.example.student.notifcationapptask.activitys.MainActivity;
import com.example.student.notifcationapptask.models.ModelTasks;

import java.util.Calendar;
import java.util.List;


public class AddTaskDialog extends DialogFragment {
    private static final String TOAST_TEXT = "This name already exists";
    private Calendar calendar = Calendar.getInstance();
    private String date;
    private String time;
    private String name;
    private int image;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_stayl, container, false);
        addButton(view);
        setData(view);
        setTime(view);
        setSpinner(view);


        return view;
    }

    private void setData(View view) {
        final ImageButton dateButton = view.findViewById(R.id.date_button);
        dateButton.setOnClickListener
                (new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         final DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                             @Override
                             public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                 date = i2 + "." + i1 + "." + i;
                                 calendar.set(Calendar.YEAR, i);
                                 calendar.set(Calendar.MONTH, i1);
                                 calendar.set(Calendar.DAY_OF_MONTH, i2);
                             }
                         }, calendar.get(Calendar.YEAR),
                                 calendar.get(Calendar.MONTH),
                                 calendar.get(Calendar.DAY_OF_MONTH));
                         dialog.show();
                     }
                 }
                );

    }

    private void setTime(View view) {
        final ImageButton timeButton = view.findViewById(R.id.time_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time = i + " : " + i1;
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });
    }


    private void setSpinner(View view) {
        final Spinner spinner = view.findViewById(R.id.spinner_dialog);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        image = R.drawable.jaava;
                        break;
                    case 1:
                        image = R.drawable.android;
                        break;
                    case 2:
                        image = R.drawable.cplus;
                        break;
                    case 3:
                        image = R.drawable.python;
                        break;
                    case 4:
                        image = R.drawable.ioss;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addButton(final View itemView) {
        final ImageButton addButton = itemView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEditText(itemView);

                if (checkName(name)) {
                    ((MainActivity) getActivity()).addItem(new ModelTasks(name, date, time, image, 1));
                    ((MainActivity) getActivity()).notifyRecycler();
                    dismiss();
                }
            }
        });
    }

    private void setEditText(View view) {
        final EditText editName = view.findViewById(R.id.dialog_edit_text);
        name = editName.getText().toString();
    }

    private boolean checkName(String name) {
        final List<ModelTasks> list = ((MainActivity) getActivity()).getList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                Toast.makeText(getActivity(), TOAST_TEXT, Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

}
