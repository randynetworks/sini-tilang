package randyramadhan.ayoboga.sinitilang;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FormSuccess extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_success);

        String strOutput = getIntent().getStringExtra("OUTPUT");
        TextView total = (TextView) findViewById(R.id.total);
        total.setText(strOutput);

    }


}