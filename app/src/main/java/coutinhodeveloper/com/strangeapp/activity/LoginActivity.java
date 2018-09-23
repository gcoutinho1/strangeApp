package coutinhodeveloper.com.strangeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import coutinhodeveloper.com.strangeapp.R;

/** Created by Guilherme Coutinho
 *  on 21/09/2018
 */

public class LoginActivity extends AppCompatActivity {
    private EditText editLoginUsuario;
    private EditText editLoginSenha;
    private Button botaoLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editLoginUsuario = findViewById(R.id.edit_login_usuario);
        editLoginSenha = findViewById(R.id.edit_login_senha);
        botaoLogar = findViewById(R.id.botao_logar);

        //ParseUser.logOut();
        //verificar usuario logado
        verificarUsuarioLogado();

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editLoginUsuario.getText().toString();
                String senha = editLoginSenha.getText().toString();
                verificarLogin(usuario,senha);
            }
        });
    }
    private void verificarLogin(String usuario, String senha){
        ParseUser.logInInBackground(usuario, senha, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e==null){//sucesso login
                    Toast.makeText(LoginActivity.this,"Login com sucesso",Toast.LENGTH_LONG).show();
                    abrirAreaPrincipal();
                }else{//erro login
                    Toast.makeText(LoginActivity.this,"Falha ao logar, " + e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
        startActivity(intent);
    }

    private void verificarUsuarioLogado(){
        if (ParseUser.getCurrentUser() != null){
            //enviar usuario pra tela principal
            abrirAreaPrincipal();

        }
    }

    private void abrirAreaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
