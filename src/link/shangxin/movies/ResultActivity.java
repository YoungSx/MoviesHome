package link.shangxin.movies;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import link.shangxin.movies.R;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class ResultActivity extends Activity {
	Intent intent = getIntent();
	String name = intent.getStringExtra("moviename");
	TextView dym;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		dym = (TextView) findViewById(R.id.dianyingming);
		
		//����һ����ų�����Ϣ������
		Parameters p = new Parameters();
		//�������д��Ҫ��ѯ�ĳ�����Ϣ
		p.add("moviename","name");
		
		JuheData.executeWithAPI(
				94,//����id 
				"http://op.juhe.cn/onebox/movie/video", //�ӿڵ�ַ
				JuheData.POST,//����ʽ 
				p, 
				//�ص���������ѯ����Ϣ֮��Ҫ��ʲô
				new DataCallBack() {
					
					//�����ѯ֮��Ҫ��������
					//error_code ��������״̬�룬�����0��˵��������һ�������������������0��˵��������������
					//reasion����������������˴���û���������У�������ľ��Ǵ���ԭ��
					//jsonResult:���������صĽ��
					public void resultLoaded(int error_code, String resson, String jsonResult) {
						if(error_code == 0 ){
							//JSONObject���������� json���ݵ�
							try {
								JSONObject json = new JSONObject(jsonResult);
								
								String diany = json.getJSONObject("result").getString("dym");
								
							
								dym.setText(diany);
							} catch (Exception e) {
								e.printStackTrace();
							}
						
					}
				}
					}
				);
	};
	
	
}
