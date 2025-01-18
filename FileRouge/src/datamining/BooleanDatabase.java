package datamining;

import modelling.BooleanVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Classe représentant une base de données transactionnelle.
 * Elle contient un ensemble d'items et une liste de transactions.
 */
public class BooleanDatabase {

    private Set<BooleanVariable> items;  
    private List<Set<BooleanVariable>> transactions;  

    /**
     * Constructeur de la base de données.
     * @param items l'ensemble des items représentés par des variables booléennes.
     */
    public BooleanDatabase(Set<BooleanVariable> items) {
        this.items = items;
        this.transactions = new ArrayList<>();
    }

    /**
     * Accesseur pour récupérer l'ensemble des items.
     * @return un Set contenant les items.
     */
    public Set<BooleanVariable> getItems() {
        return this.items;
    }

    /**
     * Accesseur pour récupérer la liste des transactions.
     * @return une List de transactions (chaque transaction est un Set d'items).
     */
    public List<Set<BooleanVariable>> getTransactions() {
        return this.transactions;
    }

    /**
     * Ajoute une transaction à la base de données.
     * @param items une transaction représentée par un ensemble d'items.
     */
    public void add(Set<BooleanVariable> transaction) {
        this.transactions.add(transaction);
    }

    /**
     * Méthode toString pour afficher la base de données.
     * @return une chaîne représentant l'ensemble des items et les transactions.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BooleanDatabase:\n");
        sb.append("Items: ").append(items).append("\n");
        sb.append("Transactions:\n");
        for (Set<BooleanVariable> transaction : transactions) {
            sb.append(" - ").append(transaction).append("\n");
        }
        return sb.toString();
    }
}
