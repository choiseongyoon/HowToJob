package choiseongyoon.howtojob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Department_list extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);
        final ListView listview = (ListView)findViewById(R.id.listView_Department);
        //데이터가 들어가 있는 리스트 생성
        List<String> list = new ArrayList<>();
        ArrayAdapter<String>adapter  = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, list);
        //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터

        listview.setAdapter(adapter);
        //리스트뷰의 어댑터를 지정

        Intent intent = getIntent();
        int size = intent.getExtras().getInt("모집부문갯수");

        final String 부문[] = new String[size];
        String 기업명 = intent.getStringExtra("기업명");
        final String 학번 = intent.getStringExtra("학번");
        for (int i = 0; i < size; i++) {
            부문[i] = intent.getExtras().getString("모집부문배열" + i);
            adapter.add(부문[i]);

        }


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {


                Intent intent = new Intent(getApplicationContext(), Student_searched_department_view.class);
                intent.putExtra("부문명",부문[position1]);
                intent.putExtra("학번", 학번);
                startActivity(intent);

            }


        });

        Button btn_뒤로 = findViewById(R.id.button_뒤로);
        btn_뒤로.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}