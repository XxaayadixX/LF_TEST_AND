package lafourchette.com.lafourchette;

/**
 * Created by Ali on 13/02/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EventViewHolder>{

    private Context             context;
    private List<Restaurant>    restaurantList;

    public RVAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        protected TextView  restaurant_name;
        protected ImageView picture;
        protected View      overflow;

        public EventViewHolder(View view) {
            super(view);
            restaurant_name = (TextView) view.findViewById(R.id.title_restaurant_card);
            picture = (ImageView) view.findViewById(R.id.picture_restaurant_card);
            overflow = view.findViewById(R.id.event_more);
        }
    }

    // Clear the list
    public void clear() {
        restaurantList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Restaurant> list) {
        restaurantList = list;
        notifyDataSetChanged();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cardview, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);

        holder.restaurant_name.setText(restaurant.getName());
        Picasso.with(context).load(restaurant.getPicture()).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

