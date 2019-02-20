package imad.jrxie.cineapp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jrxie on 2019/2/20.
 */

public class Geoloc {
    public double lat;
    //使用这种方式获取
    @SerializedName("long")
    public double longitude;
}
