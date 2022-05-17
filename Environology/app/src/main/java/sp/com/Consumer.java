package sp.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class Consumer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer);
        //getSupportActionBar().hide();

        /*
        xml file: consumer.xml
        This is the Consumer Home Page.
        It has the Bottom Navigation View for the Consumer.
        Bottom Navigation View consists of, Home, Donate, Recycle,
        Buy, and Rewards options.
         */

        //Bottom Navigation Bar codes.
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //Navigation Drawer codes.
        drawerLayout = findViewById(R.id.nav_drawerLayout);
        navigationView = findViewById(R.id.navigation_drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.consumerAbout:
                Toast.makeText(getApplicationContext(), "Welcome to About Page.", Toast.LENGTH_SHORT).show();
                Intent abouti = new Intent(Consumer.this, About.class);
                startActivity(abouti);
                break;
            case R.id.consumerEnvSus:
                Toast.makeText(getApplicationContext(), "Learn about the Environment.", Toast.LENGTH_SHORT).show();
                Intent envi = new Intent(Consumer.this, EnvironmentSustainable.class);
                startActivity(envi);
                break;
            case R.id.consumerVolunteer:
                Toast.makeText(getApplicationContext(), "Volunteer with us.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.consumerMessages:
                Toast.makeText(getApplicationContext(), "Messages will be displayed here.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.consumerPlaces:
                Toast.makeText(getApplicationContext(), "Find your nearest location.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.consumerCarbonFootprint:
                Intent i = new Intent(this, CarbonFootprintWebView.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Check your carbon footprint here.", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}