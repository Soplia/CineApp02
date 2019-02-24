/**
 * @file TheaterInfo
 * @author jrxie
 * @date 2019/2/24 10:21 PM
 * @description A container for Theater
*/

package imad.jrxie.cineapp;

public class TheaterInfo
{
    double lat;
    double longitude;
    String picUrl;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
