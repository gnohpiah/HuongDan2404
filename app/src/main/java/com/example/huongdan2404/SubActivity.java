package com.example.huongdan2404;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    private EditText etSBD;
    private EditText etName;
    private EditText etToan;
    private EditText etLy;
    private EditText etHoa;
    private Button btnSua;
    private Button btnQuayVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        etSBD = findViewById(R.id.etSBD);
        etName = findViewById(R.id.etName);
        etToan = findViewById(R.id.etToan);
        etLy = findViewById(R.id.etLy);
        etHoa = findViewById(R.id.etHoa);
        btnSua = findViewById(R.id.btnSua);
        btnQuayVe = findViewById(R.id.btnQuayVe);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String sbd = bundle.getString("SBD");
            String name = bundle.getString("Name");
            double toan = bundle.getDouble("Toan");
            double ly = bundle.getDouble("Ly");
            double hoa = bundle.getDouble("Hoa");
            etSBD.setText(sbd);
            etName.setText(name);
            etToan.setText(String.valueOf(toan));
            etLy.setText(String.valueOf(ly));
            etHoa.setText(String.valueOf(hoa));
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sbd = etSBD.getText().toString();
                String name = etName.getText().toString();
                double toan = Double.parseDouble(etToan.getText().toString());
                double ly = Double.parseDouble(etLy.getText().toString());
                double hoa = Double.parseDouble(etHoa.getText().toString());
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("SBD",sbd);
                b.putString("Name",name);
                b.putDouble("Toan",toan);
                b.putDouble("Ly",ly);
                b.putDouble("Hoa",hoa);
                intent.putExtras(b);
                setResult(150,intent);
                finish();
            }
        });
    }
}