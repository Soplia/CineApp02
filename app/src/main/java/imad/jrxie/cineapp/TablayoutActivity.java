package imad.jrxie.cineapp;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class TablayoutActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TabFragmentAdapter pagerAdapter;

    private List<TabFragment> fmList=new ArrayList<TabFragment>();
    private List<String> titleList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        Init();
    }
    private void Init()
    {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //初始化标题以及Fragment内容
        for(int i=0;i<4;i++)
        {
            titleList.add("fm"+i);
            TabFragment fm= new TabFragment();
            fmList.add(fm);
        }

        viewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager(),fmList,titleList));
        tabLayout.setupWithViewPager(viewPager);
    }

}
