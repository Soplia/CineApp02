package imad.jrxie.cineapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrxie on 2019/2/6.
 */

public class Movie
{
    public static String delimiter = "$$$";

    private String title;
    private String duration;
    private String category;
    private String press;
    private String spect;

    public String picUrl;
    public List<String> showTime;

    public Movie()
    {
        showTime = new ArrayList<String>();
    }

    /*
    int numOfMovie = 5;

    public void DownloadData()
    {
        for(int i = 0; i < numOfMovie; i++)
        {
            picUrl.add("Picture Url" + (i+1));
            for(int j = 0; j < 3; j++)
                showTime.add(2 * i + (j + 1) + "");
            showTime.add(delimiter);
        }
    }
    */

    public List<String> getShowTime() {
        return showTime;
    }

    public void setShowTime(List<String> showTime) {
        this.showTime = showTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getSpect() {
        return spect;
    }

    public void setSpect(String spect) {
        this.spect = spect;
    }

    @Override
    public String toString()
    {
        return "User [title=" + title + ", duration=" + duration + "]";
    }

}
