package datamining;

import java.util.Set;



/**
 * Interface qui Définit les méthodes pour extraire des règles d'association à partir d'une base de données transactionnelle binaire.
 */
public interface AssociationRuleMiner {

	 /**
     * Retourne la base de données transactionnelle binaire utilisée par ce miner de règles d'association.
     *
     * @return une instance de BooleanDatabase représentant la base de données
     */
	public BooleanDatabase getDatabase();
	
	/**
     * Extrait l'ensemble des règles d'association ayant une fréquence et une confiance supérieures
     * aux seuils spécifiés.
     *
     * @param frequency la fréquence minimale pour qu'une règle soit incluse dans le résultat
     * @param confidence la confiance minimale pour qu'une règle soit incluse dans le résultat
     * @return un ensemble de règles d'association (Set<AssociationRule>) répondant aux critères de fréquence et de confiance
     */
	public Set<AssociationRule> extract(float frequency , float confiance);


}
