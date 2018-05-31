package choiseongyoon.howtojob;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Admin_department_modify extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_department_modify);
        final DBHelper dbHelper = new DBHelper(this);



        final EditText et_modify_모집부문 = (EditText) findViewById(R.id.editText_modify_부문_부문명);
        final EditText et_modify_포함기업 = (EditText) findViewById(R.id.editText_modify_부문_포함기업);
        final EditText et_modify_담당업무 = (EditText) findViewById(R.id.editText_modify_담당업무);
        final Spinner sp_modify_근무지 = (Spinner)findViewById(R.id.spinner_modify_근무지);
        final Spinner sp_modify_학력 = (Spinner)findViewById(R.id.spinner_modify_학력);
        final Spinner sp_modify_경력 = (Spinner)findViewById(R.id.spinner_modify_경력);



        Button btn_modify_지원부문 = (Button) findViewById(R.id.button_modify_지원부문);
        btn_modify_지원부문.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String 모집부문 = et_modify_모집부문.getText().toString();
                String 포함기업 = et_modify_포함기업.getText().toString();
                String 담당업무 = et_modify_담당업무.getText().toString();
                String 근무지 = sp_modify_근무지.getSelectedItem().toString();
                String 학력 = sp_modify_학력.getSelectedItem().toString();
                String 경력 = sp_modify_경력.getSelectedItem().toString();

                dbHelper.부문_update(모집부문, 담당업무, 근무지, 학력, 경력);

                db.close();

            }
        });



        Button button_modify_지원_뒤로 = (Button) findViewById(R.id.button_modify_지원_뒤로);
        button_modify_지원_뒤로.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });



    }
}

