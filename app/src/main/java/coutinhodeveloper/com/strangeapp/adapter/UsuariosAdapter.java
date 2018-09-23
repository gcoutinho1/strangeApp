package coutinhodeveloper.com.strangeapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import coutinhodeveloper.com.strangeapp.R;

/** Created by Guilherme Coutinho
 *  on 23/09/2018
 */

public class UsuariosAdapter extends ArrayAdapter<ParseUser> {

    private Context context;
    private ArrayList<ParseUser> usuarios;


    public UsuariosAdapter(@NonNull Context c, @NonNull ArrayList<ParseUser> objects) {
        super(c, 0, objects);
        this.context = c;
        this.usuarios = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        /* verifica se não existe o objeto view criado,
        pois  a view utlizada é armazenada no cache do android e fica na variavel convertView
         */
        if (view==null){
            //inicializa objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //monta a view a partir do xml
            view = inflater.inflate(R.layout.lista_usuarios,parent,false);

        }

        //recuperar elementos para exibição
        TextView username = view.findViewById(R.id.text_username);

        // configurar textView para exibir usuarios
        ParseUser parseUser = usuarios.get(position);
        username.setText(parseUser.getUsername());

        return view;
    }
}
