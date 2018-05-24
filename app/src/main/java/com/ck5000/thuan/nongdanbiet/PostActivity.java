package com.ck5000.thuan.nongdanbiet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostActivity extends AppCompatActivity {

    EditText tieude, noidung;
    Button post, cancel;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tieude = (EditText)findViewById(R.id.edt_TieuDe);
        noidung = (EditText)findViewById(R.id.edt_NoiDung);
        post = (Button)findViewById(R.id.bt_dangbai);
        cancel = (Button)findViewById(R.id.bt_cancel);

        final String TacGia = getIntent().getStringExtra("TacGia");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Post");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Date date = new Date();
                final SimpleDateFormat ft = new SimpleDateFormat ("hh:mm:ss a E dd.MM.yyyy ");

                if(tieude.getText().length()==0 || noidung.getText().length()==0)
                {
                    Toast.makeText(PostActivity.this,"Xin nhập đủ!!!",Toast.LENGTH_LONG).show();
                }
                else {
                    AlertDialog.Builder b=new AlertDialog.Builder(PostActivity.this);
                    b.setTitle("Question");
                    b.setMessage("Bạn có muốn đăng bài!!!");
                    b.setPositiveButton("Yes",new DialogInterface.OnClickListener()
                    {
                       public void onClick(DialogInterface dialog, int which)
                       {
                           myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {
                                   //String count = dataSnapshot.getChildrenCount();
                                   String number = Long.toString(dataSnapshot.getChildrenCount() + 1);

                                   while (dataSnapshot.hasChild(number)) {
                                       number = Long.toString(Long.parseLong(number) + 1);
                                   }
                                   myRef.child(number).child("TieuDe").setValue(tieude.getText().toString());
                                   myRef.child(number).child("NoiDung").setValue(noidung.getText().toString());
                                   myRef.child(number).child("Time").setValue(ft.format(date));
                                   myRef.child(number).child("TacGia").setValue(TacGia);

                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           });
                           Intent i = new Intent(PostActivity.this, GroupActivity.class);
                           i.putExtra("TacGia",TacGia);
                           startActivity(i);
                       }
                    });
                    b.setNegativeButton("No", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    });
                    b.create().show();


                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder b=new AlertDialog.Builder(PostActivity.this);
                b.setTitle("Question");
                b.setMessage("Bạn có muốn hủy viết bài!!!");
                b.setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent i = new Intent(PostActivity.this, GroupActivity.class);
                        i.putExtra("TacGia",TacGia);
                        startActivity(i);
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                b.create().show();

            }
        });
    }
    public void onBackPressed() {
        AlertDialog.Builder b=new AlertDialog.Builder(PostActivity.this);
        b.setTitle("Question");
        b.setMessage("Bạn có muốn hủy viết bài!!!");
        b.setPositiveButton("Yes",new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                final String TacGia = getIntent().getStringExtra("TacGia");
                Intent i = new Intent(PostActivity.this, GroupActivity.class);
                i.putExtra("TacGia",TacGia);
                startActivity(i);
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}
