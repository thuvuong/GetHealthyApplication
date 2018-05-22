package edu.tacoma.uw.css.thuv.gethealthyapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.tacoma.uw.css.thuv.gethealthyapplication.R;
import edu.tacoma.uw.css.thuv.gethealthyapplication.model.GymCardioWorkout;

public class GymCardioDB {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "id5552560_get_healthy_app";

    private static final String GYM_CARDIO_TABLE = "GymCardio";

    private GymCardioDBHelper mGymCardioDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    /* Constructor for GymCardioDB */
    public GymCardioDB(Context context) {
        mGymCardioDBHelper = new GymCardioDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mGymCardioDBHelper.getWritableDatabase();
    }


    /**
     * Returns the list of courses from the local Course table.
     *
     * @return list
     */
    public List<GymCardioWorkout> getGymCardioWorkouts() {

        String[] columns = {
                 "title"
        };

        Cursor c = mSQLiteDatabase.query(
                GYM_CARDIO_TABLE,  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<GymCardioWorkout> list = new ArrayList<GymCardioWorkout>();
        for (int i=0; i<c.getCount(); i++) {
            //int imageID = Integer.parseInt(c.getString(0));
            String title = c.getString(1);

            GymCardioWorkout workout = new GymCardioWorkout( title);
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
    public boolean insertGymCardioWorkout(String title) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title
        );


        long rowId = mSQLiteDatabase.insert("GymCardio", null, contentValues);
        return rowId != -1;
    }


    /**
     * Delete all the data from the GYM_CARDIO_TABLE
     */
    public void deleteCourses() {
        mSQLiteDatabase.delete(GYM_CARDIO_TABLE, null, null);
    }



    class GymCardioDBHelper extends SQLiteOpenHelper {

        private final String CREATE_GYM_CARDIO_SQL;

        private final String DROP_GYM_CARDIO_SQL;

        public GymCardioDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_GYM_CARDIO_SQL = context.getString(R.string.CREATE_GYM_CARDIO_SQL);
            DROP_GYM_CARDIO_SQL = context.getString(R.string.DROP_GYM_CARDIO_SQL);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_GYM_CARDIO_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_GYM_CARDIO_SQL);
            onCreate(sqLiteDatabase);
        }
    }

}
