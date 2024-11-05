package com.example.actionmode;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> items;
    private Set<Integer> selectedItems; // 使用 Set 来存储选中的项
    private TextView selectedCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        selectedCountTextView = findViewById(R.id.selected_count);
        Button confirmButton = findViewById(R.id.delete_button); // 确认按钮
        Button deleteButton = findViewById(R.id.delete_button);

        items = new ArrayList<>();
        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // 改为多选模式

        selectedItems = new HashSet<>(); // 初始化选中的项集合

        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (listView.isItemChecked(position)) {
                selectedItems.add(position); // 添加到选中项集合
            } else {
                selectedItems.remove(position); // 移除选中项
            }
            selectedCountTextView.setText(selectedItems.size() + " selected");
        });

        confirmButton.setOnClickListener(v -> {
            // 确认选中的条目
            for (Integer position : selectedItems) {
                listView.setItemChecked(position, true);
            }
        });

        deleteButton.setOnClickListener(v -> {
            // 删除选中的条目
            List<Integer> itemsToRemove = new ArrayList<>(selectedItems);
            for (int i = itemsToRemove.size() - 1; i >= 0; i--) {
                items.remove((int) itemsToRemove.get(i));
            }
            adapter.notifyDataSetChanged();
            selectedItems.clear(); // 清空选中项
            selectedCountTextView.setText("0 selected");
        });
    }
}


