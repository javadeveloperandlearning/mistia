/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.model.sort;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import pe.com.cablered.mistia.model.Cuadrilla;

/**
 *
 * @author javadeveloper
 */
public class CombinacionCuadrillaSort1 implements Comparator<Cuadrilla> {

    @Override
    public int compare(Cuadrilla o1, Cuadrilla o2) {

        Map<Integer, Double> mppromedio1 = o1.getCompetenciasPromedios();
        Map<Integer, Double> mppromedio2 = o2.getCompetenciasPromedios();
        int i = 0;
        Set<Integer> keys1 = mppromedio1.keySet();
        for (Integer k1 : keys1) {
            if (mppromedio1.get(k1) == null) {
                return 0;
            }
        }

        Set<Integer> keys2 = mppromedio2.keySet();

        for (Integer k2 : keys2) {
            if (mppromedio2.get(k2) == null) {
                return 0;
            }
        }

        Set<Integer> keys = mppromedio1.keySet();
        for (Integer k : keys) {
            Double prom1 = mppromedio1.get(k);
            if (prom1 == null) {
                return 0;
            }

            Double prom2 = mppromedio2.get(k);
            if (prom2 == null) {
                return 0;
            }

            i = prom2.compareTo(prom1);
            if (i != 0) {
                return i;
            }
        }
        return 0;
    }

}
