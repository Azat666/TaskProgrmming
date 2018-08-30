package com.example.student.notifcationapptask.activitys;

import android.content.DialogInterface;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.student.notifcationapptask.R;
import com.example.student.notifcationapptask.adapters.AdapterTask;
import com.example.student.notifcationapptask.fragments.AddTaskDialog;
import com.example.student.notifcationapptask.helpers.DbHelper;
import com.example.student.notifcationapptask.models.ModelTasks;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<ModelTasks> list = new ArrayList<>();
    private static final String DIALOG_TAG = "tag";
    private AdapterTask adapterTask;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataBase();
        fabButton();
        recyclerView();

    }

    public List<ModelTasks> getList() {
        return list;
    }

    private void recyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapterTask = new AdapterTask(this, list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterTask);
        swipeToDeleteLeft(recyclerView);
    }

    private void fabButton() {
        final FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AddTaskDialog addTask = new AddTaskDialog();
                addTask.show(getFragmentManager(), DIALOG_TAG);


            }
        });
    }

    public void addItem(final ModelTasks modelTasks) {
        list.add(modelTasks);
    }

    private void swipeToDeleteLeft(final RecyclerView recyclerView) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                openDialog(viewHolder.getAdapterPosition());
            }
        };
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void openDialog(final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.message_del));

        builder.setPositiveButton(getString(R.string.remove), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.removeTask(list.remove(position).getName());
                adapterTask.notifyItemRemoved(position);


            }
        }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapterTask.notifyItemRemoved(position + 1);
                adapterTask.notifyItemRangeChanged(position, adapterTask.getItemCount());
            }
        }).show();
    }

    public void notifyRecycler() {
        adapterTask.notifyDataSetChanged();

    }

    private void getDataBase() {
        dbHelper = DbHelper.getInstance(MainActivity.this);
        for (int i = 0; i < dbHelper.getTaskCount(); i++) {
            list.add(dbHelper.getItem(i));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbHelper.removeAll();
        for (int i = 0; i < list.size(); i++) {
            dbHelper.insertItem(list.get(i));
        }
    }
}
