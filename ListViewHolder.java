package com.mydailyexp.anurag.mydailyexp.ViewHolders;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mydailyexp.anurag.mydailyexp.Dialogbox.MyDetailDialogFragment;
import com.mydailyexp.anurag.mydailyexp.R;


/**
 * Created by Anurag on 7/19/2016.
 */
public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView vtitle, vdetails;

    public ListViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        vtitle = (TextView) itemView.findViewById(R.id.detail);
        vdetails = (TextView) itemView.findViewById(R.id.expense);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = ((Activity) v.getContext()).getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");

        Bundle bundle = new Bundle();
        bundle.putString("detail", vtitle.getText().toString());
        bundle.putString("exp", vdetails.getText().toString());

        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        MyDetailDialogFragment editNameDialog = new MyDetailDialogFragment();
        editNameDialog.setArguments(bundle);
        editNameDialog.show(manager, "fragment_edit_name");

        Toast.makeText(v.getContext(), "position: " + getPosition() + vtitle.getText(), Toast.LENGTH_SHORT).show();
    }
}
