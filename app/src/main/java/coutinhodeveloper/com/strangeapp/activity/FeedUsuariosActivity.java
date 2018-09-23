package coutinhodeveloper.com.strangeapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import coutinhodeveloper.com.strangeapp.R;
import coutinhodeveloper.com.strangeapp.adapter.HomeAdapter;

/** Created by Guilherme Coutinho
 *  on 23/09/2018
 */

public class FeedUsuariosActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String userName;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuarios);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // recuperando dados enviados da intent
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");

        // configurando toolbar
        toolbar = findViewById(R.id.toolbar_feed_usuario);
        toolbar.setTitle(userName);
        toolbar.setTitleTextColor(getResources().getColor(R.color.Preto));
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);

        // configurando listView e adapter
        postagens = new ArrayList<>();
        listView = findViewById(R.id.list_feed_usuario);
        adapter = new HomeAdapter(getApplicationContext(),postagens);
        listView.setAdapter(adapter);

        //recuperar dados
        getPostagens();


    }

    public void getPostagens(){

        //recupera img das postagens
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Imagem");
        query.whereEqualTo("username",userName);
        query.orderByAscending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e==null){//ok
                    //verificando se existe postagens
                    if (objects.size() > 0){
                        postagens.clear();
                        for (ParseObject parseObject : objects){
                            postagens.add(parseObject);

                        }
                            adapter.notifyDataSetChanged();
                    }

                }else {//error
                    Toast.makeText(getApplicationContext(),"Erro ao recuperar o feed!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}
