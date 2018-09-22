package coutinhodeveloper.com.strangeapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import coutinhodeveloper.com.strangeapp.R;
import coutinhodeveloper.com.strangeapp.adapter.UsuariosAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuariosFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter<ParseUser> adapter;
    private ArrayList<ParseUser> usuarios;
    private ParseQuery<ParseUser> query;


    public UsuariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuarios, container, false);
        //montar listView e adapter
        usuarios = new ArrayList<ParseUser>();
        listView = view.findViewById(R.id.list_usuarios);
        adapter = new UsuariosAdapter(getActivity(),usuarios);
        listView.setAdapter(adapter);

        // recupera os usuarios
        getUsuarios();






        return view;
    }

    private void getUsuarios(){

        // recupera lista de usuarios do parse
        query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if (e==null){//ok
                    if (objects.size() > 0){ //verifica se tem usuarios

                        usuarios.clear();
                        for (ParseUser parseUser : objects){
                            usuarios.add(parseUser);
                        }
                        adapter.notifyDataSetChanged();
                    }

                }else {//error
                    e.printStackTrace();

                }
            }
        });

    }

}
