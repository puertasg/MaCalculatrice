package macalculatrice;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author gauthier
 */
public class MaCalculatrice {
    
    public static void main(String[] args) {
        int res = 0;
        List<String> l = new ArrayList<String>();
        
        //Création d'un ArrayList à partir des arguments de la console
        for(int i = 0; i < args.length; i++)
        {
            l.add(args[i]);
        }
        
        while(l.size() != 1)
        {
            //Recherche d'opérateurs prioritaires avant de lancer le calcul normalement
            for (EnumPrio ep : EnumPrio.values())
            {
                calcul_prio(l, ep.toString());
            }
            
            res = calcul(l);
            l.subList(0, 3).clear();
            l.add(0, Integer.toString(res));
        }
        
        System.out.println("Le résutat est : " + l.get(0));
    }
    
    public static void calcul_prio(List<String> list, String prio)
    {
        int indexPrio = list.indexOf(prio);
        
        if(indexPrio != -1)
        {
            //Traitement spécifique pour les paranthèses, afin de les supprimer avec leur contenu après le calcul
            if(StringUtils.equals(prio, "("))
            {
                //On récupère l'endroit où se ferme la parathèse
                int indexFinPrio = list.indexOf(")");
                //Extraction d'une sous liste contenant les nombres et l'opérande entre parathèses
                List<String> sousListe = list.subList(indexPrio+1, indexFinPrio+1);
                int res = calcul(sousListe);
                list.subList(indexPrio, indexFinPrio+1).clear();
                list.add(indexPrio, Integer.toString(res));
            }
            //Sinon on supprime ce qu'il y a avant et après l'opérande prioritaire
            else
            {
                List<String> sousListe = list.subList(indexPrio-1, indexPrio+2);
                int res = calcul(sousListe);
                list.subList(indexPrio-1, indexPrio+2).clear();
                list.add(indexPrio-1, Integer.toString(res));
            }
        }
    }
    
    public static int calcul(List<String> l)
    {
        Calculatrice calc = new Calculatrice();
        
        int a;
        int b;
        int res = 0;
        String operande = (String) l.get(1);
            
        a = Integer.parseInt(l.get(0));
        b = Integer.parseInt(l.get(2));
            
        switch(operande)
        {
            case"+":
                res = calc.addition(a, b);
                break;
            case"-":
                res = calc.soustraction(a, b);
                break;
            case"*":
                res = calc.multiplication(a, b);
                break;
            case"/":
                res = calc.division(a,b);
                break;
        }
        
        return res;
    }
}
