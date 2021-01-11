package com.example.amoriproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class userModel implements Parcelable {

    int row_idUser;
    String row_fullname;
    String row_email;
    String row_username;
    String row_password;

    public userModel(int row_idUser, String row_fullname, String row_email, String row_username, String row_password) {
        this.row_idUser = row_idUser;
        this.row_fullname = row_fullname;
        this.row_email = row_email;
        this.row_username = row_username;
        this.row_password = row_password;
    }

    protected userModel(Parcel in) {
        row_idUser = in.readInt();
        row_fullname = in.readString();
        row_email = in.readString();
        row_username = in.readString();
        row_password = in.readString();
    }

    public static final Creator<userModel> CREATOR = new Creator<userModel>() {
        @Override
        public userModel createFromParcel(Parcel in) {
            return new userModel(in);
        }

        @Override
        public userModel[] newArray(int size) {
            return new userModel[size];
        }
    };

    public int getRow_idUser() {
        return row_idUser;
    }

    public void setRow_idUser(int row_idUser) {
        this.row_idUser = row_idUser;
    }

    public String getRow_fullname() {
        return row_fullname;
    }

    public void setRow_fullname(String row_fullname) {
        this.row_fullname = row_fullname;
    }

    public String getRow_email() {
        return row_email;
    }

    public void setRow_email(String row_email) {
        this.row_email = row_email;
    }

    public String getRow_username() {
        return row_username;
    }

    public void setRow_username(String row_username) {
        this.row_username = row_username;
    }

    public String getRow_password() {
        return row_password;
    }

    public void setRow_password(String row_password) {
        this.row_password = row_password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(row_idUser);
        parcel.writeString(row_fullname);
        parcel.writeString(row_email);
        parcel.writeString(row_username);
        parcel.writeString(row_password);
    }
}
