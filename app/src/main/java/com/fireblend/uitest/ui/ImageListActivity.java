package com.fireblend.uitest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fireblend.uitest.R;
import com.fireblend.uitest.entities.Photo;
import com.fireblend.uitest.service.GestorServicio;
import com.fireblend.uitest.service.ServicioPhotos;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageListActivity extends AppCompatActivity {

    GridView list;
    List<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        list = (GridView)findViewById(R.id.lista_imagenes);
        obtenerPhotos();
    }

    private void obtenerPhotos(){
        //Se obtiene la referencia singleton desde el gestor.
        ServicioPhotos servicio = GestorServicio.obtenerServicioPhotos();
        //Se llama al metodo definido en el servicio para obtener los posts.
        servicio.getAllPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                //Si es exitosa, recuperamos la lista recibida de response.body()
                photos = response.body();
                //y llamamos al metodo para mostrar la lista
                setupList();
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                //Si no, se muestra un error
                Toast.makeText(ImageListActivity.this,
                        "Error al interactuar con el servicio",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupList() {
        //Le asignamos a la lista un nuevo BaseAdapter, implementado a continuación
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return photos.size();
            }

            @Override
            public Object getItem(int position) {
                return photos.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            //Devuelve la vista que corresponde a cada elemento de la lista
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = getLayoutInflater();
                View row = inflater.inflate(R.layout.photo_item, parent, false);

                Photo current = photos.get(position);
                ImageView image = (ImageView) row.findViewById(R.id.imageView_picture);

                Picasso.get()
                        .load(current.url)
                        .into(image);
                return row;
            }
        });

    }
}
