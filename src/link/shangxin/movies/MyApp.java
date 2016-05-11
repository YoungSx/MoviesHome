package link.shangxin.movies;

import com.thinkland.sdk.android.SDKInitializer;

import android.app.Application;

public class MyApp extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//初始化聚合环境
		SDKInitializer.initialize(getApplicationContext());
		
	}
}
