package choiseongyoon.howtojob;


public class ListviewItem extends BaseActivity{
    private String 기업명;
    private String 기업형태;
    private String 산업군;
    private String 본사근무지;
    private String 특이사항;

    public String get기업명() {
        return 기업명;
    }

    public void set기업명(String 기업명) {
        this.기업명 = 기업명;
    }
/*
    public String get기업형태() {
        return 기업형태;
    }

    public void set기업형태(String 기업형태) {
        this.기업형태 = 기업형태;
    }

    public String get산업군() {
        return 산업군;
    }

    public void set산업군(String 산업군) {
        this.산업군 = 산업군;
    }

    public String get본사근무지() {
        return 본사근무지;
    }

    public void set본사근무지(String 본사근무지) {
        this.본사근무지 = 본사근무지;
    }

    public String get특이사항() {
        return 특이사항;
    }

    public void set특이사항(String 특이사항) {
        this.특이사항 = 특이사항;
    }
*/

    public ListviewItem(String 기업명) {
        this.기업명 = 기업명;

    }




}