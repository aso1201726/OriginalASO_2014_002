package jp.ac.st.asojuku.original2014002;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("InlinedApi")
public class Kotoba_List extends Activity implements
View.OnClickListener, AdapterView.OnItemClickListener{
	SQLiteDatabase sdb = null;

	MySQLiteOpenHelper helper = null;

	int selectID = -1;
	int lastPosition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kotoba_list);
	}

@Override
protected void onResume() {
	// TODO 自動生成されたメソッド・スタブ
	super.onResume();
	Button btn_Delete = (Button)findViewById(R.id.btn_Delete);
	Button btn_back = (Button)findViewById(R.id.btn_back);
	ListView lstHitokoto = (ListView)findViewById(R.id.lst_hitokoto);

	btn_Delete.setOnClickListener(this);
	btn_back.setOnClickListener(this);

	lstHitokoto.setOnItemClickListener(this);

	this.setDBValuetoList(lstHitokoto);
}

	private void setDBValuetoList(ListView lstHitokoto) {
	// TODO 自動生成されたメソッド・スタブ
		SQLiteCursor cursor = null;

		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}
		catch(SQLiteException e){
			Log.e("ERROR", e.toString());

		}
		cursor = this.helper.selectHitokotoList(sdb);

		int db_layout = android.R.layout.simple_list_item_activated_1;
		String[]from = {"phrase"};
		int[]to = new int[]{android.R.id.text1};

		SimpleCursorAdapter adapter=
				new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		lstHitokoto.setAdapter(adapter);

}
	private void deleteFromHitokoto(int id){
		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}
		catch(SQLiteException e){
			Log.e("ERROR", e.toString());
		}
		this.helper.deleteHitokoto(sdb, id);

	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){

		case R.id.btn_Delete:
			if(this.selectID != -1){
				this.deleteFromHitokoto(this.selectID);
				ListView lstHitokoto = (ListView)findViewById(R.id.lst_hitokoto);

				this.setDBValuetoList(lstHitokoto);
				this.selectID = -1;
				this.lastPosition = -1;
		}else{
			Toast.makeText(Kotoba_List.this, "削除する行を選んでください", Toast.LENGTH_SHORT).show();
		}
			break;
		case R.id.btn_back:

			finish();
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO 自動生成されたメソッド・スタブ
		if(this.selectID!=-1){
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);

		}
		view.setBackgroundColor(android.graphics.Color.LTGRAY);

		SQLiteCursor cursor = (SQLiteCursor)parent.getItemAtPosition(position);

		this.selectID = cursor.getInt(cursor.getColumnIndex("_id"));
		this.lastPosition = position;
	}
}

