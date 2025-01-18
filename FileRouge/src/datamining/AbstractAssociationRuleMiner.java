package datamining;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;

/**
 * Classe abstraite qui Implémente l'interface AssociationRuleMiner et fournit des méthodes statiques 
 * pour calculer la fréquence et la confiance d'une règle d'association.
 */
public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner{

	protected BooleanDatabase dataBase;
	
	/**
     * Constructeur de la classe AbstractAssociationRuleMiner.
     * Initialise la base de données pour cet objet de type AssociationRuleMiner.
     *
     * @param dataBase la base de données transactionnelle utilisée pour l'extraction de règles
     */
	public AbstractAssociationRuleMiner(BooleanDatabase dataBase){
		this.dataBase = dataBase;
	}
	
	/**
     * Retourne la base de données transactionnelle associée à cet AssociationRuleMiner.
     *
     * @return la base de données transactionnelle de type BooleanDatabase
     */
	@Override
	public BooleanDatabase getDatabase() {
		return this.dataBase;
	}

	/**
     * Méthode statique qui retourne la fréquence d'un ensemble d'items (itemset) dans un ensemble d'itemsets fréquents.
     * Si l'itemset n'est pas trouvé, elle lance une exception IllegalArgumentException.
     *
     * @param items l'ensemble d'items pour lequel on souhaite connaître la fréquence
     * @param frequent l'ensemble des itemsets fréquents avec leurs fréquences
     * @return la fréquence de l'itemset si trouvé
     * @throws IllegalArgumentException si l'itemset n'est pas présent dans l'ensemble d'itemsets fréquents
     */
	public static float frequency(Set<BooleanVariable> items , Set<Itemset> frequent) {

		for(Itemset itemSet : frequent){
           if(itemSet.getItems().equals(items)){
               	 return itemSet.getFrequency();
   		   }	
		}
        throw new IllegalArgumentException(" l’ensemble d’items n’apparaît pas dans l’ensemble d’itemsets");

	
	}
	
	/**
     * Méthode statique qui calcule la confiance d'une règle d'association.
     * La confiance est définie comme le ratio de la fréquence de l'union de la prémisse et de la conclusion
     * sur la fréquence de la prémisse seule.
     *
     * @param premise l'ensemble d'items formant la prémisse de la règle
     * @param conclusion l'ensemble d'items formant la conclusion de la règle
     * @param frequent l'ensemble des itemsets fréquents avec leurs fréquences
     * @return la confiance de la règle d'association (un float)
     */
	public static float confidence(Set<BooleanVariable> premise , Set<BooleanVariable> conclusion , Set<Itemset> frequent) {

        Set<BooleanVariable> union = new HashSet<>(premise);
        union.addAll(conclusion);

        float frequencyPremise = 0;
        float frequencyUnion = 0;

        for (Itemset itemset : frequent) {
            if (itemset.getItems().equals(premise)) {
                frequencyPremise = itemset.getFrequency();
            }
            if (itemset.getItems().equals(union)) {
                frequencyUnion = itemset.getFrequency();
            }
        }

        if(frequencyPremise > 0 && frequencyUnion >= 0) {
            return frequencyUnion / frequencyPremise;
        }

        return (float) 0;
    	
	}
	
}
