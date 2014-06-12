package jp.ac.st.asojuku.original2014002;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


@SuppressWarnings("unused")
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	/**
	 * @param context　呼び出しコンテクスト
	 * @param name　利用DB名
	 * @param factory　カーソルファクトリー
	 * @param version　DBバージョン
	 */

	public MySQLiteOpenHelper(Context context){

		super(context, "2014021201726.sqlite3", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("CREATE TABLE IF NOT EXISTS"+
		"hitokoto (_id INTEGER PRIMARY KEY AUTOINCREMENT NOY NULL , kotoba TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("drop table Hitokoto");
		onCreate(db);
	}

	/**
	 *
	 * @param db
	 * @param inputMsg
	 */

	public void insertHitokoto(SQLiteDatabase db, String inputMsg){
		String sqlll = "insert into Hitokoto (kotoba) values('"+ inputMsg +"')";

		try{
			//トランザクション開始
			db.beginTransaction();
			db.execSQL(sqlll);
			//トランザクション成功
			db.setTransactionSuccessful();
		} catch(SQLException e){
			//トランザクション失敗…エラーを返す
			Log.e("エラー",e.toString());

		} finally{
			//トランザクション終了
			db.endTransaction();

		}
		return;

	}

	public String selectRandomHitokoto(SQLiteDatabase db){

		String rtString = null;

		String sqlste = " SELECT _id, kotoba FROM Hitokoto ORDER BY RANDOM(); ";

		try{

			SQLiteCursor curcur = (SQLiteCursor)db.rawQuery(sqlste, null);

			if(curcur.getCount()!=0){

				curcur.moveToFirst();
				rtString = curcur.getString(1);

			}
			curcur.close();
		}catch(SQLException e){
			Log.e("エラー",e.toString());
		}finally{
			//特に何もしないスペース

		}

		return sqlste;

	}

}
