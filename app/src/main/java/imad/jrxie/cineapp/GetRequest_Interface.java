package imad.jrxie.cineapp;



import imad.jrxie.cineapp.model.Info;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jrxie on 2019/2/13.
 */

public interface GetRequest_Interface
{
    //这个地方的网址保留了具体的地址
    @GET("pam/cine.json")
    Call<Info> getCall();
}
