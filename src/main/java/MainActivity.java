package com.cogy.launcher;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private Button addButton;
    private Button launchButton;
    private FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        addButton = findViewById(R.id.btn_add);
        launchButton = findViewById(R.id.btn_launch);
        contentFrame = findViewById(R.id.content_frame);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
            } else if (id == R.id.nav_settings) {
            } else if (id == R.id.nav_about) {
            }
            drawerLayout.closeDrawers();
            return true;
        });

        addButton.setOnClickListener(v -> addNewLauncher());
        launchButton.setOnClickListener(v -> launchAllPrograms());
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewLauncher() {
        android.content.Intent intent = new android.content.Intent(this, LauncherActivity.class);
        startActivity(intent);
    }

    private void launchAllPrograms() {
        LauncherManager manager = new LauncherManager();
        manager.launchAll();
    }
}
