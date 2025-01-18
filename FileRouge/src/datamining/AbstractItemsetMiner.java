package datamining;

import java.util.Set;
import java.util.Comparator;
import java.util.List;
import modelling.BooleanVariable;

/**
 * Classe abstraite pour l'extraction d'itemsets fréquents, implémentant ItemsetMiner.
 * Elle fournit des méthodes pour accéder à la base et calculer la fréquence d'un itemset.
 */
public abstract class AbstractItemsetMiner implements ItemsetMiner {

    protected BooleanDatabase dataBase;

    public static final Comparator<BooleanVariable> COMPARATOR = 
        (var1, var2) -> var1.getName().compareTo(var2.getName());

    /**
     * Constructeur de la classe abstraite prenant une base de données en paramètre.
     * @param base une instance de BooleanDatabase représentant la base de données.
     */
    public AbstractItemsetMiner(BooleanDatabase dataBase) {
        this.dataBase = dataBase;
    }

    /**
     * Retourne la base de données associée.
     * @return la base de données de type BooleanDatabase.
     */
    public BooleanDatabase getDatabase() {
        return this.dataBase;
    }

    /**
     * Calcule la fréquence d'apparition d'un ensemble donné d'items (motif) dans la base.
     * @param motif un ensemble d'items de type Set<BooleanVariable>.
     * @return la fréquence d'apparition du motif, entre 0.0 et 1.0.
     */
    public float frequency(Set<BooleanVariable> items) {
       
    	List<Set<BooleanVariable>> transactions = this.dataBase.getTransactions();

        if (transactions.isEmpty()) {
            return 0.0f;
        }

        int count = 0;

        for (Set<BooleanVariable> transaction : transactions) {
            if (transaction.containsAll(items)) {
                count++;
            }
        }

        return (float) count / (float) transactions.size();
    }

    /**
     * Méthode toString pour représenter la base de données.
     * @return une chaîne représentant la base de données.
     */
    @Override
    public String toString() {
        return "AbstractItemsetMiner{" + "base=" + dataBase + "}";
    }
}
