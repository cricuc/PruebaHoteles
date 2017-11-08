package almundotest.com.pruebahoteles.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import almundotest.com.pruebahoteles.R;
import almundotest.com.pruebahoteles.adapters.HotelesArrayAdapter;
import almundotest.com.pruebahoteles.models.Hotel;
import almundotest.com.pruebahoteles.services.WSHoteles;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HotelesActivity extends AppCompatActivity {

    Context context = this;
    WSHoteles wshoteles;
    List<Hotel> glTodosHoteles = new ArrayList<Hotel>();
    List<Hotel> glHotelesFiltrados = new ArrayList<Hotel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoteles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("almundo.com");
        setSupportActionBar(toolbar);

        ConsultarHoteles();

        Button btnONombre = (Button)findViewById(R.id.btnONombre);
        btnONombre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OrdenarPorNombre();
            }
        });

        Button btnOEstrellas = (Button)findViewById(R.id.btnOEstrellas);
        btnOEstrellas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                OrdenarPorEstrellas();
            }
        });

        ((ListView)findViewById(R.id.lvHoteles)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Hotel item = (Hotel) parent.getItemAtPosition(position);

                Intent intent = new Intent(HotelesActivity.this, DetalleHotelActivity.class);
                String serialItem = (new Gson().toJson(item));
                intent.putExtra("Hotel", serialItem);
                startActivity(intent);
            }
        });

        ((EditText)findViewById(R.id.inputSearch)).addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                glHotelesFiltrados = new ArrayList<Hotel>();
                glHotelesFiltrados.addAll(glTodosHoteles);
                ((HotelesArrayAdapter)((ListView)findViewById(R.id.lvHoteles)).getAdapter()).getFilter().filter(cs);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

    }

    private void ConsultarHoteles() {

        if(wshoteles == null) {
            wshoteles = new WSHoteles();
        }

        wshoteles.getApi()
                .getHoteles()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Hotel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("ErrorenConsultarHoteles", e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Hotel> hoteles) {

                        glTodosHoteles = hoteles;
                        glHotelesFiltrados.addAll(hoteles);

                        HotelesArrayAdapter adapterHoteles = new HotelesArrayAdapter(context,
                                android.R.layout.simple_list_item_1, glHotelesFiltrados);

                        ((ListView)findViewById(R.id.lvHoteles)).setAdapter(adapterHoteles);

                    }
                });
    }

    public void OrdenarPorNombre(){

        Collections.sort(glHotelesFiltrados,new Comparator<Hotel>(){
            public int compare(Hotel o1, Hotel o2){
                return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
            }
        });

        HotelesArrayAdapter adapterHoteles = new HotelesArrayAdapter(context,
                android.R.layout.simple_list_item_1, glHotelesFiltrados);

        ((ListView)findViewById(R.id.lvHoteles)).setAdapter(adapterHoteles);

        ((ListView)findViewById(R.id.lvHoteles)).deferNotifyDataSetChanged();

        ((EditText)findViewById(R.id.inputSearch)).setText(((EditText)findViewById(R.id.inputSearch)).getText());

    }

    public void OrdenarPorEstrellas(){

        Collections.sort(glHotelesFiltrados,new Comparator<Hotel>(){
            public int compare(Hotel o1, Hotel o2){
                return o2.getStars() - o1.getStars();
            }
        });

        HotelesArrayAdapter adapterHoteles = new HotelesArrayAdapter(context,
                android.R.layout.simple_list_item_1, glHotelesFiltrados);

        ((ListView)findViewById(R.id.lvHoteles)).setAdapter(adapterHoteles);

        ((ListView)findViewById(R.id.lvHoteles)).deferNotifyDataSetChanged();

        ((EditText)findViewById(R.id.inputSearch)).setText(((EditText)findViewById(R.id.inputSearch)).getText());

    }

}
