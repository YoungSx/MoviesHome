package link.shangxin.movies;

import com.thinkland.sdk.android.SDKInitializer;

import android.app.Application;

public class MyApp extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//��ʼ���ۺϻ���
		SDKInitializer.initialize(getApplicationContext());
		
	}
}
