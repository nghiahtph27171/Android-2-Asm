package account.fpoly.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import account.fpoly.duanmau.database.DbHelper;
import account.fpoly.duanmau.model.LoaiSach;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(LoaiSach obj) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LOAISACH", null, values);
    }
    public int update(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("LOAISACH", values, "maLoai = ?", new String[]{String.valueOf(obj.getMaLoai())});
    }
    public int delete(String id){
        return db.delete("LOAISACH", "maLoai = ?", new String[]{id});
    }

    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LOAISACH";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LOAISACH WHERE maLoai = ?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<LoaiSach> getData(String sql, String...selectionArgs) {
        List<LoaiSach> list = new ArrayList<LoaiSach>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach obj = new LoaiSach();
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }
}
