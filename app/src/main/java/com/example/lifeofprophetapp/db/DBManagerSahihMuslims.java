package com.example.lifeofprophetapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.example.lifeofprophetapp.pojo.DataModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DBManagerSahihMuslims extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    //public static DataBaseHelper instance = null;
    public static String DATABASE_PATH;

    public static String packageName;
    public static String DATABSE_NAME = "Sahih_Muslim_Database";


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DBManagerSahihMuslims(Context context) {

        super(context, DATABSE_NAME, null, 1);
        this.myContext = context;
        this.packageName = myContext.getPackageName();
        DATABASE_PATH = Environment.getDataDirectory() + "/data/" + packageName + "/databases/";


    }

    public void removeBookMark(String chapterName, int id) {
        try {

            ContentValues   cv=new ContentValues();
            cv.put("Bookmarks","0");
            //  db.update(tableName,cv,"Id"+"="+id,null);
            myDataBase.update("Sahih_Hadith",cv,"Chapter_Names='"+chapterName+"' and id='"+id+"'",null);


        } catch (Exception e) {
            // Toast.makeText(_context,"Exception ocurred in  removing  Bookmarks",Toast.LENGTH_LONG).show();

        }
    }



//	public static DataBaseHelper getInstance(Context context) {
//	      if(instance == null) {
//	         instance = new DataBaseHelper(context);
//	      }
//	      return instance;
//	   }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
            Log.d("Juzz Database exists", "Database Exists");
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            this.close();

            try {

                copyDataBase();

            } catch (IOException e) {
                Log.d("ex", "" + e.toString());
                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DATABASE_PATH + DATABSE_NAME;
            File file = new File(myPath);
            if (file.exists() && !file.isDirectory())
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABSE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABSE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DATABASE_PATH + DATABSE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DataModel getByChapterNameSahiMuslim(String idSearch,String chapterNameSearch) {

        ArrayList<DataModel> arrayListTime = new ArrayList<>();
        String queryText = "SELECT * from Sahih_Hadith  WHERE chapter_names='" + chapterNameSearch + "'" +"and id"+'='+idSearch;

        Cursor cursor = myDataBase.rawQuery(queryText, null);
        DataModel datamodelObject = new DataModel();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                int id = cursor.getInt(0);
                String chapterName  = cursor.getString(1);
                String hadithArabic = cursor.getString(2);
                String hadithEnglish = cursor.getString(3);
                String hadithUrdu = cursor.getString(4);
                String hadithNo=cursor.getString(5);
                String bookmark=cursor.getString(6);

                datamodelObject.setId(id);
                datamodelObject.setChapterName(chapterName+"");
                datamodelObject.setHadithArabic(hadithArabic);
                datamodelObject.setHadithEnglish(hadithEnglish);
                datamodelObject.setHadithUrdu(hadithUrdu);
                datamodelObject.setBookmark(bookmark);

                datamodelObject.setHadithNo(hadithNo);
                arrayListTime.add(datamodelObject);
                cursor.moveToNext();
            }
        }


        return datamodelObject;
    }

    public ArrayList<DataModel> getQueryById(String searchletter) {
        ArrayList<DataModel> arrayListTime = new ArrayList<>();

        String queryText = "SELECT * FROM Sahih_Hadith WHERE id='" + searchletter + "'";

        Cursor cursor = myDataBase.rawQuery(queryText, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                DataModel datamodelObject = new DataModel();


                int id = cursor.getInt(0);
                String chapterName  = cursor.getString(1);
                String hadithArabic = cursor.getString(2);
                String hadithEnglish = cursor.getString(3);
                String hadithUrdu = cursor.getString(4);
                String hadithNo=cursor.getString(5);
                String bookmark=cursor.getString(6);

                datamodelObject.setId(id);
                datamodelObject.setChapterName(chapterName+"");
                datamodelObject.setHadithArabic(hadithArabic);
                datamodelObject.setHadithEnglish(hadithEnglish);
                datamodelObject.setHadithUrdu(hadithUrdu);
                datamodelObject.setBookmark(bookmark);

                datamodelObject.setHadithNo(hadithNo);
                arrayListTime.add(datamodelObject);
                cursor.moveToNext();
            }
        }


        return arrayListTime;
    }
    public ArrayList<DataModel> getQueryByChapterName(String searchletter) {
        ArrayList<DataModel> arrayListTime = new ArrayList<>();

        String queryText = "SELECT * from Sahih_Hadith where chapter_names='" + searchletter + "'";

        Cursor cursor = myDataBase.rawQuery(queryText, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                DataModel datamodelObject = new DataModel();


                int id = cursor.getInt(0);
                String chapterName  = cursor.getString(1);
                String hadithArabic = cursor.getString(2);
                String hadithEnglish = cursor.getString(3);
                String hadithUrdu = cursor.getString(4);
                String hadithNo=cursor.getString(5);
                String bookmark=cursor.getString(6);

                datamodelObject.setId(id);
                datamodelObject.setChapterName(chapterName+"");
                datamodelObject.setHadithArabic(hadithArabic);
                datamodelObject.setHadithEnglish(hadithEnglish);
                datamodelObject.setHadithUrdu(hadithUrdu);
                datamodelObject.setBookmark(bookmark);

                datamodelObject.setHadithNo(hadithNo);
                arrayListTime.add(datamodelObject);
                cursor.moveToNext();
            }
        }


        return arrayListTime;
    }




    public ArrayList<DataModel> getQueryAll() {
        ArrayList<DataModel> arrayListTime = new ArrayList<>();


        String queryText = "SELECT * FROM Sahih_Hadith GROUP BY chapter_names";

        Cursor cursor = myDataBase.rawQuery(queryText, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                DataModel datamodelObject = new DataModel();

                int id = cursor.getInt(0);
                String chapterName  = cursor.getString(1);
                String hadithArabic = cursor.getString(2);
                String hadithEnglish = cursor.getString(3);
                String hadithUrdu = cursor.getString(4);
                String hadithNo=cursor.getString(5);
                String bookmark=cursor.getString(6);
                datamodelObject.setId(id);
                datamodelObject.setChapterName(chapterName+"");
                datamodelObject.setHadithArabic(hadithArabic);
                datamodelObject.setHadithEnglish(hadithEnglish);
                datamodelObject.setHadithUrdu(hadithUrdu);
                datamodelObject.setBookmark(bookmark);
                datamodelObject.setHadithNo(hadithNo);
                arrayListTime.add(datamodelObject);
                cursor.moveToNext();

            }
        }


        return arrayListTime;
    }


    public void setBookMarks(String chapterName, int id) {
        try {

            //   String  bookMarkQuery = "update Sahih_Hadith set Bookmarks=1 where id =" + id;
            ContentValues cv=new ContentValues();
            cv.put("Bookmarks","1");
            myDataBase.update("Sahih_Hadith",cv,"Chapter_Names='"+chapterName+"' and id='"+id+"'",null);


        } catch (Exception e) {
            Log.d("**exception",e.getMessage()+"");
            // Toast.makeText(_context,"Exception ocurred in  setting Bookmarks",Toast.LENGTH_LONG).show();
        } finally {

        }

    }

    public ArrayList<DataModel> getBookmarkQueryAll() {
        ArrayList<DataModel> arrayListTime = new ArrayList<>();

        String queryText = "SELECT * FROM Sahih_Hadith where Bookmarks=1";

        Cursor cursor = myDataBase.rawQuery(queryText, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                DataModel datamodelObject = new DataModel();

                int id = cursor.getInt(0);
                String chapterName  = cursor.getString(1);
                String hadithArabic = cursor.getString(2);
                String hadithEnglish = cursor.getString(3);
                String hadithUrdu = cursor.getString(4);
                String hadithNo=cursor.getString(5);
                String bookmark=cursor.getString(6);
                datamodelObject.setId(id);
                datamodelObject.setChapterName(chapterName+"");
                datamodelObject.setHadithArabic(hadithArabic);
                datamodelObject.setHadithEnglish(hadithEnglish);
                datamodelObject.setHadithUrdu(hadithUrdu);
                datamodelObject.setBookmark(bookmark);
                datamodelObject.setHadithNo(hadithNo);
                arrayListTime.add(datamodelObject);
                cursor.moveToNext();

            }
        }


        return arrayListTime;
    }

}
