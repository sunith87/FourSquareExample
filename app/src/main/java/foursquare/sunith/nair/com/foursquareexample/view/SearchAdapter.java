package foursquare.sunith.nair.com.foursquareexample.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import foursquare.sunith.nair.com.foursquareexample.R;
import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {


    private final List<SearchData> mDataList;

    public SearchAdapter(List<SearchData> dataList) {
        mDataList = dataList;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.venues_itemview, parent, false);
        return new SearchViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
