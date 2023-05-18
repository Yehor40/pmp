package com.example.uk4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(itemList.get(position).getName());
        holder.count.setText(String.valueOf(itemList.get(position).getCount()));
        holder.price.setText(String.valueOf(itemList.get(position).getPrice()));
        holder.done.setChecked(itemList.get(position).isDone());
        holder.delete.setOnClickListener(v -> {
            itemList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView count;
        private CheckBox done;
        private TextView price;
        private Button delete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.nameView);
            count = view.findViewById(R.id.countView);
            price = view.findViewById(R.id.priceView);
            done = view.findViewById(R.id.checkBox);
            delete = view.findViewById(R.id.delete_btn);
        }
    }
}
