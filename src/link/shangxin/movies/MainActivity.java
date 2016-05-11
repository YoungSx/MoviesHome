package link.shangxin.movies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class MainActivity extends Activity {

	Button searchButton;
	EditText searchBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        searchButton = (Button) findViewById(R.id.searchButton);
        searchBox = (EditText) findViewById(R.id.searchBox);
        
        //给搜索按钮添加click事件监听，并获取搜索框中的文本传到Result页面中
        searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
				String searchText = searchBox.getText().toString();
				intent.putExtra("searchText", searchText);
			}
		});
        
        /*
        //获取最近影讯的内容
        Parameters newMoviesParams = new Parameters();
        newMoviesParams.add("city","枣庄");
        JuheData.executeWithAPI(94, "http://op.juhe.cn/onebox/movie/pmovie", JuheData.GET, newMoviesParams, new DataCallBack() {
			
			@Override
			public void resultLoaded(int error_code, String reason, String jsonResult) {
				// TODO Auto-generated method stub
				if(error_code == 0){
					JSONObject json;
					
					try {
						json = new JSONObject(jsonResult);
						
						//
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
        
        */
        
        //获取网票票房
        Parameters hotMoviesParams = new Parameters();
        hotMoviesParams.add("area", "CN");
        JuheData.executeWithAPI(44, "http://v.juhe.cn/boxoffice/rank", JuheData.GET, hotMoviesParams, new DataCallBack() {
			
			@Override
			public void resultLoaded(int error_code, String reason, String jsonResult) {
				// TODO Auto-generated method stub
				if(error_code == 0){
					JSONObject json;
					List<Movie> movies = new ArrayList<Movie>();
					try {
						JSONArray result = new JSONObject(jsonResult).getJSONArray("result");
						
						for(int i=0;i<result.length();i++){
							JSONObject obj = result.getJSONObject(i);
							Movie m = new Movie(obj.getString("name"),obj.getString("wk"),obj.getString("wboxoffice"),obj.getString("tboxoffice"));
							movies.add(m);
						}
						
						
						
						// 准备数据：数据必须放到集合中
						ArrayList<Map<String, String>> al = new ArrayList<Map<String, String>>();

						for (int i = 0; i < result.length(); i++) {

							// 创建一个保存一个Item的数据的集合
							Map<String, String> item = new HashMap<String, String>();

							item.put("hotName", movies.get(i).getName());
							item.put("hotWk", movies.get(i).getWk());
							item.put("hotWboxoffice", movies.get(i).getWboxoffice());
							item.put("hotTboxoffice", movies.get(i).getTboxoffice());

							// 将创建好的每条数据存放到总的集合
							al.add(item);
						}
						//创建适配器：按照item.xml布局中的格式填充数据，并将这些item绑定到ListView中
						
						SimpleAdapter ada = new SimpleAdapter(
								MainActivity.this, al, R.layout.hotitem, 
								new String[]{"hotName","hotBoxoffice","hotTotals","hotFare"}, 
								new int[]{R.id.hotName, R.id.hotBoxoffice, R.id.hotTotals,R.id.hotFare}
						);
						
						ListView hotList = (ListView) findViewById(R.id.hotList);
						
						hotList.setAdapter(ada);
						
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
        
    }

}
