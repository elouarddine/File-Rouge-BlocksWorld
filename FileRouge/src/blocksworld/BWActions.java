package blocksworld;

import java.util.*;

import modelling.*;
import planning.*;

/**
 * Cette classe prépare tous les action possible dans un monde de blocks, 
 * la classe herite de la classe BWAvecConstraints
 */
public class BWActions extends BWAvecConstraints{

    //ensemble des tous les actions possibles pour ce monde de blocks
    protected Set<Action> actions;


    /**
     * Constructeur de la classe.
     *
     * @param nbBlocks Le nombre de blocks.
     * @param nbPiles Le nombre de piles.
     * 
     * le constructeur va preparer tous actions possible dans ce monde de blocks  
     */
    public BWActions(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.actions = new HashSet<>();

        //pour tout les blocks
        for(int i = 0; i < nbBlocks; i++) {
            //pour tous les positions possible pour chaque block 
            for(int j = -nbPiles; j < nbBlocks; j++) {
                //pour tous les destinations possible de chaque block 
                if(i == j) {
                    continue;
                }
                for(int k = -nbPiles; k < nbBlocks; k++) {

                    if(k==j || k==i){
                        continue;
                    }

                    Map<Variable, Object> pre = new HashMap<>();
                    Map<Variable, Object> post = new HashMap<>();
                    
                    // les precondition
                    pre.put(this.onB.get(i), j);
                    pre.put(this.fixedB.get(i), false);
                    if(k < 0)
                        pre.put(this.freeP.get(-k), true);
                    else
                        pre.put(this.fixedB.get(k), false);
                    
                    //les effets
                    post.put(this.onB.get(i), k);
                    if(j < 0)
                        post.put(this.freeP.get(-j), true);
                    else
                        post.put(this.fixedB.get(j), false);
                    if(k < 0)
                        post.put(this.freeP.get(-k), false);
                    else
                        post.put(this.fixedB.get(k), true);

                    Action action = new BasicAction(pre, post, 1);
                    this.actions.add(action);
                }
            }            
        }
    }

    /**
     * Retourne l'ensemble des actions "actions".
     *
     * @return L'ensemble des actions "actions" sous forme d'une (Set).
     */
    public Set<Action> getActions() {
        return this.actions;
    }
    
}