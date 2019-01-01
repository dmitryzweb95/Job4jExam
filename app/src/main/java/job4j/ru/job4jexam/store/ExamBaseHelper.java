package job4j.ru.job4jexam.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 01/01/2019
 */
public class ExamBaseHelper extends SQLiteOpenHelper {
    public static final String DB = "exams.db";
    public static final int VERSION = 1;

    public ExamBaseHelper(Context context) {
        super(context, DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + ExamDbSchema.ExamTable.NAME + " (" +
                        "id integer primary key autoincrement, " +
                        ExamDbSchema.ExamTable.Cols.TITLE + " " +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
