package foursquare.sunith.nair.com.foursquareexample;

import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.download.SearchData;
import foursquare.sunith.nair.com.foursquareexample.view.SearchAdapter;

public class SearchActivity extends AppCompatActivity implements SearchUIUpdater {

    private RecyclerView mRecyclerView;
    private View mRootView;
    private SearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.search_list);
        mRootView = (View) findViewById(R.id.rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.search_menu);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        mSearchView.setOnQueryTextListener(getOnQueryTextListener());
        return super.onCreateOptionsMenu(menu);

    }

    private SearchView.OnQueryTextListener getOnQueryTextListener() {
        SearchView.OnQueryTextListener onQueryTextListener = getSearchController();
        return onQueryTextListener;
    }


    public SearchController getSearchController() {
        FourSqaureApplication application = (FourSqaureApplication) getApplication();
        return application.getSearchController(this);
    }


    @Override
    public void handleSuccess(List<SearchData> searchDataList) {
        mRecyclerView.setAdapter(new SearchAdapter(searchDataList));
    }

    @Override
    public void handleError(String message) {
        Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public SearchView getSearchView() {
        return mSearchView;
    }
}
