package com.itstep.em.appa.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.itstep.em.appa.data.model.Member;


@SuppressWarnings("WeakerAccess")
public class DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "db_member";
    private static final int DATABASE_VERSION = 1;


    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbTable.DB_MEMBER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbTable.DB_MEMBER);
        onCreate(db);
    }

    public boolean insertData(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, values);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }


//  обновление данных пользователя
    public int updateData(String table, ContentValues values,
                          String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(table, values,
                DbTable.COL_USER_EMAIL + " =? ",
                new String[]{email});
    }

//  получение данных пользователя
    public Member getUserDetail(String email){
        Member user;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                DbTable.COL_USER_ID,
                DbTable.COL_USER_FNAME,
                DbTable.COL_USER_LNAME,
                DbTable.COL_USER_PHONE,
                DbTable.COL_USER_EMAIL,
                DbTable.COL_USER_PASSWORD
        };

        String selection = DbTable.COL_USER_EMAIL + " =? " ;
        String[] args={email};

        Cursor cursor = db.query(DbTable.TABLE_USER, columns,
                selection, args, null, null, null);

        if (cursor != null && cursor.moveToFirst()){

            user = new Member();
            user.setUser_id(cursor.getInt(0));
            user.setUser_fname(cursor.getString(1));
            user.setUser_lname(cursor.getString(2));
            user.setUser_phone(cursor.getString(3));
            user.setUser_email(cursor.getString(4));
            user.setUser_password(cursor.getString(5));

            cursor.close();
            return user;
        }

        return null;


    }


    // проверка существования пользователя
    public boolean  checkUser(String email, String pass){

        // получение идентификатора пользователя в таблице
        String[] columns = {
                DbTable.COL_USER_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        // валидация
        String selection = DbTable.COL_USER_EMAIL + " =? " + " AND " +
                DbTable.COL_USER_PASSWORD + " =? ";

        // значения условия
        String[] args = {email, pass};

        // запрос по пользователю в таблицу
        Cursor cursor = db.query(DbTable.TABLE_USER,
                columns, //возврат колонок
                selection, //where условие
                args, // значения условия
                null,
                null,
                null);

        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0){
            return true;
        }
        return false;
    }

    // удаление пользователя
    public boolean deleteUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(DbTable.TABLE_USER, DbTable.COL_USER_EMAIL + " =? ",
                new String[]{email}) > 0;
    }

}
