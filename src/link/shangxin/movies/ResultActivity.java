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
		// 创建一个存放城市信息的容器
		Parameters p = new Parameters();
		// 向容器中存放要查询的城市信息
		p.add("q", name);

		JuheData.executeWithAPI(
				94,// 数据id
				"http://op.juhe.cn/onebox/movie/video", // 接口地址
				JuheData.GET,// 请求方式
				p,
				// 回调函数：查询到信息之后要干什么
				new DataCallBack() {

					// 结果查询之后要做的事情
					// error_code 服务器的状态码，如果是0，说明服务器一切运行正常，如果不是0，说明服务器有问题
					// reasion：如果服务器发生了错误，没有正常运行，他保存的就是错误原因
					// jsonResult:服务器返回的结果
					public void resultLoaded(int error_code, String reason,
							String jsonResult) {
						if (error_code == 0) {
							// JSONObject是用来解析 json数据的
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
