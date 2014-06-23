package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements
View.OnClickListener{

	SQLiteDatabase sdb = null;
	MySQLiteOpenHelper helper  = null;



	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

		Intent intent = null;

		switch(v.getId()){
		case R.id.btn_ment:
			intent = new Intent(MainActivity.this, Kotoba_List.class);
			startActivity(intent);
			break;

		case R.id.btn_check:
			String strHitokoto = helper.selectRandomHitokoto(sdb);

			intent = new Intent(MainActivity.this,Kotoba_View.class);
			intent.putExtra("hitokoto",strHitokoto);

			startActivity(intent);
			break;

		case R.id.btn_touroku:

			EditText etv = (EditText)findViewById(R.id.txt_kotoba);
			String inputMsg = etv.getText().toString();

			if(inputMsg!=null && !inputMsg.isEmpty()){

				helper.insertHitokoto(sdb, inputMsg);

			}
			etv.setText("");
			break;
		}

	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Button btn_ment = (Button)findViewById(R.id.btn_ment);
		btn_ment.setOnClickListener(this);

		Button btn_touroku = (Button)findViewById(R.id.btn_touroku);
		btn_touroku.setOnClickListener(this);

		Button btn_check = (Button)findViewById(R.id.btn_check);
		btn_check.setOnClickListener(this);

		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try{
			sdb = helper.getWritableDatabase();
		}
		catch(SQLiteException e){
			//異常しゅ～りょ～
			return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
