package iam.deprest.demolistsandmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import iam.deprest.demolistsandmenu.model.MopDAO;
import iam.deprest.demolistsandmenu.recycler_utilities.MopAdapter;
import iam.deprest.demolistsandmenu.recycler_utilities.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMopjes;
    private MopAdapter adapter;

    private SearchView.OnQueryTextListener zoekListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) { //op enter gedrukt => krijg je reactie (zoeken na enter)
            adapter.getFilter().filter(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) { //op eender wat op toetsenbord gedrukt => krijg je reactie (zoeken vanaf begin van typen)
            adapter.getFilter().filter(newText);
            return false;
        }
    };

    //aanmaken menu:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hoofd_menu, menu);
        //listener voor zoekfunctie moet ik toevoegen aan een menuItem:
        SearchView searchView = (SearchView) menu.findItem(R.id.mi_search).getActionView();
        searchView.setOnQueryTextListener(zoekListener);
        return super.onCreateOptionsMenu(menu);
    }

    //er is iets aangeraakt in het menu:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mi_add_mop){
            //start navigatie
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

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
