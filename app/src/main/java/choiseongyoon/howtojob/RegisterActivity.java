package choiseongyoon.howtojob;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends BaseActivity {
    TextView tvstatus;

    EditText et학번;
    EditText et학생명;
    EditText et비밀번호;
    EditText et학과;
    EditText et학점;

    TextView tv학번;
    TextView tv학생명;
    TextView tv비밀번호;
    TextView tv학과;
    TextView tv학점;

    Button btn_완료;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_register);
        final DBHelper dbHelper = new DBHelper(this);


        final EditText et학번=(EditText)findViewById(R.id.editText_학번);
        final EditText et학생명=(EditText)findViewById(R.id.editText_학생명);
        final EditText et비밀번호=(EditText)findViewById(R.id.editText_비밀번호);
        final Spinner sp학과=(Spinner)findViewById(R.id.spinner_학과);
        final EditText et학점=(EditText)findViewById(R.id.editText_학점);
        final EditText et토익성적=(EditText)findViewById(R.id.editText_토익성적);
        final EditText et자격증=(EditText)findViewById(R.id.editText_자격증);
        final EditText et인턴경험=(EditText)findViewById(R.id.editText_인턴경험);

        final TextView tv학번=(TextView)findViewById(R.id.textView_학번) ;
        final TextView tv학생명=(TextView)findViewById(R.id.textView_학생명);
        final TextView tv비밀번호=(TextView)findViewById(R.id.textView_비밀번호);
        final TextView tv학과=(TextView)findViewById(R.id.textView_학과);
        final TextView tv학점=(TextView)findViewById(R.id.textView_학점);
        final TextView tv토익성적ㅈ=(TextView)findViewById(R.id.textView_토익성적);
        final TextView tv자격증=(TextView)findViewById(R.id.textView_자격증);
        final TextView tv인턴경험=(TextView)findViewById(R.id.textView_인턴경험);

        Button btn_완료 = (Button)findViewById(R.id.btn_완료);
        btn_완료.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                int 학번 = Integer.parseInt(et학번.getText().toString());
                String 학생명 = et학생명.getText().toString();
                int 비밀번호 = Integer.parseInt(et비밀번호.getText().toString());
                String 학과 = sp학과.getSelectedItem().toString();
                double 학점 = Double.parseDouble(et학점.getText().toString());
                int 토익성적= Integer.parseInt(et토익성적.getText().toString());
                int 자격증 = Integer.parseInt(et자격증.getText().toString());
                int 인턴경험= Integer.parseInt(et인턴경험.getText().toString());

                dbHelper.학생_insert(학번,학생명,비밀번호,학과,학점,토익성적,자격증,인턴경험);
                db.close();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        Button btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
