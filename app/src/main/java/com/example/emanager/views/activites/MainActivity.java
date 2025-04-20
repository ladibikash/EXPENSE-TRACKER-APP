package com.example.emanager.views.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.emanager.adapters.TransactionsAdapter;
import com.example.emanager.models.Transaction;
import com.example.emanager.utils.Constants;
import com.example.emanager.utils.Helper;
import com.example.emanager.viewmodels.MainViewModel;
import com.example.emanager.views.fragments.AddTransactionFragment;
import com.example.emanager.R;
import com.example.emanager.databinding.ActivityMainBinding;
import com.example.emanager.views.fragments.StatsFragment;
import com.example.emanager.views.fragments.TransactionsFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    com.example.emanager.views.activites.ActivityMainBinding binding;

    Calendar calendar;
    /*
    0 = Daily
    1 = Monthly
    2 = Calendar
    3 = Summary
    4 = Notes
     */


    public MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View ActivityMainBinding;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(com.example.emanager.views.activites.MainViewModel.class);



        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Transactions");


        Constants.setCategories();

        calendar = Calendar.getInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new com.example.emanager.views.activites.TransactionsFragment());
        transaction.commit();

        binding.bottomNavigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) item -> {
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            if(item.getItemId() == R.id.transactions) {
                getSupportFragmentManager().popBackStack();
            } else if(item.getItemId() == R.id.stats){
                transaction1.replace(R.id.content, new com.example.emanager.views.activites.StatsFragment());
                transaction1.addToBackStack(null);
            }
            transaction1.commit();
            return true;
        });


    }

    public void getTransactions() {
        viewModel.getTransactions(calendar);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}