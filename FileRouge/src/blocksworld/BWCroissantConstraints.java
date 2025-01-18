package blocksworld;

import java.util.*;

import modelling.*;

/**
 * Cette classe prépare les constraints necessaires pour qu'un etat du monde de blocks sera croissante
 */
public class BWCroissantConstraints{


    //ensemble de constraints pour vérifier qu'un etat du monde de blocks est croissante
    protected Set<Constraint> croissantConstraints;
    
    /**
     * Constructeur de la classe.
     *
     * @param bw un monde de blocks.
     * 
     * le constructeur va preparer tous les constraints necessaires pour verifier qu'un etat du monde de blocks est reguliere 
     */
    public BWCroissantConstraints(BWAvecConstraints bw) {

        this.croissantConstraints = new HashSet<>();
        Set<Object> domainePiles = new HashSet<>();
        int nbBlocks=bw.getNbBlocks();
        int nbPiles=bw.getNbPiles();
        for(int i=-nbPiles; i < 0; i++) {
            domainePiles.add(i);
        }
        Set<Object> domaineAutorise = new HashSet<>(domainePiles);
        for(int i=0; i<nbBlocks; i++) {
            Set<Object> domaineAutoriseI = new HashSet<>(domaineAutorise);
            Constraint c = new UnaryConstraint(bw.getVariablesOnB().get(i), domaineAutoriseI);
            this.croissantConstraints.add(c);
            domaineAutorise.add(i);
        }


    }

    /**
     * Retourne l'ensemble des constraints "croissantConstraints".
     *
     * @return L'ensemble des constraints "croissantConstraints" sous forme d'une (Set).
     */
    public Set<Constraint> getCroissantConstraints() {

        return this.croissantConstraints;
    }

    /**
     * Retourne un boolean qui represente est-ce ce l'etat du monde de blocks est croissante
     * 
     * @param state une etat de monde de blocks
     * @return "true" si l'etat respect tous les constraints "croissantConstraints", "false" sinon.
     */
    public boolean isAllSatisfiedBy(Map<Variable, Object> state) {
        for(Constraint c: this.getCroissantConstraints()) {
            if(!c.isSatisfiedBy(state)) {
                return false;
            }
        }
        return true;
    }
}