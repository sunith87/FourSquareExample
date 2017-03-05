package foursquare.sunith.nair.com.foursquareexample.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.R;
import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {


    static final int FIRST_ITEM_VIEW_TYPE = 10;
    private final List<SearchData> mDataList;
    private final String mQuery;

    public SearchAdapter(List<SearchData> dataList, String queryPlaced) {
        mDataList = dataList;
        mQuery = queryPlaced;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate;
        if (viewType == FIRST_ITEM_VIEW_TYPE) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            inflate = layoutInflater.inflate(R.layout.venues_searchterm, parent, false);
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            inflate = layoutInflater.inflate(R.layout.venues_itemview, parent, false);
        }

        return new SearchViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        String message = getMessage();
        if (holder.getItemViewType() == FIRST_ITEM_VIEW_TYPE){
            holder.bind(message);
        }else{
            holder.bind(mDataList.get(position-1));
        }
    }

    @NonNull
    private String getMessage() {
        return "Found " + mDataList.size() + " venues for " + mQuery;
    }

    @Override
    public int getItemCount() {
        int totalSize = mDataList.size() + 1;
        return totalSize;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return FIRST_ITEM_VIEW_TYPE;
        } else {
            return super.getItemViewType(position);
        }
    }
}
