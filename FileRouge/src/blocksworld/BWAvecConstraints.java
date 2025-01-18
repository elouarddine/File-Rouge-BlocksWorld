package blocksworld;

import java.util.*;

import modelling.*;

/**
 * Cette classe represente un monde de block avec des contraints, héritant de la classe BlockWorld 
 */
public class BWAvecConstraints extends BlockWorld{

    //ensemble de constraints pour vérifier que chaque couple de blocks b et b', la variable onb est defferente de onb'
    protected Set<Constraint> onBDiffConstraints;   
    //ensemble de constraints pour vérifier que chaque couple de blocks b et b', si onb==b' la variable fixedb' est egale a "true"
    protected Set<Constraint> fixedBConstraints;
    //ensemble de constraints pour vérifier que chaque block b et pile p,  si onb==p la variable freep est egale a "false"
    protected Set<Constraint> freePConstraints;
    
    /**
     * Constructeur de la classe qui permet d'instancier avec un nombre de blocs et un nombre de piles, et à laquelle on pourra
     * demander l’ensemble de toutes les contraintes spécifiées ci-dessus.
     *
     * @param nbBlocks Le nombre de blocks.
     * @param nbPiles Le nombre de piles.
     * 
     * le constructeur va preparer tous les constraints necessaires pour verifier que ce monde de blocks est conforme 
     */
    public BWAvecConstraints(int nbBlocs, int nbPiles) {
        super(nbBlocs, nbPiles);

        this.onBDiffConstraints = new HashSet<>();
        this.fixedBConstraints = new HashSet<>();
        this.freePConstraints = new HashSet<>();

        for(int i=0; i < nbBlocs; i++) {
            for(int j=-nbPiles; j < nbBlocs; j++) {
                Set<Object> domaineB1=new HashSet<>();
                domaineB1.add(j);
                Set<Object> domainAutorise=new HashSet<>();
                if(j>=0 && i!=j){
                    domainAutorise.add(true);
                    this.fixedBConstraints.add(new Implication(this.onB.get(i),domaineB1,this.fixedB.get(j),domainAutorise));
                }
                if(j<0){
                    domainAutorise.add(false);
                    this.freePConstraints.add(new Implication(this.onB.get(i),domaineB1,this.freeP.get(-j),domainAutorise));
                }
                if(j>=0 && i<j){
                     this.onBDiffConstraints.add(new DifferenceConstraint(this.onB.get(i), this.onB.get(j)));
                }
            }
        }
    }

    /**
     * Retourne l'ensemble de tous les constraints pour ce monde de blocks.
     *
     * @return L'ensemble de tous les constraints sous forme d'une (Set).
     */
    public Set<Constraint> getConstraints() {
        Set<Constraint> res = new HashSet<>(this.onBDiffConstraints);
        res.addAll(this.fixedBConstraints);
        res.addAll(this.freePConstraints);
        return res;
    }

    /**
     * Retourne l'ensemble des constraints "onBDiffConstraints".
     *
     * @return L'ensemble des constraints "onBDiffConstraints" sous forme d'une (Set).
     */
    public Set<Constraint> getOnBDiffConstraints() {
        return this.onBDiffConstraints;
    }

    /**
     * Retourne l'ensemble des constraints "fixedBConstraints".
     *
     * @return L'ensemble des constraints "fixedBConstraints" sous forme d'une (Set).
     */
    public Set<Constraint> getFixedBConstraints() {
        return this.fixedBConstraints;
    }

    /**
     * Retourne l'ensemble des constraints "freePConstraints".
     *
     * @return L'ensemble des constraints "freePConstraints" sous forme d'une (Set).
     */
    public Set<Constraint> getFreePConstraints() {
        return this.freePConstraints;
    }

    /**
     * Retourne un boolean qui represente est-ce ce l'etat du monde de blocks respect tous les constraints 
     * 
     * @param state une etat de monde de blocks
     * @return "true" si l'etat respect tous les constraints, "false" sinon.
     */
    public boolean isAllSatisfiedBy(Map<Variable, Object> state) {
        for(Constraint c: this.getConstraints()) {
            if(!c.isSatisfiedBy(state)) {
                return false;
            }
        }
        return true;
    }

    /**
     * transforme l'etat de la form (variable,valeur) a une forme liste des piles .
     * 
     * @param state une etat de monde de blocks
     * @return l'etat "state" sous forme List<List<Integer>  
     */
    public List<List<Integer>> stateToListForm(Map<Variable, Object> state){

        List<List<Integer>> listState = new ArrayList<>();

        for(int i=-1; i>=-nbPiles;i--){
            List<Integer> pileContent= new ArrayList<>();
            int currentValue = i;
            boolean moveOn = true;
            int pileSize=0;
            while(moveOn){
                for(Map.Entry<Variable, Object> mapentry :state.entrySet()){
                   if((mapentry.getKey().getName().startsWith("on")) && (Integer)mapentry.getValue()==currentValue){
                        int currentPos=Integer.parseInt(mapentry.getKey().getName().substring(2));
                        pileContent.add(currentPos);
                        currentValue=currentPos;
                    }
                }
                if(pileSize != pileContent.size()){
                    pileSize++;
                }
                else{
                    moveOn=false;
                }
            }
            listState.add(pileContent);
        }
        return listState;
    }


    /**
     * Retourne une représentation textuelle de la classe.
     *
     * @return une chaîne de caractères représentant l'objet au format :
     *         "BWAvecConstraints {nbBlocs=valeur, nbPiles=valeur, constraints=valeur}"
     */
    @Override
    public String toString() {
        String res = "BWAvecConstraints {nombreBlocs=" + getNbBlocks() + ", nombrebPiles=" + getNbPiles() + ", constraints=" + this.getConstraints() + "}";
        return res;
    }





}