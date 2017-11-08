package almundotest.com.pruebahoteles.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import almundotest.com.pruebahoteles.R;
import almundotest.com.pruebahoteles.helpers.DownloadImageTask;
import almundotest.com.pruebahoteles.models.Hotel;

/**
 * Created by cc_ri on 06/11/2017.
 */

public class HotelesArrayAdapter extends ArrayAdapter<Hotel> {

    List<Hotel> hoteles = new ArrayList<>();
    List<Hotel> TodosHoteles = new ArrayList<>();

    public HotelesArrayAdapter(Context context, int textViewResourceId, List<Hotel> objects) {
        super(context, textViewResourceId, objects);
        TodosHoteles = new ArrayList<>();
        hoteles = objects;
        TodosHoteles.addAll(objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(position<hoteles.size()) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.adapter_listitemhoteles, null);
            ((TextView) v.findViewById(R.id.tvNombreHotel)).setText(hoteles.get(position).getName());
            ((TextView) v.findViewById(R.id.tvPrecio)).setText("USD " + hoteles.get(position).getPrice());
            ImageView ivImagen1 = (ImageView) v.findViewById(R.id.ivImagen1);
            ImageView ivImagen2 = (ImageView) v.findViewById(R.id.ivImagen2);
            ImageView ivImagen3 = (ImageView) v.findViewById(R.id.ivImagen3);
            new DownloadImageTask(ivImagen1)
                    .execute(hoteles.get(position).getUrlimages().get(0));
            new DownloadImageTask(ivImagen2)
                    .execute(hoteles.get(position).getUrlimages().get(1));
            new DownloadImageTask(ivImagen3)
                    .execute(hoteles.get(position).getUrlimages().get(2));

            switch (hoteles.get(position).getStars()) {
                case 1:
                    ((ImageView) v.findViewById(R.id.ivStar2)).setVisibility(View.GONE);
                    ((ImageView) v.findViewById(R.id.ivStar3)).setVisibility(View.GONE);
                    ((ImageView) v.findViewById(R.id.ivStar4)).setVisibility(View.GONE);
                    ((ImageView) v.findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                    break;
                case 2:
                    ((ImageView) v.findViewById(R.id.ivStar3)).setVisibility(View.GONE);
                    ((ImageView) v.findViewById(R.id.ivStar4)).setVisibility(View.GONE);
                    ((ImageView) v.findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                    break;
                case 3:
                    ((ImageView) v.findViewById(R.id.ivStar4)).setVisibility(View.GONE);
                    ((ImageView) v.findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                    break;
                case 4:
                    ((ImageView) v.findViewById(R.id.ivStar5)).setVisibility(View.GONE);
                    break;
                case 5:
                    break;
                default:
                    break;
            }

            ImageView ivFlechaIzq = (ImageView)v.findViewById(R.id.ivFlechaIzq);
            ivFlechaIzq.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MostrarImagenAnterior(v);
                }
            });

            ImageView ivFlechaDer = (ImageView)v.findViewById(R.id.ivFlechaDer);
            ivFlechaDer.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    MostrarImagenSiguiente(v);
                }
            });

        }

        return v;

    }

    private void MostrarImagenAnterior(View v) {

        View ctx = (View)v.getParentForAccessibility();

        ImageView ivImagen1 = (ImageView) ctx.findViewById(R.id.ivImagen1);
        ImageView ivImagen2 = (ImageView) ctx.findViewById(R.id.ivImagen2);
        ImageView ivImagen3 = (ImageView) ctx.findViewById(R.id.ivImagen3);

        if(ivImagen3.getVisibility() == View.VISIBLE){
            ivImagen3.setVisibility(View.INVISIBLE);
            ivImagen2.setVisibility(View.VISIBLE);
        }else{
            if(ivImagen2.getVisibility() == View.VISIBLE){
                ivImagen2.setVisibility(View.INVISIBLE);
                ivImagen1.setVisibility(View.VISIBLE);
            }else{

            }
        }

    }

    private void MostrarImagenSiguiente(View v) {

        View ctx = (View)v.getParentForAccessibility();

        ImageView ivImagen1 = (ImageView) ctx.findViewById(R.id.ivImagen1);
        ImageView ivImagen2 = (ImageView) ctx.findViewById(R.id.ivImagen2);
        ImageView ivImagen3 = (ImageView) ctx.findViewById(R.id.ivImagen3);

        if(ivImagen1.getVisibility() == View.VISIBLE){
            ivImagen1.setVisibility(View.INVISIBLE);
            ivImagen2.setVisibility(View.VISIBLE);
        }else{
            if(ivImagen2.getVisibility() == View.VISIBLE){
                ivImagen2.setVisibility(View.INVISIBLE);
                ivImagen3.setVisibility(View.VISIBLE);
            }else{

            }
        }

    }

    @Override
    public Filter getFilter() {

        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String cadena = String.valueOf(constraint).toUpperCase();
                FilterResults filterResults = new FilterResults();
                ArrayList<Hotel> tempList=new ArrayList<Hotel>();
                hoteles.clear();
                hoteles.addAll(TodosHoteles);
                if(cadena != null && hoteles!=null) {
                    int length=hoteles.size();
                    for (Iterator<Hotel> i = hoteles.iterator(); i.hasNext();) {
                        Hotel item = i.next();
                        if(item.getName().toUpperCase().contains(cadena)) {
                            tempList.add(item);
                        }
                    }
                    filterResults.values = tempList;
                    filterResults.count = tempList.size();
                }
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {

                List<Hotel> filtrado = (ArrayList<Hotel>) results.values;
                List<Hotel> borrar = new ArrayList<Hotel>();

                for (Iterator<Hotel> i = hoteles.iterator(); i.hasNext();) {
                    Hotel item = i.next();
                    if(!filtrado.contains(item)){
                        borrar.add(item);
                    }
                }

                hoteles.removeAll(borrar);

                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };

        return myFilter;
    }

}
