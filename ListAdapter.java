package com.mydailyexp.anurag.mydailyexp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydailyexp.anurag.mydailyexp.AbstractClasses.CardListDataClass;
import com.mydailyexp.anurag.mydailyexp.R;
import com.mydailyexp.anurag.mydailyexp.ViewHolders.ListViewHolder;

import java.util.List;

/**
 * Created by Anurag on 7/18/2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private List<CardListDataClass> list;
    private final Context mContext ;

    public ListAdapter(Context context, List<CardListDataClass> l){
        mContext = context;
        list = l;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).inflate(R.layout.listitem,null);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        CardListDataClass cardListDataClass = list.get(position);
        holder.vtitle.setText(cardListDataClass.getTitle());
        holder.vdetails.setText(cardListDataClass.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
