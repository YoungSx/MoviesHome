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
        
        //��������ť���click�¼�����������ȡ�������е��ı�����Resultҳ����
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
        //��ȡ���ӰѶ������
        Parameters newMoviesParams = new Parameters();
        newMoviesParams.add("city","��ׯ");
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
        
        //��ȡ��ƱƱ��
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
						
						
						
						// ׼�����ݣ����ݱ���ŵ�������
						ArrayList<Map<String, String>> al = new ArrayList<Map<String, String>>();

						for (int i = 0; i < result.length(); i++) {

							// ����һ������һ��Item�����ݵļ���
							Map<String, String> item = new HashMap<String, String>();

							item.put("hotName", movies.get(i).getName());
							item.put("hotWk", movies.get(i).getWk());
							item.put("hotWboxoffice", movies.get(i).getWboxoffice());
							item.put("hotTboxoffice", movies.get(i).getTboxoffice());

							// �������õ�ÿ�����ݴ�ŵ��ܵļ���
							al.add(item);
						}
						//����������������item.xml�����еĸ�ʽ������ݣ�������Щitem�󶨵�ListView��
						
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
