package com.example.student.notifcationapptask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.student.notifcationapptask.R;
import com.example.student.notifcationapptask.models.ModelTasks;

import java.util.List;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.MyViewHolder> {

    private final Context context;
    private final List<ModelTasks> list;

    public AdapterTask(final Context context, final List<ModelTasks> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AdapterTask.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterTask.MyViewHolder holder, final int position) {
        holder.textTime.setText(list.get(position).getTime());
        holder.textDate.setText(list.get(position).getDate());
        holder.textName.setText(list.get(position).getName());
        holder.imageId.setImageResource(list.get(position).getImage());
        if (list.get(position).isIschekid() == 1) {
            holder.aSwitch.setChecked(true);
        } else {
            holder.aSwitch.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textName;
        private TextView textTime;
        private TextView textDate;
        private ImageView imageId;
        private SwitchCompat aSwitch;

        MyViewHolder(View itemView) {
            super(itemView);
            findView(itemView);
            switchOnClick();
        }

        private void findView(View view) {
            textDate = view.findViewById(R.id.item_date);
            textName = view.findViewById(R.id.item_name);
            textTime = view.findViewById(R.id.item_time);
            imageId = view.findViewById(R.id.item_image);
            aSwitch = view.findViewById(R.id.switch_item);
        }

        private void switchOnClick() {
            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        list.get(getAdapterPosition()).setIschekid(1);
                    } else {
                        list.get(getAdapterPosition()).setIschekid(0);
                    }
                }


            });

        }
    }

}

