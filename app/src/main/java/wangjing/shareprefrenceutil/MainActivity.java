package wangjing.shareprefrenceutil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import wangjing.shareprefrenceutil.utils.SharePrefrenceUtils;

public class MainActivity extends AppCompatActivity {

    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePrefrenceUtils.putInt("NUM", i++);
                textView.setText(SharePrefrenceUtils.getInt("NUM", 0) + "");

            }
        });
        textView.setText(SharePrefrenceUtils.getInt("NUM", 0) + "");
    }
}
