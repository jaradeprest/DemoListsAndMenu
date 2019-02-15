package iam.deprest.demolistsandmenu.recycler_utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import iam.deprest.demolistsandmenu.R;
import iam.deprest.demolistsandmenu.model.Mop;

public class MopAdapter extends RecyclerView.Adapter<MopAdapter.MopViewHolder> implements Filterable {

    //viewholder pattern:
    //class viewHolder, houdt alle elementen bij in layout
    //class enkel hier nodig, dus innerclass maken (klasse binnen andere klasse)
    public class MopViewHolder extends RecyclerView.ViewHolder {

        //verwijzingen naar tekstveldjes in layout. Die gaan niet meer wijzigen nadat ze zijn aangemaakt => final maken
        final TextView tvMop;
        public final TextView tvClou;
        //constructor maken zonder selectie
        public MopViewHolder(@NonNull View itemView) {
            //itemview == achterliggende verwijzing naar view, wat getekend w om de rijen op te vullen
            super(itemView);
            //tag geven aan viewholder om clou weer te geven
            itemView.setTag(this);
            //hier kunnen we findById gebruiken om veldjes terug te vinden in layout
            tvMop = itemView.findViewById(R.id.tv_mop);
            tvClou = itemView.findViewById(R.id.tv_clou);
        }

    }

    private ArrayList<Mop> items, gefilterdeItems;

    public MopAdapter(ArrayList<Mop> items) {
        this.items = items;
        this.gefilterdeItems = items;
    }

    @NonNull
    @Override
    //hoe ziet de rij eruit???
    public MopViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //viewgroup, lijst waar alle rijen in komen
        Context context = viewGroup.getContext();
        //binnen die context een layoutfile omzetten naar iets wat op scherm zal verschijnen
        View rijView = LayoutInflater.from(context).inflate(R.layout.mop_card, viewGroup, false);
        //nieuw viewHolder, op basis van getekende layout
        return new MopViewHolder(rijView);
    }

    @Override
    public void onBindViewHolder(@NonNull MopViewHolder mopViewHolder, int i) {
        //i == positionInList
        //rijen opvullen
        Mop mopVoorRij = gefilterdeItems.get(i);
        mopViewHolder.tvMop.setText(mopVoorRij.getMop());
        mopViewHolder.tvClou.setText(mopVoorRij.getClou());


    }

    @Override
    public int getItemCount() {
        return gefilterdeItems.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            //hiermee gaan we door de data en filteren we er items uit
            //resultatenset opbouwen
            protected FilterResults performFiltering(CharSequence constraint) {
                String zoekterm = constraint.toString();
                if (zoekterm.isEmpty()){
                    gefilterdeItems = items;
                }else {
                    ArrayList<Mop> tijdelijkeLijst = new ArrayList<>();
                    for (Mop m : items) {
                        // == for each
                        if (m.getMop().contains(zoekterm) || (m.getMop().contains(zoekterm))) {
                            tijdelijkeLijst.add(m);
                        }
                    }
                    gefilterdeItems = tijdelijkeLijst;
                }
                //resultaten maken :
                FilterResults filterResults = new FilterResults();
                filterResults.values = gefilterdeItems;
                //resultaten doorgeven naar publishResults
                return filterResults;
            }

            @Override
            //resultatenset ontvangen en gebruiken om lijst te updaten met wat overblijft na filter
            protected void publishResults(CharSequence constraint, FilterResults results) {
                gefilterdeItems = (ArrayList<Mop>) results.values;
                notifyDataSetChanged();
            }
        };


        return filter;
    }

}
