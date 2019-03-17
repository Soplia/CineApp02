/**
 * @file GetRequest_Interface
 * @author jrxie
 * @date 2019/2/13 10:11 PM
 * @description The interface used for json
*/

package imad.jrxie.cineapp;

import imad.jrxie.cineapp.model.Info;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This interface used for JSON
 */
public interface GetRequest_Interface
{
    //这个地方的网址保留了具体的地址
    @GET("pam/cine.json")
    Call<Info> getCall();
}
