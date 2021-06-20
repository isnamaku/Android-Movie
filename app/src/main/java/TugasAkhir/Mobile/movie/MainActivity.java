package TugasAkhir.Mobile.movie;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    TextView textViewEmail, textViewLogout, textViewName, textViewPassword;

    Button btnEditDetails;

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    String textViewNameString;
    String textViewEmailString;
    String textViewPasswordString;
    int textViewID;

    private BottomNavigationView main_navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewPassword = findViewById(R.id.textViewPassword);
        textViewLogout = findViewById(R.id.textViewLogout);

        final Bundle b = getIntent().getExtras();

        textViewID = Integer.parseInt(b.getString("textViewId"));
        textViewNameString = b.getString("textViewUsername");
        textViewEmailString = b.getString("textViewEmail");
        textViewPasswordString = b.getString("textViewPassword");


        Log.d("Data", String.valueOf(textViewID));
        Log.d("Data", "name: " + textViewNameString);
        Log.d("Data", "Email: " + textViewEmailString);
        Log.d("Data", "password: " + textViewPasswordString);

        textViewName.setText("Hello " + textViewNameString + "!");
        textViewEmail.setText(textViewEmailString);
        textViewPassword.setText(textViewPasswordString);

        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


        btnEditDetails = findViewById(R.id.btnEditDetails);
        btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUserDidals();
            }
        });

        main_navbar = findViewById(R.id.main_navbar);
        main_navbar.setOnNavigationItemSelectedListener(this);

       loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.profile:
                fragment = new UpcomingFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public void editUserDidals() {
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        inflater = LayoutInflater.from(MainActivity.this);
        final View view = inflater.inflate(R.layout.popup, null);
        final EditText editTextUsername = view.findViewById(R.id.editTextUsername);
        final EditText editTextPassword = view.findViewById(R.id.editTextPassword);
        final EditText editTextEmail = view.findViewById(R.id.editTextEmail);

        alertDialogBuilder.setView(view);
        dialog = alertDialogBuilder.create();
        dialog.show();

        final User user = new User();
        user.setId(textViewID);
        user.setUserName(textViewNameString);
        user.setEmail(textViewEmailString);
        user.setPassword(textViewPasswordString);

        editTextPassword.setText(user.getPassword());
        editTextUsername.setText(user.getUserName());
        editTextEmail.setText(user.getEmail());

        final DatabaseHelper db = new DatabaseHelper(MainActivity.this);

        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(editTextUsername.getText().toString());
                user.setEmail(editTextEmail.getText().toString());
                user.setPassword(editTextPassword.getText().toString());

                if (!editTextUsername.getText().toString().isEmpty()
                        && !editTextEmail.getText().toString().isEmpty()
                        && !editTextPassword.getText().toString().isEmpty()) {
                    db.updateUser(user);
                    Snackbar.make(v, "Details Saved!", Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    Snackbar.make(view, "Don't save ", Snackbar.LENGTH_LONG).show();
                }
                dialog.dismiss();

            }
        });
    }
}
