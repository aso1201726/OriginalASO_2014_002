package jp.ac.st.asojuku.original2014002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Kotoba_View extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kotoba_view);
	}

	@Override
	protected void onResume() {
		// TODO 自動生成されたメソッド・スタブ
		super.onResume();

		Intent intent = this.getIntent();

		String hitokoto = intent.getStringExtra("hitokoto");

		TextView txtHITOKOTO = (TextView)findViewById(R.id.txt1koto);
		txtHITOKOTO.setText(hitokoto);
	}


}
