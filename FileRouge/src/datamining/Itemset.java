package datamining;

import java.util.Set;
import modelling.BooleanVariable;

/**
 * Classe représentant un itemset avec sa fréquence d'apparition.
 */
public class Itemset {

    private Set<BooleanVariable> items; 
    private float frequency;  

    /**
     * Constructeur de l'itemset.
     * @param items l'ensemble des items dans l'itemset.
     * @param frequence la fréquence de l'itemset (entre 0.0 et 1.0).
     */
    public Itemset(Set<BooleanVariable> items, float frequency) {
        this.items = items;
        this.frequency = frequency;
    }

    /**
     * Accesseur pour récupérer l'ensemble des items de l'itemset.
     * @return un Set contenant les items.
     */
    public Set<BooleanVariable> getItems() {
        return this.items;
    }

    /**
     * Accesseur pour récupérer la fréquence de l'itemset.
     * @return la fréquence de l'itemset sous forme de float.
     */
    public float getFrequency() {
        return this.frequency;
    }

    /**
     * Méthode toString pour afficher l'itemset.
     * @return une chaîne représentant l'ensemble des items et la fréquence.
     */
    @Override
    public String toString() {
        return "Itemset{" +
                "items=" + items +
                ", frequence=" + frequency +
                "}\n";
    }
}
