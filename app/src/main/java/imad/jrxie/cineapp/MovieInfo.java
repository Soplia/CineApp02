/**
 * @file MovieInfo
 * @author jrxie
 * @date 2019/2/24 10:20 PM
 * @description A container for movies
*/

package imad.jrxie.cineapp;

import java.util.ArrayList;
import java.util.List;

public class MovieInfo
{
    private String title;
    private String duration;
    private String category;
    private String press;
    private String spect;

    public String picUrl;
    public String videoUrl;
    public List<String> showTime;

    public MovieInfo()
    {
        showTime = new ArrayList<String>();
    }

    public void setPicUrl(String url)
    {
        this.picUrl = url;
    }

    public void setVideoUrl(String url)
    {
        this.videoUrl = url;
    }

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
