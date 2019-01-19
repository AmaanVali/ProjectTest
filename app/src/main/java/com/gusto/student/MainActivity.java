package com.gusto.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btn_list;
    private Button btn_grid;
    private Button btn_image_loader;
    private Button btn_live_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        btn_list = findViewById(R.id.btn_list);
        btn_grid = findViewById(R.id.btn_grid);
        btn_image_loader = findViewById(R.id.btn_image_loader);
        btn_live_data = findViewById(R.id.btn_live_data);

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "List Event", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, StudentList.class);
                intent.putExtra("value","Hello");
                intent.putExtra("address","Latha");
                startActivity(intent);

            }
        });

        btn_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Grid Event", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, StudentGrid.class);
                startActivity(intent);
            }
        });

        btn_image_loader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Image Loader Event", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, StudentListWithImage.class);
                startActivity(intent);
            }
        });

        btn_live_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Api Event", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, StudentListApi.class);
                startActivity(intent);
            }
        });

    }


}
