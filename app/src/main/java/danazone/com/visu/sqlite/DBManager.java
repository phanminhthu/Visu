package danazone.com.visu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import danazone.com.visu.bean.InfoUser;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Visu";
    private static final String TABLE_NAME = "info_user";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String HOME_NUMBER = "homeNumber";
    private static final String STREET = "street";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String TYPE = "type";
    private static final String EMAIL = "email";
    private static final String AVATAR = "avatar";


    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " integer primary key, " +
                NAME + " TEXT, " +
                PHONE + " TEXT, " +
                HOME_NUMBER + " TEXT, " +
                STREET + " TEXT, " +
                COUNTRY + " TEXT, " +
                CITY + " TEXT, " +
                TYPE + " TEXT, " +
                AVATAR + " TEXT, " +
                EMAIL + " TEXT)";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add new a student
    public void addIfoUser(InfoUser user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, user.getName());
        values.put(PHONE, user.getPhone());
        values.put(HOME_NUMBER, user.getHomeNumber());
        values.put(STREET, user.getStreet());
        values.put(COUNTRY, user.getCountry());
        values.put(CITY, user.getCity());
        values.put(TYPE, user.getType());
        values.put(EMAIL, user.getEmail());
        values.put(AVATAR, user.getAvatar());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public InfoUser getAllInfo() {
        InfoUser user = new InfoUser();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        user.setId(cursor.getInt(0));
        user.setName(cursor.getString(1));
        user.setPhone(cursor.getString(2));
        user.setHomeNumber(cursor.getString(3));
        user.setStreet(cursor.getString(4));
        user.setCountry(cursor.getString(5));
        user.setCity(cursor.getString(6));
        user.setType(cursor.getInt(7));
        user.setEmail(cursor.getString(8));
        user.setAvatar(cursor.getString(9));

        cursor.close();
        db.close();
        return user;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT  * FROM " + TABLE_NAME, null);
        return res;
    }
}
