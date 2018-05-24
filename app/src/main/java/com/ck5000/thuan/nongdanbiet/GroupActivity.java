package com.ck5000.thuan.nongdanbiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ck5000.thuan.nongdanbiet.Class_Group.CustomAdapter;
import com.ck5000.thuan.nongdanbiet.Class_Group.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {

    private ListView lvPost;
    private Button create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Post");

        final String TacGia = getIntent().getStringExtra("TacGia");
        final ArrayList<Post> arrPost = new ArrayList<>();

        lvPost = (ListView)findViewById(R.id.lv_group);
        create = (Button)findViewById(R.id.bt_create);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("123","GroupActivity create.OnClick");
                Intent i = new Intent(GroupActivity.this, PostActivity.class);
                i.putExtra("TacGia",TacGia);
                startActivity(i);
            }
        });

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String count = dataSnapshot.getChildrenCount();
                Log.i("123","GroupActivity add arrPost");

                long number = dataSnapshot.getChildrenCount();
                long i = 1;
                while (i<=number)
                {
                    String TieuDe = dataSnapshot.child(Long.toString(i)).child("TieuDe").getValue().toString();
                    String NoiDung = dataSnapshot.child(Long.toString(i)).child("NoiDung").getValue().toString();
                    String TacGia = dataSnapshot.child(Long.toString(i)).child("TacGia").getValue().toString();
                    String Time = dataSnapshot.child(Long.toString(i)).child("Time").getValue().toString();
                    Post post = new Post().setNoiDung(NoiDung).setTacGia(TacGia).setTieuDe(TieuDe).setTime(Time);
                    arrPost.add(post);
                    i++;
                }
                CustomAdapter customAdapter = new CustomAdapter(GroupActivity.this,R.layout.item_post,arrPost);
                lvPost.setAdapter(customAdapter);

                lvPost.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                                Post post = (Post) lvPost.getItemAtPosition(arg2);
                                Intent baidang = new Intent(GroupActivity.this,Post1Activity.class);
                                baidang.putExtra("STT",String.valueOf(arg2+1));
                                baidang.putExtra("TieuDe",post.getTieuDe());
                                baidang.putExtra("NoiDung",post.getNoiDung());
                                baidang.putExtra("TacGia",post.getTacGia());
                                baidang.putExtra("Time",post.getTime());
                                baidang.putExtra("TacGiaCmt",TacGia);
                                startActivity(baidang);
                            }
                        });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onBackPressed() {
        // do something on back.
        String TacGia = getIntent().getStringExtra("TacGia");
        Intent i=new Intent(GroupActivity.this,Begin1Activity.class);
        i.putExtra("TacGia", TacGia);
        startActivity(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {


        menu.add(0,0,0,"Thời tiết");
        menu.add(0,1,0,"Giá cả");
        menu.add(0,2,0,"Group");
        menu.add(0,3,0,"Bài Bao");
        menu.add(0,4,0,"Map");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idex=item.getItemId();
        String TacGia = getIntent().getStringExtra("TacGia");
        switch (idex)
        {
            case 0 :
                Intent intent0=new Intent(GroupActivity.this,ThoiTietActivity.class);
                intent0.putExtra("TacGia", TacGia);
                startActivity(intent0);
                break;
            case 1 :
                Intent intent1=new Intent(GroupActivity.this,GiaCaActivity.class);
                intent1.putExtra("TacGia", TacGia);
                startActivity(intent1);
                break;
            case 2 :
                Intent intent2=new Intent(GroupActivity.this,GroupActivity.class);
                intent2.putExtra("TacGia", TacGia);
                startActivity(intent2);
                break;
            case 3 :
                Intent intent3=new Intent(GroupActivity.this,BaiBaoActivity.class);
                intent3.putExtra("TacGia", TacGia);
                startActivity(intent3);
                break;
            case 4 :
                Intent intent4=new Intent(GroupActivity.this,MapsActivity.class);
                intent4.putExtra("TacGia", TacGia);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
