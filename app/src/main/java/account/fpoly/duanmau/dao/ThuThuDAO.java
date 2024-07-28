package account.fpoly.duanmau.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import account.fpoly.duanmau.database.DbHelper;
import account.fpoly.duanmau.model.ThuThu;

public class ThuThuDAO {
    private SQLiteDatabase db;
    private DbHelper dbHelper;
    public ThuThuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(ThuThu obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("THUTHU", null, values);
    }
    public int updatePass(ThuThu obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("THUTHU", values, "maTT = ?", new String[]{obj.getMaTT()});
    }
    public int delete(String id){
        return db.delete("THUTHU", "maTT = ?", new String[]{id});
    }

    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM THUTHU";
        return getData(sql);
    }

    public ThuThu getID(String id){
        String sql = "SELECT * FROM THUTHU WHERE maTT = ?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String...selectionArgs) {
        List<ThuThu> list = new ArrayList<ThuThu>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.setMaTT(cursor.getString(cursor.getColumnIndex("maTT")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            Log.i("//=========", obj.toString());
            list.add(obj);
        }
        return list;
    }
    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM THUTHU WHERE maTT AND matKhau = ?";
        List<ThuThu> list = getData(sql, id, password);
        if(list.size() ==0)
            return -1;
        return 1;
    }
}
