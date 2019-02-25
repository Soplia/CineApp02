package imad.jrxie.cineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class TheaterPicture extends AppCompatActivity
{

    private ImageView imgMap;
    private Intent infoIntent;
    private Bundle infoBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_picture);
        InitPicture();
        SetImgListener();
    }

    public void InitPicture()
    {
        infoIntent = getIntent();
        infoBundle = infoIntent.getExtras();
        imgMap = (ImageView) findViewById(R.id.imgMap);

        Picasso.with(TheaterPicture.this)
                .load(infoBundle.getString("theater_pic"))
                .resize(500, 220)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(imgMap);
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
                        Intent it = new Intent(TheaterPicture.this, TheaterMap.class);
                        it.putExtras(infoBundle);
                        startActivity(it);
                        //Log.e(TAG, "ButtonClicked");
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
