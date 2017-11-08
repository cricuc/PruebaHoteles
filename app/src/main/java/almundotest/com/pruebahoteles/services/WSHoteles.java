package almundotest.com.pruebahoteles.services;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.List;

import almundotest.com.pruebahoteles.adapters.ItemTypeAdapterFactory;
import almundotest.com.pruebahoteles.models.Hotel;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by cc_ri on 07/11/2017.
 */

public class WSHoteles {

    private WSHotelesApi wsHotelesApi;
    private static final String WSRest_Hoteles_URL = "http://192.168.0.3:5000";

    public WSHoteles(){

        try {

            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Accept", "application/json");
                }
            };

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(WSRest_Hoteles_URL)
                    .setRequestInterceptor(requestInterceptor)
                    .setConverter(new GsonConverter(new GsonBuilder()
                            .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                            .create()))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();

            wsHotelesApi = restAdapter.create(WSHotelesApi.class);

        }catch (Exception e){
            Log.i("Error en WSHoteles", e.toString());
            e.printStackTrace();
        }

    }

    public WSHotelesApi getApi() {

        return wsHotelesApi;
    }

    public interface WSHotelesApi {

        @GET("/hotels")
        public Observable<List<Hotel>>
        getHoteles();
    }

}
