一、simpleadapter
activity_main.xml:
![12a48e73fddd0b926adeceb9ba097c43](https://github.com/user-attachments/assets/6eb81b49-147e-4d06-ab28-4f9730ff1010)
simple_item.xml:
![fbeae12a93c75f80cca6ae5f0512946a](https://github.com/user-attachments/assets/aceb3645-eef3-4434-ad01-32fa43ee4b96)
项目效果如下：
1.页面
![4fe52f9c542acade231a27a4bdbc9559](https://github.com/user-attachments/assets/00308897-b6fb-42fb-b404-ded2320f98f3)
2.点击loin
![51c71b30cb3a1fdb0f10402cc00a43d5](https://github.com/user-attachments/assets/dd615993-7cce-47ee-99be-0fc5d666db59)
下方toast提示
3.点击tiger
![69dc959086e463b3758d25136f297584](https://github.com/user-attachments/assets/9aceee55-2b7a-4a0e-80ef-fa28bda84c9b)
下方toast提示
后面的以此类推

第一个项目展示了如何使用 SimpleAdapter 将数据绑定到 ListView，并通过 ItemClickListener 响应点击事件。

1. MainActivity 类
MainActivity 类继承自 AppCompatActivity，表示这是一个活动（Activity）。onCreate 方法是该活动的入口点。

2. 声明数据源
private String[] names = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
private int[] imageIds = new int[]{R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat, R.drawable.elephant};
这两行声明了两个数组：
names：包含了六种动物的名称。
imageIds：包含了六张图片的资源ID，这些图片将与每个名称关联。

3. 创建列表项数据
List<Map<String, Object>> listItems = new ArrayList<>();
for (int i = 0; i < names.length; i++) {
    Map<String, Object> listItem = new HashMap<>();
    listItem.put("personName", names[i]);
    listItem.put("header", imageIds[i]);
    listItems.add(listItem);
}
通过 names 和 imageIds 数组的数据创建了一个 List<Map<String, Object>> 类型的 listItems 集合。
每个 Map 对象表示一个列表项，其中包含两个键值对：
"personName"：存储动物的名称（names[i]）。
"header"：存储该动物对应的图片资源ID（imageIds[i]）。

4. 设置 SimpleAdapter
SimpleAdapter simpleAdapter = new SimpleAdapter(
        this,
        listItems,
        R.layout.simple_item,
        new String[]{"personName", "header"},
        new int[]{R.id.name, R.id.image}
);
这里创建了一个 SimpleAdapter，它将数据源（listItems）和目标布局（R.layout.simple_item）进行绑定。SimpleAdapter 的构造函数有几个参数：

this：当前上下文（即 MainActivity）。
listItems：包含数据的 List<Map<String, Object>>，它包含了所有列表项的信息。
R.layout.simple_item：每个列表项的布局文件。在这个布局文件中需要有两个视图组件，一个用于显示名称，一个用于显示图片。
new String[]{"personName", "header"}：这些是 listItems 中的键名，对应每个列表项的实际数据。
new int[]{R.id.name, R.id.image}：这些是布局文件中视图组件的ID，用于显示数据。R.id.name 是显示动物名称的 TextView ID，R.id.image 是显示图片的 ImageView ID。

5. 设置 ListView 适配器
ListView list = findViewById(R.id.mylist);
list.setAdapter(simpleAdapter);
这两行代码获取了布局文件中的 ListView 控件（通过 findViewById），然后将 SimpleAdapter 设置为 ListView 的适配器（Adapter）。这会将 listItems 数据通过 SimpleAdapter 绑定到 ListView 上，显示在界面上。

6. 设置点击事件监听器
list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
        String personName = (String) item.get("personName");
        Toast.makeText(MainActivity.this, personName, Toast.LENGTH_SHORT).show();
    }
});
为 ListView 设置了一个点击事件监听器。当列表中的某一项被点击时，执行 onItemClick 方法：

parent.getItemAtPosition(position)：获取点击项的数据（Map<String, Object>）。
String personName = (String) item.get("personName");：从 Map 中获取动物名称。
Toast.makeText(MainActivity.this, personName, Toast.LENGTH_SHORT).show();：显示一个 Toast，提示用户点击的动物名称。

7. 总结
创建一个包含动物名称和图片的列表。
使用 SimpleAdapter 将这些数据绑定到 ListView。
设置一个点击事件监听器，当用户点击某一项时，显示该项的名称（例如 "Lion"）。

二、AlertDialog
项目效果如下：
1.页面
![7f3473088fc89c338bedb5f1426acb79](https://github.com/user-attachments/assets/d8bc19d5-2c2d-494a-9450-5cd98330c71a)

第二个项目展示了如何使用 AlertDialog 来显示一个带有自定义标题和输入框的登录对话框。

1. MainActivity 类
MainActivity 继承自 AppCompatActivity，这是应用程序的主活动。onCreate 方法是活动的入口点。

2. 初始化按钮并设置点击事件
showDialogButton = findViewById(R.id.showDialogButton);
showDialogButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showLoginDialog();
    }
});
通过 findViewById 获取布局中定义的按钮（showDialogButton）。
为按钮设置点击事件监听器，当用户点击按钮时，会调用 showLoginDialog 方法，显示登录对话框。

