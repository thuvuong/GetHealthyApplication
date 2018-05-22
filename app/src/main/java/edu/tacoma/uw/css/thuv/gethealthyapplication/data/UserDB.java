package edu.tacoma.uw.css.thuv.gethealthyapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;

public class UserDB {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "User.db";
    private static final String USER_TABLE = "User";

    private UserDBHelper mUserDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public UserDB(Context context) {
        mUserDBHelper = new UserDBHelper(context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mUserDBHelper.getWritableDatabase();
    }

    public boolean insertUser(final String email, final String pwd,
                              final String firstname, final String lastname,
                              final int height, final int weight,
                              final String sex, final int age) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("pwd", pwd);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("height", height);
        contentValues.put("weight", weight);
        contentValues.put("sex", sex);
        contentValues.put("age", age);

        long rowId = mSQLiteDatabase.insert("User", null, contentValues);
        return rowId != -1;
    }

    /**
     * Deletes all the data from the USER_TABLE
     */
    public void deleteUsers() {
        mSQLiteDatabase.delete(USER_TABLE, null, null);
    }



    private class UserDBHelper extends SQLiteOpenHelper {

        private final String CREATE_USER_SQL;

        private final String DROP_USER_SQL;

        public UserDBHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory,
                              int version) {

            super(context, name, factory, version);
            CREATE_USER_SQL = context.getString(R.string.CREATE_USER_SQL);
            DROP_USER_SQL = context.getString(R.string.DROP_USER_SQL);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_USER_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_USER_SQL);
            onCreate(sqLiteDatabase);
        }
    }
}