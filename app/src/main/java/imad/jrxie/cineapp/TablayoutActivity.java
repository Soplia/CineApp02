package imad.jrxie.cineapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class TablayoutActivity extends AppCompatActivity
{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_tablayout);

        mAdapter=new MyAdapter(getSupportFragmentManager());
        mViewPager= (ViewPager) findViewById(R.id.main_viewPager);
        mViewPager.setAdapter(mAdapter);

        mTabLayout= (TabLayout) findViewById(R.id.main_tab);
        //将ViewPager与TabLayout进行关联
        mTabLayout.setupWithViewPager(mViewPager);
        //设置是固定的，还可以设置为TabLayout.MODE_SCROLLABLE,
        //可滚动的，用于多个Tab
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }
}
