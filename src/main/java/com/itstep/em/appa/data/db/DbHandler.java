package com.itstep.em.appa.data.db;

import android.content.ContentValues;
import android.content.Context;

import com.itstep.em.appa.data.model.Member;


@SuppressWarnings("WeakerAccess")
public class DbHandler {

    private DbHelper dbHelper;
    Context context;

    public DbHandler(Context mContext){
        this.context = mContext;
        dbHelper = new DbHelper(context);
    }

    /**
     * Функция по сахранению пользователя в БД
     * @param user
     * @return true or false
     */
    public boolean saveUserData(Member user){
        ContentValues values = new ContentValues();
        values.put(DbTable.COL_USER_FNAME, user.getUser_fname());
        values.put(DbTable.COL_USER_LNAME, user.getUser_lname());
        values.put(DbTable.COL_USER_PHONE, user.getUser_phone());
        values.put(DbTable.COL_USER_EMAIL, user.getUser_email());
        values.put(DbTable.COL_USER_PASSWORD, user.getUser_password());
        return dbHelper.insertData(DbTable.TABLE_USER, values);
    }

    public int updateUserData(Member user, String email){
        ContentValues values = new ContentValues();
        values.put(DbTable.COL_USER_FNAME, user.getUser_fname());
        values.put(DbTable.COL_USER_LNAME, user.getUser_lname());
        values.put(DbTable.COL_USER_PHONE, user.getUser_phone());
        values.put(DbTable.COL_USER_EMAIL, user.getUser_email());
        values.put(DbTable.COL_USER_PASSWORD, user.getUser_password());
        return dbHelper.updateData(DbTable.TABLE_USER, values, email);
    }

    public boolean checkUserCredentials(String email, String password){
        return dbHelper.checkUser(email, password);
    }

    public Member getUserDetails(String email){
        return dbHelper.getUserDetail(email);
    }

    public boolean deleteAccount(String email){
        return dbHelper.deleteUser(email);
    }

}
