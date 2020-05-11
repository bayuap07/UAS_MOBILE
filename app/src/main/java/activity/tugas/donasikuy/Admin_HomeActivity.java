package activity.tugas.donasikuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
public class Admin_HomeActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button logout;
    FloatingActionButton fab;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        db = new DatabaseHelper(this);

        logout = (Button)findViewById(R.id.btn_logout);

        //floating Action Button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Floating Action Button Clicked", Snackbar.LENGTH_LONG)
                        .show();
                Intent fabIntent = new Intent(Admin_HomeActivity.this, TambahDonasiAdmin.class);
                startActivity(fabIntent);
                finish();
            }
        });

        Boolean checkSession = db.checkSession("ada");
        if(checkSession == false){
            Intent loginIntent = new Intent(Admin_HomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }


        //logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updtSession = db.upgradeSession("kosong",1);
                if(updtSession == true){
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent LoginIntent = new Intent(Admin_HomeActivity.this, LoginActivity.class);
                    startActivity(LoginIntent);
                    finish();
                }
            }
        });
    }
}
