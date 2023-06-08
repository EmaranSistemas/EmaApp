package com.emransac.emaapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.emransac.emaapp.Entity.Summary;
import com.emransac.emaapp.R;

import java.util.ArrayList;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Summary> summaryArrayList;
    private int selectedItem = RecyclerView.NO_POSITION;
    private OnItemClickListener onItemClickListener;

    public SummaryAdapter(Context context, ArrayList<Summary> summaryArrayList) {
        this.context = context;
        this.summaryArrayList = summaryArrayList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public int getSelectedPosition() {
        return selectedItem;
    }

    public void setSelectedPosition(int position) {
        selectedItem = position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Summary summary = summaryArrayList.get(position);

        holder.txtid.setText(summary.getId());
        holder.txtProducto.setText(summary.getProducto());
        holder.txtTienda.setText(summary.getTienda());
        holder.txtDiferencia.setText(summary.getDiferencia());
        holder.txtStock.setText(summary.getStock());
        holder.fecha.setText(summary.getFecha());

        Glide.with(context)
                .load(summary.getImg())
                .into(holder.imageView);

        if (selectedItem == position) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.selected_color));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    int previousSelectedItem = selectedItem;
                    selectedItem = position;
                    notifyItemChanged(previousSelectedItem);
                    notifyItemChanged(position);
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return summaryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtid;
        TextView txtTienda;
        TextView txtProducto;
        TextView txtDiferencia;
        TextView txtStock;
        TextView fecha;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtid = itemView.findViewById(R.id.id);
            txtTienda = itemView.findViewById(R.id.tienda);
            txtProducto = itemView.findViewById(R.id.producto);
            txtDiferencia = itemView.findViewById(R.id.diferencia);
            txtStock = itemView.findViewById(R.id.stock);
            fecha = itemView.findViewById(R.id.fecha);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}