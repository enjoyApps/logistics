package com.example.logistics_ui.datestore;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.logistics_ui.model.LogisticInfo;
  
/**
 * @author zhenggangji
 *
 */
public class LogisticInfoDao {  
    private DBHelpter helper;  
  
    public LogisticInfoDao(Context context) {  
        helper = new DBHelpter(context);  
    }  
      

  
    public void insert(LogisticInfo p) {  
        SQLiteDatabase db = helper.getWritableDatabase();     
          
        // 准备数据  
        ContentValues values = new ContentValues();  
        values.put(LogisticColumns.LOGISTIC_COMPANY, p.getExpTextName());  
        values.put(LogisticColumns.LOGISTIC_NO, p.getMailNo());
        values.put(LogisticColumns.TIME, p.getQueryTime());
          
        // 通过ContentValues中的数据拼接SQL语句, 执行插入操作, id为表中的一个列名  
        db.insert("LogisticInfo", null, values);        
          
        db.close();  
    }  
  
    public void delete(int id) {  
        SQLiteDatabase db = helper.getWritableDatabase();  
          
        // 执行删除操作, 在person表中删除id为指定值的记录  
        db.delete("LogisticInfo", LogisticColumns._ID + "=?", new String[]{String.valueOf(id)});  
          
        db.close();  
    }  
  
    public void update(LogisticInfo p) {  
        SQLiteDatabase db = helper.getWritableDatabase();  
  
        // 要更新的数据  
        ContentValues values = new ContentValues();  
        values.put(LogisticColumns.LOGISTIC_COMPANY, p.getExpTextName());  
        values.put(LogisticColumns.LOGISTIC_NO, p.getMailNo());
        values.put(LogisticColumns.TIME, "");
  
        // 更新person表中id为指定值的记录  
        db.update("LogisticInfo", values, LogisticColumns._ID+"=?", new String[] { String.valueOf(p.getId())});  
          
        db.close();  
    }  
  
	public LogisticInfo query(LogisticInfo p) {
		SQLiteDatabase db = helper.getReadableDatabase();

		// 执行查询: 不去重复, 表是person, 查询name和balance两列, Where条件是"id=?", 占位符是id, 不分组,
		// 没有having, 不排序, 没有分页
		Cursor c = db.query(false, "LogisticInfo", new String[] {
				LogisticColumns._ID, LogisticColumns.LOGISTIC_COMPANY,
				LogisticColumns.LOGISTIC_NO, LogisticColumns.TIME },
				LogisticColumns.LOGISTIC_COMPANY + "=? and "+LogisticColumns.LOGISTIC_NO + "=?",
				new String[] { p.getExpTextName(),p.getMailNo() }, null, null, null, null);

		LogisticInfo p2 = null;
		// 判断Cursor是否有下一条记录
		if (c.moveToNext())
			// 从Cursor中获取数据, 创建Person对象
			p2 = new LogisticInfo(c.getInt(0), c.getString(1), c.getString(2),
					c.getString(3));

		// 释放资源
		c.close();
		db.close();
		return p2;
	}
  
    public List<LogisticInfo> queryAll() {  
        SQLiteDatabase db = helper.getReadableDatabase();  
  
        // 查询所有记录, 倒序  
        Cursor c = db.query(false, "LogisticInfo", new String[]{LogisticColumns._ID,LogisticColumns.LOGISTIC_COMPANY,LogisticColumns.LOGISTIC_NO, LogisticColumns.TIME}, null, null, null, null, LogisticColumns.TIME +" DESC", null);  
          
        List<LogisticInfo> logisticInfos = new ArrayList<LogisticInfo>();  
        while (c.moveToNext())  
            logisticInfos.add(new LogisticInfo(c.getInt(0), c.getString(1), c.getString(2),c.getString(3)));  
        c.close();  
        db.close();  
        return logisticInfos;  
    }  
      
    public int queryCount() {  
        SQLiteDatabase db = helper.getReadableDatabase();  
          
        // 查询记录条数  
        Cursor c = db.query(false, "LogisticInfo", new String[]{"COUNT(*)"}, null, null, null, null, null, null);  
          
        c.moveToNext();  
        int count =c.getInt(0);   
        c.close();  
        db.close();  
        return count;  
    }  
  
} 
