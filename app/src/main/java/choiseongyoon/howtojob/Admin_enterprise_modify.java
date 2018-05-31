package choiseongyoon.howtojob;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Admin_enterprise_modify extends BaseActivity {

    Button btn_modify_수정완료;
    Button btn_modify_취소;

    EditText et_modify_기업명;
    Spinner sp_modify_기업형태;
    Spinner sp_modify_산업군;
    Spinner sp_modify_본사근무지;
    EditText et_modify_기업특이사항;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_enterprise_modify);
        final DBHelper dbHelper = new DBHelper(this);


        et_modify_기업명 = (EditText)findViewById(R.id.editText_modify_기업명);
        sp_modify_기업형태 = (Spinner) findViewById(R.id.spinner_modify_기업형태);
        sp_modify_산업군 = (Spinner) findViewById(R.id.spinner_modify_산업군);
        sp_modify_본사근무지 = (Spinner) findViewById(R.id.spinner_modify_본사근무지);
        et_modify_기업특이사항 = (EditText)findViewById(R.id.editText_modify_특이사항);



        btn_modify_수정완료 = (Button)findViewById(R.id.button_modify_수정완료);
        btn_modify_수정완료.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String 기업명 = et_modify_기업명.getText().toString();
                String 기업형태 = sp_modify_기업형태.getSelectedItem().toString();
                String 산업군 = sp_modify_산업군.getSelectedItem().toString();
                String 본사근무지 = sp_modify_본사근무지.getSelectedItem().toString();
                String 기업특이사항="";
                기업특이사항 = et_modify_기업특이사항.getText().toString();


                dbHelper.기업_update(기업명,기업형태,산업군,본사근무지,기업특이사항);
                db.close();
                Toast toast = Toast.makeText(Admin_enterprise_modify.this,"기업수정완료", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        Button btn_modify_취소  = (Button)findViewById(R.id.button_modify_취소);
        btn_modify_취소.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
