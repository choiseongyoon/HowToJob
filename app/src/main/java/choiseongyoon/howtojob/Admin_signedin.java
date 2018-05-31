package choiseongyoon.howtojob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Admin_signedin extends BaseActivity {

    Button btn_기업등록;
    Button btn_기업정보수정;
    Button btn_부문등록;
    Button btn_부문수정;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_signedin_main);
        final DBHelper dbHelper = new DBHelper(this);


         btn_기업등록= (Button)findViewById(R.id.button_기업등록);
        btn_기업등록.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Admin_enterprise_insert.class);
                startActivity(intent);
            }
        });

        btn_기업정보수정= (Button)findViewById(R.id.button_기업정보수정);
        btn_기업정보수정.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Admin_enterprise_modify.class);
                startActivity(intent);
            }
        });

        btn_부문등록 = (Button)findViewById(R.id.button_부문등록);
        btn_부문등록.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Admin_department_insert.class);
                startActivity(intent);
            }
        });

        btn_부문수정 = (Button)findViewById(R.id.button_부문수정);
        btn_부문수정.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Admin_department_modify.class);
                startActivity(intent);
            }
        });

        Button btn_뒤로  = (Button)findViewById(R.id.button_부문뒤로);
        btn_뒤로.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }



}
