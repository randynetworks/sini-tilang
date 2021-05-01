package randyramadhan.ayoboga.sinitilang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormInput extends AppCompatActivity {

    String strJumlah;
    String diterima;
    String asuransi;
    String strKurir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_input);

        // ===================== EDIT TEXT =============================
        // Pengirim
        EditText input_pengirim = findViewById(R.id.input_pengirim);
        EditText input_alamat = findViewById(R.id.input_alamat);
        EditText input_telp = findViewById(R.id.input_stnk);
        EditText input_email = findViewById(R.id.input_ktp);

        // Barang
        EditText input_barang = findViewById(R.id.input_kendaraan);
        EditText berat_barang = findViewById(R.id.berat_barang);
        EditText harga_barang = findViewById(R.id.harga_barang);

        // Penerima
        EditText input_penerima = findViewById(R.id.input_penerima);
        EditText input_alamat_penerima = findViewById(R.id.input_alamat_penerima);
        EditText input_telp_penerima = findViewById(R.id.input_telp_penerima);
        EditText input_kode_pengiriman = findViewById(R.id.input_kode_pengiriman);


        // ===================== RADIO GROUP =============================
        RadioGroup radioKurir = findViewById(R.id.kurir);

        radioKurir.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    strKurir = checkedRadioButton.getText().toString();
                    Toast.makeText(FormInput.this, "Kamu Memilih " + strKurir, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ===================== SWITCH =============================
        Switch s_asuransi = findViewById(R.id.s_asuransi);

        s_asuransi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                asuransi = (isChecked) ? "Di Asuransikan" : "Tidak Asuransikan";
                Toast.makeText(FormInput.this, "Kamu Memilih " + asuransi, Toast.LENGTH_SHORT).show();

            }
        });


        // ===================== CHECKBOX =============================

        CheckBox cb_diterima = findViewById(R.id.cb_diterima);

        cb_diterima.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                diterima = (isChecked) ? "Diterima" : "Tidak Diterima";
                Toast.makeText(FormInput.this, "Kamu Memilih " + diterima, Toast.LENGTH_SHORT).show();
            }
        });


        // ===================== SPINNER =============================
        Spinner jumlah_barang = findViewById(R.id.jumlah_barang);

        jumlah_barang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String item = String.valueOf(jumlah_barang.getSelectedItem());
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strJumlah = (item.equals(jumlah_barang.getSelectedItem())) ? "" : parent.getItemAtPosition(position).toString();
                Toast.makeText(FormInput.this, "Kamu Memilih " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ===================== SUBMIT FORM =============================

        Button btnSumbit = findViewById(R.id.btn_submit);
        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ===================== ALERT DIALOG YES NO =============================
                AlertDialog.Builder dialog = new AlertDialog.Builder(FormInput.this);
                dialog.setTitle("Yakin disimpan?");
                dialog.setMessage("Hi! Apakah data yang dimasukan Valid?");
                dialog.setIcon(R.drawable.icon_info);

                dialog.setPositiveButton("Heem Valid!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // DATA PENGIRIM
                        String strPengirim = input_pengirim.getText().toString();
                        String strAlamat = input_alamat.getText().toString();
                        String strTelp = input_telp.getText().toString();
                        String strEmail = input_email.getText().toString();

                        // DATA BARANG
                        String strBarang = input_barang.getText().toString();
                        Double dBarang = Double.parseDouble(berat_barang.getText().toString());
                        Double dHarga = Double.parseDouble(harga_barang.getText().toString());
                        Double dQty = Double.parseDouble(strJumlah);
                        // HASIL
                        Double TotalHarga = dQty * dHarga * dBarang;

                        // DATA PENERIMA
                        String strPenerima = input_penerima.getText().toString();
                        String strAlamatPenerima = input_alamat_penerima.getText().toString();
                        String strTelpPenerima = input_telp_penerima.getText().toString();
                        String strKode = input_kode_pengiriman.getText().toString();


                        String MESSAGE = "======== DATA PENERIMA ========\n" +
                                "Nama Pengirim : " + strPengirim + "\n" +
                                "Alamat Pengirim : " + strAlamat +  "\n" +
                                "No. Telp : " + strTelp + "\n" +
                                "Email Pengirim : " + strEmail + "\n" +
                                "======== DATA BARANG ========\n" +
                                "Nama Barang : " + strBarang + "\n" +
                                "Berat Barang : "+ dBarang + "\n" +
                                "Harga Kirim/Barang : " + dHarga + "\n" +
                                "Jumlah Barang : " + dQty + "\n" +
                                "Total Yang Harus Dibayar : \n" +
                                "======== " + TotalHarga + " ========\n" +
                                "Barang Diterima : " + diterima + "\n" +
                                "Barang Diasuransi : " + asuransi + "\n" +
                                "Kurir Oleh : " +  strKurir + "\n" +
                                "======== DATA PENGIRIM ========\n" +
                                "Nama Penerima : " + strPenerima + ", \n" +
                                "Alamat Penerima : " + strAlamatPenerima + ", \n" +
                                "No Telp Penerima : " + strTelpPenerima + ", \n" +
                                "\n\nKode Pengiriman : " + strKode + "\n" +
                                "===============================";

                        System.out.println(MESSAGE);

                        Intent successFormResult = new Intent(getBaseContext(), FormSuccess.class);
                        successFormResult.putExtra("OUTPUT", MESSAGE);
                        startActivity(successFormResult);
                    }
                });
                dialog.setNegativeButton("Engga valid", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(FormInput.this, "Data Gagal Disimpan.", Toast.LENGTH_LONG).show();
                    }
                });

                dialog.setCancelable(false);
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }
}
