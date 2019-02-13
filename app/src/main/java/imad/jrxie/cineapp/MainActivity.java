package imad.jrxie.cineapp;


import android.content.Intent;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import imad.jrxie.cineapp.model.Info;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends Activity
{
    private TextView mTitle;//item.xml里的TextView：textViewDetail
    private TextView mDuration;//item.xml里的TextView：textViewTime
    private TextView mCategory;//item.xml里的TextView：textViewDetail
    private TextView mpStar;//item.xml里的TextView：textViewTime
    private TextView msStar;//item.xml里的TextView：textViewDetail
    private TextView mShowtime;//item.xml里的TextView：textViewTime

    private ListView lv;//activity_main.xml里的ListView
    private BaseAdapter adapter;//要实现的类
    private ImageView ivBasicImage;

    DecimalFormat doubleFormat=new DecimalFormat(".##");

    /************************************************************/
    //private String imageUri = "https://ws3.sinaimg.cn/large/006tNc79gy1fzl6w3xzgaj308w08vwf2.jpg";
    public String TAG = "MainActivity";
    private List<Movie> movieList = new ArrayList<Movie>();//实体类

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestDatabase();
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
            { //处理返回的数据结果, 如果是null的话,可能是body是空的,那么可能的原因就是url的格式不正确
                //response.body().ShowContents();
                //初始化电影列表
                //InitMovieData(response);
                int movieQuantity = response.body().movieShowtimes.size();
                //模拟数据库
                for (int i = 0; i < movieQuantity; i++)
                {
                    Movie mv = new Movie();//给实体类赋值
                    mv.setPicUrl(response.body().movieShowtimes.get(i).onShow.movie.poster.href);
                    mv.setTitle(response.body().movieShowtimes.get(i).onShow.movie.title);


                    double duration = Double.parseDouble(response.body().movieShowtimes.get(i).onShow.movie.runtime);
                    mv.setDuration(doubleFormat.format(duration / 3600) + "H");

                    mv.setCategory(response.body().movieShowtimes.get(i).onShow.movie.genre.get(0).name);

                    String pressTemp1 = response.body().movieShowtimes.get(i).onShow.movie.statistics.pressRating;
                    if (pressTemp1 != null)
                    {
                        double pressTemp = Double.parseDouble(pressTemp1);
                        mv.setPress(doubleFormat.format(pressTemp));
                    }
                    else
                        mv.setPress("N");


                    String spectTemp1 = response.body().movieShowtimes.get(i).onShow.movie.statistics.userRating;
                    if (spectTemp1 != null)
                    {
                        double spectTemp = Double.parseDouble(spectTemp1);
                        mv.setSpect(doubleFormat.format(spectTemp));
                    }
                    else
                        mv.setSpect("N");


                    mv.showTime.add(response.body().movieShowtimes.get(i).display);

                    /*
                    String videoTemp = response.body().movieShowtimes.get(i).onShow.movie.trailer.href;
                    if (videoTemp != null)
                        mv.setVideoUrl(videoTemp);
                    else
                        mv.setVideoUrl("https://www.youtube.com/watch?v=QUV-6UxwlZE&list=PLG8vJQBHlyoErKrozK21hyZestXaj18AT&index=22");
                    */
                    movieList.add(mv);
                }


                //网络请求需要时间,所以需要把设置放在这个的后面
                SetAdapter();

                Log.d(TAG, "NumberofMovieList:" + movieList.size());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Info> call, Throwable throwable)
            {
                Log.e(TAG, "Connection Failed!", throwable);
            }
        });
    }

    public void SetAdapter()
    {
        adapter = new BaseAdapter()
        {
            @Override
            public int getCount()
            {
                // TODO Auto-generated method stub
                return movieList.size();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View view;

                if (convertView == null)
                {
                    //因为getView()返回的对象，adapter会自动赋给ListView
                    view = inflater.inflate(R.layout.item_main, null);
                }
                else
                {
                    view = convertView;
                    //Log.d(TAG,"有缓存，不需要重新生成: " + position);
                }
                mTitle = (TextView) view.findViewById(R.id.textViewDetail);//找到textViewDetail
                mTitle.setText(movieList.get(position).getTitle());//设置参数

                mDuration = (TextView) view.findViewById(R.id.movieDetailRightD);//找到textViewTime
                mDuration.setText(movieList.get(position).getDuration());//设置参数

                mCategory = (TextView) view.findViewById(R.id.movieDetailRightC);//找到textViewDetail
                mCategory.setText(movieList.get(position).getCategory());//设置参数

                mpStar = (TextView) view.findViewById(R.id.movieDetailRightPstar);//找到textViewTime
                mpStar.setText(movieList.get(position).getPress());//设置参数

                msStar = (TextView) view.findViewById(R.id.movieDetailRightSstar);//找到textViewTime
                msStar.setText(movieList.get(position).getSpect());//设置参数

                ivBasicImage = (ImageView)view.findViewById(R.id.movieDetailLeftPic);
                Picasso.with(MainActivity.this)
                        .load(movieList.get(position).picUrl)
                        .resize(50, 50)
                        .centerCrop()
                        .error(R.drawable.ic_launcher_background)
                        .into(ivBasicImage);

                StringBuilder sb = new StringBuilder();
                for(int i = 0 ; i < movieList.get(position).showTime.size(); i++)
                {
                    sb.append(movieList.get(position).showTime.toString());
                }

                mShowtime = (TextView) view.findViewById(R.id.textViewTime);//找到textViewDetail
                mShowtime.setText(sb.toString());//设置参数

                return view;
            }

            @Override
            public long getItemId(int position)
            {
                //获取在列表中与指定索引对应的行id
                return 0;
            }

            @Override
            public Object getItem(int position)
            {
                //获取数据集中与指定索引对应的数据项
                return null;
            }
        };

        lv = (ListView) findViewById(R.id.listView1);

        lv.setAdapter(adapter);


        //获取当前ListView点击的行数，并且得到该数据
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            //Position 代表了每个item的序号,从0开始.
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent it = new Intent(MainActivity.this, MovieActivity.class); //
                Bundle b = new Bundle();

                mTitle = (TextView) view.findViewById(R.id.textViewDetail);//找到textViewDetail
                b.putString("title", mTitle.getText().toString());

                mDuration = (TextView) view.findViewById(R.id.movieDetailRightD);//找到textViewDetail
                b.putString("duration", mDuration.getText().toString());

                mCategory = (TextView) view.findViewById(R.id.movieDetailRightC);//找到textViewDetail
                b.putString("category", mCategory.getText().toString());

                mpStar = (TextView) view.findViewById(R.id.movieDetailRightPstar);//找到textViewDetail
                b.putString("press", mpStar.getText().toString());

                msStar = (TextView) view.findViewById(R.id.movieDetailRightSstar);
                b.putString("spect", msStar.getText().toString());

                b.putString("picUrl", movieList.get(position).picUrl);
                b.putString("videoUrl", movieList.get(position).videoUrl);

                b.putString("description", "Hello World");
                b.putString("country", "France");

                it.putExtras(b);
                startActivity(it);
            }
        });
    }

}