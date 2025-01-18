package datamining;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;

public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner{

	
	public BruteForceAssociationRuleMiner(BooleanDatabase dataBase) {
		super(dataBase);
	}


   /**
     * Génère tous les sous-ensembles non vides d'un ensemble d'items donné, sauf l'ensemble complet.
     * 
     * @param items l'ensemble d'items de type Set<BooleanVariable> pour lequel générer les sous-ensembles
     * @return un ensemble de sous-ensembles (Set<Set<BooleanVariable>>), excluant l'ensemble complet
     */
    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items) {
        Set<Set<BooleanVariable>> resultat = new HashSet<>();
        
        if (items.size() <= 1) {
            return resultat;
        }
        
        Set<Set<BooleanVariable>> subsetsCourant = new HashSet<>();

        for (BooleanVariable booleanvar : items) {
            subsetsCourant.add(Set.of(booleanvar));
        }

        int maxSize = items.size() - 1; 

        while (!subsetsCourant.isEmpty()) {
            resultat.addAll(subsetsCourant);

            Set<Set<BooleanVariable>> nextSubsets = new HashSet<>();
            for (Set<BooleanVariable> subset : subsetsCourant) {
                if (subset.size() < maxSize) {
                    for (BooleanVariable booleanvar : items) {
                        if (!subset.contains(booleanvar)) {
                            Set<BooleanVariable> newSubset = new HashSet<>(subset);
                            newSubset.add(booleanvar);
                            nextSubsets.add(newSubset);
                        }
                    }
                }
            }
            subsetsCourant = nextSubsets; 
        }

        return resultat;
    }


    /**
      * Extrait un ensemble de règles d'association fréquentes et de confiance élevée à partir de la base de données.
      * Cette méthode utilise un algorithme d'extraction d'itemsets fréquents, tel que Apriori, pour générer
      * les itemsets fréquents, puis elle génère toutes les règles d'association possibles à partir de ces itemsets.
      * Une règle d'association est ajoutée à l'ensemble final si sa confiance et sa fréquence sont supérieures aux seuils définis.
      *
      * @param frequency La fréquence minimale requise pour qu'un itemset soit considéré comme fréquent. Ce seuil
      *        filtre les itemsets ayant une occurrence inférieure à cette valeur dans la base de données.
      * @param confiance La confiance minimale requise pour qu'une règle d'association soit considérée comme valide.
      *        Ce seuil filtre les règles dont la probabilité conditionnelle du conséquent est inférieure à cette valeur.
      * @return Un ensemble de règles d'association (Set<AssociationRule>) respectant les seuils de fréquence et de confiance donnés.
      *         Chaque règle dans l'ensemble satisfait les conditions de fréquence et de confiance minimales.
      */
	@Override
	public Set<AssociationRule> extract(float frequency, float confiance) {
		ItemsetMiner itemsetFrequent = new Apriori(getDatabase());
        
        Set<Itemset> frequentItemset = itemsetFrequent.extract(frequency);

        Set<AssociationRule> resultat = new HashSet<>();

        for(Itemset x: frequentItemset) {
            for(Set<BooleanVariable> y: allCandidatePremises(x.getItems())) {

                Set<BooleanVariable> setX = new HashSet<>(x.getItems());
                setX.removeAll(y);    

                AssociationRule newCandidate = new AssociationRule(y, setX, x.getFrequency(), AbstractAssociationRuleMiner.confidence(y, setX, frequentItemset));
                
                if( newCandidate.getConfidence() >= confiance && newCandidate.getFrequency() >= frequency ) {                     
                    resultat.add(newCandidate);
                }

            }
            
        }

        return resultat;
    }

}




