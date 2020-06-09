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
import com.example.lifeofprophetapp.pojo.NamesDataModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DBManager99Names extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    //public static DataBaseHelper instance = null;
    public static String DATABASE_PATH;
    int bookMarkValue=0;
    public static String packageName;
    public static String DATABSE_NAME = "99_names.db";


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DBManager99Names(Context context) {

        super(context, DATABSE_NAME, null, 1);
        this.myContext = context;
        this.packageName = myContext.getPackageName();
        DATABASE_PATH = Environment.getDataDirectory() + "/data/" + packageName + "/databases/";


    }
    public Cursor getCursor(String queryText)
    {

      Cursor  cursor = myDataBase.rawQuery(queryText, null);
        return cursor;

    }




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







    public NamesDataModel getQueryById(String id) {
        NamesDataModel nameModel=new NamesDataModel();
        String queryText = "SELECT * from muhammad_names where id='" + id + "'";

        Cursor cursor = myDataBase.rawQuery(queryText, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
               nameModel = new NamesDataModel();

                int ida = cursor.getInt(0);
                String nameArabic  = cursor.getString(1);
                String nameEnglish = cursor.getString(2);
                String nameMeaning = cursor.getString(3);
                String nameUrdu = cursor.getString(4);
                String nameBenefitsEng=cursor.getString(5);
                String nameBenefitsUrdu=cursor.getString(6);

                nameModel.setId(ida);
                nameModel.setArabicName(nameArabic+"");
                nameModel.setNameEnglish(nameEnglish);
                nameModel.setNameEnglishMeaning(nameMeaning);
                nameModel.setNameUrdu(nameUrdu);
                nameModel.setNameBenefitsEng(nameBenefitsEng);
                nameModel.setNameBenefitUrdu(nameBenefitsUrdu);

                cursor.moveToNext();
            }
        }


        return nameModel;
    }


    public ArrayList<NamesDataModel> getQueryAll() {
        ArrayList<NamesDataModel> arrayListTime = new ArrayList<>();


        String queryText = "SELECT * FROM muhammad_names";

        Cursor cursor = myDataBase.rawQuery(queryText, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                NamesDataModel nameModel = new NamesDataModel();

                int id = cursor.getInt(0);
                String nameArabic  = cursor.getString(1);
                String nameEnglish = cursor.getString(2);
                String nameMeaning = cursor.getString(3);
                String nameUrdu = cursor.getString(4);
                String nameBenefitsEng=cursor.getString(5);
                String nameBenefitsUrdu=cursor.getString(6);

                nameModel.setId(id);
                nameModel.setArabicName(nameArabic+"");
                nameModel.setNameEnglish(nameEnglish);
                nameModel.setNameEnglishMeaning(nameMeaning);
                nameModel.setNameUrdu(nameUrdu);
                nameModel.setNameBenefitsEng(nameBenefitsEng);
                nameModel.setNameBenefitUrdu(nameBenefitsUrdu);
                arrayListTime.add(nameModel);
                cursor.moveToNext();

            }
        }


        return arrayListTime;
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
