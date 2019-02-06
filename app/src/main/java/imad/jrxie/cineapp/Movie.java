package imad.jrxie.cineapp;

/**
 * Created by jrxie on 2019/2/6.
 */

public class Movie
{
    private String title;
    private String duration;
    private String category;
    private String press;
    private String spect;
    private String timetable[];

    public String[] getTimetable() {
        return timetable;
    }

    public void setTimetable(String[] timetable) {
        this.timetable = timetable;
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
