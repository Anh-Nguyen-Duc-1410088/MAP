package com.ck5000.thuan.nongdanbiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextView TaoTaiKhoa;
    EditText username, password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TaoTaiKhoa = (TextView) findViewById(R.id.tv_TaoTaiKhoan);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");
        Log.i("123","Conected firebase");

        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(username.getText().toString().length() == 0 || password.getText().toString().length() == 0)
                {
                    Toast.makeText(LoginActivity.this,"Xin nhập đủ!!!",Toast.LENGTH_LONG).show();
                }
                else {
                    myRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.hasChild(username.getText().toString()))
                                Toast.makeText(LoginActivity.this,"Không tộn tại tài khoản!!!",Toast.LENGTH_LONG).show();

                            else
                                if(dataSnapshot.child(username.getText().toString()).getValue().equals(password.getText().toString()))
                                {
                                    Intent login = new Intent(LoginActivity.this,Begin1Activity.class);
                                    login.putExtra("TacGia",username.getText().toString());
                                    Toast.makeText(LoginActivity.this,"Đăng nhập thành công!!!",Toast.LENGTH_LONG).show();
                                    startActivity(login);
                                }
                                else Toast.makeText(LoginActivity.this,"Password sai!!!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });



        View.OnClickListener Click =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.tv_TaoTaiKhoan:
                        register();
                        break;

                }
            }
        };
        TaoTaiKhoa.setOnClickListener(Click);
    }
    private void register()
    {
        //setContentView(R.layout.activity_login);
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

}
