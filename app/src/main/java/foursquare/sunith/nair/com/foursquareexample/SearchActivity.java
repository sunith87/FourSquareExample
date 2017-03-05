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
import foursquare.sunith.nair.com.foursquareexample.view.SearchDataListParcelable;

public class SearchActivity extends AppCompatActivity implements SearchUIUpdater {

    private static final String SEARCH_DATA_LIST = "foursquare.sunith.nair.com.foursquareexample.SearchActivity.SEARCH_DATA_LIST";
    public static final String USE_SEARCHBAR_TO_SEARCH_FOR_VENUES = "Use Searchbar to search for venues";
    private RecyclerView mRecyclerView;
    private View mRootView;
    private SearchView mSearchView;
    private SearchDataListParcelable mSearchDataParcelable;


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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SEARCH_DATA_LIST, mSearchDataParcelable);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SearchDataListParcelable parcelable = savedInstanceState.getParcelable(SEARCH_DATA_LIST);
        handleSuccess(parcelable.getDataList(), parcelable.getQueryPlaced());
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
    public void handleSuccess(List<SearchData> searchDataList, String queryPlaced) {
        mSearchDataParcelable = new SearchDataListParcelable(searchDataList, queryPlaced);

        if (searchDataList != null && !searchDataList.isEmpty()) {
            SearchAdapter adapter = new SearchAdapter(searchDataList, queryPlaced);
            mRecyclerView.setAdapter(adapter);
        } else {
            mRecyclerView.setAdapter(null);
            Snackbar.make(mRootView, USE_SEARCHBAR_TO_SEARCH_FOR_VENUES, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void handleError(String message) {
        Snackbar.make(mRootView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void searchStarted(String query) {
        mSearchView.clearFocus();
        mRecyclerView.setAdapter(null);
        Snackbar.make(mRootView, "Searching for venues in " + query, Snackbar.LENGTH_LONG).show();
    }
}
