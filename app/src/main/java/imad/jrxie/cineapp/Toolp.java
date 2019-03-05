package imad.jrxie.cineapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import imad.jrxie.cineapp.model.Info;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jrxie on 2019/2/25.
 */

public class Toolp
{
    /**通过图片url生成Bitmap对象
     * @param urlpath
     * @return Bitmap
     * 根据图片url获取图片对象
     */
    public Bitmap GetBitMBitmap(String urlpath)
    {
        Bitmap map = null;
        try
        {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String TAG = "tablayout";
    private List<MovieInfo> movieList = new ArrayList<MovieInfo>();
    private StringBuilder [][] sb = new StringBuilder[13][32];
    public String tabStr [][] = new String [13][42];

    private String Month [] = {
            "janvier", "février", "mars", "avril",
            "mai", "juin", "juillet", "août",
            "septembre", "octobre", "novembre", "décembre"
    };

    private int GetIndexbyMonthName(String str)
    {
        //Log.e(TAG, "GetIndexbyMonthName: " + "$"+str+"$" );
        for ( int i = 0; i < 12; i++)
            if (Month[i].compareTo(str) == 0)
                return i + 1;
        return -1;
    }

    private String GetMonthNamebyindex(int i)
    {
        return Month[i];
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
        //InitSb();

        //Log.e(TAG, "ExtractMovieShowTime: " + movieList.size() );
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

}
