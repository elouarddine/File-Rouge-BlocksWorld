package blocksworld;

import java.util.*;

import modelling.*;


/**
 * Cette classe prépare les constraints necessaires pour qu'un monde de blocks sera régulière
 */
public class BWReguliereConstraints{

    //ensemble de constraints pour vérifier qu'un etat du monde de blocks est réguliere
    private Set<Constraint> regConstraints;
    
    private BWAvecConstraints bw;
    /**
     * Constructeur de la classe qui prepare tous les constraints necessaires pour verifier qu'un etat du monde de blocks est reguliere .
     *
     * @param bw un monde de blocks.
     * 
     */
    public BWReguliereConstraints(BWAvecConstraints bw) {

        this.bw = bw;
    	this.regConstraints = new HashSet<>();
        
        Set<Object> domainePiles = new HashSet<>();
       
        int nbBlocks=bw.getNbBlocks();
        int nbPiles=bw.getNbPiles();
        
        for(int i=-nbPiles; i < 0; i++) {
            domainePiles.add(i);
        }

        for(int i=0; i<nbBlocks; i++) {
            
        	Variable onB1 = bw.getVariablesOnB().get(i);
            
        	for(Object o: onB1.getDomain()) {
            
        		Integer b2 = (Integer) o;
                
        		if(b2 >= 0) {
                    
        			Integer ecart = b2 - i;
                    Integer blockAutorise = b2 + ecart;
                    Variable onB2 = bw.getVariablesOnB().get(b2);
                    Set<Object> domaineAutorise = new HashSet<>(domainePiles);
                    
                    if(blockAutorise < nbBlocks && blockAutorise >= 0) {
                        domaineAutorise.add(blockAutorise);
                    }
                
                    Set<Object> onB1Domaine = Set.of(b2);
                    Constraint c = new Implication(onB1, onB1Domaine, onB2, domaineAutorise);
                    this.regConstraints.add(c);
                }
            }
        }


    }

    /**
     * Retourne l'ensemble des constraints "regConstraints".
     *
     * @return L'ensemble des constraints "regConstraints" sous forme d'une (Set).
     */
    public Set<Constraint> getRegConstraints() {

        return this.regConstraints;
    }

    /**
     * Retourne un boolean qui represente est-ce ce l'etat du monde de blocks est reguliere
     * 
     * @param state une etat de monde de blocks
     * @return "true" si l'etat respect tous les constraints "regConstraints", "false" sinon.
     */
    public boolean isAllSatisfiedBy(Map<Variable, Object> state) {
        for(Constraint c: this.regConstraints) {
            if(!c.isSatisfiedBy(state)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne une représentation textuelle de la classe.
     *
     * @return une chaîne de caractères représentant l'objet au format :
     *         {nombreBlocs=" + nbBlocs + ", nombrePiles=" + nbPiles + ", constraints=" + this.getConstraints() + "}";
     */
    @Override
    public String toString() {
       String res = "BWReguliereConstraints [nombreBlocs=" + this.bw.getNbBlocks() + ", nombrePiles=" + this.bw.getNbPiles() + ", constraints=" + getRegConstraints() + "]";
       return res;
    }




}