package imad.jrxie.cineapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import java.util.ArrayList;
import java.util.List;
import imad.jrxie.cineapp.model.Info;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TablayoutActivity extends AppCompatActivity
{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MyAdapter mAdapter;
/*************************************************************************/

    public String TAG = "tablayout";
    private List<MovieInfo> movieList = new ArrayList<MovieInfo>();
    //private StringBuilder [][] sb = new StringBuilder[13][32];
    public String tabStr [][] = new String [13][42];

    private String Month [] =
            {
                "janvier", "février", "mars", "avril",
                "mai", "juin", "juillet", "août",
                "septembre", "octobre", "novembre", "décembre"
            };

    private void InitTabstr()
    {
        for (int i = 0; i < 13; i++)
            for (int j = 0; j < 32; j++)
                tabStr[i][j] = "";
    }

    private int GetIndexbyMonthName(String str)
    {
        for ( int i = 0; i < 12; i++)
            if (Month[i].compareTo(str) == 0)
                return i + 1;
        return -1;
    }

    private String GetMonthNamebyindex(int i)
    {
        return Month[i - 1];
    }

    public void RequestDatabase()
    {
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://etudiants.openium.fr/") // 设置 网络请求 Url , 这个地方的保留了最主要的网址,需要与前面的合并
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Info> call = request.getCall();

        //发送网络请求(异步)
        call.enqueue(new Callback<Info>()
        {
            //请求成功时回调
            @Override
            public void onResponse(Call<Info> call, Response<Info> response)
            {
                int movieQuantity = response.body().movieShowtimes.size();

                for (int i = 0; i < movieQuantity; i++)
                {
                    MovieInfo mv = new MovieInfo();//给实体类赋值
                    mv.setTitle(response.body().movieShowtimes.get(i).onShow.movie.title);
                    mv.showTime.add(response.body().movieShowtimes.get(i).display);
                    movieList.add(mv);
                    //Log.e(TAG, "RequestDatabase: "+ movieList.size() );
                }

                ExtractMovieShowTime();
                InitTablayout();
                //Log.e(TAG, "onResponse: " + CountNotNullNum() );
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<Info> call, Throwable throwable)
            {
                Log.e(TAG, "Connection Failed!", throwable);
            }
        });
    }

    public void ExtractMovieShowTime()
    {
        //InitTabstr();
        if (!movieList.isEmpty())
        {
            int movieNum = movieList.size();
            for (int i = 0; i < movieNum; i++)
            {
                List<String> showTime1 = movieList.get(i).getShowTime();
                String moiveTitle = movieList.get(i).getTitle();

                for (int j = 0; j < showTime1.size(); j++)
                {
                    String strArr1 [] = showTime1.get(j).split("\r\n");

                    for ( int k = 0; k < strArr1.length; k++)
                    {
                        String strArr2 [] = strArr1[k].split(" ");
                        if (GetIndexbyMonthName(strArr2[4].trim()) != -1)
                            tabStr[GetIndexbyMonthName(strArr2[4])][Integer.parseInt(strArr2[3])] += "" +
                                    "\r\n" + moiveTitle;
                    }
                }
            }
        }
        else
            Log.e(TAG, "ExtractMovieShowTime: movielist is empty" );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tablayout);

        RequestDatabase();
    }

    private void InitTablayout()
    {
        //Log.e(TAG, "onCreate: " + CountNotNullNum() );

        mAdapter = new MyAdapter(getSupportFragmentManager());

        for ( int i = 1; i < 13; i++)
            for (int j = 1; j < 32; j++)
                if (tabStr[i][j] != null)
                {
                    String title = GetMonthNamebyindex(i) + "-" + j;
                    mAdapter.addFragment(ViewpagerFragment.newInstance(tabStr[i][j]),title);

                    Log.e(TAG, "onCreate: " + title );
                    Log.e(TAG, "onCreate: " + tabStr[i][j] );
                }

        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        mViewPager.setAdapter(mAdapter);

        mTabLayout= (TabLayout) findViewById(R.id.main_tab);
        mTabLayout.setupWithViewPager(mViewPager);

        //设置是固定的，还可以设置为TabLayout.MODE_SCROLLABLE,
        //可滚动的，用于多个Tab
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
