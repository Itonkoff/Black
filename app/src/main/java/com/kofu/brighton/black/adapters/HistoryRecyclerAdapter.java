package com.kofu.brighton.black.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kofu.brighton.black.R;
import com.kofu.brighton.black.dtos.HistoryDto;

import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final LayoutInflater inflater;
    private final List<HistoryDto> historyList;

    public HistoryRecyclerAdapter(Context context, List<HistoryDto> historyList) {
        this.context = context;
        this.historyList = historyList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View historyItem = inflater.inflate(R.layout.history_list_item, parent, false);
        return new ViewHolder(historyItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryDto history = historyList.get(position);
        holder.date.setText(history.date.toString());
        holder.value.setText(String.valueOf(history.predicted_value));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView date;
        private final TextView value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.tv_date);
            value = itemView.findViewById(R.id.tv_value);
        }
    }
}
