package Hardkiller.classdatabase;

/**
 * Created by Hardkiller on 02-07-2014.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseConnector
{
    // database name
    private static final String DATABASE_NAME = "UserContacts";

    private SQLiteDatabase database; // for interacting with the database
    private DatabaseOpenHelper databaseOpenHelper; // creates the database

    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    }

    // open the database connection
    public void open() throws SQLException
    {
        // create or open a database for reading/writing
        database = databaseOpenHelper.getWritableDatabase();
    }

    // close the database connection
    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    }

    // inserts a new contact in the database
    public long insertStudentMark(String name, String mark)
    {
        ContentValues newStudentMark = new ContentValues();
        newStudentMark.put("name", name);
        newStudentMark.put("mark", mark);

        open(); // open the database
        long rowID = database.insert("studentMarks", null, newStudentMark);
        close(); // close the database
        return rowID;
    }

    // updates an existing contact in the database
    public void updateStudentMark(long id, String name, String mark)
    {
        ContentValues editStudentMark = new ContentValues();
        editStudentMark.put("name", name);
        editStudentMark.put("phone", mark);

        open(); // open the database
        database.update("studentMark", editStudentMark, "_id=" + id, null);
        close(); // close the database
    } // end method updateContact

    // return a Cursor with all contact names in the database
    public Cursor getAllStudentMarks()
    {
        return database.query("studentMark", new String[] {"_id", "name"},
                null, null, null, null, "name");
    }

    // return a Cursor containing specified contact's information
    public Cursor getOneStudentMark(long id)
    {
        return database.query(
                "studentMark", null, "_id=" + id, null, null, null, null);
    }

    // delete the contact specified by the given String name
    /*public void deleteContact(long id)
    {
        open(); // open the database
        database.delete("studentMark", "_id=" + id, null);
        close(); // close the database
    }*/

    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        // constructor
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named contacts
            String createQuery = "CREATE TABLE studentMark" +
                    "(_id integer primary key autoincrement," +
                    "name TEXT, mark TEXT);";

            db.execSQL(createQuery); // execute query to create the database
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }
    } // end class DatabaseOpenHelper
} // end class DatabaseConnector
