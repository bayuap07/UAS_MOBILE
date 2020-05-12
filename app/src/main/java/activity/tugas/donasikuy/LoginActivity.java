package activity.tugas.donasikuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button login, register;
    EditText username,password;
    CheckBox cekPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);

        username    = (EditText)findViewById(R.id.editText_username);
        password    = (EditText)findViewById(R.id.editText_password);
        login       = (Button)findViewById(R.id.btn_login);
        register    = (Button)findViewById(R.id.btn_register);
        cekPW         = (CheckBox)findViewById(R.id.cek);

        //Set onClickListener, untuk menangani kejadian saat Checkbox diklik
        cekPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cekPW.isChecked()){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                Boolean masuk = db.checkLogin(strUsername, strPassword);
                if(TextUtils.isEmpty(strUsername))
                {
                    username.setError("Username Tidak Boleh Kosong");
                }
                else if(TextUtils.isEmpty(strPassword))
                {
                    password.setError("Password Tidak Boleh Kosong");
                }
                else if(masuk == true){
                    Boolean updateSession = db.upgradeSession("ada", 1);
                    if(updateSession == true){
                        Boolean status = db.checkStatus(strUsername);
                         if(status == false) {
                             startActivity(new Intent(LoginActivity.this, Admin_HomeActivity.class));
                        }else{
                            Intent mainIntent = new Intent(LoginActivity.this, User_HomeActivity.class);
                            startActivity(mainIntent);
                            finish();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Username atau Kata Sandi yang anda masukan salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Buat_akun(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
