package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat;

    public Reponse(String motSecret) {
        this.motSecret = motSecret;
        this.resultat = new ArrayList<>();
    }

    // on récupère la lettre à la position dans le résultat
    public Lettre lettre(int position) {
        return resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String motPropose) {
        resultat.clear();
        resultat.addAll(
            IntStream.range(0, motPropose.length())
                .mapToObj(i -> evaluerLettre(motPropose.charAt(i), i))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
        );
    }

    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(lettre -> lettre == Lettre.PLACEE);
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

    // renvoie le statut du caractère
    private Lettre evaluerLettre(char lettreProposee, int position) {
        if (motSecret.charAt(position) == lettreProposee) {
            return Lettre.PLACEE;
        } else if (motSecret.indexOf(lettreProposee) != -1) {
            return Lettre.NON_PLACEE;
        } else {
            return Lettre.INCORRECTE;
        }
    }
}