3. showLoginDialog 方法
这是一个自定义的登录对话框方法，使用 AlertDialog.Builder 创建和显示对话框。
AlertDialog.Builder builder = new AlertDialog.Builder(this);
AlertDialog.Builder 用于构建一个对话框。构造器的上下文（this）指当前活动（MainActivity）。

4. 自定义对话框标题

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
创建了一个 LinearLayout 作为对话框的自定义标题。
设置标题的背景颜色为橙黄色（#FFA500）。
创建了一个 TextView 来显示标题文本 "ANDROID APP"，并设置文字颜色为白色。
使用 setCustomTitle 方法将这个自定义的标题设置到对话框中。

5. 创建输入框

final EditText inputUsername = new EditText(this);
inputUsername.setHint("Username");
inputUsername.setInputType(InputType.TYPE_CLASS_TEXT);

final EditText inputPassword = new EditText(this);
inputPassword.setHint("Password");
inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
创建两个 EditText 控件：一个用于输入用户名，另一个用于输入密码。
inputUsername：设置提示文本为 "Username"，并指定它的输入类型为普通文本。
inputPassword：设置提示文本为 "Password"，并指定它的输入类型为密码文本（输入的内容将被隐藏）。

6. 将输入框添加到布局中
LinearLayout layout = new LinearLayout(this);
layout.setOrientation(LinearLayout.VERTICAL);
layout.addView(inputUsername);
layout.addView(inputPassword);
builder.setView(layout);
创建一个 LinearLayout 来容纳输入框，并设置垂直排列。
将两个 EditText 添加到这个布局中。
使用 setView 方法将这个包含输入框的布局设置为对话框的内容视图。

7. 设置对话框按钮
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
Positive Button（登录按钮）：

设置按钮文本为 "Login in"。
在按钮点击时，会获取用户输入的用户名和密码，然后执行相应的登录逻辑（这个部分目前没有实现）。
Negative Button（取消按钮）：

设置按钮文本为 "Cancel"。
在按钮点击时，会调用 finish() 方法关闭当前活动（MainActivity）。

8. 显示对话框
AlertDialog dialog = builder.create();
dialog.show();
调用 create() 方法生成 AlertDialog 对象。
使用 show() 方法将对话框显示出来。

9. 总结
这段代码展示了如何：
使用 AlertDialog.Builder 创建自定义的对话框。
设置自定义的标题布局。
在对话框中添加输入框（用户名和密码）。
设置对话框的按钮（登录和取消按钮）。
处理按钮点击事件。

