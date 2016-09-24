package com.mydailyexp.anurag.mydailyexp.Dialogbox;

/**
 * Created by Anurag on 7/22/2016.
 */

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mydailyexp.anurag.mydailyexp.AbstractClasses.CardListDataClass;
import com.mydailyexp.anurag.mydailyexp.MainActivityFragment;
import com.mydailyexp.anurag.mydailyexp.R;
import com.mydailyexp.anurag.mydailyexp.database.createDb;

import java.util.ArrayList;
import java.util.List;

public class MyDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    List<CardListDataClass> listDataClasses = new ArrayList<>();
    Fragment fragment;
    FragmentTransaction ft;
    List<CardListDataClass> list;
    UserNameListener userNameListener;
    private EditText mEditText, mEditText1, mEditText2;

    // Empty constructor required for DialogFragment
    public MyDialogFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_item_layout, container);
        mEditText = (EditText) view.findViewById(R.id.detailEdit);
        mEditText1 = (EditText) view.findViewById(R.id.expEdit);
        mEditText2 = (EditText) view.findViewById(R.id.expExtra);

        CardView submit = (CardView) view.findViewById(R.id.submitDetail);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String det, exp, extra;
                det = mEditText.getText().toString();
                exp = mEditText1.getText().toString();
                extra = mEditText2.getText().toString();
                createDb createDb = new createDb(view.getContext());
                createDb.dbopen();
                createDb.createEntry(det, exp);
                createDb.close();
                listDataClasses.add(new CardListDataClass(det, exp));
                getDialog().dismiss();
            }
        });

        // set this instance as callback for editor action
        mEditText.setOnEditorActionListener(this);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle("Please enter details");
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            userNameListener = (UserNameListener) getActivity();
        } catch (ClassCastException c) {
            Log.e("ClassCastException", "yeah");
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // Return input text to activity
        userNameListener.onFinishUserDialog(mEditText.getText().toString(), mEditText1.getText().toString());
        this.dismiss();
        return true;
    }

    public interface UserNameListener {
        void onFinishUserDialog(String detail, String exp);
    }
}
