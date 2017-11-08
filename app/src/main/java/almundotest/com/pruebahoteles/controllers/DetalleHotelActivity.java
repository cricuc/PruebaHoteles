package almundotest.com.pruebahoteles.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import almundotest.com.pruebahoteles.R;
import almundotest.com.pruebahoteles.helpers.Utilities;
import almundotest.com.pruebahoteles.models.Hotel;

public class DetalleHotelActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Hotel hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_hotel);
        getSupportActionBar().setTitle("Detalle de hotel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String serialItem = getIntent().getStringExtra("Hotel");
        hotel = new Gson().fromJson(serialItem, Hotel.class);

        ((TextView)findViewById(R.id.tvNombre)).setText(hotel.getName());
        ((TextView)findViewById(R.id.tvPrecio)).setText("USD "+hotel.getPrice());
        ((TextView)findViewById(R.id.tvUbicacion)).setText(hotel.getCountry() + ", " + hotel.getCity());
        ((EditText)findViewById(R.id.tvDescripcion)).setText(hotel.getDescription());

        switch (hotel.getStars()) {
            case 1:
                ((ImageView) findViewById(R.id.ivStar2)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.ivStar3)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.ivStar4)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                break;
            case 2:
                ((ImageView) findViewById(R.id.ivStar3)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.ivStar4)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                break;
            case 3:
                ((ImageView) findViewById(R.id.ivStar4)).setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                break;
            case 4:
                ((ImageView) findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                break;
            case 5:
                break;
            default:
                break;
        }

        ImageView imageView = (ImageView) findViewById(R.id.ivEstado);

        switch (hotel.getState()) {
            case "full":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.full));
                break;
            case "available":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.available));
                break;
            case "not-available":
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.not_available));
                break;
            default:
                break;
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if(hotel != null) {
            LatLng ubicacion = Utilities.getLatLong(Utilities.getLocationInfo(hotel.getAddress()));

            mMap.addMarker(new MarkerOptions().position(ubicacion).title(hotel.getAddress()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,16.0f));
        }
    }

}
