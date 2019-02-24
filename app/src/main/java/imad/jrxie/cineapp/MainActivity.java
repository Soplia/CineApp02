/**
 * @file MainActivity
 * @author jrxie
 * @date 2019/2/24 7:03 PM
 * @description show all the avaliable movies with list, this is the main page of this app
*/

package imad.jrxie.cineapp;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import imad.jrxie.cineapp.model.Info;
import imad.jrxie.cineapp.model.Trailer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity
{
    private TextView mTitle;
    private TextView mDuration;
    private TextView mCategory;
    private TextView mShowtime;
    private ListView lv;
    private BaseAdapter adapter;
    private ImageView ivBasicImage;
    private Button buttonMap;
    private ImageView imgMap;
    private RatingBar pRatingBar;
    private RatingBar sRatingBar;
    DecimalFormat doubleFormat=new DecimalFormat(".##");

    public String TAG = "MainActivity";
    private List<MovieInfo> movieList = new ArrayList<MovieInfo>();
    public TheaterInfo myTheater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestDatabase();
    }

    /**
     * Get information of movies from database
     */
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
                myTheater = new TheaterInfo();
                myTheater.setLat(response.body().place.theater.geoloc.lat);
                myTheater.setLongitude(response.body().place.theater.geoloc.longitude);
                myTheater.setPicUrl(response.body().place.theater.picture.href);

                imgMap = (ImageView) findViewById(R.id.imgMap);
                Picasso.with(MainActivity.this)
                        .load(response.body().place.theater.picture.href)
                        .resize(500, 220)
                        .centerCrop()
                        .error(R.drawable.ic_launcher_background)
                        .into(imgMap);

                //处理返回的数据结果, 如果是null的话,可能是body是空的,那么可能的原因就是url的格式不正确
                //初始化电影列表
                int movieQuantity = response.body().movieShowtimes.size();
                //模拟数据库
                for (int i = 0; i < movieQuantity; i++)
                {
                    MovieInfo mv = new MovieInfo();//给实体类赋值
                    mv.setPicUrl(response.body().movieShowtimes.get(i).onShow.movie.poster.href);
                    mv.setTitle(response.body().movieShowtimes.get(i).onShow.movie.title);

                    double duration = Double.parseDouble(response.body().movieShowtimes.get(i).onShow.movie.runtime);
                    mv.setDuration(doubleFormat.format(duration / 3600) + "H");

                    String pressTemp1 = response.body().movieShowtimes.get(i).onShow.movie.statistics.pressRating;
                    if (pressTemp1 != null)
                    {
                        double pressTemp = Double.parseDouble(pressTemp1);
                        mv.setPress(doubleFormat.format(pressTemp));
                    }
                    else
                        mv.setPress(String.valueOf(R.integer.no_press));

                    String spectTemp1 = response.body().movieShowtimes.get(i).onShow.movie.statistics.userRating;
                    if (spectTemp1 != null)
                    {
                        double spectTemp = Double.parseDouble(spectTemp1);
                        mv.setSpect(doubleFormat.format(spectTemp));
                    }
                    else
                        mv.setSpect(String.valueOf(R.integer.no_spect));

                    mv.showTime.add(response.body().movieShowtimes.get(i).display);
                    Trailer temp = response.body().movieShowtimes.get(i).onShow.movie.trailer;
                    if(temp != null)
                    {
                        String videoTemp = temp.href;
                        if (videoTemp != null)
                        {
                            mv.setVideoUrl(videoTemp);
                            //Log.d(TAG, "vidoeTemp is not null: " + videoTemp);
                        }
                        else
                        {
                            mv.setVideoUrl("https://www.youtube.com/watch?v=QUV-6UxwlZE&list=PLG8vJQBHlyoErKrozK21hyZestXaj18AT&index=22");
                            //mv.setVideoUrl(String.valueOf(R.string.video_url));
                            //Log.d(TAG,"videoTemp is null");
                        }
                    }
                    else
                        mv.setVideoUrl("https://www.youtube.com/watch?v=QUV-6UxwlZE&list=PLG8vJQBHlyoErKrozK21hyZestXaj18AT&index=22");
                    //mv.setVideoUrl(String.valueOf(R.string.video_url));

                    movieList.add(mv);
                }

                //网络请求需要时间,所以需要把设置放在这个的后面
                SetAdapter();
                SetImgListener();
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Info> call, Throwable throwable)
            {
                Log.e(TAG, "Connection Failed!", throwable);
            }
        });
    }

    /**
     * Set list and Set list item clicked listener
     */
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

                Log.d(TAG,movieList.get(position).getPress());
                pRatingBar  = (RatingBar)view.findViewById(R.id.movieDetailRightPstar);
                pRatingBar.setRating(Float.parseFloat(movieList.get(position).getPress()));

                sRatingBar  = (RatingBar)view.findViewById(R.id.movieDetailRightSstar);
                sRatingBar.setRating(Float.parseFloat(movieList.get(position).getSpect()));

                ivBasicImage = (ImageView)view.findViewById(R.id.movieDetailLeftPic);
                Picasso.with(MainActivity.this)
                        .load(movieList.get(position).picUrl)
                        .resize(200, 200)
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

                pRatingBar = (RatingBar) view.findViewById(R.id.movieDetailRightPstar);//找到textViewDetail
                b.putString("press", String.valueOf(pRatingBar.getRating()));

                sRatingBar = (RatingBar) view.findViewById(R.id.movieDetailRightSstar);
                b.putString("spect", String.valueOf(sRatingBar.getRating()));

                b.putString("picUrl", movieList.get(position).picUrl);
                b.putString("videoUrl", movieList.get(position).videoUrl);

                b.putString("description", "Hello World");
                b.putString("country", "France");

                it.putExtras(b);
                startActivity(it);
            }
        });
    }

    /**
     * @description Go to the TheaterMap activity
     */
    public void SetImgListener()
    {
        imgMap.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        // TODO Auto-generated method stub
                        Intent it = new Intent(MainActivity.this, TheaterMap.class);
                        Bundle b = new Bundle();

                        b.putDouble("theater_lat", myTheater.getLat());
                        b.putDouble("theater_long", myTheater.getLongitude());
                        b.putString("theater_pic", myTheater.getPicUrl());

                        it.putExtras(b);
                        startActivity(it);
                        Log.e(TAG, "ButtonClicked");
                    }
                }
        );
        /*
        buttonMap = (Button) findViewById(R.id.buttonMap);

        buttonMap.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                Intent it = new Intent(MainActivity.this, TheaterMap.class);
                Bundle b = new Bundle();

                b.putDouble("theater_lat", myTheater.getLat());
                b.putDouble("theater_long", myTheater.getLongitude());
                b.putString("theater_pic", myTheater.getPicUrl());

                it.putExtras(b);
                startActivity(it);
                Log.e(TAG, "ButtonClicked");
            }
        });
        */
    }

}