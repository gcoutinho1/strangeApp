package coutinhodeveloper.com.strangeapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import coutinhodeveloper.com.strangeapp.R;
import coutinhodeveloper.com.strangeapp.util.ParseErros;

/** Created by Guilherme Coutinho
 *  on 21/09/2018
 */

public class CadastroActivity extends AppCompatActivity {

    private EditText textoUsuario;
    private EditText textoEmail;
    private EditText textoSenha;
    private Button botaoCadastrar;
    private TextView facaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        textoUsuario = findViewById(R.id.text_usuario);
        textoEmail = findViewById(R.id.text_email);
        textoSenha = findViewById(R.id.text_senha);
        facaLogin = findViewById(R.id.text_faca_login);
        botaoCadastrar = findViewById(R.id.button_cadastrar);

        facaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLoginUsuario();
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });
    }

    private void cadastrarUsuario(){
        //criar objeto usuario
        ParseUser usuario = new ParseUser();
        usuario.setUsername(textoUsuario.getText().toString());
        usuario.setEmail(textoEmail.getText().toString());
        usuario.setPassword(textoSenha.getText().toString());

        //salvar dados do usuario
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){ //sucesso
                    Toast.makeText(CadastroActivity.this,"Cadastro feito com sucesso",Toast.LENGTH_LONG).show();
                    abrirLoginUsuario();

                }else { // erro
                    ParseErros parseErros = new ParseErros();
                    String erro = parseErros.getErro(e.getCode());
                    Toast.makeText(CadastroActivity.this,erro + e.getCode(),Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }




}
