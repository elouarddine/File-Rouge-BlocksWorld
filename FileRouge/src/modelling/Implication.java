package modelling ;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;




/**
 * Représente une contrainte d'implication de la forme (v1 Appartient à  S1) -> (v2 Appartient à S2).
 */
public class Implication implements Constraint {
   
   private Variable v1,v2;
   private Set<Object> s1,s2;
  

   
   /**
    * Constructeur de la classe Implication 
    *
    * @param v1 La première variable impliquée dans l'implication.
    * @param s1 Le sous-ensemble du domaine de v1 pour lequel l'implication est testée.
    * @param v2 La seconde variable impliquée dans l'implication.
    * @param s2 Le sous-ensemble du domaine de v2 pour lequel l'implication est testée.
    */
   public Implication(Variable v1,Set<Object> s1,Variable v2,Set<Object> s2){ 
	     
	     this.v1=v1;
         this.v2=v2;
         this.s1=s1;
         this.s2=s2;
     
     }

   /**
    * Retourne la première variable impliquée dans l'implication.
    *
    * @return La variable v1.
    */
   public Variable getV1(){return this.v1;}
   /**
    * Retourne la seconde variable impliquée dans l'implication.
    *
    * @return La variable v2.
    */
   public Variable getV2(){return this.v2;}
   /**
    * Retourne le sous-ensemble du domaine de v1 (S1).
    *
    * @return Un ensemble d'objets représentant S1.
    */
   public Set<Object> getS1(){return this.s1;}
   /**
    * Retourne le sous-ensemble du domaine de v2 (S2).
    *
    * @return Un ensemble d'objets représentant S2.
    */
   public Set<Object> getS2(){return this.s2;}

   
   /**
    * Retourne l'ensemble des variables impliquées dans cette contrainte (v1 et v2).
    *
    * @return Un ensemble de deux variables (v1 et v2).
    */
    @Override 
    public Set<Variable> getScope(){ 
 	
	   return new HashSet<Variable>(){private static final long serialVersionUID = 1L;
       {add(v1); add(v2);}};
     
    }

    /**
     * Vérifie si la contrainte d'implication est satisfaite par une instanciation donnée.
     *
     * @param instanciation Une map associant chaque variable à sa valeur.
     * @return true si l'implication est respectée, c'est-à-dire que si v1 est dans S1, v2 doit être dans S2, sinon la contrainte est toujours satisfaite.
     * @throws IllegalArgumentException Si l'instanciation ne fournit pas une valeur pour chaque variable du scope.
     */
    @Override  
    public boolean isSatisfiedBy(Map<Variable,Object> instanciation) throws IllegalArgumentException{  
	 
	   for(Variable var : getScope()) { 
  	   
           if(!(instanciation.containsKey(var)) || !(instanciation.get(var) != null)) {
               throw new IllegalArgumentException("y'a au moins une variable du scope de la contrainte qui n'as pas de valeur") ;
      
           }
	   }
           
            
        Object o= instanciation.get(v1);
        if(s1.contains(o))
         return s2.contains(instanciation.get(v2)) ;
        return true;
     }



}




