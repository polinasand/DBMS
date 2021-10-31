package app.util;

public class Path {
    public final static String ROOT = "/";

    public final static String STORAGE_ID = ":storage";
    public final static String STORAGE = "/storage/" + STORAGE_ID;


    public final static String DATABASES = "/databases/";
    public final static String DATABASE_ID = ":database";
    public final static String DATABASE = DATABASES + DATABASE_ID;


    public final static String SAVE_DATABASE = STORAGE + "/" + DATABASE_ID + "/save/";
    public final static String LOAD_DATABASE = STORAGE + "/" + DATABASE_ID + "/load/";

    public final static String TABLES = "/tables/";
    public final static String TABLE_ID = ":table";
    public final static String TABLE = TABLES + TABLE_ID;

    public final static String SCHEMA = "/schema/";
    public final static String COLUMN_ID = ":column";
    public final static String COLUMN = SCHEMA + COLUMN_ID;

    public final static String ROWS = "/rows/";
    public final static String ROW_ID = ":row";
    public final static String ROW = ROWS + ROW_ID;

    public final static String DIFF = "/diff/:table2";
    public final static String TABLE_ID_2 = ":table2";
}
