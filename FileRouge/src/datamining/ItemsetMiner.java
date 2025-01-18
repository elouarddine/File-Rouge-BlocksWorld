package datamining;

import java.util.Set;

/**
 * Interface pour extraire les itemsets fréquents dans une base de données.
 */
public interface ItemsetMiner {

    /**
     * Retourne la base de données associée.
     * @return une instance de BooleanDatabase.
     */
    BooleanDatabase getDatabase();

    /**
     * Extrait les itemsets ayant au moins la fréquence minimale spécifiée.
     * @param frequency la fréquence minimale (entre 0.0 et 1.0).
     * @return un ensemble d'itemsets ayant une fréquence supérieure ou égale à la fréquence minimale.
     */
    Set<Itemset> extract(float minFrequency);
}
