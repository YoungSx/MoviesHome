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
		// 创建一个存放城市信息的容器
		Parameters p = new Parameters();
		// 向容器中存放要查询的城市信息
		p.add("q", name);

		JuheData.executeWithAPI(94,// 数据id
				"http://op.juhe.cn/onebox/movie/video", // 接口地址
				JuheData.GET,// 请求方式
				p,
				// 回调函数：查询到信息之后要干什么
				new DataCallBack() {

					// 结果查询之后要做的事情
					// error_code 服务器的状态码，如果是0，说明服务器一切运行正常，如果不是0，说明服务器有问题
					// reasion：如果服务器发生了错误，没有正常运行，他保存的就是错误原因
					// jsonResult:服务器返回的结果
					@Override
					public void resultLoaded(int error_code, String reason,
							String jsonResult) {
						if (jsonResult == null) {
							dym.setText("找不到此电影");
							lx.setText("");
							dy.setText("");
							zy.setText("");
							sysj.setText("");
							jq.setText("");
						} else if (error_code == 0) {
							// JSONObject是用来解析 json数据的
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
								String xing = ("★ ★ ★ ★ ★");
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

									// 接收下载下来的图片，更新到界面
									// result：下载下来的那张图片

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
											// 建立连接
											HttpURLConnection con = (HttpURLConnection) url
													.openConnection();
											// 设置连接的请求时间
											con.setConnectTimeout(10000);
											// 设置请求方式
											con.setRequestMethod("GET");
											// 准备接收数据
											// 如果响应的状态码是200代码，数据传输完成，则开始解析数据
											if (con.getResponseCode() == 200) {
												// 获取内存中的数据
												InputStream is = con
														.getInputStream();
												// 将数据转换成图片
												Bitmap image = BitmapFactory
														.decodeStream(is);
												// 将解析出来的图片返回给主线程UI
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
