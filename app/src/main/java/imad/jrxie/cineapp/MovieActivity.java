/**
 * @file MovieActivity
 * @author jrxie
 * @date 2019/2/24 10:11 PM
 * @description
*/

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
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class MovieActivity extends Activity
{
    public String TAG = "MovieActivity";
    private TextView mDuration;
    private TextView mCategory;
    //private TextView mCountry;
    //private TextView mDescription;
    private Intent myIntent;
    private Bundle myBundle;
    private RatingBar pRatingBar;
    private RatingBar sRatingBar;
    private JCVideoPlayerStandard player;

    public void Init()
    {
        myIntent = getIntent();
        myBundle = myIntent.getExtras();

        player = (JCVideoPlayerStandard) findViewById(R.id.videoPlayer);

        //这个地方需要重点注意，id是全局唯一的，即使是在两个不同的布局文件中也需要使用不同的ID
        //在设置文字的时候不要使用view来获取相应的控件直接使用findViewById即可
        mDuration = (TextView) findViewById(R.id.movieDetailDuration);
        mDuration.setText(myBundle.getString("duration" ));

        mCategory = (TextView) findViewById(R.id.movieDetailCategory);
        mCategory.setText(myBundle.getString("category" ));

        //mCountry = (TextView) findViewById(R.id.movieDetailCountry);
        //mCountry.setText(myBundle.getString("country" ));

        //mDescription = (TextView) findViewById(R.id.movieDetailDesc);

        pRatingBar  = (RatingBar)findViewById(R.id.movieDetailRightPstar);
        pRatingBar.setRating(Float.parseFloat(myBundle.getString("spect" )));

        sRatingBar  = (RatingBar)findViewById(R.id.movieDetailRightSstar);
        sRatingBar.setRating(Float.parseFloat(myBundle.getString("press" )));

        //mDescription = (TextView) findViewById(R.id.movieDetailDesc);
        //mDescription.setText(myBundle.getString("description" ));
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
                //Log.e(TAG, myBundle.getString("videoUrl"));
                intent.setData(Uri.parse(myBundle.getString("videoUrl")));
                startActivity(intent);
            }
        });

        //Log.e(TAG, myBundle.getString("videoUrl"));

        Glide.with(MovieActivity.this).load(myBundle.getString("picUrl")).into(player.thumbImageView);

    }

    /*
    public void onBackPressed() {
        if (player.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.releaseAllVideos();
    }
    */

}