三、Xmlmenu
activity_main.xml:
![8f179f28-7b17-402f-8f82-cf671eb4de94](https://github.com/user-attachments/assets/d1cce936-48f1-4e83-9146-8592dae01ca2)
menu_main.xml:
![60dd6957cb79e5bf6bd5b67565adba21](https://github.com/user-attachments/assets/0615693e-1255-4d95-83aa-44378def9b0a)
效果如下：
1.页面
![bcbfd23aee2bdb9a13069e62d0211ff7](https://github.com/user-attachments/assets/b6a0924a-5819-4970-9a2b-5ff907bbc830)
2.点击右上角菜单栏
![1a86963c8c2339d32b0adda53359ebaa](https://github.com/user-attachments/assets/b340ace9-635d-4ff4-a1e1-d16ca52bddbc)
3.点击字体大小
![b8a578884ae35512a9085a7d31560123](https://github.com/user-attachments/assets/03905487-7078-40b1-a618-ccc882bd913e)
字体变大
4.再次点击字体大小
![fd151ac27d5e67bfbf8a30bfb44dc27e](https://github.com/user-attachments/assets/8d4fa7a3-ea75-4c2d-a9bf-f0465475d14d)
字体变小
5.点击普通从菜单栏：
![a57774c50c24f4b5b291e74133dc24b6](https://github.com/user-attachments/assets/de8d920c-0d96-40f4-a3fa-a51bf3416099)
下方toast提示
6.点击字体颜色
![ac10b8e883f710523fa997c323d415a1](https://github.com/user-attachments/assets/4d34f11c-1294-4bed-a5db-6acce698a5f3)
字体变红

第三个项目展示了一个显示 PopupMenu 的功能以及一个选项菜单，用户可以通过点击菜单项切换文本的字体大小和颜色。

1. 类和成员变量
public class MainActivity extends AppCompatActivity {

    private TextView testText;
    private int fontSizeIndex = 0; // 0: 小, 1: 中, 2: 大
    private boolean isRed = false; // 用于切换字体颜色
}
MainActivity 继承自 AppCompatActivity，是 Android 中的一个活动类，用于显示界面。
testText 是一个 TextView，用于显示文本。
fontSizeIndex 用于控制字体大小，0 表示小字体，1 表示中等字体，2 表示大字体。
isRed 是一个布尔值，控制字体颜色的切换，false 表示黑色，true 表示红色。

2. onCreate 方法
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
onCreate 方法是活动的生命周期方法，界面初始化时调用。
通过 findViewById 获取布局中的 TextView（testText）和菜单按钮（menuButton）。
为 menuButton 设置点击监听器，当按钮被点击时，调用 showPopupMenu(v) 显示一个弹出菜单。

3. 选项菜单 (onCreateOptionsMenu)
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
}
onCreateOptionsMenu 方法用于创建选项菜单，getMenuInflater().inflate(R.menu.menu_main, menu) 将 menu_main.xml 菜单文件加载到 menu 中。
返回 true 表示菜单成功创建并显示。

4. 处理菜单项点击 (onOptionsItemSelected)
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
该方法处理菜单项的点击事件。
R.id.font_size：根据 fontSizeIndex 的值切换字体大小，依次在小（10sp）、中（16sp）、大（20sp）之间切换。
R.id.menu_item：点击时弹出一个 Toast 提示消息：“这里是菜单栏”。
R.id.font_color：切换字体颜色，使用 isRed 布尔值来决定字体是红色还是黑色。每次点击时，isRed 的值会取反。

5. 弹出菜单 (showPopupMenu 方法)
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
创建一个 PopupMenu，当点击按钮时会显示一个菜单，PopupMenu 与点击的视图（view）绑定。
设置菜单项点击事件的监听器，监听器调用 onOptionsItemSelected 方法来处理菜单项的点击事件。
使用 inflate 方法加载菜单布局，并通过 show 方法显示菜单。

      
6. 总结
显示选项菜单：通过点击屏幕上的按钮弹出一个 PopupMenu，这个菜单包含了选项，如切换字体大小、字体颜色等。
切换字体大小：通过点击“字体大小”菜单项，TextView 的字体大小在小、中、大三种尺寸之间切换。
切换字体颜色：通过点击“字体颜色”菜单项，TextView 的字体颜色在红色和黑色之间切换。
显示 Toast 提示：点击菜单项时，弹出一个简单的 Toast 提示。

四、actionmode
activity_main.xml:
![d6d5bc3c1674abd5989c10b17bdb9995](https://github.com/user-attachments/assets/162d318c-c189-414b-98cb-6907e858119e)
context_menu.xml:
![2c5e440995148a07647942fa9a43dc4f](https://github.com/user-attachments/assets/c58fc641-9eac-44a2-b8da-9a7a8dd99eb2)
效果如下：
1.页面
![d9245e682201482e4d3e5f071bfb3ca7](https://github.com/user-attachments/assets/fe098109-b428-4fbc-9d33-7e7cbb1d3364)
2.任意选中
![2182187f04a788e44816e12cc1788cee](https://github.com/user-attachments/assets/26e9440b-681e-48c1-aef1-818d4718481d)
3.点击右上方删除
![86ed2b5cda5df4a6ed07aae78fb7a656](https://github.com/user-attachments/assets/2ac28d63-f4fa-495a-9b67-c107a31249c2)

第四个项目展示了在 ListView 中展示了一些条目，用户可以选择多个条目，进行确认或删除操作。

1. 类和成员变量
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> items;
    private Set<Integer> selectedItems; // 使用 Set 来存储选中的项
    private TextView selectedCountTextView;
}
MainActivity 继承自 AppCompatActivity，是一个 Android 活动类。
listView 是 ListView 控件，用来展示可选择的条目。
adapter 是一个 ArrayAdapter，用于将数据（items）绑定到 ListView。
items 是一个 List<String>，存储了展示在 ListView 中的条目。
selectedItems 是一个 Set<Integer>，用于存储选中项的索引（避免重复选择）。
selectedCountTextView 是一个 TextView，用于显示当前选中项的数量。

2. onCreate 方法
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    listView = findViewById(R.id.list_view);
    selectedCountTextView = findViewById(R.id.selected_count);
    Button confirmButton = findViewById(R.id.delete_button); // 确认按钮
    Button deleteButton = findViewById(R.id.delete_button);
}
onCreate 是活动的生命周期方法，用于初始化界面和控件。
setContentView(R.layout.activity_main) 加载布局文件。
listView、selectedCountTextView 和 Button 被初始化，通过 findViewById 获取布局中的控件引用。

3. 数据和适配器
items = new ArrayList<>();
items.add("One");
items.add("Two");
items.add("Three");
items.add("Four");
items.add("Five");

adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
listView.setAdapter(adapter);
listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // 改为多选模式
items 列表存储了展示在 ListView 中的条目，包含了五个字符串：“One”、“Two”、“Three”、“Four”和“Five”。
创建了一个 ArrayAdapter，它使用 simple_list_item_multiple_choice 布局（一个内置的布局）来展示每一项，并且绑定 items 数据到 ListView。
listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE) 将 ListView 设置为多选模式，这意味着用户可以选择多个条目。

4. 处理选项的点击事件
selectedItems = new HashSet<>(); // 初始化选中的项集合

listView.setOnItemClickListener((parent, view, position, id) -> {
    if (listView.isItemChecked(position)) {
        selectedItems.add(position); // 添加到选中项集合
    } else {
        selectedItems.remove(position); // 移除选中项
    }
    selectedCountTextView.setText(selectedItems.size() + " selected");
});
初始化了 selectedItems 为一个 HashSet，用于存储选中的条目的索引，避免重复选中。
setOnItemClickListener 为 ListView 设置了点击事件监听器。当用户点击某一项时，会通过 listView.isItemChecked(position) 判断该项是否被选中。
如果选中，则将该项的索引添加到 selectedItems 集合中。
如果取消选中，则从集合中移除该项。
在每次点击后，更新 selectedCountTextView 来显示当前选中的项数。

5. 确认按钮的点击事件
confirmButton.setOnClickListener(v -> {
    // 确认选中的条目
    for (Integer position : selectedItems) {
        listView.setItemChecked(position, true);
    }
});
confirmButton 是一个确认按钮，当点击时，它会遍历 selectedItems 中的所有选中项的索引，并调用 listView.setItemChecked(position, true) 确认这些项被选中。
但由于 ListView 默认已经是多选模式，理论上选中项已经在 selectedItems 中标记为 true，此代码是多余的，实际上可以去掉。

6. 删除按钮的点击事件
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
deleteButton 是一个删除按钮，当点击时，它会删除所有选中的条目：
将 selectedItems 转换成一个 List，然后按逆序删除选中的项（倒序删除是因为删除操作会影响集合的索引）。
调用 adapter.notifyDataSetChanged() 通知适配器数据发生变化，刷新 ListView。
清空 selectedItems 集合，重置选中的项。
更新 selectedCountTextView 显示为“0 selected”。

7. 总结
多选模式：通过 ListView 的 CHOICE_MODE_MULTIPLE 设置，使用户能够选择多个条目。
选中项数量：点击列表项时，实时更新选中项的数量，并显示在 TextView 中。
确认按钮：确认按钮当前功能多余（因为选中的项已经存在 selectedItems 集合中），理论上可以去掉。
删除选中项：点击删除按钮时，删除所有选中的条目，并更新 ListView。















