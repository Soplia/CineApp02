package imad.jrxie.cineapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import imad.jrxie.cineapp.model.Info;
import imad.jrxie.cineapp.model.Trailer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jrxie on 2019/2/27.
 */

public class MyAdapter extends FragmentPagerAdapter
{
    //private int mCount=3;
    //private int[] mColors=new int[]{android.R.color.holo_orange_dark,android.R.color.holo_green_dark,android.R.color.holo_red_dark};

    private final List<ViewpagerFragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public void addFragment(ViewpagerFragment fragment, String title)
    {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        //return  ViewpagerFragment.newInstance(position,mColors[position]);
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        //return "Page"+(position+1);
        return mFragmentTitles.get(position);
    }
}
