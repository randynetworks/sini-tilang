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

    String tipe;
    String diterima;
    String tahan;
    String strSidang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_input);

        // ===================== EDIT TEXT =============================
        // PENGENDARA
        EditText input_pengendara = findViewById(R.id.input_pengendara);
        EditText input_alamat = findViewById(R.id.input_alamat);
        EditText input_stnk = findViewById(R.id.input_stnk);
        EditText input_ktp = findViewById(R.id.input_ktp);

        // KENDARAAN
        EditText input_kendaraan = findViewById(R.id.input_kendaraan);
        EditText jml_orng = findViewById(R.id.jml_orng);
        EditText denda = findViewById(R.id.denda);

        // DATA POLISI
        EditText input_polisi = findViewById(R.id.input_polisi);
        EditText input_telp_polisi = findViewById(R.id.input_telp_polisi);
        EditText no_tilang = findViewById(R.id.no_tilang);


        // ===================== RADIO GROUP =============================
        RadioGroup jenis_sidang = findViewById(R.id.jenis_sidang);

        jenis_sidang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    strSidang = checkedRadioButton.getText().toString();
                    Toast.makeText(FormInput.this, "Kamu Memilih " + strSidang, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ===================== SWITCH =============================
        Switch s_ditahan = findViewById(R.id.s_ditahan);

        s_ditahan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tahan = (isChecked) ? "Di Tahan" : "Tidak Ditahan";
                Toast.makeText(FormInput.this, "Kamu Memilih " + tahan, Toast.LENGTH_SHORT).show();

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
        Spinner tipe_kendaraan = findViewById(R.id.tipe_kendaraan);

        tipe_kendaraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String item = String.valueOf(tipe_kendaraan.getSelectedItem());
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipe = (item.equals(tipe_kendaraan.getSelectedItem())) ? "" : parent.getItemAtPosition(position).toString();
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
                dialog.setIcon(R.drawable.icon_info);

                dialog.setPositiveButton("Data Valid", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // DATA PENGIRIM
                        String pengendara = input_pengendara.getText().toString();
                        String strAlamat = input_alamat.getText().toString();
                        String strTelp = input_stnk.getText().toString();
                        String strEmail = input_ktp.getText().toString();

                        // DATA BARANG
                        String nama_kendaraan = input_kendaraan.getText().toString();
                        Double jmlOrang = Double.parseDouble(jml_orng.getText().toString());
                        Double ddenda = Double.parseDouble(denda.getText().toString());
                        // HASIL
                        Double TotalHarga =  ddenda * jmlOrang;

                        // DATA POLISI
                        String strPolisi = input_polisi.getText().toString();
                        String strTelpPolisi = input_telp_polisi.getText().toString();
                        String noTilang = no_tilang.getText().toString();


                        String MESSAGE = "Data Pengendara\n" +
                                "Nama Pengendara : " + pengendara + "\n" +
                                "Alamat Pengirim : " + strAlamat +  "\n" +
                                "No. Telp : " + strTelp + "\n" +
                                "Email  : " + strEmail + "\n" +
                                "Informasi Kendaraan\n" +
                                "Nama Kendaraan : " + nama_kendaraan + "\n" +
                                "Jumlah Orang : "+ jmlOrang + "\n" +
                                "Harga Denda : " + ddenda + "\n" +
                                "Tipe kendaraan : " + tipe + "\n" +
                                "Total Yang Harus Dibayar : \n" +
                                "======== " + TotalHarga + " ========\n" +
                                "Barang Diterima : " + diterima + "\n" +
                                "Barang Ditahan : " + tahan + "\n" +
                                "Kurir Oleh : " +  strSidang + "\n" +
                                "======== DATA PENGIRIM ========\n" +
                                "Nama Polisi : " + strPolisi + ", \n" +
                                "No Telp Polisi : " + strTelpPolisi + ", \n" +
                                "\nNo Tilang : " + noTilang + "\n" +
                                "===============================";

                        System.out.println(MESSAGE);

                        Intent successFormResult = new Intent(getBaseContext(), FormSuccess.class);
                        successFormResult.putExtra("OUTPUT", MESSAGE);
                        startActivity(successFormResult);
                    }
                });
                dialog.setNegativeButton("Data Tidak Valid", new DialogInterface.OnClickListener() {
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
