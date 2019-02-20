package imad.jrxie.cineapp.model;

import android.util.Log;

import java.util.List;

/**
 * Created by jrxie on 2019/2/13.
 */

public class Info
{
    public static String TAG = "MainActivity";
    //变量的名字一定要和数据库中的变量的名字相同
    public Place place;
    public List<MovieShowtime> movieShowtimes;

    public void ShowContents()
    {
        //这个地方不能够初始化为null,需要为其分配内存空间
        StringBuilder sb = new StringBuilder();
        sb.append(place.theater.name);
        sb.append(movieShowtimes.get(0).onShow.movie.poster.href);
        Log.d(TAG, sb.toString());
    }

}
