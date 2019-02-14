package iam.deprest.demolistsandmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import iam.deprest.demolistsandmenu.model.MopDAO;
import iam.deprest.demolistsandmenu.recycler_utilities.MopAdapter;
import iam.deprest.demolistsandmenu.recycler_utilities.RecyclerTouchListener;

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
        //Voor de geïnteresseerden: tabel in andere richting laten scrollen:::
        //((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMopjes.setLayoutManager(layoutManager);

        rvMopjes.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("TEST", MopDAO.getInstance().getMopList().get(position).getMop());
                //clou opvragen via tag
                MopAdapter.MopViewHolder verwijzing = (MopAdapter.MopViewHolder)view.getTag();
                verwijzing.tvClou.setVisibility(View.VISIBLE);
            }
        }));
    }
}
