package ivan.franjkovic.weatherfranjkovic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ivan.franjkovic.weatherfranjkovic.R;
import ivan.franjkovic.weatherfranjkovic.geonames_api_models.Geoname;
import ivan.franjkovic.weatherfranjkovic.mvp.geonames_api.Contract;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Geoname> autocompleteSearchResults;
    private LayoutInflater layoutInflater;
    private OnClickItemListener mOnClickItemListener;

    public MyAdapter(Context context, List<Geoname> list) {
        this.autocompleteSearchResults = list;
        this.layoutInflater = layoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Geoname result = autocompleteSearchResults.get(position);
        holder.tvCity.setText(result.getName());
        holder.tvCountry.setText(result.getCountryName() + " (" + result.getAdminName1() + ")");
    }

    @Override
    public int getItemCount() {
        return autocompleteSearchResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvCity)
        TextView tvCity;

        @BindView(R.id.tvCountry)
        TextView tvCountry;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnClickItemListener != null) {
                mOnClickItemListener.onItemClick(autocompleteSearchResults.get(getAdapterPosition()).getName());
            }
        }
    }

    public void setClickListener(OnClickItemListener listener) {
        this.mOnClickItemListener = listener;
    }

}
