package com.movemint;

/**
 * Created by InSaYnE on 4/9/2015.
 */
public class Province {
    int id;
    String provinceName;
    double total_expenditure;
    double food;
    double shelter;
    double clothing;
    double transportation;
    double health_care;
    double recreation;
    double education;
    double tobacco_alcohol;
    Double tax_rate_1;
    Double tax_rate_2;
    Double tax_rate_3;
    Double tax_rate_4;
    Double tax_rate_5;
    Double tax_rate_6;
    Double threshold1;
    Double threshold2;
    Double threshold3;
    Double threshold4;

    public Double getThreshold5() {
        return threshold5;
    }

    public void setThreshold5(Double threshold5) {
        this.threshold5 = threshold5;
    }

    public Double getTax_rate_1() {
        return tax_rate_1;
    }

    public void setTax_rate_1(Double tax_rate_1) {
        this.tax_rate_1 = tax_rate_1;
    }

    public Double getTax_rate_2() {
        return tax_rate_2;
    }

    public void setTax_rate_2(Double tax_rate_2) {
        this.tax_rate_2 = tax_rate_2;
    }

    public Double getTax_rate_3() {
        return tax_rate_3;
    }

    public void setTax_rate_3(Double tax_rate_3) {
        this.tax_rate_3 = tax_rate_3;
    }

    public Double getTax_rate_4() {
        return tax_rate_4;
    }

    public void setTax_rate_4(Double tax_rate_4) {
        this.tax_rate_4 = tax_rate_4;
    }

    public Double getTax_rate_5() {
        return tax_rate_5;
    }

    public void setTax_rate_5(Double tax_rate_5) {
        this.tax_rate_5 = tax_rate_5;
    }

    public Double getTax_rate_6() {
        return tax_rate_6;
    }

    public void setTax_rate_6(Double tax_rate_6) {
        this.tax_rate_6 = tax_rate_6;
    }

    public Double getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(Double threshold1) {
        this.threshold1 = threshold1;
    }

    public Double getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(Double threshold2) {
        this.threshold2 = threshold2;
    }

    public Double getThreshold3() {
        return threshold3;
    }

    public void setThreshold3(Double threshold3) {
        this.threshold3 = threshold3;
    }

    public Double getThreshold4() {
        return threshold4;
    }

    public void setThreshold4(Double threshold4) {
        this.threshold4 = threshold4;
    }

    Double threshold5;

    public double getTobacco_alcohol() {
        return tobacco_alcohol;
    }

    public void setTobacco_alcohol(double tobacco_alcohol) {
        this.tobacco_alcohol = tobacco_alcohol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public double getTotal_expenditure() {
        return total_expenditure;
    }

    public void setTotal_expenditure(double total_expenditure) {
        this.total_expenditure = total_expenditure;
    }

    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    public double getShelter() {
        return shelter;
    }

    public void setShelter(double shelter) {
        this.shelter = shelter;
    }

    public double getClothing() {
        return clothing;
    }

    public void setClothing(double clothing) {
        this.clothing = clothing;
    }

    public double getTransportation() {
        return transportation;
    }

    public void setTransportation(double transportation) {
        this.transportation = transportation;
    }

    public double getHealth_care() {
        return health_care;
    }

    public void setHealth_care(double health_care) {
        this.health_care = health_care;
    }

    public double getRecreation() {
        return recreation;
    }

    public void setRecreation(double recreation) {
        this.recreation = recreation;
    }

    public double getEducation() {
        return education;
    }

    public void setEducation(double education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", provinceName='" + provinceName + '\'' +
                ", total_expenditure=" + total_expenditure +
                ", food=" + food +
                ", shelter=" + shelter +
                ", clothing=" + clothing +
                ", transportation=" + transportation +
                ", health_care=" + health_care +
                ", recreation=" + recreation +
                ", education=" + education +
                ", tobacco_alcohol=" + tobacco_alcohol +
                ", tax_rate_1=" + tax_rate_1 +
                ", tax_rate_2=" + tax_rate_2 +
                ", tax_rate_3=" + tax_rate_3 +
                ", tax_rate_4=" + tax_rate_4 +
                ", tax_rate_5=" + tax_rate_5 +
                ", tax_rate_6=" + tax_rate_6 +
                ", threshold1=" + threshold1 +
                ", threshold2=" + threshold2 +
                ", threshold3=" + threshold3 +
                ", threshold4=" + threshold4 +
                ", threshold5=" + threshold5 +
                '}';
    }
}
