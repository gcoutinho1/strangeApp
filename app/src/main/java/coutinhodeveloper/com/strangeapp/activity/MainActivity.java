package coutinhodeveloper.com.strangeapp.activity;

import android.content.Intent;
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

import coutinhodeveloper.com.strangeapp.R;
import coutinhodeveloper.com.strangeapp.adapter.TabsAdapter;
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
                // fazer algo
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void deslogarUsuario(){
        ParseUser.logOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
