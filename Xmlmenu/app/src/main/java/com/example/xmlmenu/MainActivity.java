package com.example.xmlmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView testText;
    private int fontSizeIndex = 0; // 0: 小, 1: 中, 2: 大
    private boolean isRed = false; // 用于切换字体颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = findViewById(R.id.test_text);
        ImageButton menuButton = findViewById(R.id.menu_button);

        // 设置菜单按钮的点击监听器
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示菜单
                showPopupMenu(v);
            }
        });
    }

    // 创建选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 处理菜单项的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.font_size) {
            // 切换字体大小
            fontSizeIndex = (fontSizeIndex + 1) % 3; // 0 -> 1 -> 2 -> 0
            if (fontSizeIndex == 0) {
                testText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
            } else if (fontSizeIndex == 1) {
                testText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            } else if (fontSizeIndex == 2) {
                testText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            }
            return true;
        } else if (item.getItemId() == R.id.menu_item) {
            Toast.makeText(this, "这里是菜单栏", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.font_color) {
            // 切换字体颜色
            isRed = !isRed; // 切换状态
            testText.setTextColor(isRed ? Color.RED : Color.BLACK);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // 显示弹出菜单
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        popupMenu.inflate(R.menu.menu_main);
        popupMenu.show();
    }
}




