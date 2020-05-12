package activity.tugas.donasikuy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity{
    DatabaseHelper db;
    Button register;
    EditText username,password,passwordConf, nama, email, npm, no_hp,alamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        db = new DatabaseHelper(this);
        nama        = (EditText) findViewById(R.id.nama);
        username    = (EditText) findViewById(R.id.username);
        email       = (EditText) findViewById(R.id.email);
        npm         = (EditText) findViewById(R.id.NPM);
        alamat      = (EditText) findViewById(R.id.alamat);
        no_hp       = (EditText) findViewById(R.id.NoHP);
        password    = (EditText) findViewById(R.id.password);
        passwordConf= (EditText) findViewById(R.id.passwordConf);
        register    = (Button)   findViewById(R.id.btn_register);

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                String strPasswordConf = passwordConf.getText().toString();
                String strNama = nama.getText().toString();
                String strEmail = email.getText().toString();
                String strNoHP = no_hp.getText().toString();
                String strNPM = npm.getText().toString();
                String strAlamat = alamat.getText().toString();
                Boolean cekUsername = db.CekUsername(strUsername);
                Boolean cekEmail = db.CekEmail(strEmail);
                Boolean cekHP = db.CekNoHP(strNoHP);

                if(TextUtils.isEmpty(strNama))
                {
                    nama.setError("Nama Tidak Boleh Kosong");
                }
                else if(TextUtils.isEmpty(strNPM))
                {
                    npm.setError("NPM / NIDN Tidak Boleh Kosong");
                }
                else if(TextUtils.isEmpty(strUsername))
                {
                    username.setError("Username Tidak Boleh Kosong");
                }
                else if(cekUsername == true)
                {
                    Toast.makeText(getApplicationContext(), "Username sudah terdaftar", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(strEmail))
                {
                    email.setError("Email Tidak Boleh Kosong");
                }
                else if(cekEmail == true)
                {
                    Toast.makeText(getApplicationContext(), "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(strNoHP))
                {
                    no_hp.setError("No. Telepon Tidak Boleh Kosong");
                }
                else if(cekHP == true)
                {
                    Toast.makeText(getApplicationContext(), "No. Telepon sudah terdaftar", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(strAlamat))
                {
                    alamat.setError("Alamat Tidak Tidak Boleh Kosong");
                }
                else if(TextUtils.isEmpty(strPassword))
                {
                    password.setError("Password Tidak Boleh Kosong");
                }
                else if(strPassword.equals(strPasswordConf))
                {
                    Boolean daftar = db.insertUser(strNama, strNPM, strUsername, strEmail, strNoHP, strAlamat,strPassword);
                    if(daftar == true){
                        Toast.makeText(getApplicationContext(),"Akun Berhasil dibuat, Silahkan Login", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Registrasi Akun Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
