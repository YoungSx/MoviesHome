package link.shangxin.movies;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class ResultActivity extends Activity {

	TextView dym;
	TextView lx;
	TextView dy;
	TextView zy;
	TextView sysj;
	TextView jq;
	ImageView hb;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		dym = (TextView) findViewById(R.id.dianyingming);
		lx = (TextView) findViewById(R.id.leixing);
		dy = (TextView) findViewById(R.id.daoyan);
		zy = (TextView) findViewById(R.id.zhuyan);
		sysj = (TextView) findViewById(R.id.shangyingshijian);
		jq = (TextView) findViewById(R.id.juqing);
		hb = (ImageView) findViewById(R.id.tupian);
		Intent intent = getIntent();
		String name = intent.getStringExtra("searchText");
		// ����һ����ų�����Ϣ������
		Parameters p = new Parameters();
		// �������д��Ҫ��ѯ�ĳ�����Ϣ
		p.add("q", name);

		JuheData.executeWithAPI(
				94,// ����id
				"http://op.juhe.cn/onebox/movie/video", // �ӿڵ�ַ
				JuheData.GET,// ����ʽ
				p,
				// �ص���������ѯ����Ϣ֮��Ҫ��ʲô
				new DataCallBack() {

					// �����ѯ֮��Ҫ��������
					// error_code ��������״̬�룬�����0��˵��������һ�������������������0��˵��������������
					// reasion����������������˴���û���������У�������ľ��Ǵ���ԭ��
					// jsonResult:���������صĽ��
					public void resultLoaded(int error_code, String reason,
							String jsonResult) {
						if (error_code == 0) {
							// JSONObject���������� json���ݵ�
							try {
								JSONObject json = new JSONObject(jsonResult);
								String diany = json.getJSONObject("result").getString("title");
								String leix = json.getJSONObject("result").getString("tag");
								String daoy = json.getJSONObject("result").getString("dir");
								String zhuy = json.getJSONObject("result").getString("act");
								String shay = json.getJSONObject("result").getString("year");
								String juq = json.getJSONObject("result").getString("desc");
								String haib = json.getJSONObject("result").getString("cover");
								
								dym.setText(diany);
								lx.setText(leix);
								dy.setText(daoy);
								zy.setText(zhuy);
								sysj.setText(shay);
								jq.setText(juq);
								
							} catch (Exception e) {
								e.printStackTrace();
							}

						}else{
							Toast.makeText(ResultActivity.this, reason, Toast.LENGTH_LONG).show();
						}
					}
				});
	};

}
