package a.m.carrental;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.security.PublicKey;

import a.m.carrental.model.Constants;
import a.m.carrental.ui.home.DashBoardFragment;
import a.m.carrental.ui.orders.OrdersFragment;
import a.m.carrental.ui.home.HomeFragment;
import a.m.carrental.ui.cars.CarsFragment;
import a.m.carrental.ui.customers.CustomerFragment;
import a.m.carrental.ui.editdetail.EditDetailFragment;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FragmentManager fragmentManager;
    private ActionBarDrawerToggle t;
    DrawerLayout drawer;
    NavigationView navigationView;
    String userType = "";
    String userName = "";
    String userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, drawer,R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView = findViewById(R.id.nav_view);


        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textView_sessionUserName);
        TextView navUserEmail = (TextView) headerView.findViewById(R.id.textView_sessionUserEmail);



        Intent intentPrevious = getIntent();

        userType = intentPrevious.getStringExtra("userType");
        userName = intentPrevious.getStringExtra("name");
        userEmail = intentPrevious.getStringExtra("email");

        navUsername.setText(userName);
        navUserEmail.setText(userEmail);

        setMenuVisibilityBasedOnUser(userType);

        fragmentManager = getSupportFragmentManager();


        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, selectHomeFragementBasedOnUserType(userType.toUpperCase()) )
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment  = null;

                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        fragment = selectHomeFragementBasedOnUserType(userType);
                        break;
                    case R.id.nav_orders:
                        fragment = new OrdersFragment();
                        break;
                    case R.id.nav_cars:
                        fragment = new CarsFragment();
                        break;
                    case R.id.nav_customers:
                        fragment = new CustomerFragment(fragmentManager,Constants.USER_TYPE_CUSTOMER);
                        break;
                    case R.id.nav_logout:
                        //fragment = new EditDetailFragment();
                        final AlertDialog dialog = new AlertDialog.Builder(HomeActivity.this)
                                //.setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Logout")
                                .setMessage("Are you sure you want to logout?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent Loginscreen = new Intent(HomeActivity.this, LoginActivity.class);
                                        startActivity(Loginscreen);
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .create();
                            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                @Override
                                public void onShow(DialogInterface dialogInterface) {
                                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.buttonColor));
                                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.buttonColor));
                                }
                            });
                            dialog.show();
                        break;
                }

                if(fragment != null){
                    fragmentManager.beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment, Integer.toString(menuItem.getItemId()))
                            .addToBackStack(null)
                            .commit();
                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;

            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    void setMenuVisibilityBasedOnUser(String userType){

        NavigationView navigationView =  findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        if(userType.equals(Constants.USER_TYPE_CUSTOMER)){
            nav_Menu.findItem(R.id.nav_cars).setVisible(false);
            nav_Menu.findItem(R.id.nav_customers).setVisible(false);
        }
    }


    public Fragment selectHomeFragementBasedOnUserType(String userType){
        if(userType.equals(Constants.USER_TYPE_CUSTOMER)){
            return new HomeFragment(fragmentManager);
        }else{
            return new DashBoardFragment(fragmentManager);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
