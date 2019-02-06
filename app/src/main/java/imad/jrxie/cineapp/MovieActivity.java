package imad.jrxie.cineapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieActivity extends Activity
{
    public String TAG = "MovieActivity";
    private TextView mDuration;//item.xml里的TextView：textViewTime
    private TextView mCategory;//item.xml里的TextView：textViewDetail
    private TextView mCountry;//item.xml里的TextView：textViewDetail
    private TextView mpStar;//item.xml里的TextView：textViewTime
    private TextView msStar;//item.xml里的TextView：textViewDetail
    private TextView mDescription;//item.xml里的TextView：textViewTime
    private ImageView ivBasicImage;

    private Intent myIntent;
    private Bundle myBundle;

    public void Init()
    {
        myIntent = getIntent();
        myBundle = myIntent.getExtras();

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

        msStar = (TextView) findViewById(R.id.movieDetailRightSstar);//找到textViewTime
        msStar.setText(myBundle.getString("spect" ));//设置参数

        mpStar = (TextView) findViewById(R.id.movieDetailRightPstar);//找到textViewTime
        mpStar.setText(myBundle.getString("press" ));//设置参数

        mDescription = (TextView) findViewById(R.id.movieDetailDesc);
        mDescription.setText(myBundle.getString("description" ));//设置参数

        ivBasicImage = (ImageView)findViewById(R.id.movieDetailPic);
        Picasso.with(MovieActivity.this)
                .load(myBundle.getString("link"))
                .resize(50, 50)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(ivBasicImage);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Init();
    }
}