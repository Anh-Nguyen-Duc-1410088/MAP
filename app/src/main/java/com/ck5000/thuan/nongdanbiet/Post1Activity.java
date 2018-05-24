package com.ck5000.thuan.nongdanbiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ck5000.thuan.nongdanbiet.Class_Group.Cmt;
import com.ck5000.thuan.nongdanbiet.Class_Group.CmtAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Post1Activity extends AppCompatActivity {

    TextView tvTieuDe,tvNoiDung,tvTacGia,tvTime;
    EditText edt_cmt;
    Button cmt;
    ListView lvCmt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post1);

        final String TacGiaCmt = getIntent().getStringExtra("TacGiaCmt");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Post");

        final String STT,TieuDe,NoiDung,TacGia,Time;
        tvTieuDe = (TextView)findViewById(R.id.tv_TieuDe_post1);
        tvNoiDung = (TextView)findViewById(R.id.tv_NoiDung_post1);
        tvTacGia = (TextView)findViewById(R.id.TacGia_post1);
        tvTime = (TextView)findViewById(R.id.Time_post1);
        edt_cmt = (EditText)findViewById(R.id.ed_cmt_post1);
        cmt = (Button)findViewById(R.id.Dang_post);

        Intent i = getIntent();
        STT = i.getStringExtra("STT").toString();
        TieuDe = i.getStringExtra("TieuDe").toString();
        NoiDung = i.getStringExtra("NoiDung").toString();
        TacGia = i.getStringExtra("TacGia").toString();
        Time = i.getStringExtra("Time").toString();

        tvTieuDe.setText(TieuDe);
        tvTacGia.setText(TacGia);
        tvNoiDung.setText(NoiDung);
        tvTime.setText(Time);

        cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_cmt.getText().length()!= 0)
                {
                    final Date date = new Date();
                    final SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss a E dd.MM.yyyy ");

                    myRef.child(STT).child("CMT").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //String count = dataSnapshot.getChildrenCount();
                            String number = Long.toString(dataSnapshot.getChildrenCount() + 1);

                            while (dataSnapshot.hasChild(number)) {
                                number = Long.toString(Long.parseLong(number) + 1);
                            }
                            myRef.child(STT).child("CMT").child(number).child("NoiDung").setValue(edt_cmt.getText().toString());
                            myRef.child(STT).child("CMT").child(number).child("Time").setValue(ft.format(date));
                            myRef.child(STT).child("CMT").child(number).child("TacGia").setValue(TacGiaCmt);
                            Toast.makeText(Post1Activity.this,"Bình luận thành công",Toast.LENGTH_LONG).show();
                            edt_cmt.setText("");
                            Intent i = new Intent(Post1Activity.this, Post1Activity.class);
                            i.putExtra("TacGia",TacGiaCmt);
                            startActivity(i);

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

        lvCmt = (ListView)findViewById(R.id.lv_cmt_post1);
        final ArrayList<Cmt> arrPost = new ArrayList<>();
        String TacGiaCmt1, TimeCmt, NoiDungCmt;

        myRef.child(STT).child("CMT").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String count = dataSnapshot.getChildrenCount();
                long number = dataSnapshot.getChildrenCount();
                long i = 1;
                while (i<=number)
                {
                    String NoiDungCmt = dataSnapshot.child(Long.toString(i)).child("NoiDung").getValue().toString();
                    String TacGiaCmt1 = dataSnapshot.child(Long.toString(i)).child("TacGia").getValue().toString();
                    String TimeCmt = dataSnapshot.child(Long.toString(i)).child("Time").getValue().toString();
                    Cmt post = new Cmt().setNoiDung(NoiDungCmt).setTacGia(TacGiaCmt1).setTime(TimeCmt);
                    arrPost.add(post);
                    i++;
                }
                CmtAdapter customAdapter = new CmtAdapter(Post1Activity.this,R.layout.item_cmt,arrPost);
                lvCmt.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void onBackPressed() {
        // do something on back.
        final String TacGiaCmt = getIntent().getStringExtra("TacGiaCmt");
        Intent i=new Intent(Post1Activity.this,GroupActivity.class);
        i.putExtra("TacGia", TacGiaCmt);
        startActivity(i);
    }
}
