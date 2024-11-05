package com.example.alertdialog;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button showDialogButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化按钮并设置点击事件监听器
        showDialogButton = findViewById(R.id.showDialogButton);
        showDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });
    }

    private void showLoginDialog() {
        // 创建AlertDialog构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 创建自定义标题布局
        LinearLayout titleLayout = new LinearLayout(this);
        titleLayout.setOrientation(LinearLayout.VERTICAL);
        titleLayout.setGravity(Gravity.CENTER);
        titleLayout.setBackgroundColor(Color.parseColor("#FFA500")); // 橙黄色

        TextView title = new TextView(this);
        title.setText("ANDROID APP");
        title.setTextSize(20);
        title.setTextColor(Color.WHITE); // 设置字体颜色为白色
        title.setGravity(Gravity.CENTER);
        titleLayout.addView(title);

        builder.setCustomTitle(titleLayout); // 使用自定义标题

        // 创建布局容器
        final EditText inputUsername = new EditText(this);
        inputUsername.setHint("Username");
        inputUsername.setInputType(InputType.TYPE_CLASS_TEXT);

        final EditText inputPassword = new EditText(this);
        inputPassword.setHint("Password");
        inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // 将输入框添加到布局中
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(inputUsername);
        layout.addView(inputPassword);
        builder.setView(layout);

        // 设置按钮及其点击事件
        builder.setPositiveButton("Login in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();
                // 在这里处理登录逻辑
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // 关闭当前Activity
            }
        });

        // 创建并显示对话框
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}