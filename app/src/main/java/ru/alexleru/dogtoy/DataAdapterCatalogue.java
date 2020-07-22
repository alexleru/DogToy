package ru.alexleru.dogtoy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.alexleru.dogtoy.Model.ModelCatalogue;
import ru.alexleru.dogtoy.View.InterfaceActivity;

public class DataAdapterCatalogue extends RecyclerView.Adapter<DataAdapterCatalogue.ViewHolder> {
    List<ModelCatalogue> modelCatalogues;
    InterfaceActivity interfaceActivity;


    public DataAdapterCatalogue(List<ModelCatalogue> modelCatalogues, InterfaceActivity interfaceActivity) {
        this.modelCatalogues = modelCatalogues;
        this.interfaceActivity = interfaceActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.list_item_games, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelCatalogue modelCatalogue = modelCatalogues.get(i);
        Context context = viewHolder.itemView.getContext();
        viewHolder.textName.setText(modelCatalogue.name);
        Picasso.get()
                .load("http://alexleru.h1n.ru/dogtoy/catalog/drawable-xxxhdpi/" + modelCatalogue.picture + ".png")
                .into(viewHolder.imageItem);

        Drawable imageHeartDrawable = viewHolder.imageHeart.getDrawable().mutate();
        int colorCondition;
        if (DogToyApp.isLike(modelCatalogue.id, context)) {
            colorCondition = Color.RED;
        } else {
            colorCondition = Color.GRAY;
        }
        imageHeartDrawable.setColorFilter(colorCondition, PorterDuff.Mode.MULTIPLY);
        viewHolder.button.setOnClickListener(v ->
            interfaceActivity.goIntent(modelCatalogue));

        viewHolder.imageHeart.setOnClickListener(v -> {
            boolean value = DogToyApp.setLike(modelCatalogue.id, context);
            if (value) imageHeartDrawable.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            else imageHeartDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        });
    }

    void initDrawable(){

    }

    void changeDrawable(){

    }

    @Override
    public int getItemCount() {
        return modelCatalogues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageItem;
        TextView textName;
        Button button;
        ImageView imageHeart;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageItem = itemView.findViewById(R.id.imageItem);
            textName = itemView.findViewById(R.id.textName);
            button = itemView.findViewById(R.id.buttonCatalogue);
            imageHeart = itemView.findViewById(R.id.imageHeart);
        }
    }

}
