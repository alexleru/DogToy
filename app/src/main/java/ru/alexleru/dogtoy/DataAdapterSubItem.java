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

import java.util.List;

import ru.alexleru.dogtoy.Model.ModelSubItem;
import ru.alexleru.dogtoy.View.SubItemActivity;

public class DataAdapterSubItem extends RecyclerView.Adapter<DataAdapterSubItem.ViewHolder> {
    List<ModelSubItem> modelSubItems;
    SubItemActivity subItemActivity;

    public DataAdapterSubItem(List<ModelSubItem> modelSubItems, SubItemActivity subItemActivity) {
        this.modelSubItems = modelSubItems;
        this.subItemActivity = subItemActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.list_item_subitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ModelSubItem modelSubItem = modelSubItems.get(i);
        int intColor = Color.parseColor("#" + modelSubItem.color);
        viewHolder.background.setColorFilter(intColor, PorterDuff.Mode.MULTIPLY);
        viewHolder.textTitle.setText(modelSubItem.name);
        viewHolder.buttonToCatalogue.setOnClickListener(v ->
                goCatalogueActivity(modelSubItem));
    }

    private void goCatalogueActivity(ModelSubItem modelSubItem) {
        subItemActivity.goCatalogueActivity(modelSubItem);
    }

    @Override
    public int getItemCount() {
        return modelSubItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Drawable background;
        TextView textTitle;
        Button buttonToCatalogue;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSubItemList);
            background = imageView.getBackground();
            textTitle = itemView.findViewById(R.id.textSubItemName);
            buttonToCatalogue = itemView.findViewById(R.id.buttonCatalogue);
        }
    }
}
