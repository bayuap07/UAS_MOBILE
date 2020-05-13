package activity.tugas.donasikuy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TambahDonasiAdmin extends AppCompatActivity {
    DatabaseHelper db;
    Button btnsimpan;
    EditText judul, penerima, alasan, alamat, nomortelp, waktu, jumlah, foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_donasi_admin);

        db = new DatabaseHelper(this);
        judul = (EditText) findViewById(R.id.judulTD);
        penerima = (EditText) findViewById(R.id.PenerimaBantuanTD);
        alasan = (EditText) findViewById(R.id.AlasanTD);
        alamat = (EditText) findViewById(R.id.AlamatTD);
        nomortelp = (EditText) findViewById(R.id.NomorTeleponTD);
        waktu = (EditText) findViewById(R.id.WaktuBerakhirTD);
        jumlah = (EditText) findViewById(R.id.JumlahTargetTD);
        foto = (EditText) findViewById(R.id.FotoTD);
        btnsimpan = (Button) findViewById(R.id.btn_simpanTD);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strJudul = judul.getText().toString();
                String strPenerima = penerima.getText().toString();
                String strAlasan = alasan.getText().toString();
                String strAlamat = alamat.getText().toString();
                String strNomortelp = nomortelp.getText().toString();
                String strWaktu = waktu.getText().toString();
                String strJumlah = jumlah.getText().toString();
                String strFoto = foto.getText().toString();

                if (TextUtils.isEmpty(strJudul)) {
                    judul.setError("Judul Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(strPenerima)) {
                    penerima.setError("Wajib Melampirkan Nama Penerima");
                } else if (TextUtils.isEmpty(strAlasan)) {
                    alasan.setError("Alasan Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(strNomortelp)) {
                    nomortelp.setError("No Telepon Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(strWaktu)) {
                    waktu.setError("Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(strJumlah)) {
                    jumlah.setError("Tidak Boleh Kosong");
                } else if (TextUtils.isEmpty(strFoto)) {
                    foto.setError("Lampirkan foto");
                } else if (btnsimpan.isPressed()) {
                    Boolean simpandata = db.insertData(strJudul, strPenerima, strAlasan, strAlamat, strNomortelp, strWaktu, strJumlah, strFoto);
                    if (simpandata == true) {
                        Toast.makeText(getApplicationContext(), "Data Berhasil dibuat", Toast.LENGTH_SHORT).show();
                        Intent simpanIntent = new Intent(TambahDonasiAdmin.this, Admin_HomeActivity.class);
                        startActivity(simpanIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Data donasi gagal ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "salah", Toast.LENGTH_SHORT).show();
                    }

                }
        });
    }
}
