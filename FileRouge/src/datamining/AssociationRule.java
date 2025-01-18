package datamining;

import java.util.Set;

import modelling.BooleanVariable;

/**
 * Classe qui Représente une règle d'association avec une prémisse, une conclusion, une fréquence et une confiance.
 * Utilisée pour l'extraction de règles dans des bases de données transactionnelles binaires.
 */
public class AssociationRule {

	private Set<BooleanVariable> premise ; 
	private Set<BooleanVariable> conclusion ; 
	private float frequency;
	private float confidence ; 
	 
	/**
     * Constructeur de la classe AssociationRule.
     *
     * @param premise l'ensemble d'items formant la prémisse de la règle
     * @param conclusion l'ensemble d'items formant la conclusion de la règle
     * @param frequency la fréquence de la règle dans la base de données
     * @param confidence la confiance associée à la règle d'association
     */
	public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion , float frequency , float confidence){
		this.premise = premise;
		this.conclusion = conclusion; 
		this.frequency = frequency ;
		this.confidence = confidence;
	}
	
	/**
     * Retourne la prémisse de la règle d'association.
     *
     * @return un ensemble de variables booléennes représentant la prémisse
     */
	public Set<BooleanVariable> getPremise(){return this.premise;}
	
	/**
     * Retourne la conclusion de la règle d'association.
     *
     * @return un ensemble de variables booléennes représentant la conclusion
     */
	public Set<BooleanVariable> getConclusion(){return this.conclusion;}
	
	/**
     * Retourne la fréquence de la règle d'association dans la base de données.
     *
     * @return la fréquence de la règle, en tant que float
     */
	public float getFrequency(){return this.frequency;}
	
	/**
     * Retourne la confiance de la règle d'association.
     *
     * @return la confiance de la règle, en tant que float
     */
	public float getConfidence(){return this.confidence;}

	/**
     * Retourne une représentation textuelle de la règle d'association.
     *
     * @return une chaîne de caractères représentant la règle d'association
     */
	@Override
    public String toString() {
        return "Règle d'association: \n\t----> prémisse=" + premise + ",\n\t----> conclusion=" + conclusion + ", fréquence=" + frequency + ", confience=" + confidence + "\n";
    }
}
