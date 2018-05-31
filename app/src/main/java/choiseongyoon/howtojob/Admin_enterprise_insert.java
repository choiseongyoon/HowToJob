package choiseongyoon.howtojob;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Admin_enterprise_insert extends BaseActivity {


    TextView tv_기업리스트;
    Button btn_기업등록;

    EditText et_기업명;
    Spinner sp_기업형태;
    Spinner sp_산업군;
    Spinner sp_본사근무지;
    EditText et_기업특이사항;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_enterprise_insert);
        final DBHelper dbHelper = new DBHelper(this);

        et_기업명=(EditText)findViewById(R.id.editText_insert_기업명);
        sp_기업형태 = (Spinner)findViewById(R.id.spinner_insert_기업형태);
        sp_산업군 = (Spinner)findViewById(R.id.spinner_insert_산업군);
        sp_본사근무지 = (Spinner)findViewById(R.id.spinner_insert_본사근무지);
        et_기업특이사항=(EditText)findViewById(R.id.editText_insert_특이사항);



        btn_기업등록=(Button)findViewById(R.id.button_insert_기업등록);
        btn_기업등록.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String 기업명 = et_기업명.getText().toString();
                String 기업형태 = sp_기업형태.getSelectedItem().toString();
                String 산업군 = sp_산업군.getSelectedItem().toString();
                String 본사근무지 = sp_본사근무지.getSelectedItem().toString();
                String 기업특이사항 = et_기업특이사항.getText().toString();

                dbHelper.기업_insert(기업명, 기업형태, 산업군, 본사근무지, 기업특이사항);
                db.close();
                Toast toast = Toast.makeText(Admin_enterprise_insert.this, "기업 등록 완료", Toast.LENGTH_SHORT);
                toast.show();
            }


        });


        Button btn_취소=(Button)findViewById(R.id.button_insert_취소);
        btn_취소.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



}
