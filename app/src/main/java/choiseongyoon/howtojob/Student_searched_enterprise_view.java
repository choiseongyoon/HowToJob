package choiseongyoon.howtojob;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Student_searched_enterprise_view extends BaseActivity {
    String enterprise_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_searched_enterprise_view);
        final DBHelper dbHelper = new DBHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;

        // Student_enterprise_search 인텐트로부터  전달 받아 '기업명' 변수에 저장
        Intent intent=getIntent();
        enterprise_name=intent.getStringExtra("기업명");
        final String  학번=intent.getStringExtra("학번");

        String 기업명= null;
        String 기업형태=null;
        String 산업군 = null;
        String 본사근무지=null;
        String 기업특이사항 = null;


        TextView tv_searched_기업명 = (TextView)findViewById(R.id.textView_searched_enterprise_기업명) ;
        TextView tv_searched_기업형태  = (TextView)findViewById(R.id.textView_searched_enterprise_기업형태) ;
        TextView tv_searched_산업군 = (TextView)findViewById(R.id.textView_searched_enterprise_산업군) ;
        TextView tv_searched_본사근무지 = (TextView)findViewById(R.id.textView_searched_enterprise_본사근무지) ;
        TextView tv_searched_기업특이사항  = (TextView)findViewById(R.id.textView_searched_enterprise_기업특이사항) ;


        String sql="SELECT * FROM 기업 WHERE 기업명='"+enterprise_name+"'";

        cursor = db.rawQuery(sql,null);


        if(cursor!=null){
            int count=cursor.getCount();

            for(int i=0;i<count;i++){
                cursor.moveToNext();
                기업명 = cursor.getString(0);
                기업형태= cursor.getString(1);
                산업군 = cursor.getString(2);
                본사근무지=cursor.getString(3);
                기업특이사항 = cursor.getString(4);

            }
            cursor.close();

            tv_searched_기업명.setText(기업명);
            tv_searched_기업형태.setText(기업형태);
            tv_searched_산업군.setText(산업군);
            tv_searched_본사근무지.setText(본사근무지);
            tv_searched_기업특이사항.setText(기업특이사항);

        }
        Button btn_searched_enterprise_baek = (Button)findViewById(R.id.button_searched_enterprise_이전);
        btn_searched_enterprise_baek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button button_지원 = (Button)findViewById(R.id.button_지원);
        button_지원.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor;

                cursor = db.rawQuery("SELECT 모집부문 FROM 포함 WHERE 포함기업 = '" + enterprise_name + "' ", null);


                int size = cursor.getCount();
                if (cursor.getCount() == 0) {
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(Student_searched_enterprise_view.this, "기업에 해당하는 모집부문이 없습니다", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }


                String 부문[] = new String[size];
                for (int i = 0; i < size; i++) {
                    cursor.moveToNext();
                    부문[i] = cursor.getString(0);
                }

                cursor.close();


                //기업에 해당하는 모든 부문을 원소로 가진 배열(부문)을 Department_list 액티비티로 전달
                Intent intent = new Intent(getApplicationContext(),Department_list.class);

                for (int i = 0; i < size; i++) {
                    intent.putExtra("모집부문배열" + i, 부문[i]);
                }
                intent.putExtra("기업명", enterprise_name);
                intent.putExtra("모집부문갯수", size);
                intent.putExtra("학번", 학번);
                startActivity(intent);
                db.close();




            }
        });

    }
}
