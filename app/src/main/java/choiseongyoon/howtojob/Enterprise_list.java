package choiseongyoon.howtojob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Enterprise_list extends BaseActivity {
    ArrayList<ListviewItem> Enterprise_list=new ArrayList<ListviewItem>();
    ListView listView_Enterprise;
    Enterprise_list_Adapter adapter = new Enterprise_list_Adapter();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_list);
        listView_Enterprise = (ListView) findViewById(R.id.listView_Enterprise);

        Intent intent = getIntent();
        int size = intent.getExtras().getInt("사이즈");
        String 기업형태 = intent.getStringExtra("기업형태");
        String 산업군 = intent.getStringExtra("산업군");
        String 본사근무지 = intent.getStringExtra("본사근무지");
        String 기업특이사항=intent.getStringExtra("기업특이사항");
        final String 학번=intent.getStringExtra("학번");

        final String 기업[] = new String[size];
        listView_Enterprise.setAdapter(adapter);

        String 기업명 = intent.getStringExtra("기업명");
        for (int i = 0; i < size; i++) {
            기업[i] = intent.getExtras().getString("기업명배열" + i);
            adapter.addItem(기업[i]);

        }





        listView_Enterprise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                Intent intent = new Intent(getApplicationContext(), Student_searched_enterprise_view.class);
                ListviewItem Item = (ListviewItem) listView_Enterprise.getItemAtPosition(position1);
                intent.putExtra("기업명",기업[position1]);
                intent.putExtra("학번",학번);
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
