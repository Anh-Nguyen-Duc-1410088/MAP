package com.ck5000.thuan.nongdanbiet.Class_Group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ck5000.thuan.nongdanbiet.R;

import java.util.ArrayList;





public class CmtAdapter extends ArrayAdapter<Cmt> {

    private Context context;
    private int resource;
    private ArrayList<Cmt> arrPost;


    public CmtAdapter(Context context, int resource, ArrayList<Cmt> objects)
    {
        super(context,resource,objects);
        this.context = context;
        this.resource = resource;
        this.arrPost = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_cmt,parent,false);
        TextView TacGia = (TextView) convertView.findViewById(R.id.item_cmt_TacGia);
        TextView Time = (TextView) convertView.findViewById(R.id.item_cmt_Time);
        TextView NoiDung = (TextView)convertView.findViewById(R.id.item_cmt_NoiDung);

        Cmt post = arrPost.get(position);

        TacGia.setText(post.getTacGia());
        Time.setText(post.getTime());
        NoiDung.setText(post.getNoiDung());

        return convertView;
    }

}
