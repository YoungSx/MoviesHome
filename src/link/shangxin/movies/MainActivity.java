package link.shangxin.movies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
        
        
        
        
    }

}
