package discordlayout.yassinedeveloper.com.discordlayout;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

//    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Layout_Discord";
    // Login table name
    private static final String TABLE_USER = "USER";
    private static final String TABLE_CURRENT_USER = "CURRENT_USER";
    private static final String TABLE_CHAT = "CHAT";

    String TABLE_SERVER_NAME = "servername";
    String TABLE_FRIEND_NAME = "friendname";
    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";


    // CHAT TABLE

    String FROM_EMAIL = "from_email";
    String TO_EMAIL = "to_email";
    String TEXT_MESSAGE = "message";


    // Server table

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT UNIQUE,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_PASSWORD + " TEXT )";
        db.execSQL(CREATE_LOGIN_TABLE);

        String CREATE_CURRENT_USER = "CREATE TABLE " + TABLE_CURRENT_USER + "("
                + KEY_NAME + " TEXT , " + KEY_EMAIL + " TEXT NOT NULL, " + KEY_PASSWORD + " TEXT )";
        db.execSQL(CREATE_CURRENT_USER);

        String CREATE_CHAT_TABLE = "CREATE TABLE " + TABLE_CHAT + "("
                + KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                FROM_EMAIL + " TEXT ," +
                TO_EMAIL + " TEXT , " +
                TEXT_MESSAGE + " TEXT )";
        db.execSQL(CREATE_CHAT_TABLE);

        String CREATE_SERVER_NAME_TABLE = "CREATE TABLE " + TABLE_SERVER_NAME + "("
                + KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT ," +
                KEY_EMAIL + " TEXT )";
        db.execSQL(CREATE_SERVER_NAME_TABLE);

        String CREATE_FRIEND_NAME_TABLE = "CREATE TABLE " + TABLE_FRIEND_NAME + "("
                + KEY_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT ," +
                KEY_EMAIL + " TEXT )";
        db.execSQL(CREATE_FRIEND_NAME_TABLE);

        //Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENT_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVER_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND_NAME);

        // Create tables again
        onCreate(db);
    }
    /**
     * Storing user details in database
     * */
    public void addUser( String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
      //values.put(KEY_ID, id); // Name
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_PASSWORD, password); // Email
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
        //Log.d(TAG, "New user inserted into sqlite: " + id);
    }


    public void addservername(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name Server
        values.put(KEY_EMAIL, email); // Email user
        // Inserting Row
        db.insert(TABLE_SERVER_NAME, null, values);
        db.close(); // Closing database connection
        //Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public void addfriendname(String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name Server
        values.put(KEY_EMAIL, email); // Email user
        // Inserting Row
        db.insert(TABLE_FRIEND_NAME, null, values);
        db.close(); // Closing database connection
        //Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    public  void SendMessage(String from, String to ,String msg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
      //  values.put(KEY_ID, id);
        values.put(FROM_EMAIL, from);
        values.put(TO_EMAIL, to);
        values.put(TEXT_MESSAGE, msg); // Email
        // Inserting Row
        db.insert(TABLE_CHAT, null, values);
        db.close(); // Closing database connection
    }

    public void addCurrentuser(String name, String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // id
        values.put(KEY_EMAIL, email); // N
        values.put(KEY_PASSWORD, password); // password
        // Inserting Row
        db.insert(TABLE_CURRENT_USER, null, values);
        db.close(); // Closing database connection
        //Log.d(TAG, "New user inserted into sqlite: " + id);
    }


    public void update(String id, String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(KEY_ID, id); // Name
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_PASSWORD, password); // Email
        // Inserting Row
        db.update(TABLE_USER, values, KEY_ID + " = " + id, null);
        db.close(); // Closing database connection
    }
public void updatefriendsname(String id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
       // values.put(KEY_ID, id); // Name
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        // Inserting Row
        db.update(TABLE_FRIEND_NAME, values, KEY_ID + " = " + id, null);
        db.close(); // Closing database connection
    }

    public void updateCurrentuser(String email,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email); // Name
        values.put(KEY_PASSWORD, password); // Name
        // Inserting Row
        db.update(TABLE_CURRENT_USER, values, KEY_EMAIL + " = " + email, null);
        db.close(); // Closing database connection
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("id", cursor.getString(0));
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("password", cursor.getString(3));
        }
        cursor.close();
        db.close();
        // return user
        //Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
        return user;
    }
    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getCurrentUser() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CURRENT_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(0));
            user.put("email", cursor.getString(1));
            user.put("password", cursor.getString(2));
        }
        cursor.close();
        db.close();
        // return user
        //Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
        return user;
    }
    public Cursor fetch() {
        String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchFriendName() {
        String[] columns = new String[] { KEY_NAME,KEY_EMAIL };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FRIEND_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchServerName() {
        String[] columns = new String[] { KEY_NAME,KEY_EMAIL };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SERVER_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchMessage(){
        String[] columns = new String[] { FROM_EMAIL, TO_EMAIL, TEXT_MESSAGE };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CHAT, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public HashMap<String, String> getChatDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CHAT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("id", cursor.getString(0));
            user.put("msg", cursor.getString(1));
         }
        cursor.close();
        db.close();
        // return user
        //Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteChat() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_CHAT, null, null);
        db.close();
        //Log.d(TAG, "Deleted all user info from sqlite");
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();
        //Log.d(TAG, "Deleted all user info from sqlite");
    }
    public void deleteCurrentUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_CURRENT_USER, null, null);
        db.close();
        //Log.d(TAG, "Deleted all user info from sqlite");
    }
    public void deleteFriendsName() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_FRIEND_NAME, null, null);
        db.close();
        //Log.d(TAG, "Deleted all user info from sqlite");
    }

}