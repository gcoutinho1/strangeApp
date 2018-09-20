package coutinhodeveloper.com.strangeapp;

import android.app.Application;
import android.util.Log;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class StarterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        //Habilite armazenamento local
        Parse.enableLocalDatastore(this);
        //cfg app
         Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("5qszhsU9hxzOGkMdNhNhq5Vv5p2HJNlI0NYKmI7X")
            .clientKey("0tbV2qoLuLzyNOUBWdKL29Oxr3sAjubbRSEmqjdm")
            .server("https://parseapi.back4app.com")
            //.server("strangeapp.back4app.io")
            .build()



        );

        ParseObject pontuacao = new ParseObject("Pontuacao");
        pontuacao.put("pontos",200);
        pontuacao.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    Log.i("testeExecucao","Salvo com sucesso!");

                }else {
                    Log.i("testeExecucao","Falha ao salvar!");
                }
            }
        });


    }
}
