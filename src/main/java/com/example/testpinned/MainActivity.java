package com.example.testpinned;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.testpinned.list.HeaderAdapter;
import com.example.testpinned.list.PinnedListView;
import com.example.testpinned.log.LogUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.test_list)
    PinnedListView mPinnedListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
    }

    private void init() {

        mPinnedListView = (PinnedListView)findViewById(R.id.test_list);

        findViewById(R.id.list_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        TextView view = new TextView(this);
        view.setWidth(0);
        view.setHeight(0);
        mPinnedListView.addHeaderView(view);

        view = new TextView(this);
        view.setWidth(0);
        view.setHeight(0);
        mPinnedListView.addHeaderView(view);

        HeaderAdapter adapter = new HeaderAdapter();
        List<ItemType.ItemData> list = new ArrayList<ItemType.ItemData>();
        list.add(new ItemType.ItemData(ItemType.TYPE_TITLE, "《这也是一切》"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切大树,都被风暴折断"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切种子\n" + "　　都找不到生根的土壤；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切真情\n" + "　　都流失在人心的沙漠里；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切梦想\n" + "　　都甘愿被折断翅膀。"));
        list.add(new ItemType.ItemData(ItemType.TYPE_TITLE, "《再别康桥》"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不、不是一切\n" + "　　都像你说的那样！"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切火焰\n" + "　　都只燃烧自己\n" + "　　而不把别人照亮；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切星星\n" + "　　都仅指示黑暗\n" + "　　而不报告曙光；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切歌声\n" + "　　都只掠过耳旁\n" + "　　而不留在心上"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不、不是一切\n" + "　　都像你说的那样！"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切呼吁都没有回响；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_TITLE, "《乡愁》"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切损失都无法补偿；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切深渊都是灭亡；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切灭亡都覆盖在弱者头上；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切心灵\n" + "　　都踩在脚下、烂在泥里；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切后果\n" + "　　都是眼泪血印，而不展现欢容"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "一切的现在都在孕育着未来，"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "未来的一切都生长于它的昨天。"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "希望，而且为它斗争，\n" + "　　请把这一切放在你的肩上。"));
        list.add(new ItemType.ItemData(ItemType.TYPE_TITLE, "《天籁》"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切损失都无法补偿；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切深渊都是灭亡；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切灭亡都覆盖在弱者头上；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切心灵\n" + "　　都踩在脚下、烂在泥里；"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "不是一切后果\n" + "　　都是眼泪血印，而不展现欢容"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "一切的现在都在孕育着未来，"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "未来的一切都生长于它的昨天。"));
        list.add(new ItemType.ItemData(ItemType.TYPE_CONTENT, R.mipmap.ic_launcher, "希望，而且为它斗争，\n" + "　　请把这一切放在你的肩上。"));
        adapter.setData(list);

        LogUtility.i("mPinnedListView:", mPinnedListView);

        mPinnedListView.setPinnedItemType(ItemType.TYPE_TITLE);
        mPinnedListView.setAdapter(adapter);
    }
}
