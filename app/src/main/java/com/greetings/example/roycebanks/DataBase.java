package com.greetings.example.roycebanks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private static  final String db_name = "royce_banks";


    public DataBase(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, email VARCHAR, account VARCHAR, password VARCHAR, confirm VARCHAR, phone VARCHAR, balance VARCHAR);";
        db.execSQL(sql);

    }

    public boolean addUser(String name, String email, String account, String password, String confirm, String phone, String balance)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("account",account);
        contentValues.put("password",password);
        contentValues.put("confirm",confirm);
        contentValues.put("phone",phone);
        contentValues.put("balance",balance);

        sdb.insert("users",null,contentValues);
        sdb.close();
        return true;
    }

    public boolean login_check(String username, String password)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where account = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor get_data()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("Select * from users",null);
        return  cur;
    }

    public boolean update_withdraw(String rem_balance, String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("balance",rem_balance);
        db.update("users",cv,"id = ? ",new String[]{id});
        return true;

    }

    public boolean update_deposit(String rem_balance, String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("balance",rem_balance);
        db.update("users",cv,"id = ? ",new String[]{id});
        return true;

    }

    public boolean update_password(String pass, String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",pass);
        db.update("users",cv,"id = ? ",new String[]{id});
        return true;

    }

    public boolean transfer_remove(String rem_balance, String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("balance",rem_balance);
        db.update("users",cv,"id = ? ",new String[]{id});
        return true;

    }

    public void transfer_add(String rem_balance, String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("balance",rem_balance);
        db.update("users",cv,"id = ? ",new String[]{id});

    }

    public Boolean changePassword(String passwordxz, String confirmxz, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password",passwordxz);
        cv.put("confirm",confirmxz);
        db.update("users",cv,"id = ? ",new String[]{id});
        return true;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_d = "DROP TABLE IF EXISTS users";
        db.execSQL(sql_d);

        onCreate(db);

    }

}
