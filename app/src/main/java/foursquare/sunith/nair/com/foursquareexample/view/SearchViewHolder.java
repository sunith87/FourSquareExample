package foursquare.sunith.nair.com.foursquareexample.view;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import foursquare.sunith.nair.com.foursquareexample.R;
import foursquare.sunith.nair.com.foursquareexample.download.SearchData;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public SearchViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(SearchData data) {
        TextView name = (TextView) itemView.findViewById(R.id.venue_name);
        TextView details = (TextView) itemView.findViewById(R.id.venue_otherDetails);

        name.setText(data.getName());
        String otherDetails = data.getOtherDetails();
        if (!TextUtils.isEmpty(otherDetails)) {
            details.setText(otherDetails);
        } else {
            details.setVisibility(View.GONE);
        }
    }
}
