package com.example.starsgallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarViewHolder> implements Filterable {

    private List<Star> stars;
    private List<Star> starsFilter;
    private Context context;
    private NewFilter mFilter;

    public StarAdapter(Context context, List<Star> stars) {
        this.context = context;
        this.stars = stars;
        this.starsFilter = new ArrayList<>(stars);
        this.mFilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public StarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.star_item, parent, false);
        final StarViewHolder holder = new StarViewHolder(v);

        holder.itemView.setOnClickListener(view -> {
            // Charger le layout du popup
            View popup = LayoutInflater.from(context).inflate(R.layout.star_edit_item, null, false);
            final ImageView popupImg  = popup.findViewById(R.id.img);
            final RatingBar popupBar  = popup.findViewById(R.id.ratingBar);
            final TextView  popupIdss = popup.findViewById(R.id.idss);

            // Récupérer les valeurs de la ligne cliquée
            ImageView cellImg = view.findViewById(R.id.img);
            if (cellImg.getDrawable() instanceof BitmapDrawable) {
                Bitmap bmp = ((BitmapDrawable) cellImg.getDrawable()).getBitmap();
                popupImg.setImageBitmap(bmp);
            }
            popupBar.setRating(((RatingBar) view.findViewById(R.id.stars)).getRating());
            popupIdss.setText(((TextView) view.findViewById(R.id.ids)).getText().toString());

            // Afficher la boîte de dialogue
            new AlertDialog.Builder(context)
                    .setTitle("Notez :")
                    .setMessage("Donner une note entre 1 et 5 :")
                    .setView(popup)
                    .setPositiveButton("Valider", (d, which) -> {
                        float newRating = popupBar.getRating();
                        int   starId    = Integer.parseInt(popupIdss.getText().toString());
                        Star star = StarService.getInstance().findById(starId);
                        if (star != null) {
                            star.setStar(newRating);
                            StarService.getInstance().update(star);
                            notifyItemChanged(holder.getAdapterPosition());
                        }
                    })
                    .setNegativeButton("Annuler", null)
                    .show();
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StarViewHolder holder, int position) {
        Star s = starsFilter.get(position);
        holder.name.setText(s.getName().toUpperCase());
        holder.stars.setRating(s.getStar());
        holder.idss.setText(String.valueOf(s.getId()));
        Glide.with(context)
                .asBitmap()
                .load(s.getImg())
                .apply(new RequestOptions().override(100, 100))
                .into(holder.img);
    }

    @Override
    public int getItemCount() { return starsFilter.size(); }

    @Override
    public Filter getFilter() { return mFilter; }

    // ── ViewHolder ──────────────────────────────────────────────────────────
    public static class StarViewHolder extends RecyclerView.ViewHolder {
        TextView  idss, name;
        ImageView img;
        RatingBar stars;

        public StarViewHolder(@NonNull View itemView) {
            super(itemView);
            idss  = itemView.findViewById(R.id.ids);
            img   = itemView.findViewById(R.id.img);
            name  = itemView.findViewById(R.id.name);
            stars = itemView.findViewById(R.id.stars);
        }
    }

    // ── Filtre ──────────────────────────────────────────────────────────────
    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;

        public NewFilter(RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Star> filtered = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filtered.addAll(stars);
            } else {
                String pattern = charSequence.toString().toLowerCase().trim();
                for (Star p : stars) {
                    if (p.getName().toLowerCase().startsWith(pattern))
                        filtered.add(p);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            results.count  = filtered.size();
            return results;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            starsFilter = (List<Star>) filterResults.values;
            mAdapter.notifyDataSetChanged();
        }
    }
}