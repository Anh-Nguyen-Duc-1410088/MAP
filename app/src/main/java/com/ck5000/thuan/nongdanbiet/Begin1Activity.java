package com.ck5000.thuan.nongdanbiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Begin1Activity extends AppCompatActivity {

    RelativeLayout BackGround1;
    private Button login,baibao,group, map, thoitiet ;
    protected String TacGia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin1);
//        BackGround1 =(RelativeLayout) findViewById(R.id.ManHinh1);
//        BackGround1.setBackgroundResource(R.drawable.nen);

        Intent i = getIntent();
        TacGia = i.getStringExtra("TacGia");



        View.OnClickListener Click =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.bt_Login:
                        login();
                        break;
                    case R.id.bt_ThoiTiet:
                        ThoiTiet();
                        break;

                    case R.id.bt_Group:
                        Group();
                        break;
                    case R.id.bt_BaiBao:
                        BaiBao();
                        break;
                    case R.id.bt_Map:
                        Map();
                        break;
                }
            }
        };
        login = (Button) findViewById(R.id.bt_Login);
        group = (Button) findViewById(R.id.bt_Group);
        map = (Button) findViewById(R.id.bt_Map);
        thoitiet = (Button) findViewById(R.id.bt_ThoiTiet);
        baibao = (Button) findViewById(R.id.bt_BaiBao);

        if(TacGia != null) login.setVisibility(View.INVISIBLE);

        login.setOnClickListener(Click);
        group.setOnClickListener(Click);
        map.setOnClickListener(Click);
        thoitiet.setOnClickListener(Click);
        baibao.setOnClickListener(Click);

    }

    public void onBackPressed() {
        // do something on back.
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
        finish();
    }

    private void login()
    {
        //setContentView(R.layout.activity_login);
        Intent intent1=new Intent(Begin1Activity.this,LoginActivity.class);
        startActivity(intent1);
    }

    private void ThoiTiet()
    {
        Intent intent2=new Intent(Begin1Activity.this,ThoiTietActivity.class);
        intent2.putExtra("TacGia", TacGia);
        startActivity(intent2);
    }
    private void GiaCa()
    {
        Intent intent3=new Intent(Begin1Activity.this,GiaCaActivity.class);
        intent3.putExtra("TacGia", TacGia);
        startActivity(intent3);
    }
    private void Group()
    {
        Log.i("123", "Begin1Activity Group()");

        if(TacGia == null)
        {
            Toast.makeText(Begin1Activity.this,"Cần đăng nhập trước khi dùng chức năng này!!!", Toast.LENGTH_LONG).show();

        }
        else {
            Intent intent4 = new Intent(Begin1Activity.this, GroupActivity.class);
            intent4.putExtra("TacGia", TacGia);
            Log.i("123","startActivity Group");
            startActivity(intent4);
        }
    }
    private void BaiBao()
    {
        Intent intent5=new Intent(Begin1Activity.this,BaiBaoActivity.class);
        intent5.putExtra("TacGia", TacGia);
        startActivity(intent5);
    }
    private void Map()
    {
        Intent intent6=new Intent(Begin1Activity.this,MapsActivity.class);
        intent6.putExtra("TacGia", TacGia);
        startActivity(intent6);
    }


}
