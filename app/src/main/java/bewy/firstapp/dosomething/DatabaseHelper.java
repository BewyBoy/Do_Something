package bewy.firstapp.dosomething;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static private final String DB_NAME = "Ideas";
    static private final int DB_VERSION = 1;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlStatement = "";
        sqLiteDatabase.execSQL(sqlStatement);

        ContentValues values = new ContentValues();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
