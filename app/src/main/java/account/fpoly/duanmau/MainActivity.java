package account.fpoly.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import account.fpoly.duanmau.dao.DemoDB;
import account.fpoly.mob2041.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DemoDB demoDB = new DemoDB(getApplicationContext());
        demoDB.thanhVien();
        demoDB.thuThu();
        LinearLayout linearCate = findViewById(R.id.btnLoaiSach);
        LinearLayout linearBook = findViewById(R.id.btnSach);

        linearCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CategoryActivity.class));
            }
        });

        linearBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookActivity.class));
            }
        });
    }
}