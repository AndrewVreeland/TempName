package com.study.quizzler2;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.study.quizzler2.fragments.HomeFragment;
import com.study.quizzler2.fragments.LoginFragment;
import com.study.quizzler2.managers.UserManager;
import com.study.quizzler2.helpers.AuthHelper;
import com.study.quizzler2.helpers.HamburgerMenuHelper;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private UserManager userManager;
    private AuthHelper authHelper;
    private HamburgerMenuHelper hamburgerMenuHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userManager = new UserManager(this);
        authHelper = new AuthHelper(this, userManager);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        if (savedInstanceState == null) {
            if (userManager.isLoggedIn()) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }

        hamburgerMenuHelper = new HamburgerMenuHelper(this, drawerLayout, authHelper);
        hamburgerMenuHelper.setupNavigationView(navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (hamburgerMenuHelper.onOptionsItemSelected()) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}