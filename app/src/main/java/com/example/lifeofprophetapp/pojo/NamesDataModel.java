package com.example.lifeofprophetapp.pojo;

public class NamesDataModel {

    int id;
    String arabicName;
    String nameEnglish;
    String nameEnglishMeaning;
    String nameUrdu;
    String nameBenefitsEng;
    String nameBenefitUrdu;

    public NamesDataModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public String getNameEnglishMeaning() {
        return nameEnglishMeaning;
    }

    public void setNameEnglishMeaning(String nameEnglishMeaning) {
        this.nameEnglishMeaning = nameEnglishMeaning;
    }

    public String getNameUrdu() {
        return nameUrdu;
    }

    public void setNameUrdu(String nameUrdu) {
        this.nameUrdu = nameUrdu;
    }

    public String getNameBenefitsEng() {
        return nameBenefitsEng;
    }

    public void setNameBenefitsEng(String nameBenefitsEng) {
        this.nameBenefitsEng = nameBenefitsEng;
    }

    public String getNameBenefitUrdu() {
        return nameBenefitUrdu;
    }

    public void setNameBenefitUrdu(String nameBenefitUrdu) {
        this.nameBenefitUrdu = nameBenefitUrdu;
    }

    public NamesDataModel(int id, String arabicName, String nameEnglish, String nameEnglishMeaning, String nameUrdu, String nameBenefitsEng, String nameBenefitUrdu) {
        this.id = id;
        this.arabicName = arabicName;
        this.nameEnglish = nameEnglish;
        this.nameEnglishMeaning = nameEnglishMeaning;
        this.nameUrdu = nameUrdu;
        this.nameBenefitsEng = nameBenefitsEng;
        this.nameBenefitUrdu = nameBenefitUrdu;
    }
}
