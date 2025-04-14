package app.webview.errorpage;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        Toolbar toolbar = findViewById(R.id.toolbar);
        ListView navView = findViewById(R.id.navView);
        ListView listView = findViewById(R.id.listView);
        drawerLayout = findViewById(R.id.drawerLayout);

        // Setup Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        // Setup Drawer Toggle
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Load items from JSON
        items = JsonLoader.loadItems(this);

        // Setup Navigation Drawer
        String[] menuItems = {"Home", "Profile", "Settings"};
        ArrayAdapter<String> navAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, menuItems);
        navView.setAdapter(navAdapter);
        navView.setOnItemClickListener((parent, view, position, id) -> {
            // Handle navigation item clicks
            switch (position) {
                case 0: // Home
                    // Refresh data or navigate
                    break;
                case 1: // Profile
                    // Navigate to profile
                    break;
                case 2: // Settings
                    // Navigate to settings
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        // Setup Main ListView with custom adapter
        items = JsonLoader.loadItems(this);

        if (!items.isEmpty()) {
            ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(
                    this,
                    android.R.layout.simple_list_item_1,
                    items
            ) {
                @NonNull
                @Override
                public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = view.findViewById(android.R.id.text1);
                    text1.setText(items.get(position).getTitle()); // show title from each Item
                    return view;
                }
            };
            listView.setAdapter(itemAdapter);
        } else {
            Toast.makeText(this, "No items loaded from JSON.", Toast.LENGTH_SHORT).show();
        }

        // Handle item clicks
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click
            Toast.makeText(MainActivity.this,
                    "Clicked: " + items.get(position).getTitle(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
}