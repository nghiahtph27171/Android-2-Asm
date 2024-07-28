package account.fpoly.duanmau.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import account.fpoly.duanmau.database.DbHelper;
import account.fpoly.duanmau.model.ThanhVien;
import account.fpoly.duanmau.model.ThuThu;

public class DemoDB {
    private SQLiteDatabase db;
    ThuThuDAO thuThuDAO;
    ThanhVienDAO thanhVienDAO;
    static final String TAG = "//===========";
    public DemoDB(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDAO = new ThanhVienDAO(context);
    }
    public void thanhVien(){
    //    List<ThanhVien> list = new ArrayList<>();
        ThanhVien thanhVien = new ThanhVien(1, "Nguyen Van A", "2000");
//        if (thanhVienDAO.insert(thanhVien)>0){
//            Log.i(TAG, "Them thanh cong");
//        }else {
//           Log.i(TAG, "Them that bai");
//        }
        Log.i(TAG, "=========================");
        Log.i(TAG, "Tong so thanh vien" + thanhVienDAO.getAll().size());

        Log.i(TAG, "===========sau khi sua==============");
        thanhVien = new ThanhVien(2, "Nguyen Van B", "2003");
        thanhVienDAO.updateTV(thanhVien);
        Log.i(TAG, "Tong so thanh vien" + thanhVienDAO.getAll().size());
        thanhVienDAO.delete("2");
        Log.i(TAG, "===========sau khi xoa==============");
        Log.i(TAG, "Tong so thanh vien" + thanhVienDAO.getAll().size());
    }
    public void thuThu(){
        ThuThu tt = new ThuThu("admin", "Nguyen Admin", "admin123");
        thuThuDAO.insert(tt);
        Log.i(TAG, "=========================");
        Log.i(TAG, "Tong so thanh vien" + thuThuDAO.getAll().size());
        tt = new ThuThu("admin", "Tran Admin", "123456");
        thuThuDAO.updatePass(tt);
        Log.i(TAG, "=========================");
        Log.i(TAG, "Tong so thanh vien" + thuThuDAO.getAll().size());
    }
}
