package coutinhodeveloper.com.strangeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseObject pontuacao = new ParseObject("Pontuacao");
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
        }); 

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


    }
}
