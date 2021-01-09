package com.example.amoriproject.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public  static final String database_name = "db_login";
    public  static final String table_user = "table_user";
    public static final String table_review = "table_review";

    public  static final String row_idUser = "_idUser";
    public  static final String row_fullname = "Fullname";
    public  static final String row_email = "Email";
    public  static final String row_username = "Username";
    public  static final String row_password = "Password";

    public static final String row_idReview = "_idReview";
    public static final String row_namaProduk = "Nama_Produk";
    public static final String row_category = "Kategory_Produk";
    public static final String row_isiReview = "Isi_Review";
    public static final String row_tanggal = "Tanggal_Review";

    private SQLiteDatabase db;

    public DBHelper (Context context) {
        super(context,  database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        String queryUser = "CREATE TABLE " + table_user
                + "(" + row_idUser + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + row_fullname + " TEXT, "
                + row_email + " TEXT, "
                + row_username + " TEXT, "
                + row_password + " TEXT )";
        db.execSQL(queryUser);

        String queryReview  = "CREATE TABLE " + table_review +
                "(" + row_idReview + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + row_namaProduk + " TEXT, "
                + row_category + " TEXT, "
                + row_isiReview + " TEXT, "
                + row_username + " TEXT, "
                + row_tanggal + " DATE, FOREIGN KEY("+ row_username+") REFERENCES "+ table_user +"("+ row_username+") " +
                "ON DELETE CASCADE)";
        db.execSQL(queryReview);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("DROP TABLE IF EXISTS " + table_user);
        db.execSQL("DROP TABLE IF EXISTS " + table_review);
        onCreate(db);
    }

    // Insert User
    public void  insertUser (ContentValues values) {
        db.insert(table_user, null, values);
    }

    // Insert Review
    public void  insertReview (ContentValues values) {
        db.insert(table_review, null, values);
    }

    public boolean checkUser(String username, String password) {
        Cursor cursor = db.rawQuery("SELECT " + row_idUser + " FROM " +
                table_user + " WHERE " + row_username + "=? and " + row_password + "=?", new String[]
                {username, password});

//        String[] columns = {row_idUser};
//        SQLiteDatabase db = getReadableDatabase();
//        String selection = row_username + "=?" + " and " + row_password + "=?";
//        String[] selectionArgs = {username, password};
//        Cursor cursor = db.query(table_user, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return  true;
        else
            return false;
    }

    public Cursor fetchAllProfileData(String username) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + table_user + " WHERE " + row_username +
                " =? ", new String[]
                {username});
        return res;
    }

    public void updateProfileData(String Fullname, String Email, String Username, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + table_user + " SET " + row_fullname + " = (?), " + row_email +
                " = (?), " + "" + row_username + " = (?), " + row_password + "=(?) WHERE "
                + row_idUser + " = (?)",new String[]{Fullname, Email, Username, Password});
    }

    public void deleteProfileData(String idUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " +table_user+" WHERE "+row_idUser+"=(?)",new String[]{idUser});
    }

    public Cursor fetchAllReview() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + table_review, null);
        return res;
    }

    public boolean updateReview(String idReview ,String NamaProduk, String ProdukKategori, String isiReview, String username, String tanggal ) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + table_review + " SET " + row_namaProduk + " = (?), " + row_isiReview +" = (?), " +row_username + " " +
                "= (?), " + row_tanggal + "=(?) WHERE " + row_idReview + " = (?)",new String[]{NamaProduk, ProdukKategori, isiReview, username, tanggal, idReview});

        return true;
    }

    public boolean deleteReview(String idReview) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " +table_review+" WHERE "+row_idReview+"=(?)",new String[]{idReview});

        return true;
    }

    public int getIdRev(String productName, String productCategory, String reviewDet, String username, String tanggal){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + row_idReview +
                " FROM " + table_review + " WHERE " + row_namaProduk +
                "=(?) and " + row_category +
                "=(?) and "+ row_isiReview +
                "=(?) and "+ row_tanggal +
                "=(?) and "+ row_username +
                "=(?)", new String[]{productName, productCategory, reviewDet, username, tanggal});

        int idRev = res.getInt(0);
        return idRev;
    }

    public Cursor fetchMyReview(String username) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + table_review + " WHERE " + row_username +
                " = ?", new String[]
                {username});
        return res;
    }

    public Cursor getReview(String Kategory_Produk) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + table_review + " WHERE " + row_category +
                " = ? ", new String[]
                {Kategory_Produk});
        return res;
    }

}
