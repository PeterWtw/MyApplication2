package com.example.fabumodule;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_fabu extends Fragment {
    private static GridView gview;
    private static List<Map<String, Object>> data_list;
    private static SimpleAdapter sim_adapter;
    private static ListView list;


    // 图片封装为一个数组
    private int[] icon = { R.mipmap.meishi, R.mipmap.yule,
            R.mipmap.fangchan, R.mipmap.che, R.mipmap.hunqing,
            R.mipmap.zhuangxiu, R.mipmap.jiaoyu, R.mipmap.gongzuo,
            R.mipmap.baihuo, R.mipmap.tiaozhao, R.mipmap.shangwu,
            R.mipmap.bianmin,R.mipmap.laoxianghui,R.mipmap.qita};
    private String[] iconName = { "美食", "娱乐", "房产", "车", "婚庆", "装修", "教育",
            "工作", "百货", "跳蚤", "商务", "便民","老乡会","其他" };

    //String [] msg2={"酒店","饭店","西点","夜宵","外卖","茶馆","零食特产","其他","小吃"};
    String [][] msg={{"酒店","饭店","西点","夜宵","外卖","茶馆","零食特产","其他","小吃"},
            {"KTV","电影","酒吧","宾馆","足底按摩","其他"},
            {"买卖","租赁","其他"},
            {"买卖","租赁","代驾","学车","修理","其他"},
            {"礼仪庆典","婚车","摄影","鲜花","其他"},
            {"家/工装","建材家居","装修工人","其他"},
            {"学校","培训","家教","其他"},
            {"全职","兼职","钟点工","临时工","其他"},
            {"手机","服装","食品","酒水","数码电器","母婴玩具","美容美发","珠宝配饰","办公耗材","家居家纺","日用品","其他"},
            {"家具电器","服装箱包","手表珠宝","办公设施","其他"},
            {"投资担保","咨询顾问","演出会展","其他"},
            {"便民","其他"},
            {},
            {"兰州拉面","沙县小吃","过桥米线","水煮活鱼","黄焖鸡米饭","衡阳螺蛳粉","其他"},
            {"家政","信息","旅游","运动","医疗","丧事","其他"}};


    View mainview;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainview = inflater.inflate(R.layout.fabu_layout, null);

        gview = (GridView) mainview.findViewById(R.id.gview);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Display dp=getActivity().getWindow().getWindowManager().getDefaultDisplay();
                View v=inflater.inflate(R.layout.fabuwindow_layout,null);
                final PopupWindow popupWindow=new PopupWindow(v,(int)(dp.getWidth()*0.92),(int)(dp.getHeight()*0.6));
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(mainview , Gravity.BOTTOM,0,0);

                list = (ListView) v.findViewById(R.id.PopupWindow_listview);

                list.setAdapter(new ArrayAdapter(getContext(),R.layout.window_item_layout,R.id.PopupWindow_listview_text,msg[i]));

                view.findViewById(R.id.pop_error).setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }

                });
                WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                lp.alpha=0.6f;
                //设置回去,启用这个Attribute配置
                getActivity().getWindow().setAttributes(lp);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                        lp.alpha=1f;
                        getActivity().getWindow().setAttributes(lp);
                    }
                });
            }
        });

        data_list = new ArrayList<Map<String, Object>>();

        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }

        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.item, from, to);
        gview.setAdapter(sim_adapter);
        return mainview;
    }

}

