package iam.deprest.demolistsandmenu.recycler_utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import iam.deprest.demolistsandmenu.R;
import iam.deprest.demolistsandmenu.model.Mop;

public class MopAdapter extends RecyclerView.Adapter<MopAdapter.MopViewHolder> {

    //viewholder pattern:
    //class viewHolder, houdt alle elementen bij in layout
    //class enkel hier nodig, dus innerclass maken (klasse binnen andere klasse)
    class MopViewHolder extends RecyclerView.ViewHolder {

        //verwijzingen naar tekstveldjes in layout. Die gaan niet meer wijzigen nadat ze zijn aangemaakt => final maken
        final TextView tvMop, tvClou;
        //constructor maken zonder selectie
        public MopViewHolder(@NonNull View itemView) {
            //itemview == achterliggende verwijzing naar view, wat getekend w om de rijen op te vullen
            super(itemView);
            //hier kunnen we findById gebruiken om veldjes terug te vinden in layout
            tvMop = itemView.findViewById(R.id.tv_mop);
            tvClou = itemView.findViewById(R.id.tv_clou);
        }

    }

    private ArrayList<Mop> items;

    public MopAdapter(ArrayList<Mop> items) {
        this.items = items;
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
        Mop mopVoorRij = items.get(i);
        mopViewHolder.tvMop.setText(mopVoorRij.getMop());
        mopViewHolder.tvClou.setText(mopVoorRij.getClou());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
