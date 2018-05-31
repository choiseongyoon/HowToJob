package choiseongyoon.howtojob;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    Button btn_로그인;
    Button btn_등록;
    EditText editText_학번;
    EditText editText_비밀번호;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_학번 = (EditText)findViewById(R.id.editText_학번);
        editText_비밀번호 = (EditText)findViewById(R.id.editText_비밀번호);
        final DBHelper dbHelper = new DBHelper(this);


       // TextView tv_main_학생테이블 = (TextView)findViewById(R.id.textView_main_학생테이블);
       // tv_main_학생테이블.setText(dbHelper.학생_getResult());

        //TextView tv_main_기업테이블 = (TextView)findViewById(R.id.textView_main_기업테이블);
        //tv_main_기업테이블.setText(dbHelper.기업_getResult());


        btn_로그인 = (Button)findViewById(R.id.btn_로그인);
        btn_로그인.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String 학번 = editText_학번.getText().toString();
                String 비밀번호 = editText_비밀번호.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor;

                if(학번.length() == 0 || 비밀번호.length() == 0) {
                    //아이디와 비밀번호는 필수 입력사항입니다.
                    Toast toast = Toast.makeText(MainActivity.this, "아이디와 비밀번호는 필수 입력사항입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                cursor=db.rawQuery("SELECT 학번 FROM 학생 "+  " WHERE 학번 = '" + 학번+ "'", null);

                if(cursor.getCount() != 1){
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(MainActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                cursor=db.rawQuery("SELECT 비밀번호 FROM 학생 "+  " WHERE 학번 = '" + 학번+ "'", null);


                cursor.moveToNext();
                if(!비밀번호.equals(cursor.getString(0))){
                    //비밀번호가 틀렸습니다.
                    Toast toast = Toast.makeText(MainActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    //로그인성공
                    Toast toast = Toast.makeText(MainActivity.this,"로그인 성공", Toast.LENGTH_SHORT);
                    toast.show();

                    //인텐트 생성 및 호출
                    Intent intent = new Intent(getApplicationContext(),Student_main_Activity.class);
                    intent.putExtra("stu_num",학번);
                    startActivity(intent);


                }
                cursor.close();
            }
        });


        btn_등록 = (Button)findViewById(R.id.btn_등록);
        btn_등록.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        Button btn_관리자 = (Button)findViewById(R.id.btn_관리자모드);
        btn_관리자.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Admin_main_Activity.class);
                startActivity(intent);
            }
        });



    }


}
