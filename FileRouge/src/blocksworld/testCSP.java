package blocksworld;

import modelling.*;
import java.util.*;

import cp.*;
public class testCSP{

    public static void main(String[] args) {
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n######################## Test des résolveur CSP sur un monde de 6 block et 4 piles ########################\n");
        BWAvecConstraints bw=new BWAvecConstraints(6,4);
        BWReguliereConstraints bwReg=new BWReguliereConstraints(bw);
        Set<Constraint> constraints=bw.getConstraints();
        constraints.addAll(bwReg.getRegConstraints());
        Set<Variable> vars=bw.getVariables();

        System.out.println("\n+++++++++++++++++++ BacktrackSolver - Configuration régulière +++++++++++++++++++ \n");

        BacktrackSolver solver2=new BacktrackSolver(vars,constraints);
        long startTime = System.nanoTime();
        Map<Variable,Object> state2=solver2.solve();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state2)));

        System.out.println("\n+++++++++++++++++++ MacSolver - Configuration régulière +++++++++++++++++++ \n");

        MACSolver solver3=new MACSolver(vars,constraints);
        startTime = System.nanoTime();
        Map<Variable,Object> state3=solver3.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state3)));

        System.out.println("\n+++++++++++++++++++ HeuristicMacSolver 1 - Configuration régulière +++++++++++++++++++ \n");

        VariableHeuristic varH=new NbConstraintsVariableHeuristic(constraints,true);
        Random r=new Random(40);
        ValueHeuristic valH=new RandomValueHeuristic(r);
        HeuristicMACSolver solver1=new HeuristicMACSolver(vars,constraints,varH,valH);
        startTime = System.nanoTime();
        Map<Variable,Object> state=solver1.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state)));

        System.out.println("\n+++++++++++++++++++ HeuristicMacSolver 2 - Configuration régulière +++++++++++++++++++ \n");

        VariableHeuristic varH1=new DomainSizeVariableHeuristic(true);
        HeuristicMACSolver solver4=new HeuristicMACSolver(vars,constraints,varH1,valH);
        startTime = System.nanoTime();
        Map<Variable,Object> state4=solver4.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state4)));




        BWCroissantConstraints bwCrois=new BWCroissantConstraints(bw);
        Set<Constraint> constraints2=bw.getConstraints();
        constraints2.addAll(bwCrois.getCroissantConstraints());

        System.out.println("\n+++++++++++++++++++ BacktrackSolver - Configuration Croissante +++++++++++++++++++ \n");

        BacktrackSolver solver5=new BacktrackSolver(vars,constraints2);
        startTime = System.nanoTime();
        Map<Variable,Object> state5=solver5.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state5)));

        System.out.println("\n+++++++++++++++++++ MacSolver - Configuration Croissante +++++++++++++++++++ \n");

        MACSolver solver6=new MACSolver(vars,constraints2);
        startTime = System.nanoTime();
        Map<Variable,Object> state6=solver6.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state6)));

        System.out.println("\n+++++++++++++++++++ HeuristicMacSolver 1 - Configuration Croissante +++++++++++++++++++ \n");

        
        HeuristicMACSolver solver7=new HeuristicMACSolver(vars,constraints2,varH,valH);
        startTime = System.nanoTime();
        Map<Variable,Object> state7=solver7.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state7)));

        System.out.println("\n+++++++++++++++++++ HeuristicMacSolver 2 - Configuration Croissante +++++++++++++++++++ \n");

        HeuristicMACSolver solver8=new HeuristicMACSolver(vars,constraints2,varH1,valH);
        startTime = System.nanoTime();
        Map<Variable,Object> state8=solver8.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state8)));



        Set<Constraint> constraints3=bw.getConstraints();
        constraints3.addAll(bwCrois.getCroissantConstraints());
        constraints3.addAll(bwReg.getRegConstraints());

        System.out.println("\n+++++++++++++++++++ BacktrackSolver - Configuration Croissante et Régulière +++++++++++++++++++ \n");

        BacktrackSolver solver9=new BacktrackSolver(vars,constraints3);
        startTime = System.nanoTime();
        Map<Variable,Object> state9=solver9.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state9)));

        System.out.println("\n+++++++++++++++++++ MacSolver - Configuration Croissante et Régulière +++++++++++++++++++ \n");

        MACSolver solver10=new MACSolver(vars,constraints3);
        startTime = System.nanoTime();
        Map<Variable,Object> state10=solver10.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state10)));

        System.out.println("\n+++++++++++++++++++ HeuristicMacSolver 1 - Configuration Croissante et Régulière +++++++++++++++++++ \n");

        
        HeuristicMACSolver solver11=new HeuristicMACSolver(vars,constraints3,varH,valH);
        startTime = System.nanoTime();
        Map<Variable,Object> state11=solver11.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state11)));

        System.out.println("\n+++++++++++++++++++ HeuristicMacSolver 2 - Configuration Croissante et Régulière +++++++++++++++++++ \n");

        HeuristicMACSolver solver12=new HeuristicMACSolver(vars,constraints3,varH1,valH);
        startTime = System.nanoTime();
        Map<Variable,Object> state12=solver12.solve();
        endTime = System.nanoTime();
        duration = endTime - startTime;
        durationInMillis = duration / 1_000_000.0;
        System.out.println("Temps d'exécution de l'algorithme : " + durationInMillis + " ms\n");
        System.out.println("Résultat : \n\n"+ testConstraints.afficheEtat(bw.stateToListForm(state12)));

        


        System.out.println("\n######################################## Fin de test #########################################\n");

    }

}