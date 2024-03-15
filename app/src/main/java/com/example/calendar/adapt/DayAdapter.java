package com.example.calendar.adapt;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.calendar.MainActivity;
import com.example.calendar.R;

import java.util.ArrayList;
import java.util.List;

public class DayAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DayItem> itemList;
    private String username;
    //  数据库对象
    private SQLiteDatabase db;
    private DbHelper dbHelper;
    public DayAdapter(Context context, ArrayList<DayItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        //数据库初始化
        dbHelper = new DbHelper(this.context);
        db = dbHelper.getReadableDatabase();

        // 获取列表项中的各个视图
        TextView titleTextView = convertView.findViewById(R.id.item_title);
        TextView descriptionTextView = convertView.findViewById(R.id.item_description);
        TextView dateTextView = convertView.findViewById(R.id.item_date);
        ImageButton editbutton = convertView.findViewById(R.id.editbutton);
        ImageButton deletebutton = convertView.findViewById(R.id.deletebutton);

        // 获取当前位置的数据项
        DayItem currentItem = itemList.get(position);

        // 设置视图中的数据
        titleTextView.setText(currentItem.getTitle());
        String str=currentItem.getDescription().toString();
        descriptionTextView.setText(str.substring(0, Math.min(str.length(), 10)));
        dateTextView.setText(currentItem.getDate());


        // 设置按钮的点击事件
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("Date", currentItem.getDate());
                intent.putExtra("Username", currentItem.getUsername());
                context.startActivity(intent);
            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///
            }
        });

        return convertView;
    }
}
