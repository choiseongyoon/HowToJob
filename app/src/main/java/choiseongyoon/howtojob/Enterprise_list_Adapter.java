package choiseongyoon.howtojob;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Enterprise_list_Adapter extends BaseAdapter  {

    private Context context;
    private int layoutId;
    private ArrayList<ListviewItem> list  = new ArrayList<ListviewItem>() ;;
    private LayoutInflater inflater;


    public Enterprise_list_Adapter(){
    }


    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return list.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        //  Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_listview_item, parent, false);
        }

        ListviewItem item = list.get(position);


        //텍스트뷰에 이름 넣기
        TextView 기업명=(TextView)convertView.findViewById(R.id.item_기업명);
        기업명.setText(item.get기업명());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return list.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String 입력기업명) {
        ListviewItem item = new ListviewItem(입력기업명);
        item.set기업명(입력기업명);
        //item.set기업형태(입력기업형태);
       // item.set산업군(입력산업군);
        //item.set본사근무지(입력본사근무지);
       // item.set특이사항(입력특이사항);

        list.add(item);
    }
    public void delItem(){
        list.clear();
    }

}
