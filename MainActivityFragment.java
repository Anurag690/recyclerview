package com.mydailyexp.anurag.mydailyexp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydailyexp.anurag.mydailyexp.AbstractClasses.CardListDataClass;
import com.mydailyexp.anurag.mydailyexp.Adapter.ListAdapter;
import com.mydailyexp.anurag.mydailyexp.database.createDb;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    static public List<CardListDataClass> list = null;
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleview1);
        list = new ArrayList<CardListDataClass>();
        //recyclerView.setHasFixedSize(true);

        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        list = new ArrayList<CardListDataClass>();
//        ItemTouchHelper swiper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.LEFT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                list.remove(viewHolder.getPosition());
//            }
//        });
//        swiper.attachToRecyclerView(recyclerView);
        createlist(v.getContext());
        listAdapter = new ListAdapter(v.getContext(), list);
        listAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(listAdapter);
        return v;
    }

    private void createlist(Context context) {
        createDb.DataForList[] dataForLists;
        createDb fetchDb = new createDb(context);
        fetchDb.dbopen();
        dataForLists = fetchDb.getData();
        fetchDb.close();
        if (dataForLists != null)
            for (int i = 0; i < dataForLists.length; i++) {
                list.add(new CardListDataClass(dataForLists[i].sdetails, dataForLists[i].sExp));
            }
    }
}
