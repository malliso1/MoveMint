package com.movemint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by InSaYnE on 2/25/2015.
 */
public class ExpenditureDBHandler extends SQLiteOpenHelper {

    Context context;

    private static final int dbVersion = 1;
    private static final String dbName = "expenditureDB";
    private static final String expenditureTable = "expenditure";
    private static final String taxesTable = "taxes";

    //Expenditure Fields
    private static final String expenditureID = "exp_id";
    private static final String region = "region";
    private static final String total_expenditure = "total_expenditure";
    private static final String food = "food";
    private static final String shelter = "shelter";
    private static final String clothing = "clothing";
    private static final String transportation = "transportation";
    private static final String health_care = "health_care";
    private static final String recreation = "recreation";
    private static final String education  = "education";
    private static final String tobacco_alcohol = "tobacco_alcohol";

    //Taxes Fields
    private static final String taxID = "tax_id";
    private static final String rate1 = "rate1";
    private static final String rate2 = "rate2";
    private static final String rate3 = "rate3";
    private static final String rate4 = "rate4";
    private static final String rate5 = "rate5";
    private static final String rate6 = "rate6";
    private static final String threshold1 = "threshold1";
    private static final String threshold2 = "threshold2";
    private static final String threshold3 = "threshold3";
    private static final String threshold4 = "threshold4";
    private static final String threshold5 = "threshold5";

    public ExpenditureDBHandler(Context context, String dbName) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create two tables. One for expenses, one for taxes.
        String createExpenditureTable = "CREATE TABLE " + expenditureTable + "("
                + expenditureID + " INTEGER PRIMARY KEY," + region + " TEXT," + total_expenditure + " DOUBLE,"
                + food + " DOUBLE," + shelter + " DOUBLE," + clothing + " DOUBLE, " + transportation + " DOUBLE, "
                + health_care + " DOUBLE," + recreation + " DOUBLE," + education + " DOUBLE,"
                + tobacco_alcohol + "DOUBLE" + ")";

