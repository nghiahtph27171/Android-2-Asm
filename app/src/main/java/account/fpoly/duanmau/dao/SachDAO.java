package account.fpoly.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import account.fpoly.duanmau.database.DbHelper;
import account.fpoly.duanmau.model.Sach;

public class SachDAO {
    private SQLiteDatabase db;

    public SachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(Sach obj) {
        ContentValues values = new ContentValues();

        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.insert("SACH", null, values);
    }
    public int update(Sach obj){
        ContentValues values = new ContentValues();
        values.put("tenSach", obj.getTenSach());
        values.put("giaThue", obj.getGiaThue());
        values.put("maLoai", obj.getMaLoai());
        return db.update("SACH", values, "maSach = ?", new String[]{String.valueOf(obj.getMaSach())});
    }
    public int delete(String id){
        return db.delete("SACH", "maSach = ?", new String[]{id});
    }

    public List<Sach> getAll(){
        String sql = "SELECT * FROM SACH";
        return getData(sql);
    }

    public Sach getID(String id){
        String sql = "SELECT * FROM SACH WHERE maSach = ?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<Sach> getData(String sql, String...selectionArgs) {
        List<Sach> list = new ArrayList<Sach>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            Sach obj = new Sach();
            obj.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            obj.setTenSach(cursor.getString(cursor.getColumnIndex("tenSach")));
            obj.setGiaThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue"))));
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            list.add(obj);
        }
        return list;
    }
}
