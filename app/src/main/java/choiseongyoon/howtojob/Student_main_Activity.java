package choiseongyoon.howtojob;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Student_main_Activity extends BaseActivity {
    Button btn_back;
    Button btn_회원정보수정;
    String stu_num;
    TextView tv_지원내역;

    TextView tv_학번;
    TextView tv_학생명;
    TextView tv_학과;
    TextView tv_학점;
    TextView tv_토익성적;
    TextView tv_자격증;
    TextView tv_인턴경험;

    TextView tv_지원회사;
    TextView tv_지원부문;
    TextView tv_지원결과;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);
        final DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        Cursor cursor1;
        Intent intent = getIntent();
        stu_num = intent.getStringExtra("stu_num");


        int 학번 = 0;
        String 학생명 = null;
        String 학과 = null;
        double 학점 = 0;
        int 토익성적 = 0;
        int 자격증 = 0;
        int 인턴경험 = 0;


        // tv_지원내역 = (TextView)findViewById(R.id.textView_지원내역) ;
        tv_지원회사 = (TextView) findViewById(R.id.textView_지원회사);
        tv_지원부문 = (TextView) findViewById(R.id.textView_지원부문);
     //   tv_지원결과 = (TextView) findViewById(R.id.textView_지원결과);


        tv_학번 = (TextView) findViewById(R.id.textView_학번);
        tv_학생명 = (TextView) findViewById(R.id.textView_학생명);
        tv_학과 = (TextView) findViewById(R.id.textView_학과);
        tv_학점 = (TextView) findViewById(R.id.textView_학점);
        tv_토익성적= (TextView) findViewById(R.id.textView_토익성적);
        tv_자격증 = (TextView) findViewById(R.id.textView_자격증);
        tv_인턴경험 = (TextView) findViewById(R.id.textView_인턴경험);


        String sql = "SELECT * FROM 학생 WHERE 학번='" + stu_num + "'";

        cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            int count = cursor.getCount();

            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                학번 = cursor.getInt(0);
                학생명 = cursor.getString(1);
                학과 = cursor.getString(3);
                학점 = cursor.getDouble(4);
                토익성적 = cursor.getInt(5);
                자격증 = cursor.getInt(6);
                인턴경험 = cursor.getInt(7);

            }
            cursor.close();

            tv_학번.setText(String.valueOf(학번));
            tv_학생명.setText(학생명);
            tv_학과.setText(학과);
            tv_학점.setText(String.valueOf(학점));
            tv_토익성적.setText(String.valueOf(토익성적));
            tv_자격증.setText(String.valueOf(자격증));
            tv_인턴경험.setText(String.valueOf(인턴경험));

        }

        int size=0;
        String 지원부문[]=null;
        String sql2 = "SELECT * FROM 지원 WHERE 지원자='" + stu_num + "'";
        cursor = db.rawQuery(sql2, null);

        if (cursor != null) {
           int count = cursor.getCount();
            String result_지원부문 = "";
          //  String result_지원결과 = "";
            지원부문 = new String[count];


            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                지원부문[i] = cursor.getString(1);
                result_지원부문 += cursor.getString(1) + "\n";
             //   result_지원결과 += cursor.getString(2) + "\n";

            }
            cursor.close();
            size = count;
            tv_지원부문.setText(result_지원부문);
           // tv_지원결과.setText(result_지원결과);


        }

        String result_지원회사 ="";

        for (int j = 0; j < size; j++) {
        String sql3 = "SELECT * FROM 포함 WHERE 모집부문='" + 지원부문[j]  + "'";

        cursor = db.rawQuery(sql3, null);

        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                result_지원회사 += cursor.getString(1) + "\n";
            }

            cursor.close();
        }
        }
        tv_지원회사.setText(result_지원회사);


        btn_back = findViewById(R.id.button_back);
            btn_back.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                finish();
                                            }
                                        }
            );

            btn_회원정보수정 = findViewById(R.id.회원정보수정);
            btn_회원정보수정.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  Intent intent = new Intent(getApplicationContext(), Student_info_modify.class);
                                                  intent.putExtra("stu_num", stu_num);
                                                  startActivity(intent);

                                              }
                                          }
            );

            Button btn_기업검색및지원 = findViewById(R.id.button_기업검색및지원);
            btn_기업검색및지원.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Student_enterprise_search.class);
                    intent.putExtra("학번", stu_num);
                    startActivity(intent);

                }
            });


    }}
