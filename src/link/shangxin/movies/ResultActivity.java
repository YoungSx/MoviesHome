package link.shangxin.movies;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
	TextView pf;
	String addr = "http://img.baidu.com/img/iknow/sula2604/wenka270170.jpg";

	@Override
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
		pf = (TextView) findViewById(R.id.pingfen);
		Intent intent = getIntent();
		String name = intent.getStringExtra("searchText");
		// ����һ����ų�����Ϣ������
		Parameters p = new Parameters();
		// �������д��Ҫ��ѯ�ĳ�����Ϣ
		p.add("q", name);

		JuheData.executeWithAPI(94,// ����id
				"http://op.juhe.cn/onebox/movie/video", // �ӿڵ�ַ
				JuheData.GET,// ����ʽ
				p,
				// �ص���������ѯ����Ϣ֮��Ҫ��ʲô
				new DataCallBack() {

					// �����ѯ֮��Ҫ��������
					// error_code ��������״̬�룬�����0��˵��������һ�������������������0��˵��������������
					// reasion����������������˴���û���������У�������ľ��Ǵ���ԭ��
					// jsonResult:���������صĽ��
					@Override
					public void resultLoaded(int error_code, String reason,
							String jsonResult) {
						if (jsonResult == null) {
							dym.setText("�Ҳ����˵�Ӱ");
							lx.setText("");
							dy.setText("");
							zy.setText("");
							sysj.setText("");
							jq.setText("");
						} else if (error_code == 0) {
							// JSONObject���������� json���ݵ�
							try {
								JSONObject json = new JSONObject(jsonResult);
								String diany = json.getJSONObject("result")
										.getString("title");
								String leix = json.getJSONObject("result")
										.getString("tag");
								String daoy = json.getJSONObject("result")
										.getString("dir");
								String zhuy = json.getJSONObject("result")
										.getString("act");
								String shay = json.getJSONObject("result")
										.getString("year");
								String juq = json.getJSONObject("result")
										.getString("desc");
								String haib = json.getJSONObject("result")
										.getString("cover");
								String xing = ("�� �� �� �� ��");
								double xj = json.getJSONObject("result").getDouble("rating");
//								int sz = Integer.parseInt(xj,10);
//								String zh =  xing.substring(0, sz-1);
								
								Double double1=new Double(xj);
								int a=double1.intValue();
								String zh =  xing.substring(0, a-1);
								//System.out.println("xj:"+a);
								
								/*
  								String youkuURL = json.getJSONObject("result")
										.getJSONObject("playlinks")
										.getString("youku");
								dym.setText(Html.fromHtml("<a href='https://souly.cn'>"+youkuURL+"</a>"));
 								*/
								dym.setText(diany);
								lx.setText(leix);
								dy.setText(daoy);
								zy.setText(zhuy);
								sysj.setText(shay);
								jq.setText(juq);
								pf.setText(String.valueOf(zh));
								// hb.Bitmap();

								AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {

									// ��������������ͼƬ�����µ�����
									// result����������������ͼƬ

									@Override
									protected void onPostExecute(Bitmap result) {
										hb.setImageBitmap(result);
										//hb.setBackground(result);
									}

									@Override
									protected Bitmap doInBackground(
											String... arg0) {
										// TODO Auto-generated method stub
										//
										try {
											URL url = new URL(arg0[0]);
											// ��������
											HttpURLConnection con = (HttpURLConnection) url
													.openConnection();
											// �������ӵ�����ʱ��
											con.setConnectTimeout(10000);
											// ��������ʽ
											con.setRequestMethod("GET");
											// ׼����������
											// �����Ӧ��״̬����200���룬���ݴ�����ɣ���ʼ��������
											if (con.getResponseCode() == 200) {
												// ��ȡ�ڴ��е�����
												InputStream is = con
														.getInputStream();
												// ������ת����ͼƬ
												Bitmap image = BitmapFactory
														.decodeStream(is);
												// ������������ͼƬ���ظ����߳�UI
												return image;
											}

										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										return null;
									}
								};
								task.execute(haib);

								
								
							} catch (Exception e) {
								e.printStackTrace();
							}

						} else {
							Toast.makeText(ResultActivity.this, reason,
									Toast.LENGTH_LONG).show();
						}
					}
				});
	};

}
