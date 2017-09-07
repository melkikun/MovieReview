package com.example.mikie.moviereview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mikie.moviereview.fragment.MovieFragment;
import com.example.mikie.moviereview.fragment.TvFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //drawer layout
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    //navigation view sebelah kiri
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //untuk fragment
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    Fragment fragment = null;
    //penanda fragment mana yang aktif
    private int checkitem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //set first fragment
        setFragment();
        //toggle navigation view
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //change icon navigation view
        toolbar.setNavigationIcon(R.drawable.menu);
        //rename title toolbar
        getSupportActionBar().setTitle("GenreMovie");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFFFF"));

        //navigation view on click
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawers();
                selectDrawerItem(item);
                return true;
            }
        });
    }

    public void selectDrawerItem(MenuItem item){
        switch (item.getItemId()) {
            case R.id.nav_movie:
                if(checkitem != 1) {
                    checkitem = 1;
                    getSupportActionBar().setTitle("GenreMovie");
                    fragment = new MovieFragment();
                }
                break;
            case R.id.nav_tv:
                if(checkitem != 2){
                    checkitem = 2;
                    getSupportActionBar().setTitle("TV Shows");
                    fragment = new TvFragment();
                }
                break;
        }
        Toast.makeText(getApplicationContext(), checkitem+"", Toast.LENGTH_SHORT).show();
        if(fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }
        item.setChecked(true);
        drawer.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Toast.makeText(getApplicationContext(), "Tombol Search", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setFragment(){
        fragment = new MovieFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
        checkitem = 1;

    }
}
