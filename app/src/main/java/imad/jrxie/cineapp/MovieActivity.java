/**
 * @file MovieActivity
 * @author jrxie
 * @date 2019/2/24 10:11 PM
 * @description
*/

package imad.jrxie.cineapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class MovieActivity extends Activity
{
    public String TAG = "MovieActivity";
    private TextView mDuration;
    private TextView mCategory;
    private Intent myIntent;
    private Bundle myBundle;
    private RatingBar pRatingBar;
    private RatingBar sRatingBar;
    private JCVideoPlayerStandard player;
    private LinearLayout line1,line2,line3;
    private Toolp tool;

    public void Init()
    {
        myIntent = getIntent();
        myBundle = myIntent.getExtras();

        tool = new Toolp();

        line1 = (LinearLayout) findViewById(R.id.movieDetail3);
        line2 = (LinearLayout) findViewById(R.id.movieDetailRightP);
        line3 = (LinearLayout) findViewById(R.id.movieDetailRightS);

        player = (JCVideoPlayerStandard) findViewById(R.id.videoPlayer);

        //这个地方需要重点注意，id是全局唯一的，即使是在两个不同的布局文件中也需要使用不同的ID
        //在设置文字的时候不要使用view来获取相应的控件直接使用findViewById即可
        mDuration = (TextView) findViewById(R.id.movieDetailDuration);
        mDuration.setText(myBundle.getString("duration" ));

        mCategory = (TextView) findViewById(R.id.movieDetailCategory);
        mCategory.setText(myBundle.getString("category" ));

        pRatingBar  = (RatingBar)findViewById(R.id.movieDetailRightPstar);
        pRatingBar.setRating(Float.parseFloat(myBundle.getString("spect" )));

        sRatingBar  = (RatingBar)findViewById(R.id.movieDetailRightSstar);
        sRatingBar.setRating(Float.parseFloat(myBundle.getString("press" )));
    }

    public void SetColor()
    {
        Bitmap bitmap = tool.GetBitMBitmap(myBundle.getString("picUrl"));
        //Log.e(TAG,"picurl = " + myBundle.get("picUrl" ));
        if (bitmap == null)
        {
            return;
        }

        Palette palette = Palette.from(bitmap).generate();

        Palette.Swatch vibrant = palette.getVibrantSwatch();//有活力的
        Palette.Swatch vibrantLight = palette.getLightVibrantSwatch();//有活力的，亮色
        Palette.Swatch muted = palette.getMutedSwatch();//柔和的

        if(vibrantLight != null)
            line1.setBackgroundColor(vibrantLight.getRgb());
        if (vibrant != null)
            line2.setBackgroundColor(vibrant.getRgb());
        if (muted != null)
            line3.setBackgroundColor(muted.getRgb());
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
                intent.setData(Uri.parse(myBundle.getString("videoUrl")));
                startActivity(intent);
            }
        });

        Glide.with(MovieActivity.this).load(myBundle.getString("picUrl")).into(player.thumbImageView);

        SetColor();
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