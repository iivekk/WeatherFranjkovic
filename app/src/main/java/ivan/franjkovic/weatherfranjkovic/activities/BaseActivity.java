package ivan.franjkovic.weatherfranjkovic.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ivan.franjkovic.weatherfranjkovic.R;
import ivan.franjkovic.weatherfranjkovic.adapter.WeatherListAdapter;
import ivan.franjkovic.weatherfranjkovic.dialog.SearchDialog;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    protected FrameLayout contentFrame;
    protected TextView tvLocationBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initWidgets();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView rv = findViewById(R.id.loc_list);
        final WeatherListAdapter adapter = new WeatherListAdapter(this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initWidgets() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        contentFrame = findViewById(R.id.content_frame);
        tvLocationBase = findViewById(R.id.tv_location);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.add_location:
                /*SearchDialog searchDialog = new SearchDialog();
                searchDialog.show(getSupportFragmentManager(), "show dialog");*/
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
