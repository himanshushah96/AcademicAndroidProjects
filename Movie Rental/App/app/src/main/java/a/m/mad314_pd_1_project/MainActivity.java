package a.m.mad314_pd_1_project;

import a.m.mad314_pd_1_project.responsemodel.LoginResponseModel;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;
    LoginResponseModel loginResponseModel=new LoginResponseModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpNavigation();
    }

    public void setUpNavigation(){

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);

        ((TextView)headerView.findViewById(R.id.loggedIn_email)).setText(UserSession.getInstance().getEmail());
        ((TextView)headerView.findViewById(R.id.loggedIn_name)).setText(UserSession.getInstance().getName());

        navController = Navigation.findNavController(this,R.id.hostFragment);
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);

    }
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.hostFragment),drawerLayout);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {



        int id = menuItem.getItemId();
        switch (id){
            case R.id.homeFragment:
                navController.navigate(R.id.homeFragment);
                break;

            case R.id.rentedMovieListFragment:
                navController.navigate(R.id.rentedMovieListFragment);
                break;
        }
        menuItem.setCheckable(true); //to highlight the fragments
        drawerLayout.closeDrawers();
        return true;
    }
}