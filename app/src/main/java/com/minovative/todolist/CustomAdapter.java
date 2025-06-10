package com.minovative.todolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> itemList;

    public CustomAdapter(Context context, List<String> itemList) {
        super(context, R.layout.item_list, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_list, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        EditText editText = convertView.findViewById(R.id.editListText);
        ImageView deleteButton = convertView.findViewById(R.id.deleteButton);

        editText.setText(itemList.get(position));

        checkBox.setOnClickListener(view -> {

                boolean isChecked = checkBox.isChecked();
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("saveCheckStatus",Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("key_" + position, isChecked);
                editor.apply();

            if (checkBox.isChecked()) {

                editText.setTextColor(ContextCompat.getColor(context,R.color.green));

            } else if (!checkBox.isChecked()) {

                editText.setTextColor(Color.BLACK);
            }
        });

        deleteButton.setOnClickListener(view -> {
            
            itemList.remove(position);
            notifyDataSetChanged();
        });

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("saveCheckStatus", Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("key_" + position, false);
        checkBox.setChecked(isChecked);

        if (checkBox.isChecked()) {

            editText.setTextColor(ContextCompat.getColor(context,R.color.green));
        }

        return convertView;
    }
}
