package edu.tacoma.uw.css.thuv.gethealthyapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.HomeCardioWorkout;

public class HomeCardioDB {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "id5552560_get_healthy_app";

    private static final String Home_CARDIO_TABLE = "HomeCardio";

    private HomeCardioDBHelper mHomeCardioDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    /* Constructor for HomeCardioDB */
    public HomeCardioDB(Context context) {
        mHomeCardioDBHelper = new HomeCardioDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mHomeCardioDBHelper.getWritableDatabase();
    }


    /**
     * Returns the list of courses from the local Course table.
     * @return list
     */
    public List<HomeCardioWorkout> getHomeCardioWorkouts() {

        String[] columns = {
                "title"
        };

        Cursor c = mSQLiteDatabase.query(
                Home_CARDIO_TABLE,  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<HomeCardioWorkout> list = new ArrayList<HomeCardioWorkout>();
        for (int i=0; i<c.getCount(); i++) {
            //int imageID = Integer.parseInt(c.getString(0));
            String title = c.getString(1);

            HomeCardioWorkout workout = new HomeCardioWorkout(title);
            list.add(workout);
            c.moveToNext();
        }

        return list;
    }



    /**
     * Inserts the course into the local sqlite table. Returns true if successful, false otherwise.
     * @param title
     * @return true or false
     */
    public boolean insertHomeCardioWorkout(String title) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);


        long rowId = mSQLiteDatabase.insert("HomeCardio", null, contentValues);
        return rowId != -1;
    }


    /**
     * Delete all the data from the Home_CARDIO_TABLE
     */
    public void deleteCourses() {
        mSQLiteDatabase.delete(Home_CARDIO_TABLE, null, null);
    }



    class HomeCardioDBHelper extends SQLiteOpenHelper {

        private final String CREATE_Home_CARDIO_SQL;

        private final String DROP_Home_CARDIO_SQL;

        public HomeCardioDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_Home_CARDIO_SQL = context.getString(R.string.CREATE_HOME_CARDIO_SQL);
            DROP_Home_CARDIO_SQL = context.getString(R.string.DROP_HOME_CARDIO_SQL);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_Home_CARDIO_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_Home_CARDIO_SQL);
            onCreate(sqLiteDatabase);
        }
    }

}
