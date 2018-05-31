package choiseongyoon.howtojob;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import static android.graphics.Color.BLACK;

public class BaseActivity extends Activity {

    private static Typeface mTypeface;
    private static TextView mTextview;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        if (BaseActivity.mTypeface == null)
            BaseActivity.mTypeface = Typeface.createFromAsset(getAssets(), "NanumGothicCoding-Bold.ttf");

        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);
    }
    void setGlobalFont(ViewGroup root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView){
                ((TextView)child).setTypeface(mTypeface);
                ((TextView)child).setTextSize(20);
                ((TextView)child).setTextColor(BLACK);


            }
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup)child);
        }
}}