package iam.deprest.demolistsandmenu.model;

import java.util.ArrayList;

public class MopDAO {
    private static final MopDAO ourInstance = new MopDAO();
    //lijst met moppen, die dynamisch is (kunnen moppen toevoegen of verwijderen) => ArrayList!!! (array is exact aantal elementen, staat vast)
    private ArrayList<Mop> mopList = new ArrayList<>();

    public static MopDAO getInstance() {
        return ourInstance;
    } //DATA ACCESS OBJECT

    private MopDAO() {
        mopList.add(new Mop("Het is oud en het vliegt...", "Een Bejaardelaar"));
        mopList.add(new Mop("Het is rood en vliegt door de klas...", "Een ongestelde vraag"));
        mopList.add(new Mop("Wat is de gelijkens tussen Heineken en seks in een roeibootje...", "It's fucking close to water"));
        mopList.add(new Mop("Het is oranje en als het regent is het weg...", "De mannen van de gemeente"));
        mopList.add(new Mop("Het is geel en weegt niet veel...", "Lichtgeel"));
        mopList.add(new Mop("Waarom betaalt een lul geen belastingen?", "Hij is aftrekbaar"));
        mopList.add(new Mop("Het is groen en heeft een gewei...", "Een dophertje"));
        mopList.add(new Mop("Wat is een heks in de woestijn?", "Een sandwich"));
        mopList.add(new Mop("Wat is een jood met een gasfles?", "Een doe-het-zelfer"));
        mopList.add(new Mop("Wat is een jood met twee gasflessen?", "Een die-hard"));
        mopList.add(new Mop("Hoe heet de bovenste steen van een huis?", "De schoorsteen"));
        mopList.add(new Mop("Het is groen en ruikt naar verf...", "Groene verf"));
    }

    public ArrayList<Mop> getMopList() {
        return mopList;
    }

    //add new mop
    public void addMop(Mop nieuweMop){
        mopList.add(nieuweMop);
    }
}
