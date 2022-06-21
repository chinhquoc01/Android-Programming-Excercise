package com.example.bttuan10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bttuan10.fragment.CameraFragment;
import com.example.bttuan10.fragment.GalleryFragment;
import com.example.bttuan10.fragment.SlideshowFragment;
import com.example.bttuan10.fragment.ToolsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_CAMERA = 0;
    private static final int FRAGMENT_GALLERY = 1;
    private static final int FRAGMENT_SLIDESHOW = 2;
    private static final int FRAGMENT_TOOLS = 3;

    private int mCurrentFragment = FRAGMENT_CAMERA;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFagment(new CameraFragment());
        navigationView.getMenu().findItem(R.id.nav_camera).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera){
            if (mCurrentFragment != FRAGMENT_CAMERA){
                replaceFagment(new CameraFragment());
                mCurrentFragment = FRAGMENT_CAMERA;
            }
        }
        else if(id == R.id.nav_gallery){
            if (mCurrentFragment != FRAGMENT_GALLERY){
                replaceFagment(new GalleryFragment());
                mCurrentFragment = FRAGMENT_GALLERY;
            }
        }
        else if(id == R.id.nav_slideshow){
            if (mCurrentFragment != FRAGMENT_SLIDESHOW){
                replaceFagment(new SlideshowFragment());
                mCurrentFragment = FRAGMENT_SLIDESHOW;
            }
        }
        else if(id == R.id.nav_manage){
            if (mCurrentFragment != FRAGMENT_TOOLS){
                replaceFagment(new ToolsFragment());
                mCurrentFragment = FRAGMENT_TOOLS;
            }
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private void replaceFagment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}