        String createTaxTable = "CREATE TABLE " + taxesTable + "("
                + taxID + "INTEGER PRIMARY KEY," + region + " TEXT, "
                + rate1 + " DOUBLE, " + rate2 + " DOUBLE," + rate3 + " DOUBLE,"
                + rate4 + " DOUBLE, " + rate5 + " DOUBLE," + rate6 + " DOUBLE,"
                + threshold1 + " DOUBLE, " + threshold2 + " DOUBLE," + threshold3 + " DOUBLE,"
                + threshold4 + " DOUBLE, " + threshold5 + " DOUBLE)";
        db.execSQL(createExpenditureTable);
        db.execSQL(createTaxTable);
        //Add items to expenditure table...
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(context.getAssets().open("HouseholdTotalExpenditure.csv")));
            CSVReader taxReader = new CSVReader(new InputStreamReader(context.getAssets().open("TaxationBrackets.csv")));
            String curLine[];
            int count = 0;
            String prevRegion = "";
            String curRegion = "";
            String cur_region = "", cur_total_expenditure = "", cur_food = "", cur_shelter = "", cur_clothing = "", cur_transportation = "", cur_health_care = "", cur_recreation = "", cur_education = "", cur_tobacco_alcohol = "";
            //For each item in the expenditure CSV file...
            while ((curLine = reader.readNext()) != null) {
                curRegion = curLine[0];
                //We are adding a bunch of lines to a single province before committing. If the current province is different from the last, we should commit the previous one.
                if (!(prevRegion.equals("")) && (!prevRegion.equals(curRegion))) {
                    count++;
                    db.execSQL("INSERT INTO " + expenditureTable + " VALUES (" + count + ", \"" + prevRegion + "\"," + cur_total_expenditure + "," + cur_food + "," + cur_shelter + "," + cur_clothing + "," + cur_transportation + "," + cur_health_care + "," + cur_recreation + "," + cur_education + "," + cur_tobacco_alcohol + ")");
                }
                //Figure out where to put the current record, as each record indicates a single type of expenditure. Many apply to one province.
                cur_region = curLine[0];
                if (curLine[2].equals("Total expenditure")) cur_total_expenditure = curLine[3];
                if (curLine[2].equals("Food expenditures")) cur_food = curLine[3];
                if (curLine[2].equals("Shelter")) cur_shelter = curLine[3];
                if (curLine[2].equals("Clothing and accessories")) cur_clothing = curLine[3];
                if (curLine[2].equals("Transportation")) cur_transportation = curLine[3];
                if (curLine[2].equals("Health care")) cur_health_care = curLine[3];
                if (curLine[2].equals("Recreation")) cur_recreation = curLine[3];
                if (curLine[2].equals("Education")) cur_education = curLine[3];
                if (curLine[2].equals("Tobacco products and alcoholic beverages")) cur_tobacco_alcohol = curLine[3];
                prevRegion = curRegion;
            }
            //Commit the final record.
            if (!curRegion.equals("")) {
                count++;
                db.execSQL("INSERT INTO " + expenditureTable + " VALUES (" + count + ", \"" + prevRegion + "\"," + cur_total_expenditure + "," + cur_food + "," + cur_shelter + "," + cur_clothing + "," + cur_transportation + "," + cur_health_care + "," + cur_recreation + "," + cur_education + "," + cur_tobacco_alcohol + ")");
            }

            count = 0;
            //Adding records to the taxation table is much simpler.
            while ((curLine = taxReader.readNext()) !=null ) {
                count++;
                db.execSQL("INSERT INTO " + taxesTable + " VALUES (" + count + ",\"" + ((curLine[0].equals("")) ? "NULL" : curLine[0]) + "\"," + ((curLine[1].equals("")) ? "NULL" : curLine[1]) + "," + ((curLine[3].equals("")) ? "NULL" : curLine[3]) + "," + ((curLine[5].equals("")) ? "NULL" : curLine[5]) + "," + ((curLine[7].equals("")) ? "NULL" : curLine[7]) + "," + ((curLine[9].equals("")) ? "NULL" : curLine[9]) + "," + ((curLine[11].equals("")) ? "NULL" : curLine[11]) + "," + ((curLine[2].equals("")) ? "NULL" : curLine[2]) + "," + ((curLine[4].equals("")) ? "NULL" : curLine[4]) + "," + ((curLine[6].equals("")) ? "NULL" : curLine[6]) + "," + ((curLine[8].equals("")) ? "NULL" : curLine[8]) + "," + ((curLine[10].equals("")) ? "NULL" : curLine[10]) + ")");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        //erdb.execSQL(initialFriends);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + expenditureTable);
        db.execSQL("DROP TABLE IF EXISTS " + taxesTable);
        onCreate(db);
    }

    //Simple code to retrieve all provinces.
    public List<Province> getAllCountries() {
        List<Province> countryList = new ArrayList<Province>();
        String selectQuery = "SELECT  * FROM " + expenditureTable;
        String taxQuery;
        Province currentCountry;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor taxCursor;
        if (cursor.moveToFirst()) {
            do {
                //For every record we find, we have a new province to create.
                currentCountry = new Province();
                currentCountry.setId(cursor.getInt(0));
                currentCountry.setProvinceName(cursor.getString(1));
                currentCountry.setTotal_expenditure(cursor.getDouble(2));
                currentCountry.setFood(cursor.getDouble(3));
                currentCountry.setShelter(cursor.getDouble(4));
                currentCountry.setClothing(cursor.getDouble(5));
                currentCountry.setTransportation(cursor.getDouble(6));
                currentCountry.setHealth_care(cursor.getDouble(7));
                currentCountry.setRecreation(cursor.getDouble(8));
                currentCountry.setEducation(cursor.getDouble(9));
                currentCountry.setTobacco_alcohol(cursor.getDouble(10));

                //For every province, we must search for its tax information and inject it.
                taxQuery = "SELECT * FROM " + taxesTable + " WHERE " + region + " = \"" + currentCountry.getProvinceName() + "\"";
                taxCursor = db.rawQuery(taxQuery, null);
                if (taxCursor.moveToFirst()) {
                    currentCountry.setTax_rate_1(taxCursor.getDouble(2));
                    currentCountry.setTax_rate_2(taxCursor.getDouble(3));
                    currentCountry.setTax_rate_3(taxCursor.getDouble(4));
                    currentCountry.setTax_rate_4(taxCursor.getDouble(5));
                    currentCountry.setTax_rate_5(taxCursor.getDouble(6));
                    currentCountry.setTax_rate_6(taxCursor.getDouble(7));

                    currentCountry.setThreshold1(taxCursor.getDouble(8));
                    currentCountry.setThreshold2(taxCursor.getDouble(9));
                    currentCountry.setThreshold3(taxCursor.getDouble(10));
                    currentCountry.setThreshold4(taxCursor.getDouble(11));
                    currentCountry.setThreshold5(taxCursor.getDouble(12));
                }

                countryList.add(currentCountry);
            } while (cursor.moveToNext());
        }
        return countryList;
    }

    //Same comments are in previous getAllCountries code, this only retrieves one however.
    public Province getCountry(String countryName) {
        List<Province> countryList = new ArrayList<Province>();
        String selectQuery = "SELECT  * FROM " + expenditureTable + " WHERE region = \"" + countryName + "\"";
        String taxQuery;
        Province currentCountry = new Province();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor taxCursor;
        if (cursor.moveToFirst()) {
            currentCountry.setId(cursor.getInt(0));
            currentCountry.setProvinceName(cursor.getString(1));
            currentCountry.setTotal_expenditure(cursor.getDouble(2));
            currentCountry.setFood(cursor.getDouble(3));
            currentCountry.setShelter(cursor.getDouble(4));
            currentCountry.setClothing(cursor.getDouble(5));
            currentCountry.setTransportation(cursor.getDouble(6));
            currentCountry.setHealth_care(cursor.getDouble(7));
            currentCountry.setRecreation(cursor.getDouble(8));
            currentCountry.setEducation(cursor.getDouble(9));
            currentCountry.setTobacco_alcohol(cursor.getDouble(10));


            taxQuery = "SELECT * FROM " + taxesTable + " WHERE " + region + " = \"" + currentCountry.getProvinceName() + "\"";
            taxCursor = db.rawQuery(taxQuery, null);
            if (taxCursor.moveToFirst()) {
                currentCountry.setTax_rate_1(taxCursor.getDouble(2));
                currentCountry.setTax_rate_2(taxCursor.getDouble(3));
                currentCountry.setTax_rate_3(taxCursor.getDouble(4));
                currentCountry.setTax_rate_4(taxCursor.getDouble(5));
                currentCountry.setTax_rate_5(taxCursor.getDouble(6));
                currentCountry.setTax_rate_6(taxCursor.getDouble(7));

                currentCountry.setThreshold1(taxCursor.getDouble(8));
                currentCountry.setThreshold2(taxCursor.getDouble(9));
                currentCountry.setThreshold3(taxCursor.getDouble(10));
                currentCountry.setThreshold4(taxCursor.getDouble(11));
                currentCountry.setThreshold5(taxCursor.getDouble(12));
            }
        }
        return currentCountry;
    }
}
