package com.ck5000.thuan.nongdanbiet.Class_Group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ck5000.thuan.nongdanbiet.R;

import java.util.ArrayList;





public class CustomAdapter extends ArrayAdapter<Post> {

    private Context context;
    private int resource;
    private ArrayList<Post> arrPost;


    public CustomAdapter(Context context, int resource, ArrayList<Post> objects)
    {
        super(context,resource,objects);
        this.context = context;
        this.resource = resource;
        this.arrPost = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        TextView TieuDe = (TextView) convertView.findViewById(R.id.tv_TieuDe);
        TextView TacGia = (TextView) convertView.findViewById(R.id.tv_TacGia);
        TextView Time = (TextView) convertView.findViewById(R.id.tv_Time);

        Post post = arrPost.get(position);

        TieuDe.setText(post.getTieuDe());
        TacGia.setText(post.getTacGia());
        Time.setText(post.getTime());

        return convertView;
    }

}
