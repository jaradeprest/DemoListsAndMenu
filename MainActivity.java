package iam.deprest.demolistsandmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import iam.deprest.demolistsandmenu.model.MopDAO;
import iam.deprest.demolistsandmenu.recycler_utilities.MopAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMopjes;
    private MopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verwijzing naar recycler
        rvMopjes = findViewById(R.id.rv_mopjes);
        //adapter, hoe recycler opvullen
        adapter = new MopAdapter(MopDAO.getInstance().getMopList());
        rvMopjes.setAdapter(adapter);
        //hoe elementen weergeven? Horizontale lijst? verticale lijst?
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMopjes.setLayoutManager(layoutManager);
    }
}
