package blocksworld;

import java.util.Map;

import modelling.Variable;
import planning.*;
/**
 * Classe BWHeuristic2 pour estimer le coût basé sur le nombre de blocks mal placées + le nombre de blocks en dessous de premier block mal placé dans chaque pile par rapport à l'état but.
 */
public class BWHeuristic2 implements Heuristic {

    private Map<Variable, Object> etatBut;  
    private BlockWorld bw;

    /**
     * Constructeur prenant en paramètre l'état but.
     * 
     * @param bw Le monde de block concerné
     * @param etatBut L'état final que l'on souhaite atteindre
     */
    public BWHeuristic2(BlockWorld bw,Map<Variable, Object> etatBut) {
        this.etatBut=etatBut;
        this.bw=bw;
    }

    /**
     * Estime le coût d'un plan optimal depuis l'état donné jusqu'au but.
     * 
     * @param etat L'état à partir duquel l'heuristique doit être calculée. Cet état est représenté sous forme d'une Map associant les variables à leurs valeurs.
     * @return Une estimation heuristique du coût (de type float) pour atteindre l'objectif depuis l'état donné.
     */
    @Override
    public float estimate(Map<Variable, Object> etat) {
        float res = 0.0f;
        boolean moveOn;
        boolean startCount;
        float onPileCount=0.0f;
        Variable variable=null;
        int valeur=0;
        int detectChange;
        int currentPos;
        int misplacedCount=0;
        for (int i=-(this.bw.getNbPiles());i<0; i++) {
            onPileCount=0.0f;
            startCount=false;
            moveOn=true;
            currentPos=i;
            while(moveOn){
                detectChange=currentPos;
                for(Map.Entry<Variable, Object> mapentry :etat.entrySet()){
                    if(mapentry.getKey().getName().startsWith("on") && (int)mapentry.getValue()==currentPos){
                        variable = mapentry.getKey();
                        valeur = currentPos;
                        currentPos=Integer.parseInt(variable.getName().substring(2));
                    }
                }
                if(currentPos!=detectChange){
                    if(valeur!=(int)etatBut.get(variable)){
                        misplacedCount++;
                        startCount=true;
                    }
                    if(startCount){
                        onPileCount++;
                    }
                }else
                {
                    moveOn=false;
                }
            }
            res=res+onPileCount;
        }
        return res+misplacedCount;
    }
}
