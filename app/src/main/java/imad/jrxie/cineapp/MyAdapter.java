package imad.jrxie.cineapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jrxie on 2019/2/27.
 */

public class MyAdapter extends FragmentPagerAdapter
{
    private int mCount=3;
    private int[] mColors=new int[]{android.R.color.holo_orange_dark,android.R.color.holo_green_dark,android.R.color.holo_red_dark};

    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        return  ViewpagerFragment.newInstance(position,mColors[position]);
    }

    @Override
    public int getCount()
    {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return "Page"+(position+1);
    }
}
