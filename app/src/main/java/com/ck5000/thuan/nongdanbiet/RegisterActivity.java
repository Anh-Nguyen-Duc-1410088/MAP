package com.ck5000.thuan.nongdanbiet;

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

public class RegisterActivity extends AppCompatActivity {

    EditText username, pass1, pass2;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.register_user);
        pass1 = (EditText)findViewById(R.id.register_pass1);
        pass2 = (EditText)findViewById(R.id.register_pass2);
        register = (Button)findViewById(R.id.Register);

        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length() == 0 || pass1.getText().length() == 0 || pass2.getText().length() ==0)
                    Toast.makeText(RegisterActivity.this,"Xin nhập đủ thông tin",Toast.LENGTH_LONG).show();
                else {
                    if (pass1.getText().toString().equals(pass2.getText().toString()) != true)

                        Toast.makeText(RegisterActivity.this, "2 password không giống nhau", Toast.LENGTH_LONG).show();
                    else {

                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild(username.getText().toString())) {
                                    Toast.makeText(RegisterActivity.this, "Tên đăng nhập đã tồn tại!!!", Toast.LENGTH_LONG).show();
                                } else {
                                    myRef.child(username.getText().toString()).setValue(pass1.getText().toString());
                                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(RegisterActivity.this, Begin1Activity.class);
                                    i.putExtra("TacGia", username.getText().toString());
                                    startActivity(i);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                }
            }
        });
    }
}
