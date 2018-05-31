package choiseongyoon.howtojob;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;



public class Student_enterprise_search extends BaseActivity {
    LinearLayout layout_기업명, layout_기업조건;

    //기업명으로 검색하는 버튼
    Button btn_searchBy기업명;

    //기업조건으로 검색하는 버튼
    Button btn_searchBy기업조건;

    EditText et_search_기업명;
    Spinner sp_search_기업형태;
    Spinner sp_search_산업군;
    Spinner sp_search_본사근무지;

    Button button_기업명,button_기업조건;


    Cursor cursor;
    final DBHelper dbHelper = new DBHelper(this);


    String 조회기업명=null;
    String 조회기업형태=null;
    String 조회산업군=null;
    String 조회본사근무지=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_enterprise_search);
        final DBHelper dbHelper = new DBHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        Intent intent=getIntent();
        final String 학번 =intent.getStringExtra("학번");



        et_search_기업명 = (EditText)findViewById(R.id.editText_검색기업명);
        sp_search_기업형태 = (Spinner)findViewById(R.id.spinner_search_기업형태);
        sp_search_산업군 = (Spinner)findViewById(R.id.spinner_search_산업군);
        sp_search_본사근무지 = (Spinner)findViewById(R.id.spinner_search_본사근무지);



        String 기업명 = et_search_기업명.getText().toString();
        String 기업형태 = sp_search_기업형태.getSelectedItem().toString();
        String 산업군 = sp_search_산업군.getSelectedItem().toString();
        String 본사근무지 = sp_search_본사근무지.getSelectedItem().toString();


        button_기업명 = (Button) findViewById(R.id.button_기업명);
        button_기업조건 = (Button) findViewById(R.id.button_기업조건);
        layout_기업명 = (LinearLayout) findViewById(R.id.layout_기업명);
        layout_기업조건 = (LinearLayout) findViewById(R.id.layout_기업조건);

        setVisibility();

        btn_searchBy기업명= (Button)findViewById(R.id.button_searchBy기업명);
        btn_searchBy기업명.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String 기업명 = et_search_기업명.getText().toString();
                Intent intent = new Intent(getApplicationContext(),Student_searched_enterprise_view.class);
                intent.putExtra("기업명",기업명);
                intent.putExtra("학번",학번);

              startActivity(intent);
            }
        });

        btn_searchBy기업조건= (Button)findViewById(R.id.button_searchBy기업조건);
        btn_searchBy기업조건.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String 기업형태 = sp_search_기업형태.getSelectedItem().toString();
                String 산업군 = sp_search_산업군.getSelectedItem().toString();
                String 본사근무지 = sp_search_본사근무지.getSelectedItem().toString();

                cursor = db.rawQuery("SELECT * FROM 기업 WHERE 기업형태 = '" + 기업형태 + "' AND 산업군 = '" + 산업군 + "' AND 본사근무지 = '" + 본사근무지 + "' ",null);
                int size = cursor.getCount();

                if(cursor.getCount() ==0){
                    //아이디가 틀렸습니다.
                    Toast toast = Toast.makeText(Student_enterprise_search.this, "일치하는 기업이 없습니다", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                String 기업[]= new String[size];


                for(int i=0;i<size;i++){
                    cursor.moveToNext();
                    기업[i] = cursor.getString(0);

                }



                Intent intent = new Intent(getApplicationContext(), Enterprise_list.class);
                for(int i=0;i<size;i++){
                    intent.putExtra("기업명배열"+i, 기업[i]);}
                intent.putExtra("기업형태", 기업형태);
                intent.putExtra("산업군", 산업군);
                intent.putExtra("본사근무지", 본사근무지);
                intent.putExtra("사이즈", size);
                intent.putExtra("학번",학번);

                startActivity(intent);
                db.close();
            }



        });

        Button btn_search_back = (Button)findViewById(R.id.button_search_취소);
        btn_search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void setVisibility()
    {
        button_기업명.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_기업명.setVisibility(v.VISIBLE);
                layout_기업조건.setVisibility(v.INVISIBLE);
            }
        });
        button_기업조건.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_기업명.setVisibility(v.INVISIBLE);
                layout_기업조건.setVisibility(v.VISIBLE);

            }
        });

    }



}
