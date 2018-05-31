package choiseongyoon.howtojob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//새로운 테이블을 생성하고 데이터 삽입, 삭제 등 전반적으로 db관리
public class DBHelper extends SQLiteOpenHelper {
    //데이타베이스 버전
    private static final int DATABASE_VERSION = 19;

    //데이타베이스 명
    private static final String DATABASE_NAME = "job19.db";

    //테이블 생성
    //기업 테이블
    String sql_기업= "CREATE TABLE IF NOT EXISTS 기업 (" +
            "기업명 CHAR(20) PRIMARY KEY," +
            "기업형태 CHAR(20) NOT NULL," +
            "산업군 CHAR(20) NOT NULL," +
            "본사근무지 CHAR(20) NOT NULL," +
            "기업특이사항 CHAR(20));";

    String sql_학생= "CREATE TABLE IF NOT EXISTS 학생(" +
            "학번 INTEGER PRIMARY KEY AUTOINCREMENT," +
            "학생명 CHAR(20) NOT NULL," +
            "비밀번호 INTEGER NOT NULL," +
            "학과 CHAR(20) NOT NULL," +
            "학점 DOUBLE NOT NULL," +
            "토익성적 INT," +
            "자격증 INT NOT NULL," +
            "인턴경험 INT NOT NULL);";

    String sql_부문= "CREATE TABLE IF NOT EXISTS 부문 (" +
            "부문명 CHAR(20) PRIMARY KEY," +
            "담당업무 CHAR(20) NOT NULL," +
            "근무지 CHAR(20) NOT NULL," +
            "학력 CHAR(10) NOT NULL," +
            "경력 CHAR(20) NOT NULL);";


    String sql_포함= "CREATE TABLE IF NOT EXISTS 포함 (" +
            "모집부문 CHAR(20)," +
            "포함기업 CHAR(20),"+
            "PRIMARY KEY(모집부문, 포함기업),"+
            "FOREIGN KEY(모집부문) REFERENCES 부문(부문명)" +
            "FOREIGN KEY(포함기업) REFERENCES 기업(기업명));";


