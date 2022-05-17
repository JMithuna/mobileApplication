package sp.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Business extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business);

        //getSupportActionBar().hide();

        //Bottom Navigation Bar codes.
        BottomNavigationView business_bottomNavigationView = findViewById(R.id.business_bottom_navigationView);
        NavHostFragment business_navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.business_fragmentContainerView);
        NavController business_navController = business_navHostFragment.getNavController();
        NavigationUI.setupWithNavController(business_bottomNavigationView, business_navController);



    }
}