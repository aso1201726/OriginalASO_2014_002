package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class Kotoba_List extends Activity implements
View.OnClickListener, AdapterView.OnItemClickListener{
	SQLiteDatabase sdb = null;

	MySQLiteOpenHelper helper = null;

	int selectId = -1;
	int lastposition = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kotoba_list);
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		switch(v.getId()){

		case R.id.btn_touroku:

		Intent intent = new Intent(Kotoba_List.this, MainActivity.class);
		startActivity(intent);
		break;
		}
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();
		Button btn_touroku = (Button)findViewById(R.id.btn_touroku);
		Button btn_check = (Button)findViewById(R.id.);
		btn_touroku.setOnClickListener(this);
	}



}
