package activity.tugas.donasikuy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Donkuy.db", null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session(id_session integer PRIMARY KEY, login text)");
        db.execSQL("CREATE TABLE user(id_user integer PRIMARY KEY AUTOINCREMENT, nama text, npm text, username text, email text, no_hp text,alamat text,  password text,status text, saldo text)");
        db.execSQL("INSERT INTO user(id,nama, npm, username, email, no_hp, alamat, password, status, saldo ) VALUES(1,'Admin','1','admin.com', 'admin@admin.com', '082242484585','Karawang','12345','admin','0')");
        db.execSQL("CREATE TABLE donasi(id_donasi integer PRIMARY KEY AUTOINCREMENT, judul text, penerima text, alasan text, alamat text, nomortelp text, jumlah text, waktu text, foto text)");
        db.execSQL("INSERT INTO session(id_session,login) VALUES(1,'kosong')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS donasi");
        onCreate(db);
    }

    //check session
    public Boolean checkSession(String sessionValues){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = db.update("session",contentValues,"id="+id, null);
        if (update == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //insert user
    public boolean insertUser(String strNama, String strNPM, String strUsername, String strEmail, String strNoHP, String strAlamat, String strPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", strNama);
        contentValues.put("username", strUsername);
        contentValues.put("email", strEmail);
        contentValues.put("npm", strNPM);
        contentValues.put("alamat", strAlamat);
        contentValues.put("no_hp", strNoHP);
        contentValues.put("password", strPassword);
        contentValues.put("status", "user");
        contentValues.put("saldo", "0");
        long insert = db.insert("user", null, contentValues);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //check login
    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username,password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    //isidata donasi admin
    public boolean insertData(String judul, String penerima, String alasan, String alamat, String nomortelp, String jumlah, String waktu, String foto)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("judul", judul);
        contentValues.put("penerima", penerima);
        contentValues.put("alasan", alasan);
        contentValues.put("alamat", alamat);
        contentValues.put("nomortelp", nomortelp);
        contentValues.put("jumlah", jumlah);
        contentValues.put("waktu", waktu);
        contentValues.put("foto", foto);
        Long insert = db.insert("donasi",null, contentValues);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }
}

