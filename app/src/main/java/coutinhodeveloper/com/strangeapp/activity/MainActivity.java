package coutinhodeveloper.com.strangeapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import coutinhodeveloper.com.strangeapp.R;
import coutinhodeveloper.com.strangeapp.adapter.TabsAdapter;
import coutinhodeveloper.com.strangeapp.fragments.HomeFragment;
import coutinhodeveloper.com.strangeapp.util.SlidingTabLayout;

/** Created by Guilherme Coutinho
 *  on 19/09/2018
 */

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarprincipal;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // cfg toolbar
        toolbarprincipal = findViewById(R.id.toolbar_principal);
        toolbarprincipal.setLogo(R.drawable.strangebar);
        setSupportActionBar(toolbarprincipal);

        // cfg abas
        slidingTabLayout = findViewById(R.id.sliding_tab_main);
        viewPager = findViewById(R.id.view_pager_main);

        // cfg adapter
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(tabsAdapter);
        slidingTabLayout.setCustomTabView(R.layout.tab_view,R.id.text_item_tab);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.CinzaEscuro));
        slidingTabLayout.setViewPager(viewPager);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_exit:
                //fazer algo
                deslogarUsuario();
                return true;
            case R.id.action_settings:
                // fazer algo
                return true;
            case R.id.action_share:
                compartilharFoto();
                // fazer algo
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void compartilharFoto(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // testando processo de retorno dos dados
        if (requestCode==1 && resultCode==RESULT_OK && data != null){

            //recuperar local do recurso
            Uri localImagemSelecionado = data.getData();

            //recupera a imagem do local que foi selecionada
            try {
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(),localImagemSelecionado);

                //comprimir formato png
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG,75,stream);
                // criar array de bytes
                byte[] byteArray = stream.toByteArray();

                //mudando nome do arquivo no parse
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmyyyyhhmmss");
                String nomeImagem = dateFormat.format(new Date());

                //  criar arquivo do formato proprio do parse
                ParseFile arquivoParse = new ParseFile(nomeImagem +"imagem.png",byteArray);

                // monta objeto pra salvar no parse
                ParseObject parseObject = new ParseObject("Imagem");
                parseObject.put("username",ParseUser.getCurrentUser().getUsername());
                parseObject.put("imagem",arquivoParse);

                // salvando os dados
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e==null){//sucesso
                            Toast.makeText(getApplicationContext(),"Sua imagem foi publicada!",Toast.LENGTH_LONG).show();
                        // atualizando a listagem de items do fragmento HomeFragment
                        TabsAdapter adapterNovo = (TabsAdapter) viewPager.getAdapter();
                        HomeFragment homeFragmentNovo = (HomeFragment) adapterNovo.getFragment(0);
                        homeFragmentNovo.atualizaPostagens();


                        }else {//erro
                            Toast.makeText(getApplicationContext(),"Erro ao publicar sua imagem, tente novamente",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void deslogarUsuario(){
        ParseUser.logOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
