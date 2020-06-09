package com.example.lifeofprophetapp.pojo;

public class DataModelImageOfChapter {

    String chapterName;
    int image;

    public DataModelImageOfChapter() {

    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public DataModelImageOfChapter(String chapterName, int image) {
        this.chapterName = chapterName;
        this.image = image;
    }
}
