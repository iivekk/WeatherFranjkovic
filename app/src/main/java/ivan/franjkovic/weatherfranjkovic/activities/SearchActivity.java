package ivan.franjkovic.weatherfranjkovic.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ivan.franjkovic.weatherfranjkovic.R;
import ivan.franjkovic.weatherfranjkovic.adapter.MyAdapter;
import ivan.franjkovic.weatherfranjkovic.adapter.OnClickItemListener;
import ivan.franjkovic.weatherfranjkovic.geonames_api_models.Geoname;
import ivan.franjkovic.weatherfranjkovic.mvp.geonames_api.Contract;
import ivan.franjkovic.weatherfranjkovic.mvp.geonames_api.Presenter;

import static ivan.franjkovic.weatherfranjkovic.tools.Tools.Constants.SELECTED_CITY;

public class SearchActivity extends AppCompatActivity implements Contract.View, OnClickItemListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_my_recycler_view)
    RecyclerView mRecyclerView;

    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Presenter mPresenter;

    private boolean emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        settingActionBar();

        mPresenter = new Presenter(this);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_menu).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mPresenter.requestDataFromServer(newText);
                if (newText.length() > 0) {
                    emptyText = false;
                } else {
                    emptyText = true;
                }
                return true;
            }
        });

        return true;
    }

    private void settingActionBar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void setMessage(String message) {

    }

    @Override
    public void setDataToRecyclerView(List<Geoname> resultList) {
        if (emptyText) {
            rvAdapter(this, new ArrayList<>());
        } else {
            rvAdapter(this, resultList);
        }
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseFailure(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    private void rvAdapter(Context context, List<Geoname> list) {
        mAdapter = new MyAdapter(context, list);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onItemClick(String cityName) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(SELECTED_CITY, cityName);
        startActivity(i);
    }
}
