package account.fpoly.duanmau.dao;
import account.fpoly.duanmau.database.DbHelper;
import account.fpoly.duanmau.model.ThanhVien;

import android.annotation.SuppressLint;
import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.insert("THANHVIEN", null, values);
    }
    public int updateTV(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.update("THANHVIEN", values, "maTV = ?", new String[]{String.valueOf(obj.getMaTV())});
    }
    public int delete(String id){
        return db.delete("THANHVIEN", "maTV = ?", new String[]{id});
    }

    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM THANHVIEN";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "SELECT * FROM THANHVIEN WHERE maTV = ?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<ThanhVien> getData(String sql, String...selectionArgs) {
        List<ThanhVien> list = new ArrayList<ThanhVien>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV"))));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setNamSinh(cursor.getString(cursor.getColumnIndex("namSinh")));
            Log.i("//=====", obj.toString());
            list.add(obj);
        }
        return list;
    }
}
