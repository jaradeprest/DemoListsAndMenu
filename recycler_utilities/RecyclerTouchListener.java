package iam.deprest.demolistsandmenu.recycler_utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    //abstracte listener (zal later moeten worden overgeschreven) NODIG OM ACTIVITY TE LATEN INVULLEN :
    public interface ClickListener{
        void onClick(View view, int position);
    }

    //variabele bijhouden die gaat verwijzen naar clicklistener
    private ClickListener clickListener;
    //wat heeft gebruiker aangeraakt?
    private GestureDetector gestureDetector;

    //constructor:
    public RecyclerTouchListener(Context context, ClickListener clickListener){
        this.clickListener = clickListener;
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener());
    }



    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View rij = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                //methode gaat zoeken in je scherm: waar is er iets aangeraakt? => coordinaten van motionEvent (waar gebeurd er iets?)
        if(rij != null && motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            //action_down zorgt ervoor dat we pas een melding krijgen als je effectief geklikt hebt (anders 2x uitvoer)
            //listener hier is verwijzing naar listener in activity
            //methode uitvoeren, dit stuurt dan door naar activity
            clickListener.onClick(rij, recyclerView.getChildAdapterPosition(rij));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
