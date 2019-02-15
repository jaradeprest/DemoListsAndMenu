package iam.deprest.demolistsandmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import iam.deprest.demolistsandmenu.model.Mop;
import iam.deprest.demolistsandmenu.model.MopDAO;

public class DetailActivity extends AppCompatActivity {

    private EditText etMop, etClou;
    private ImageButton btnSave;

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mop = etMop.getText().toString();
            String clou = etClou.getText().toString();

            Mop nieuweMop = new Mop(mop, clou);
            MopDAO.getInstance().addMop(nieuweMop);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            //haalt overige schermen uit navigatie, nadat je op save duwt kan je dus niet meer teruggaan naar dat detailvenster
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etMop = findViewById(R.id.et_mop);
        etClou = findViewById(R.id.et_clou);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(saveListener);
    }

    //TODO findviewbyid
    //TODO listener aan knop hangen
}
