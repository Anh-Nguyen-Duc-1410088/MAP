package com.ck5000.thuan.nongdanbiet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GiaCaActivity extends AppCompatActivity {

    final String TacGia = getIntent().getStringExtra("TacGia");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gia_ca);

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
        switch (idex)
        {
            case 0 :
                Intent intent0=new Intent(GiaCaActivity.this,ThoiTietActivity.class);
                startActivity(intent0);
                intent0.putExtra("TacGia", TacGia);
                break;
            case 1 :
                Intent intent1=new Intent(GiaCaActivity.this,GiaCaActivity.class);
                intent1.putExtra("TacGia", TacGia);
                startActivity(intent1);
                break;
            case 2 :
                if(TacGia == null)
                {
                    Toast.makeText(GiaCaActivity.this,"Cần đăng nhập trước khi dùng chức năng này!!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GiaCaActivity.this, Begin1Activity.class);
                    intent.putExtra("TacGia", TacGia);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(GiaCaActivity.this, GroupActivity.class);
                    intent.putExtra("TacGia", TacGia);
                    startActivity(intent);
                }
                break;
            case 3 :
                Intent intent3=new Intent(GiaCaActivity.this,BaiBaoActivity.class);
                intent3.putExtra("TacGia", TacGia);
                startActivity(intent3);
                break;
            case 4 :
                Intent intent4=new Intent(GiaCaActivity.this,MapsActivity.class);
                intent4.putExtra("TacGia", TacGia);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


