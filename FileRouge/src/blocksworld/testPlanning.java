package blocksworld;

import modelling.*;
import java.util.*;
import javax.swing.*;

import bwmodel.*;
import bwui.*;
import planning.*;

public class testPlanning {

    public static void main(String[] args) {
        
        BWActions bw1=new BWActions(5,3);
        Map<Variable, Object> etatInit = new HashMap<>();
        Map<Variable, Object> etatBut = new HashMap<>();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n######################## Test des planificateur sur un monde de 5 block et 3 piles ########################\n");
        etatInit.put(bw1.getVariablesOnB().get(0), -3);
        etatInit.put(bw1.getVariablesOnB().get(1), 0);
        etatInit.put(bw1.getVariablesOnB().get(2), -2);
        etatInit.put(bw1.getVariablesOnB().get(3), 2);
        etatInit.put(bw1.getVariablesOnB().get(4), -1);


        etatInit.put(bw1.getVariablesFixedB().get(0),true);
        etatInit.put(bw1.getVariablesFixedB().get(1), false);
        etatInit.put(bw1.getVariablesFixedB().get(2), true);
        etatInit.put(bw1.getVariablesFixedB().get(3), false);
        etatInit.put(bw1.getVariablesFixedB().get(4), false);


        etatInit.put(bw1.getVariablesFreeP().get(1), false);
        etatInit.put(bw1.getVariablesFreeP().get(2), false);
        etatInit.put(bw1.getVariablesFreeP().get(3), false);

    
        etatBut.put(bw1.getVariablesOnB().get(0), -1);
        etatBut.put(bw1.getVariablesOnB().get(1), 0);
        etatBut.put(bw1.getVariablesOnB().get(2), 1);
        etatBut.put(bw1.getVariablesOnB().get(3), 2);
        etatBut.put(bw1.getVariablesOnB().get(4), 3);


        etatBut.put(bw1.getVariablesFixedB().get(0), true);
        etatBut.put(bw1.getVariablesFixedB().get(1), true);
        etatBut.put(bw1.getVariablesFixedB().get(2), true);
        etatBut.put(bw1.getVariablesFixedB().get(3), true);
        etatBut.put(bw1.getVariablesFixedB().get(4), false);


        etatBut.put(bw1.getVariablesFreeP().get(1), false);
        etatBut.put(bw1.getVariablesFreeP().get(2), true);
        etatBut.put(bw1.getVariablesFreeP().get(3), true);

        Goal g=new BasicGoal(etatBut);

        System.out.println("\n++++++++++++++++++++++ L'état initiale  +++++++++++++++++++++\n");

        System.out.println(testConstraints.afficheEtat(bw1.stateToListForm(etatInit)));

        System.out.println("\n++++++++++++++++++++++  L'état finale  ++++++++++++++++++++++\n");

        System.out.println(testConstraints.afficheEtat(bw1.stateToListForm(etatBut)));
        
        System.out.println("\n+++++++++++++++++++++++++++  BFS  +++++++++++++++++++++++++++\n");
        BFSPlanner bfs=new  BFSPlanner(etatInit,bw1.getActions(),g);
        bfs.activateNodeCount(true);
        long startTime = System.nanoTime();
        List<planning.Action> BFSplan=bfs.plan();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms");
        System.out.println("Nombre de noeuds explorés : "+ bfs.getExploredNode());
        System.out.println("Nombre d'actions pour atteindre l'état final : "+ BFSplan.size());

        // Planificateur DFS
        System.out.println("\n+++++++++++++++++++++++++++  DFS  +++++++++++++++++++++++++++\n");
        try {
            DFSPlanner dfs=new  DFSPlanner(etatInit,bw1.getActions(),g);
            dfs.activateNodeCount(true);
            startTime = System.nanoTime();
            List<planning.Action> DFSplan=dfs.plan();
            endTime = System.nanoTime();
            duration = endTime - startTime;
            durationInMillis = duration / 1_000_000.0;
            System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms");
            System.out.println("Nombre de noeuds explorés : "+ dfs.getExploredNode());
            System.out.println("Nombre d'actions pour atteindre l'état final : "+ DFSplan.size());
        } catch (StackOverflowError e) {
            System.out.println("Grande profondeur, pas de plan trouver");
        }

        System.out.println("\n+++++++++++++++++++++++ Dijikstra  ++++++++++++++++++++++++\n");
        DijkstraPlanner dijikstra=new  DijkstraPlanner(etatInit,bw1.getActions(),g);
        dijikstra.activateNodeCount(true);
        startTime = System.nanoTime();
        List<planning.Action> dijikstraplan=dijikstra.plan();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms");
        System.out.println("Nombre de noeuds explorés : "+ dijikstra.getExploredNode());
        System.out.println("Nombre d'actions pour atteindre l'état final : "+ dijikstraplan.size());

        
        System.out.println("\n+++++++++++++++++  AStar avec Heuristic1  +++++++++++++++++\n");
        Heuristic h1 = new BWHeuristic1(etatBut);  
        AStarPlanner AStarH1 = new AStarPlanner(etatInit, bw1.getActions(), g, h1);
        AStarH1.activateNodeCount(true);
        startTime = System.nanoTime();
        List<planning.Action> AStarH1plan=AStarH1.plan();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms");
        System.out.println("Nombre de noeuds explorés : "+ AStarH1.getExploredNode());
        System.out.println("Nombre d'actions pour atteindre l'état final : "+ AStarH1plan.size());

        System.out.println("\n+++++++++++++++++  AStar avec Heuristic2 +++++++++++++++++\n");
        Heuristic h2= new BWHeuristic2(bw1,etatBut); 
        AStarPlanner AStarH2 = new AStarPlanner(etatInit, bw1.getActions(), g, h2);
        AStarH2.activateNodeCount(true);
        startTime = System.nanoTime();
        List<planning.Action> AStarH2plan=AStarH2.plan();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms");
        System.out.println("Nombre de noeuds explorés : "+ AStarH2.getExploredNode());
        System.out.println("Nombre d'actions pour atteindre l'état final : "+ AStarH2plan.size());

        System.out.println("\n+++++++++++++++++ Visualisation des plans +++++++++++++++++\n");
        boolean quitter=false;
        Scanner scanner = new Scanner(System.in);
        while(!quitter){
            System.out.println("1- plan BFS ");
            System.out.println("2- plan Dijikstra ");
            System.out.println("3- AStar avec Heuristic 1");
            System.out.println("4- AStar avec Heuristic 2\n");
            System.out.print("Choisissez le numéro de plan que vous voulez visialiser ou entrer '0' pour quiter :");
            int choix = scanner.nextInt();
            int choixMode=1;
            if(choix==1 || choix==2 || choix==3 ||choix==4){
                System.out.println("\n1- mode graphique ");
                System.out.println("2- mode console\n ");
                System.out.print("Choisissez le mode de visiualisation :");
                choixMode = scanner.nextInt();
            }
            switch(choix){
                case 1 :
                    visiualiser(bw1,etatInit,BFSplan,"BFS",choixMode);
                    break;
                case 2 :
                    visiualiser(bw1,etatInit,dijikstraplan,"Dijikstra",choixMode);
                    break;
                case 3 :
                    visiualiser(bw1,etatInit,AStarH1plan,"AStar avec Heuristic 1",choixMode);
                    break;
                case 4 :
                    visiualiser(bw1,etatInit,AStarH2plan,"AStar avec Heuristic 2",choixMode);
                    break;
                default:
                    System.exit(0); 
            }
            
        }
        scanner.close();
    
    
    
    }
    public static void visiualiser(BWActions bw,Map<Variable, Object> etat, List<planning.Action> plan,String titre,int mode){
        System.out.println("+++++++++++++++> Début de simulation de plan.\n");
        if(mode==1){
            int n=5;
            BWIntegerGUI gui = new BWIntegerGUI(n);
            Map<Variable, Object> state=new HashMap<>(etat);
            JFrame frame = new JFrame("Plan trouvé par "+titre);
            BWState<Integer> bwState = makeBWState(state,bw);
            BWComponent<Integer> component = gui.getComponent(bwState);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.add(component);
            frame.setVisible(true);
            //Playing plan
            for (planning.Action a: plan) {
                try { Thread.sleep(1_000); }
                catch (InterruptedException e) { e.printStackTrace(); }
                state=a.successor(state);
                component.setState(makeBWState(state,bw));
            }
        }
        else{
            System.out.println(testConstraints.afficheEtat(bw.stateToListForm(etat)));
            Map<Variable, Object> state=new HashMap<>(etat);
            for(planning.Action a:plan){
                try {
                    state=a.successor(state);
                    Thread.sleep(1000);
                    System.out.println(testConstraints.afficheEtat(bw.stateToListForm(state))+"\n");
                } catch (InterruptedException e) {
                    System.err.println("");
                }
            }
        }
        System.out.println("+++++++++++++++> Fin de simulation de plan.\n");
    }
    public static BWState<Integer> makeBWState(Map<Variable, Object> e,BWActions bw){
        int n=5;
        BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(n);
        for (int b = 0; b < n; b++) {
            Variable onB = bw.getVariablesOnB().get(b); // get instance of Variable for "on_b"
            int under = (int) e.get(onB);
            if (under >= 0) { // if the value is a block (as opposed to a stack)
            builder.setOn(b, under);
            }
        }
        BWState<Integer> state = builder.getState();
        return state;
   }
}
