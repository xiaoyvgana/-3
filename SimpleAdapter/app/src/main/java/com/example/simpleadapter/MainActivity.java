package com.example.simpleadapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String[] names = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private int[] imageIds = new int[]{R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat, R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建一个 List 集合，存放每个项的信息
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("personName", names[i]);
            listItem.put("header", imageIds[i]);
            listItems.add(listItem);
        }

        // 创建一个 SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                listItems,
                R.layout.simple_item,
                new String[]{"personName", "header"},
                new int[]{R.id.name, R.id.image}
                );

        ListView list = findViewById(R.id.mylist);
        // 为 ListView 设置 Adapter
        list.setAdapter(simpleAdapter);

        // 为 ListView 的列表项的单击事件绑定事件监听器
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 获取点击项的数据
                Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
                String personName = (String) item.get("personName");

                // 显示 Toast 消息
                Toast.makeText(MainActivity.this, personName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}


