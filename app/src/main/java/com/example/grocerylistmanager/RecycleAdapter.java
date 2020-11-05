package com.example.grocerylistmanager;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {


    ArrayList<String> titleData;
    Context context;

    public RecycleAdapter(Context context, ArrayList<String> titles){
        this.context = context;
        titleData = titles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(titleData.get(position));
    }

    @Override
    public int getItemCount() {

        return titleData.size();
    }

    public void remove(int pos){
        titleData.remove(pos);
        notifyDataSetChanged();
    }

    public void renameItem(String name, int pos){
        titleData.set(pos, name);
        notifyDataSetChanged();
    }







    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView title;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.listNameFirstAct);
            cardView = itemView.findViewById(R.id.cardParentCardView);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Options");
            contextMenu.add(this.getAdapterPosition(),R.id.navigateList, Menu.NONE, "Navigate List");
            contextMenu.add(this.getAdapterPosition(), R.id.renameList, Menu.NONE, "Rename List");
            contextMenu.add(this.getAdapterPosition(),R.id.deleteList, Menu.NONE, "Delete List");

        }
    }


}
