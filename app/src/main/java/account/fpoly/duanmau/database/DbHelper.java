package account.fpoly.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
static final String dbName = "QLYNS";
static final int dbVersion =1;
    public DbHelper(Context context){
        super(context, dbName, null,dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String tThuThu = "CREATE TABLE THUTHU(maTT text primary key, hoTen text not null, matKhau text not null)";
        db.execSQL(tThuThu);
        db.execSQL("INSERT INTO THUTHU(maTT, hoTen, matKhau) VALUES (1, 'Nguyen Van A', '123456')");
        String tThanhVien = "CREATE TABLE THANHVIEN(maTV  integer primary key autoincrement, hoTen text not null, namSinh text not null)";
        db.execSQL(tThanhVien);

        String tLoaiSach = "CREATE TABLE LOAISACH(maLoai integer primary key autoincrement, tenLoai text )";
        db.execSQL(tLoaiSach);
        db.execSQL("INSERT INTO LOAISACH VALUES(1, 'thieu nhi'),(2, 'tinh cam'), (3, 'hanh dong')");

        String tSach = "CREATE TABLE SACH(maSach integer primary key  autoincrement, tenSach text not null, giaThue integer not null, maLoai integer references LOAISACH(maLoai))";
        db.execSQL(tSach);
        String tPhieuMuon = "CREATE TABLE PHIEUMUON(maPM integer primary key autoincrement, maTT text references THUTHU(maTT), maTV integer references THANHVIEN(maTV), maSach integer references SACH(maSach), tienThue integer not null, ngay DATE not null, traSach integer not null)";
        db.execSQL(tPhieuMuon);

        //       String tCTPM = "CREATE TABLE CTPM (mactpm integer primary key autoincrement ,mapm integer references PHIEUMUON(mapm), masach integer references SACH(masach), soluong integer)";
   //     db.execSQL(tCTPM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);
        }
    }
}
