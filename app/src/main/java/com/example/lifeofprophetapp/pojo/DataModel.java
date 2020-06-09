package com.example.lifeofprophetapp.pojo;

public class DataModel {
    int id;
    String chapterName;
    String hadithArabic;
    String hadithEnglish;
    String hadithUrdu;
    String hadithNo;
    String bookmark;

    public DataModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getHadithArabic() {
        return hadithArabic;
    }

    public void setHadithArabic(String hadithArabic) {
        this.hadithArabic = hadithArabic;
    }

    public String getHadithEnglish() {
        return hadithEnglish;
    }

    public void setHadithEnglish(String hadithEnglish) {
        this.hadithEnglish = hadithEnglish;
    }

    public String getHadithUrdu() {
        return hadithUrdu;
    }

    public void setHadithUrdu(String hadithUrdu) {
        this.hadithUrdu = hadithUrdu;
    }

    public String getHadithNo() {
        return hadithNo;
    }

    public void setHadithNo(String hadithNo) {
        this.hadithNo = hadithNo;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public DataModel(int id, String chapterName, String hadithArabic, String hadithEnglish, String hadithUrdu, String hadithNo, String bookmark) {
        this.id = id;
        this.chapterName = chapterName;
        this.hadithArabic = hadithArabic;
        this.hadithEnglish = hadithEnglish;
        this.hadithUrdu = hadithUrdu;
        this.hadithNo = hadithNo;
        this.bookmark = bookmark;
    }
}
