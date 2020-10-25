package com.ricatalog.ricatalog.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.adapter.NavigationAdapter;
import com.ricatalog.ricatalog.fragment.MainCategoryFragment;
import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.network.EditProfile;
import com.ricatalog.ricatalog.utils.Utils;
import com.ricatalog.ricatalog.viewmodel.CartViewModel;
import com.ricatalog.ricatalog.viewmodel.LoginViewModel;
import com.ricatalog.ricatalog.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.framelayout)
    FrameLayout frameLayout;
    @BindView(R.id.navigation_drawer)
    NavigationView navigationView;
    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.nestedrecycler)
    RecyclerView nestedrecycler;
    @BindView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerFrameLayout;
    NavigationAdapter adapter;
    int mCartCount=0;
    TextView cartvalue;
    MainActivityViewModel viewModel;
    CartViewModel cartviewmodel;
    List<Category.CategoryList> categoryListList;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Category");
        setSupportActionBar(toolbar);
        shimmerFrameLayout.startShimmer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.whitecolor));
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.whitecolor));
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle.syncState();
       viewModel=new ViewModelProvider(this).get(MainActivityViewModel.class);
        cartviewmodel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CartViewModel.class);
       viewModel.getCategories();
       viewModel.getCategoryList().observe(this, new Observer<Category>() {
           @Override
           public void onChanged(Category category) {
               if (category != null) {
                   categoryListList = category.getCategoryList();
                   adapter.setAdapter(categoryListList);
                   shimmerFrameLayout.stopShimmer();
                   shimmerFrameLayout.setVisibility(View.GONE);
                   Fragment categoryFragment = new MainCategoryFragment();
                   Bundle bundle = new Bundle();
                   bundle.putParcelable("list",  category);
                   categoryFragment.setArguments(bundle);
                   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, categoryFragment,"hh").addToBackStack("hh").commit();

               }
               else
               {
                   Snackbar.make(findViewById(android.R.id.content),"Network Error",Snackbar.LENGTH_LONG);
               }
           }
       });
       cartviewmodel.getEntityCount().observe(this, new Observer<Integer>() {
           @Override
           public void onChanged(Integer integer) {
               mCartCount=integer;
               setupBadge();
           }
       });
        String fname=Utils.getShraredPreferences(getApplicationContext()).getString("name","RI");
       name.setText(fname);
    adapter=new NavigationAdapter(MainActivity.this);
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        nestedrecycler.setLayoutManager(manager);
        nestedrecycler.setAdapter(adapter);

    }
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
@OnClick(R.id.cardedit)
public void editProfile(View v)
{
    Intent i=new Intent(MainActivity.this, EditProfileActivity.class);
    startActivity(i);

}
    @OnClick(R.id.cardlogout)
    public void logout(View v)
    {
        Utils.getShraredPreferencesEditor(MainActivity.this).clear().apply();
        Intent i=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = menuItem.getActionView();
        cartvalue = (TextView) actionView.findViewById(R.id.cart_badge);
        setupBadge();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_cart: {
                // Do something
                Intent i=new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupBadge() {

        if (cartvalue != null) {
            if (mCartCount == 0) {
                if (cartvalue.getVisibility() != View.GONE) {
                    cartvalue.setVisibility(View.GONE);
                }
            } else {
                cartvalue.setText(String.valueOf(Math.min(mCartCount, 99)));
                if (cartvalue.getVisibility() != View.VISIBLE) {
                    cartvalue.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}