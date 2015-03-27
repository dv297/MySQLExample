package edu.sc.cse.rdc.mysqlexample;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;


/**
 * Created by Daniel Vu on 3/17/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{
    // Database
    public static final String DATABASE_NAME = "items.db";

    // Tables
    public static final String ITEMS_TABLE = "items";

    // Columns
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_MESSAGE = "message";

    private static final String CREATE_STATEMENT = "CREATE TABLE " + ITEMS_TABLE +
            " (" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_MESSAGE + " text not null);";



    // Meta data
    private static final int VERSION_NUMBER = 1;

    public MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);
    }

}
