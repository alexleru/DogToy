package ru.alexleru.dogtoy;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.alexleru.dogtoy.Model.ModelPrecautionary;

public class DataAdapterPrecautionary extends RecyclerView.Adapter<DataAdapterPrecautionary.ViewHolder> {
    private List<ModelPrecautionary> modelPrecautionaries;

    public DataAdapterPrecautionary(List<ModelPrecautionary> modelPrecautionaries) {
        this.modelPrecautionaries = modelPrecautionaries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_precautionary, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelPrecautionary modelPrecautionary = modelPrecautionaries.get(i);
        int intColor = Color.parseColor("#" + modelPrecautionary.colors);
        viewHolder.background.setColorFilter(intColor, PorterDuff.Mode.MULTIPLY);
        Picasso.get()
                .load("http://alexleru.h1n.ru/dogtoy/preocationary/drawable-xxxhdpi/" + modelPrecautionary.picture +".png")
                .into(viewHolder.imageView);
        viewHolder.textTitle.setText(modelPrecautionary.name);
        viewHolder.textPrecautionary.setText(modelPrecautionary.description);
    }

    @Override
    public int getItemCount() {
        return modelPrecautionaries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Drawable background;
        TextView textTitle, textPrecautionary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagePrecautionaryList);
            background = imageView.getBackground();
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrecautionary = itemView.findViewById(R.id.textPrecautionary);
        }
    }
}
