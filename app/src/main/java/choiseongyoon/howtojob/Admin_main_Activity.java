package choiseongyoon.howtojob;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Admin_main_Activity extends BaseActivity {

    Button btn_관리자_로그인;
    Button getBtn_관리자_취소;
    EditText et_ID ;
    EditText et_비밀번호;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        final DBHelper dbHelper = new DBHelper(this);
        et_ID = (EditText)findViewById(R.id.editText_관리자ID);
        et_비밀번호 = (EditText)findViewById(R.id.editText_관리자비밀번호);

        btn_관리자_로그인 = (Button)findViewById(R.id.button_관리자_로그인);
        btn_관리자_로그인.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String 관리자ID = et_ID.getText().toString();
                String 관리자비밀번호 = et_비밀번호.getText().toString();


                if(관리자ID.length() == 0 || 관리자비밀번호.length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(Admin_main_Activity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                if(!관리자ID.equals("admin") ){
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(Admin_main_Activity.this, "ID가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if(!관리자비밀번호.equals("1")){
                    //비밀번호가 틀렸습니다.
                    Toast toast = Toast.makeText(Admin_main_Activity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    //로그인성공
                    Toast toast = Toast.makeText(Admin_main_Activity.this,"관리자 로그인 성공", Toast.LENGTH_SHORT);
                    toast.show();

                    //인텐트 생성 및 호출
                    Intent intent = new Intent(getApplicationContext(),Admin_signedin.class);
                    startActivity(intent);
                    finish();


                }

            }
        });
        getBtn_관리자_취소 = (Button)findViewById(R.id.button_admin_취소);
        getBtn_관리자_취소.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
