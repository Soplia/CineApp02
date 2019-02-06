package imad.jrxie.cineapp;


import android.content.Intent;
import android.os.Bundle;
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
    /************************************************************/
    private String imageUri = "https://ws3.sinaimg.cn/large/006tNc79gy1fzl6w3xzgaj308w08vwf2.jpg";
    public String TAG = "MainActivity";
    private List<Movie> movieList = new ArrayList<Movie>();//实体类

    public void InitMovieData()
    {
        //模拟数据库
        for (int i = 1; i < 8; i++)
        {
            Movie mv = new Movie();//给实体类赋值

            mv.setTitle("Movie" + i);
            mv.setDuration(2 + i + "H");
            mv.setCategory("Love" + i);
            mv.setPress(i + "star");
            mv.setSpect(i + "star");

            for (int j = 0; j < 3; j++)
                mv.showTime.add(i * 2 + j + "");

            movieList.add(mv);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView1);

        InitMovieData();

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
                        .load(imageUri)
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

                b.putString("link", imageUri);
                b.putString("description", "Hello World");
                b.putString("country", "France");

                it.putExtras(b);
                startActivity(it);
            }
        });

    }


}