package coutinhodeveloper.com.strangeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ParseObject pontuacao = new ParseObject("Pontuacao");
        pontuacao.put("nome", "Gerald");
        pontuacao.put("pontos", 150);
        pontuacao.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){ //significa que não tem erro
                    Log.i("salvarPontos", "dados salvos");
                }else {
                    Log.i("salvarPontos", "erro ao salvar");
                }
            }
        }); */

        /*ParseQuery<ParseObject> consulta = ParseQuery.getQuery("Pontuacao");
        consulta.getInBackground("PTZBjb6bYi", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {

                if (e==null){
                    object.put("pontos", 333);
                    object.saveInBackground();
                }else {
                    Log.i("consulta", "erro na consulta");
                }
            }
        });  */


        ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Pontuacao");
        // aplicando filtros na listagem dos objetos
        //filtro.whereGreaterThan("pontos", 800);
        //filtro.whereGreaterThanOrEqualTo("pontos", 777);
        //filtro.whereLessThanOrEqualTo("pontos",777);
        //filtro.whereEndsWith("nome","gu");
        //filtro.whereStartsWith("nome","G");
        //filtro.addAscendingOrder("pontos");
        //filtro.setLimit(1);

        //listar dados
        filtro.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null){ //efetuado listagem
                    for (ParseObject object : objects){
                        Log.i("listagem", "objetos - Nome: " + object.get("nome") + " ponto: " + object.get("pontos"));


                    }

                }else {
                    Log.i("listagem", "erro na listagem -" + e.getMessage());
                }
            }
        });


    }
}
