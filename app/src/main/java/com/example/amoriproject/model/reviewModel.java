package com.example.amoriproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class reviewModel implements Parcelable {

    //define variables
    int row_idReview;
    String row_namaProduk;
    String row_category;
    String row_isiReview;
    String row_tanggal;

    //get constructor for review model class
    public reviewModel(int row_idReview, String row_namaProduk, String row_category, String row_isiReview, String row_tanggal) {
        this.row_idReview = row_idReview;
        this.row_namaProduk = row_namaProduk;
        this.row_category = row_category;
        this.row_isiReview = row_isiReview;
        this.row_tanggal = row_tanggal;
    }

    protected reviewModel(Parcel in) {
        row_idReview = in.readInt();
        row_namaProduk = in.readString();
        row_category = in.readString();
        row_isiReview = in.readString();
        row_tanggal = in.readString();
    }

    //setter and getter for review model class
    public static final Creator<reviewModel> CREATOR = new Creator<reviewModel>() {
        @Override
        public reviewModel createFromParcel(Parcel in) {
            return new reviewModel(in);
        }

        @Override
        public reviewModel[] newArray(int size) {
            return new reviewModel[size];
        }
    };

    public int getRow_idReview() {
        return row_idReview;
    }

    public void setRow_idReview(int row_idReview) {
        this.row_idReview = row_idReview;
    }

    public String getRow_namaProduk() {
        return row_namaProduk;
    }

    public void setRow_namaProduk(String row_namaProduk) {
        this.row_namaProduk = row_namaProduk;
    }

    public String getRow_category() {
        return row_category;
    }

    public void setRow_category(String row_category) {
        this.row_category = row_category;
    }

    public String getRow_isiReview() {
        return row_isiReview;
    }

    public void setRow_isiReview(String row_isiReview) {
        this.row_isiReview = row_isiReview;
    }

    public String getRow_tanggal() {
        return row_tanggal;
    }

    public void setRow_tanggal(String row_tanggal) {
        this.row_tanggal = row_tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(row_idReview);
        parcel.writeString(row_namaProduk);
        parcel.writeString(row_category);
        parcel.writeString(row_isiReview);
        parcel.writeString(row_tanggal);
    }
}
