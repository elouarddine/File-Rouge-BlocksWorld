package datamining;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import modelling.BooleanVariable;


/**
 * Classe Implémente l'algorithme Apriori pour l'extraction de motifs fréquents dans une base de données transactionnelle binaire.
 * Hérite de la classe AbstractItemsetMiner.
 */
public class Apriori extends AbstractItemsetMiner{

	/**
     * Constructeur de la classe Apriori.
     *
     * @param dataBase la base de données transactionnelle binaire utilisée pour l'extraction de motifs.
     */
	public Apriori(BooleanDatabase dataBase) {
		super(dataBase);
	}

	 /**
     * Extrait les itemsets fréquents de taille 1 (singletons) dans la base de données,
     * en fonction de la fréquence minimale spécifiée.
     *
     * @param minFrequency la fréquence minimale pour qu'un itemset soit considéré comme fréquent.
     * @return un ensemble d'itemsets singletons fréquents avec leur fréquence.
     */
	public Set<Itemset> frequentSingletons(float minFrequency) {
	
		Set<Itemset> itemsetFrequency = new HashSet<>();
		for(BooleanVariable item : getDatabase().getItems()) {
			 float itemFrequence = frequency(Set.of(item));
             if(itemFrequence >= minFrequency) {
            	 itemsetFrequency.add(new Itemset(Set.of(item), itemFrequence));
             }
	     }
	    return itemsetFrequency;

	}

	 /**
     * Combine deux ensembles d'items fréquents en un seul, si possible, en respectant les conditions de compatibilité.
     *
     * @param items1 le premier ensemble trié de variables booléennes.
     * @param items2 le deuxième ensemble trié de variables booléennes.
     * @return un nouvel ensemble trié de variables booléennes combiné, ou null si la combinaison n'est pas possible.
     */
	public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> items1, SortedSet<BooleanVariable> items2){
		
		 if(items1.size() == 0 || items1.size() != items2.size() || items1.last().equals(items2.last()) || !items1.headSet(items1.last()).equals(items2.headSet(items2.last()))) {
	         return null;
	     }
		 
		 SortedSet<BooleanVariable> items = new TreeSet<>(COMPARATOR);
		 items.addAll(items1);
		 items.add(items2.last());
		 return items;
   	
	}

	 /**
     * Vérifie si tous les sous-ensembles de taille k-1, obtenus en enlevant un élément de l'ensemble items,
     * sont présents dans une collection d'ensembles fréquents.
     *
     * @param items l'ensemble d'items de taille k à vérifier.
     * @param itemsCollection la collection d'ensembles fréquents de taille k-1.
     * @return true si tous les sous-ensembles sont fréquents, false sinon.
     */
	public static boolean allSubsetsFrequent(Set<BooleanVariable> items , Collection<SortedSet<BooleanVariable>> itemsCollection){
		
		    SortedSet<BooleanVariable> it = new TreeSet<>(COMPARATOR);
		    it.addAll(items);
	        for(BooleanVariable item: items) {
	        	
	        	it.remove(item);
	            if(!itemsCollection.contains(it))
	                return false;
			    it.add(item);

	        }
	        return true;
	}
	
	/**
     * Extrait les itemsets fréquents de la base de données, en fonction de la fréquence minimale spécifiée.
     * Utilise l'algorithme Apriori pour générer les combinaisons d'items et vérifier leur fréquence.
     *
     * @param minFreq la fréquence minimale pour qu'un itemset soit considéré comme fréquent.
     * @return un ensemble d'itemsets fréquents extraits de la base de données.
     */
    @Override
    public Set<Itemset> extract(float minFreq) {
        
    	Set<Itemset> res = new HashSet<>();
        List<SortedSet<BooleanVariable>> listeItemsFrequents = new LinkedList<>(); 
        res.addAll(frequentSingletons(minFreq));

        for(Itemset single: res) {
            SortedSet<BooleanVariable> itemsFrequents = new TreeSet<>(COMPARATOR);
            itemsFrequents.addAll(single.getItems()); 
            listeItemsFrequents.add(itemsFrequents);
        }

        while(!listeItemsFrequents.isEmpty() && listeItemsFrequents.get(0).size() < getDatabase().getItems().size()) {

            List<SortedSet<BooleanVariable>> newHeight = new LinkedList<>();
            
            for(int i=0; i < listeItemsFrequents.size(); ++i) {
                for(int j=i+1; j < listeItemsFrequents.size(); ++j) {

                    SortedSet<BooleanVariable> combinaison = Apriori.combine(listeItemsFrequents.get(i), listeItemsFrequents.get(j));
                    
                    if(combinaison != null) {
                        float frequenceCourant = this.frequency(combinaison);
                        if(frequenceCourant >= minFreq) {
                            res.add(new Itemset(combinaison, frequenceCourant));
                            newHeight.add(combinaison);
                        }
                    }
                    
                }
            }
            listeItemsFrequents = newHeight;
        }

        return res;
    }

    /**
     * Retourne une représentation textuelle de la classe Apriori.
     *
     * @return une chaîne de caractères représentant la classe Apriori.
     */
	 @Override
	    public String toString() {
	        return "Apriori" + super.toString();
	    }
	

	}
