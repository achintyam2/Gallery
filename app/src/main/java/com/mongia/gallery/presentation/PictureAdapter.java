package com.mongia.gallery.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mongia.gallery.R;
import com.mongia.gallery.model.Photo;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private List<Photo> photos;

    public PictureAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    public void addData(List<Photo> photos){
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_photo, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo photo = photos.get(holder.getAdapterPosition());
        holder.image_id.setText("Id : ".concat(photo.getImageId()));
        Picasso.get().load(photo.getImageUrl()).placeholder(R.drawable.no_preview).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView image_id;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image_id = itemView.findViewById(R.id.image_id);
            image = itemView.findViewById(R.id.image);
        }
    }
}
