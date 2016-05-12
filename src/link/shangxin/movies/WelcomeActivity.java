package link.shangxin.movies;

import link.shangxin.movies.MainActivity;
import link.shangxin.movies.WelcomeActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		//推迟多久去做什么事
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// 你要做的事
				
				//创建一个意图：WelcomeActivity to MainActivity
				Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
				
				startActivity(intent);
				finish();
			}
		}, 3000);
	}

}
