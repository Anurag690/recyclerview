package com.mydailyexp.anurag.mydailyexp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mydailyexp.anurag.mydailyexp.AbstractClasses.CardListDataClass;
import com.mydailyexp.anurag.mydailyexp.Dialogbox.MyDialogFragment;
import com.mydailyexp.anurag.mydailyexp.database.createDb;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyDialogFragment.UserNameListener {
    CardView cardView;
    createDb createDb = new createDb(this);
    List<CardListDataClass> listDataClasses = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cardView = (CardView) findViewById(R.id.AddCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                Fragment frag = manager.findFragmentByTag("fragment_edit_name");
                if (frag != null) {
                    manager.beginTransaction().remove(frag).commit();
                }
                MyDialogFragment editNameDialog = new MyDialogFragment();
                editNameDialog.show(manager, "fragment_edit_name");
                MainActivityFragment.list.add(new CardListDataClass("b", "10"));
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.total_exp);
        fab.setRippleColor(Color.BLACK);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Aaj ka kharch Rs. "+calculate_total(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private float calculate_total() {
        listDataClasses = MainActivityFragment.list;
        float total_exp=0;
        for(int i = 0; i<listDataClasses.size();i++)
            total_exp += Float.parseFloat(listDataClasses.get(i).getDescription());
        return total_exp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishUserDialog(String detail, String exp) {
        Toast.makeText(MainActivity.this, "hi" + detail + exp, Toast.LENGTH_LONG).show();
        MainActivityFragment.list.add(new CardListDataClass(detail, exp));
    }
}
