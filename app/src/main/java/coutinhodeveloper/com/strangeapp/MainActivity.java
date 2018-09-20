package coutinhodeveloper.com.strangeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;

/** Created by Guilherme Coutinho
 *  on 19/09/2018
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* // cadastro de usuarios
        ParseUser usuario = new ParseUser();
        usuario.setUsername("guilherme");
        usuario.setPassword("123456");
        usuario.setEmail("guilherme@gmail.com");

        // logar
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    Log.i ("cadastrarUsuario", "sucesso ao cadastrar ");
                }else {
                    Log.i ("cadastrarUsuario", "erro ao cadastrar - " + e.getMessage());
                }
            }
        }); */

        /** verificar usuario
         *
         */
        /*ParseUser.logOut();
        if (ParseUser.getCurrentUser() != null){
            Log.i ("loginUsuario", "logado ");

        }else {
            Log.i ("loginUsuario", "deslogado ");

        } */

        /* ParseUser.logInInBackground("guilherme", "123456", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e==null){// logado
                    Log.i("verificacaoLogin", "login com sucesso");

                }else {
                    Log.i("verificacaoLogin", "login falhou" + e.getMessage());

                }
            }
        }); */


    }
}
