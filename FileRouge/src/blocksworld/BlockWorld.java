package blocksworld;

import java.util.*; 
import modelling.*;


/**
 * Cette classe represente un monde de block avec un nombre de block et un nombre de piles et 3 ensembles de variable.
 */
public class BlockWorld {


    protected int nbBlocks, nbPiles;
    //l'ensemble de variables qui attribut a chaque block une variable "onB" qui va represente le block ou la pile sur lequel ce block est pose  
    protected Map<Integer, Variable> onB;    
    //l'ensemble de variables qui attribut a chaque block une variable boolean "fixedB" qui va indiquer est-ce le block est deplaçable     
    protected Map<Integer, BooleanVariable> fixedB;
    //l'ensemble de variables qui attribut a chaque pile une variable boolean "freeP" qui va indiquer est-ce la pile est libre
    protected Map<Integer, BooleanVariable> freeP;


    /**
     * Constructeur de la classe BlockWorld qui permet d'instancier cette classe en spécifiant un nombre de blocs et un nombre de piles.
     *
     * @param nbBlocks Le nombre de blocks.
     * @param nbPiles Le nombre de piles.
     * 
     * le constructeur va preparer tous les variables necessaires pour representer des etats de ce monde de blocks 
     */
    public BlockWorld(int nbBlocks, int nbPiles) {

        this.nbBlocks=nbBlocks;
        this.nbPiles=nbPiles;

        this.onB=new HashMap<>();
        this.fixedB=new HashMap<>();
        this.freeP=new HashMap<>();

        Set<Object> domaine = new HashSet<>();
        for (int i = -nbPiles; i < nbBlocks; i++) {
            domaine.add(i);
        }

        for (int i = 0; i < nbBlocks; i++) {
            Set<Object> domaineI=new HashSet<>(domaine);
            domaineI.remove(i);
            this.onB.put(i, new Variable("on"+i,domaineI));
            this.fixedB.put(i, new BooleanVariable("fixed"+i+""));
        }

        for (int i = 1; i <= nbPiles; i++) {
            this.freeP.put(i, new BooleanVariable("-"+i));
        }
    }

    /**
     * Retourne l'ensemble des variables "onB".
     *
     * @return L'ensemble des variables "onB" sous forme d'une (Map).
     */
    protected Map<Integer, Variable> getVariablesOnB() {
        return this.onB;
    }
    
    /**
     * Retourne l'ensemble des variables "fixedB".
     *
     * @return L'ensemble des variables "fixedB" sous forme d'une (Map).
     */
    protected Map<Integer, BooleanVariable> getVariablesFixedB() {
        return this.fixedB;
    }

    /**
     * Retourne l'ensemble des variables "freeP".
     *
     * @return L'ensemble des variables "freeP" sous forme d'une (Map).
     */
    protected Map<Integer, BooleanVariable> getVariablesFreeP() {
        return this.freeP;
    }

    /**
     * Retourne l'ensemble de tous les variables ("fixedB" et "onB" et "freeP") .
     *
     * @return L'ensemble de tous les variables sous forme d'une (Map).
     */
    public Set<Variable> getVariables() {
        Set<Variable> res = new HashSet<>(this.onB.values());
        res.addAll(this.fixedB.values());
        res.addAll(this.freeP.values());
        return res;
    }

    /**
     * Retourne le nombre de blocks.
     *
     * @return le nombre de blocks sous forme de d'entier.
     */
    public int getNbBlocks() {
        return this.nbBlocks;
    }

    /**
     * Retourne le nombre de piles.
     *
     * @return le nombre de blocks sous forme d'entier.
     */
    public int getNbPiles() {
        return this.nbPiles;
    }
    /**
     * Retourne une représentation textuelle de la classe.
     *
     * @return une chaîne de caractères représentant la classe.
     */
    @Override
    public String toString() { 
        return "Monde de blocks avec "+this.nbBlocks+" block et "+this.nbPiles+" pile";
    }

}