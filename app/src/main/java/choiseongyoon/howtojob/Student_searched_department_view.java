package choiseongyoon.howtojob;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class Student_searched_department_view extends Graph {

    String department_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_searched_department_view);
        final DBHelper dbHelper = new DBHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;


        //부문명 Student_enterprise_search 인텐트로부터  전달 받아 '부문명' 변수에 저장
        Intent intent = getIntent();
        final String department_name = intent.getStringExtra("부문명");
        final String 학번 = intent.getStringExtra("학번");
        String 부문명 = null;
        String 담당업무 = null;
        String 근무지 = null;
        String 학력 = null;
        String 경력 = null;


        TextView tv_searched_부문명 = (TextView) findViewById(R.id.textView_searched_department_부문명);
        TextView tv_searched_담당업무 = (TextView) findViewById(R.id.textView_searched_department_담당업무);
        TextView tv_searched_근무지 = (TextView) findViewById(R.id.textView_searched_department_근무지);
        TextView tv_searched_학력 = (TextView) findViewById(R.id.textView_searched_department_학력);
        TextView tv_searched_경력 = (TextView) findViewById(R.id.textView_searched_department_경력);

        String sql = "SELECT * FROM 부문 WHERE 부문명='" + department_name + "'";

        cursor = db.rawQuery(sql, null);


        if (cursor != null) {
            int count = cursor.getCount();

            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                부문명 = cursor.getString(0);
                담당업무 = cursor.getString(1);
                근무지 = cursor.getString(2);
                학력 = cursor.getString(3);
                경력 = cursor.getString(4);

            }
            cursor.close();

            tv_searched_부문명.setText(부문명);
            tv_searched_담당업무.setText(담당업무);
            tv_searched_근무지.setText(근무지);
            tv_searched_학력.setText(학력);
            tv_searched_경력.setText(경력);

        }
        final int student_num = parseInt(학번);
        TextView tv_지원자수 = (TextView) findViewById(R.id.textView_지원자수);
        TextView tv_학점평균 = (TextView) findViewById(R.id.textView_평균학점);
        TextView tv_토익점수평균 = (TextView) findViewById(R.id.textView_평균토익점수);
        TextView tv_자격증평균 = (TextView) findViewById(R.id.textView_평균자격증);
        TextView tv_인턴경험평균 = (TextView) findViewById(R.id.textView_평균인턴경험);


        int 지원자수 = 0;
        double 평균학점 = 0;
        int 평균토익성적 = 0;
        int 평균자격증수 = 0;
        int 평균인턴경험수 = 0;
        sql = "SELECT COUNT(*),AVG(학점),AVG(토익성적),AVG(자격증),AVG(인턴경험) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "'";
        cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                지원자수 = cursor.getInt(0);
                평균학점 = cursor.getDouble(1);
                평균토익성적 = cursor.getInt(2);
                평균자격증수 = cursor.getInt(3);
                평균인턴경험수 = cursor.getInt(4);
            }
            cursor.close();

            tv_지원자수.setText(String.valueOf(지원자수));
            tv_학점평균.setText(String.format("%.2f",평균학점));
            tv_토익점수평균.setText(String.valueOf(평균토익성적));
            tv_자격증평균.setText(String.valueOf(평균자격증수));
            tv_인턴경험평균.setText(String.valueOf(평균인턴경험수));
        }

        Button btn_searched_department_지원 = (Button) findViewById(R.id.button_searched_department_지원);
        btn_searched_department_지원.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.지원_insert(student_num, department_name, "미정");


            }
        });


        Button btn_searched_department_back = (Button) findViewById(R.id.button_searched_department_이전);
        btn_searched_department_back.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                finish();
                                                            }
                                                        }
        );

        PieChart pieChart = (PieChart) findViewById(R.id.piechart_학점);
        pieChart.setUsePercentValues(true);
        ArrayList<Entry> yvalues = new ArrayList<Entry>();

        int 학점_1 = 0, 학점_2 = 0, 학점_3 = 0, 학점_4 = 0, 학점_5 = 0;
        String sql_1 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 0<=학생.학점 AND 학생.학점<2.0";
        cursor = db.rawQuery(sql_1, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                학점_1 = cursor.getInt(0);
            }
        }
        String sql_2 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 2.0<=학생.학점 AND 학생.학점<3.0";
        cursor = db.rawQuery(sql_2, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                학점_2 = cursor.getInt(0);
            }
        }
        String sql_3 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 3.0<=학생.학점 AND 학생.학점<3.5";
        cursor = db.rawQuery(sql_3, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                학점_3 = cursor.getInt(0);
            }
        }
        String sql_4 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 3.5<=학생.학점 AND 학생.학점<4.0";
        cursor = db.rawQuery(sql_4, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                학점_4 = cursor.getInt(0);
            }
        }
        String sql_5 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 4.0<=학생.학점 AND 학생.학점<=4.5";
        cursor = db.rawQuery(sql_5, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                학점_5 = cursor.getInt(0);
            }
        }

        cursor.close();

        yvalues.add(new Entry(학점_1, 0));
        yvalues.add(new Entry(학점_2, 1));
        yvalues.add(new Entry(학점_3, 2));
        yvalues.add(new Entry(학점_4, 3));
        yvalues.add(new Entry(학점_5, 4));

        PieDataSet dataSet = new PieDataSet(yvalues, "학점분포");
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("1.0 - 2.0");
        xVals.add("2.0 - 3.0");
        xVals.add("3.0 - 3.5");
        xVals.add("3.5 - 4.0");
        xVals.add("4.0 - 4.5");

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueFormatter(new DefaultValueFormatter(0));
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(data);

        //토익성적
        PieChart pieChart_토익 = (PieChart) findViewById(R.id.piechart_토익점수);
        pieChart_토익.setUsePercentValues(true);
        ArrayList<Entry> yvalues_토익 = new ArrayList<Entry>();

        int 토익_1 = 0, 토익_2 = 0, 토익_3 = 0, 토익_4 = 0, 토익_5 = 0, 토익_6 = 0;
        String sql_토익_1 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 0<=학생.토익성적 AND 학생.토익성적<750";
        cursor = db.rawQuery(sql_토익_1, null);

        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                토익_1 = cursor.getInt(0);
            }
        }
        String sql_토익_2 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 750<=학생.토익성적 AND 학생.토익성적<800";
        cursor = db.rawQuery(sql_토익_2, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                토익_2 = cursor.getInt(0);
            }
        }
        String sql_토익_3 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 800<=학생.토익성적 AND 학생.토익성적<850";
        cursor = db.rawQuery(sql_토익_3, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                토익_3 = cursor.getInt(0);
            }
        }
        String sql_토익_4 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 850<=학생.토익성적 AND 학생.토익성적<900";
        cursor = db.rawQuery(sql_토익_4, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                토익_4 = cursor.getInt(0);
            }
        }
        String sql_토익_5 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 900<=학생.토익성적 AND 학생.토익성적<950";
        cursor = db.rawQuery(sql_토익_5, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                토익_5 = cursor.getInt(0);
            }
        }
        String sql_토익_6 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 950<=학생.토익성적 AND 학생.토익성적<=990";
        cursor = db.rawQuery(sql_토익_6, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                토익_6 = cursor.getInt(0);
            }
        }


        yvalues_토익.add(new Entry(토익_1, 0));
        yvalues_토익.add(new Entry(토익_2, 1));
        yvalues_토익.add(new Entry(토익_3, 2));
        yvalues_토익.add(new Entry(토익_4, 3));
        yvalues_토익.add(new Entry(토익_5, 4));
        yvalues_토익.add(new Entry(토익_6, 5));

        PieDataSet dataSet_토익 = new PieDataSet(yvalues_토익, "토익성적분포");
        ArrayList<String> xVals_토익 = new ArrayList<String>();

        xVals_토익.add("0-750");
        xVals_토익.add("750-800");
        xVals_토익.add("800-850");
        xVals_토익.add("850-900");
        xVals_토익.add("900-950");
        xVals_토익.add("900-990");

        PieData data_토익 = new PieData(xVals_토익, dataSet_토익);
        data_토익.setValueFormatter(new PercentFormatter());
        data_토익.setValueFormatter(new DefaultValueFormatter(0));
        dataSet_토익.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart_토익.setData(data_토익);

        //자격증
        PieChart pieChart_자격증 = (PieChart) findViewById(R.id.piechart_자격증);
        pieChart_자격증.setUsePercentValues(true);
        ArrayList<Entry> yvalues_자격증 = new ArrayList<Entry>();

        int 자격증_1 = 0, 자격증_2 = 0, 자격증_3 = 0, 자격증_4 = 0, 자격증_5 = 0;
        String sql_자격증_1 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.자격증=0";
        cursor = db.rawQuery(sql_자격증_1, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                자격증_1 = cursor.getInt(0);
            }
        }

        String sql_자격증_2 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.자격증=1";
        cursor = db.rawQuery(sql_자격증_2, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                자격증_2 = cursor.getInt(0);
            }
        }

        String sql_자격증_3 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.자격증=2";
        cursor = db.rawQuery(sql_자격증_3, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                자격증_3 = cursor.getInt(0);
            }
        }

        String sql_자격증_4 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.자격증=3";
        cursor = db.rawQuery(sql_자격증_4, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                자격증_4 = cursor.getInt(0);
            }
        }

        String sql_자격증_5 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 4<=학생.자격증";
        cursor = db.rawQuery(sql_자격증_5, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                자격증_5 = cursor.getInt(0);
            }
        }



        yvalues_자격증.add(new Entry(자격증_1, 0));
        yvalues_자격증.add(new Entry(자격증_2, 1));
        yvalues_자격증.add(new Entry(자격증_3, 2));
        yvalues_자격증.add(new Entry(자격증_4, 3));
        yvalues_자격증.add(new Entry(자격증_5, 4));

        PieDataSet dataSet_자격증 = new PieDataSet(yvalues_자격증, "자격증 갯수");
        ArrayList<String> xVals_자격증 = new ArrayList<String>();

        xVals_자격증.add("0개");
        xVals_자격증.add("1개");
        xVals_자격증.add("2개");
        xVals_자격증.add("3개");
        xVals_자격증.add("4개이상");

        PieData data_자격증 = new PieData(xVals_자격증, dataSet_자격증);
        data_자격증.setValueFormatter(new PercentFormatter());
        data_자격증.setValueFormatter(new DefaultValueFormatter(0));
        dataSet_자격증.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart_자격증.setData(data_자격증);


        //인턴경험
        PieChart pieChart_인턴경험= (PieChart) findViewById(R.id.piechart_인턴경험);
        pieChart_인턴경험.setUsePercentValues(true);
        ArrayList<Entry> yvalues_인턴경험 = new ArrayList<Entry>();

        int 인턴경험_1=0, 인턴경험_2=0, 인턴경험_3=0, 인턴경험_4=0, 인턴경험_5=0;
        String sql_인턴경험_1 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.인턴경험=0";
        cursor = db.rawQuery(sql_인턴경험_1, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                인턴경험_1 = cursor.getInt(0);
            }
        }
        String sql_인턴경험_2 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.인턴경험=1";
        cursor = db.rawQuery(sql_인턴경험_2, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                인턴경험_2 = cursor.getInt(0);
            }
        }
        String sql_인턴경험_3 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.인턴경험=2";
        cursor = db.rawQuery(sql_인턴경험_3, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                인턴경험_3 = cursor.getInt(0);
            }
        }
        String sql_인턴경험_4 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 학생.인턴경험=3";
        cursor = db.rawQuery(sql_인턴경험_4, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                인턴경험_4 = cursor.getInt(0);
            }
        }
        String sql_인턴경험_5 = "SELECT COUNT(*) FROM 학생 학생 INNER JOIN 지원 지원 ON 학생.학번=지원.지원자  WHERE 지원.지원부문 = '" + department_name + "' AND 4<=학생.인턴경험";
        cursor = db.rawQuery(sql_인턴경험_5, null);
        if (cursor != null) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                인턴경험_5 = cursor.getInt(0);
            }
        }
        yvalues_인턴경험.add(new Entry(인턴경험_1, 0));
        yvalues_인턴경험.add(new Entry(인턴경험_2, 1));
        yvalues_인턴경험.add(new Entry(인턴경험_3, 2));
        yvalues_인턴경험.add(new Entry(인턴경험_4, 3));
        yvalues_인턴경험.add(new Entry(인턴경험_5, 4));

        PieDataSet dataSet_인턴경험 = new PieDataSet(yvalues_인턴경험, "인턴 경험 횟수");
        ArrayList<String> xVals_인턴경험 = new ArrayList<String>();

        xVals_인턴경험.add("0회");
        xVals_인턴경험.add("1회");
        xVals_인턴경험.add("2회");
        xVals_인턴경험.add("3회");
        xVals_인턴경험.add("4회이상");

        PieData data_인턴경험 = new PieData(xVals_인턴경험, dataSet_인턴경험);
        data_인턴경험.setValueFormatter(new PercentFormatter());
        data_인턴경험.setValueFormatter(new DefaultValueFormatter(0));
        dataSet_인턴경험.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart_인턴경험.setData(data_인턴경험);

        cursor.close();
    }

}
