package com.example.todolist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task>
{
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> tasks)
    {
        super(context, 0, tasks);
        this.taskList = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Task task = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_task, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView taskTextView = convertView.findViewById(R.id.taskTextView);
        TextView dateTimeView = convertView.findViewById(R.id.dateTimeView);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        taskTextView.setText(task.getText());
        dateTimeView.setText(task.getDate() + " " + task.getTime());
        checkBox.setChecked(task.isCompleted());

        // Strike-through if completed
        if (task.isCompleted()) {
            taskTextView.setPaintFlags(taskTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            taskTextView.setPaintFlags(taskTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            notifyDataSetChanged();
        });

        deleteButton.setOnClickListener(v -> {
            taskList.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}
