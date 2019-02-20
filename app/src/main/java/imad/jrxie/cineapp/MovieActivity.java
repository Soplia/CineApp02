package imad.jrxie.cineapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class MovieActivity extends Activity
{
    public String TAG = "MovieActivity";
    private TextView mDuration;//item.xml里的TextView：textViewTime
    private TextView mCategory;//item.xml里的TextView：textViewDetail
    private TextView mCountry;//item.xml里的TextView：textViewDetail
    private TextView mpStar;//item.xml里的TextView：textViewTime
    private TextView msStar;//item.xml里的TextView：textViewDetail
    private TextView mDescription;//item.xml里的TextView：textViewTime

    private Intent myIntent;
    private Bundle myBundle;
    private RatingBar pRatingBar;
    private RatingBar sRatingBar;
    private String videoUrl = "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4";


    private JCVideoPlayerStandard player;

    public void Init()
    {
        myIntent = getIntent();
        myBundle = myIntent.getExtras();

        player = (JCVideoPlayerStandard) findViewById(R.id.videoPlayer);

        //这个地方需要重点注意，id是全局唯一的，即使是在两个不同的布局文件中也需要使用不同的ID
        //在设置文字的时候不要使用view来获取相应的控件直接使用findViewById即可
        //mDuration = (TextView) view.findViewById(R.id.movieDetailDuration);//找到textViewTime
        mDuration = (TextView) findViewById(R.id.movieDetailDuration);//找到textViewTime
        mDuration.setText(myBundle.getString("duration" ));//设置参数

        mCategory = (TextView) findViewById(R.id.movieDetailCategory);//找到textViewTime
        mCategory.setText(myBundle.getString("category" ));//设置参数

        mCountry = (TextView) findViewById(R.id.movieDetailCountry);//找到textViewTime
        mCountry.setText(myBundle.getString("country" ));//设置参数

        mDescription = (TextView) findViewById(R.id.movieDetailDesc);//找到textViewTime
        //mDescription.setText(myBundle.getString("mDescription" ));//设置参数

        pRatingBar  = (RatingBar)findViewById(R.id.movieDetailRightPstar);

        pRatingBar.setRating(Float.parseFloat(myBundle.getString("spect" )));


        sRatingBar  = (RatingBar)findViewById(R.id.movieDetailRightSstar);
        sRatingBar.setRating(Float.parseFloat(myBundle.getString("press" )));

        //msStar = (TextView) findViewById(R.id.movieDetailRightSstar);//找到textViewTime
        //msStar.setText(myBundle.getString("spect" ));//设置参数

        //mpStar = (TextView) findViewById(R.id.movieDetailRightPstar);//找到textViewTime
        //mpStar.setText(myBundle.getString("press" ));//设置参数

        mDescription = (TextView) findViewById(R.id.movieDetailDesc);
        mDescription.setText(myBundle.getString("description" ));//设置参数


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Init();

        player.startButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {


                Intent intent = new Intent(Intent.ACTION_VIEW);
                Log.d(TAG, myBundle.getString("videoUrl"));
                intent.setData(Uri.parse(myBundle.getString("videoUrl")));
                startActivity(intent);


            }
        });

        //Log.d(TAG, myBundle.getString("videoUrl"));

        //boolean setUp = player.setUp(myBundle.getString("videoUrl"), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");

        //if (setUp)
        //{
        Glide.with(MovieActivity.this).load(myBundle.getString("picUrl")).into(player.thumbImageView);
       // }


        //直接进入全屏
        //player.startFullscreen(this, JCVideoPlayerStandard.class, videoUrl, "");
        //模拟用户点击开始按钮，NORMAL状态下点击开始播放视频，播放中点击暂停视频
        //player.startButton.performClick();
    }

    /*
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    */

}