    String sql_지원= "CREATE TABLE IF NOT EXISTS 지원 (" +
            "지원자 INT," +
            "지원부문 CHAR(20)," +
            "지원결과 CHAR(20)," +
            "FOREIGN KEY(지원자) REFERENCES 학생(학번)" +
            "FOREIGN KEY(지원부문) REFERENCES 부문(부문명)" +
            ");";


    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    //DB생성하는 역할, DB가 존재하지 않을 때 한번 실행
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql_기업);
        db.execSQL(sql_학생);
        db.execSQL(sql_부문);
        db.execSQL(sql_포함);
        db.execSQL(sql_지원);








    }

    @Override

    public void onUpgrade(SQLiteDatabase  db , int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS dic");
        onCreate(db);
    }

     //insert
    public void 학생_insert(int 학번, String 학생명, int 비밀번호, String 학과,double 학점,int 토익성적, int 자격증,int 인턴경험){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO 학생 VALUES("+학번+",'"+학생명+"',"+비밀번호+",'"+학과+"',"+학점+","+토익성적+","+자격증+","+인턴경험+");");
        db.close();
    }


    public void 기업_insert(String 기업명, String 기업형태, String 산업군, String 본사근무지, String 기업특이사항){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO 기업 VALUES('"+기업명+"','"+기업형태+"','"+산업군+"','"+본사근무지+"','"+기업특이사항+"');");
        db.close();
    }


    public void 부문_insert(String 부문명, String 담당업무, String 근무지, String 학력, String 경력){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO 부문 VALUES('"+부문명+"','"+담당업무+"','"+근무지+"','"+학력+"', '"+경력+"');");
        db.close();
    }


    public void 포함_insert(String 모집부문 ,String 포함기업){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO 포함 VALUES('"+모집부문+"','"+포함기업+"');");
        db.close();
    }

    public void 지원_insert(int 지원자 ,String 지원부문, String 지원결과){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO 지원 VALUES("+지원자+",'"+지원부문+"','"+지원결과+"');");
        db.close();
    }



    //update
    public void 학생_update(String 학번, String 학생명, int 비밀번호, String 학과,double 학점, int 토익성적, int 자격증, int 인턴경험){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("학생명", 학생명);
        values.put("비밀번호", 비밀번호);
        values.put("학과", 학과);
        values.put("학점", 학점);
        values.put("토익성적", 토익성적);
        values.put("자격증", 자격증);
        values.put("인턴경험", 인턴경험);

        db.update("학생", values, "학번=?", new String[]{학번});

        db.close();
    }

    public void 기업_update(String 기업명, String 기업형태, String 산업군, String 본사근무지, String 기업특이사항) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("기업형태", 기업형태);
        value.put("산업군", 산업군);
        value.put("본사근무지", 본사근무지);
        value.put("기업특이사항", 기업특이사항);
        db.update("기업", value, "기업명=?", new String[]{기업명});
        db.close();
    }

    public void 부문_update(String 부문명, String 담당업무, String 근무지, String 학력, String 경력) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("부문명", 부문명);
        value.put("담당업무", 담당업무);
        value.put("근무지", 근무지);
        value.put("학력", 학력);
        value.put("경력", 경력);
        db.update("부문", value, "부문명=?", new String[]{부문명});
        db.close();
    }

    public void 지원_update(int 지원자, String 지원부문, String 지원결과) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("지원자", 지원자);
        value.put("지원부문", 지원부문);
        value.put("지원결과", 지원결과);
        db.update("지원자", value, "지원자=?", new String[]{String.valueOf(지원자)});
        db.close();
    }



    public void 학생_delete(int 학번){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM 학생 WHERE 학번 = '"+학번+"'; ");
        db.close();
    }



    public String 학생_getResult(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";
        Cursor cursor=db.rawQuery("SELECT * FROM 학생", null);
        while(cursor.moveToNext()){
            result+= "[학번]"+cursor.getInt(0)+"[성명]"+cursor.getString(1)+"[비밀번호]"+
                    cursor.getInt(2)+"[학과]"+cursor.getString(3)+"[학점]"+cursor.getDouble(4)+"[토익성적]"+cursor.getInt(5)+"[자격증]"+cursor.getInt(6)+"[인턴경험]"+cursor.getInt(7)+"\n";

        }
        db.close();
        return result;
    }

    public String 기업_getResult(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";
        Cursor cursor=db.rawQuery("SELECT * FROM 기업", null);
        while(cursor.moveToNext()){
            result+= "[기업명]"+cursor.getString(0)+"[기업형태]"+cursor.getString(1)+"[산업군]"+
                    cursor.getString(2)+"[본사근무지]"+cursor.getString(3)+"[특이사항]"+cursor.getString(4)+"\n";

        }
        db.close();
        return result;
    }



    public String 부문_getResult(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";
        Cursor cursor=db.rawQuery("SELECT * FROM 부문", null);
        while(cursor.moveToNext()){
            result+="[부문명]"+cursor.getString(0)+"[담당업무]"+cursor.getString(1)+"[근무지]"+cursor.getString(2)+"[학력]"+cursor.getString(3)+"[경력]"+cursor.getString(4)+"\n";

        }
        db.close();
        return result;
    }

    public String 포함_getResult(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";
        Cursor cursor=db.rawQuery("SELECT * FROM 포함", null);
        while(cursor.moveToNext()){
            result+="[모집부문]"+cursor.getString(0)+"[포함기업]"+cursor.getString(1)+"\n";

        }
        db.close();
        return result;
    }

    public String 지원_getResult(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";
        Cursor cursor=db.rawQuery("SELECT * FROM 지원", null);
        while(cursor.moveToNext()){
            result+="[지원자]"+cursor.getInt(0)+"[지원기업]"+cursor.getString(1)+"\n";
        }
        db.close();
        return result;
    }



    public String 학생_getResultby학번(String stu_num){
        SQLiteDatabase db=getReadableDatabase();
        String result="";
        Cursor cursor=db.rawQuery("SELECT * FROM 학생 WHERE 학번 ='"+stu_num+"' ", null);
        while(cursor.moveToNext()){
            result+= "학번:"+cursor.getInt(0)+"학생명:"+cursor.getString(1)+"비밀번호:"+
                    cursor.getInt(2)+"학과"+cursor.getString(3)+"학점"+cursor.getDouble(4)+"\n";
        }
        db.close();
        return result;
    }





}







