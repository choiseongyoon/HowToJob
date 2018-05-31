package choiseongyoon.howtojob;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Admin_department_insert extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_department_insert);
        final DBHelper dbHelper = new DBHelper(this);



        final EditText et_insert_모집부문 = (EditText) findViewById(R.id.editText_insert_부문_부문명);
        final EditText et_insert_포함기업 = (EditText) findViewById(R.id.editText_insert_부문_포함기업);
        final EditText et_insert_담당업무 = (EditText) findViewById(R.id.editText_insert_담당업무);
        final Spinner sp_insert_근무지 = (Spinner)findViewById(R.id.spinner_insert_근무지);
        final Spinner sp_insert_학력 = (Spinner)findViewById(R.id.spinner_insert_학력);
        final Spinner sp_insert_경력 = (Spinner)findViewById(R.id.spinner_insert_경력);


        Button btn_insert_지원부문 = (Button) findViewById(R.id.button_insert_지원부문);
        btn_insert_지원부문.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String 모집부문 = et_insert_모집부문.getText().toString();
               String 포함기업 = et_insert_포함기업.getText().toString();
                String 담당업무 = et_insert_담당업무.getText().toString();
                String 근무지 = sp_insert_근무지.getSelectedItem().toString();
                String 학력 = sp_insert_학력.getSelectedItem().toString();
                String 경력 = sp_insert_경력.getSelectedItem().toString();

                dbHelper.부문_insert(모집부문, 담당업무, 근무지, 학력, 경력);
                dbHelper.포함_insert(모집부문, 포함기업);
                Toast toast = Toast.makeText(Admin_department_insert.this, "모집 부문 등록 완료", Toast.LENGTH_SHORT);
                toast.show();

                db.close();

            }
        });



        Button button_insert_recruit_뒤로 = (Button) findViewById(R.id.button_insert_recruit_뒤로);
        button_insert_recruit_뒤로.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });



    }
}
