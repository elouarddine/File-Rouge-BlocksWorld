package modelling ;

import java.util.HashSet;
import java.util.Set;



/**
 * Cette classe représente une variable booléenne, héritant de la classe Variable. 
 * Son domaine est automatiquement initialisé à {true, false}.
 */
public class BooleanVariable extends Variable {

    public String nom;
    public Set<Boolean> domaine;
    
    
    /**
     * Constructeur de la classe BooleanVariable.
     * Ce Constructeur initialise le domaine de la BooleanVariable à l'ensemble {true, false}.
     *
     * @param n Le nom de la variable booléenne.
     */
    
     public BooleanVariable(String n)
    {  
       super(n,new HashSet<Object>(){private static final long serialVersionUID = 1L;

	{
       	add(true);
       	add(false);
       }});
       
    }
       
       
       

 





























}

