package com.example.retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView title;
    Button btnGetComic;
    XkcdService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        btnGetComic = findViewById(R.id.btnGetComic);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://xkcd.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(XkcdService.class);


        btnGetComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Comic> call = service.getComic(new Random().nextInt(100));

                call.enqueue(new Callback<Comic>() {
                    @Override
                    public void onResponse(Call<Comic> call, Response<Comic> response) {
                            Comic comic = response.body();
                          try {
                              if (comic != null) {
                                  title.setText(comic.getTitle());
                                  Picasso.with(MainActivity.this)
                                          .load(comic.getImg())
                                          .into(imageView);
                              }
                          }catch (Exception e){
                              Log.e("Main activity" , e.toString());
                          }
                    }

                    @Override
                    public void onFailure(Call<Comic> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Ocurrio un error con la API", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}