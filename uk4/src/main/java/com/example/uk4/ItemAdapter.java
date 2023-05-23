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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CustomViewHolder> {
    private List<Item> list;

    public ItemAdapter(List<Item> itemList) {
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new CustomViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.count.setText(String.valueOf(list.get(position).getCount()));
        holder.price.setText(String.valueOf(list.get(position).getPrice()));
        holder.done.setChecked(list.get(position).isDone());
        holder.delete.setOnClickListener(v -> {
            list.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView count;
        private CheckBox done;
        private TextView price;
        private Button delete;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.nameView);
            count = view.findViewById(R.id.countView);
            price = view.findViewById(R.id.priceView);
            done = view.findViewById(R.id.checkBox);
            delete = view.findViewById(R.id.delete_btn);
        }
    }
}
