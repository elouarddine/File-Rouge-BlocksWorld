package blocksworld;

import java.util.*;

import modelling.*;

/**
 * Cette classe represente un monde de block avec des variables booleans  
 * la classe hérite de la classe BlockWorld 
 */
public class  BWVariablesBoolean extends BlockWorld{

    //l'ensemble de variables qui attribue a chaque couple de blocks b1 et b2 une variable boolean qui va indiquer est-ce le block b1 est sur le block b2 
    protected Map<Couple, BooleanVariable> onB1B2;
    //l'ensemble de variables qui attribue a chaque couple de block b1 et de pile p une variable boolean qui va indiquer est-ce le block b est sur la pile p
    protected Map<Couple, BooleanVariable> onTableBP;


    /**
     * Constructeur de la classe.
     *
     * @param nbBlocks Le nombre de blocks.
     * @param nbPiles Le nombre de piles.
     * 
     * le constructeur va preparer tous les variable boolean pour ce monde de blocks  
     */
    public BWVariablesBoolean(int nbBlocks, int nbPiles) {

        super(nbBlocks,nbPiles);

        this.onB1B2=new HashMap<>();
        this.onTableBP=new HashMap<>();

        for (int i = 0; i < nbBlocks; i++) {
            for (int j = -(nbPiles); j < nbBlocks; j++) {
                if(j<0){
                    this.onTableBP.put(new Couple(i,j),new BooleanVariable(i+" on "+j));
                }
                if(j>=0 && j!=i){
                    this.onB1B2.put(new Couple(i,j),new BooleanVariable(i+" on "+j));
                }
            }
        }

    }

    /**
     * Retourne l'ensemble des variables booleans "onB1B2".
     *
     * @return L'ensemble des variables booleans "onB1B2" sous forme d'une (Map).
     */
    public Map<Couple, BooleanVariable> getVariablesOnB1B2() {
        return this.onB1B2;
    }

    /**
     * Retourne l'ensemble des variables booleans "onTableBP".
     *
     * @return L'ensemble des variables booleans "onTableBP" sous forme d'une (Map).
     */
    public Map<Couple, BooleanVariable> getVariablesOnTableBP() {
        return this.onTableBP;
    }

    /**
     * Retourne l'ensemble de tous les variables booleans.
     *
     * @return L'ensemble de tous les variables sous forme d'une (Set).
     */
    public Set<BooleanVariable> getBooleanVariables() {
        Set<BooleanVariable> res = new HashSet<>(this.onB1B2.values());
        res.addAll(this.fixedB.values());
        res.addAll(this.freeP.values());
        res.addAll(this.onTableBP.values());
        return res;
    }

    /**
     * Retourne l'ensemble de tous les variables booleans qui sont a "true" selon une etat donne.
     * @param state une etat de monde du block 
     * @return L'ensemble de variables  booleans sous forme d'une (Set).
     */
    public Set<BooleanVariable> getBooleanVariablesForState(List<List<Integer>> state){
        Set<BooleanVariable> res=new HashSet<>();
        int nbPile=state.size();
        for (int i=0;i<nbPile;i++) {
            if(!state.get(i).isEmpty()){
                res.add(this.onTableBP.get(new Couple(state.get(i).get(0),-(i+1))));
                int nbBlock=state.get(i).size();
                for (int j=0;j<(nbBlock-1);j++) {
                    res.add(this.onB1B2.get(new Couple(state.get(i).get(j+1),state.get(i).get(j))));
                    res.add(this.fixedB.get(j));
                }
            }
            else{
                res.add(this.freeP.get((i+1)));
            }
        }

        return res;
    }

}