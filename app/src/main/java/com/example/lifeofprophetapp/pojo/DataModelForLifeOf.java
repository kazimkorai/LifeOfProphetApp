package com.example.lifeofprophetapp.pojo;

public class DataModelForLifeOf {

    String id;
    String chapterName;
    String chapterSubName;
    String  Desc;
    String check;

    public DataModelForLifeOf() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterSubName() {
        return chapterSubName;
    }

    public void setChapterSubName(String chapterSubName) {
        this.chapterSubName = chapterSubName;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public DataModelForLifeOf(String id, String chapterName, String chapterSubName, String desc, String check) {
        this.id = id;
        this.chapterName = chapterName;
        this.chapterSubName = chapterSubName;
        Desc = desc;
        this.check = check;
    }
}
