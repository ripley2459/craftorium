package fr.cyrilneveu.craftorium.api.substance.property;

import com.google.common.collect.ImmutableSet;
import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.SubstanceStack;
import fr.cyrilneveu.craftorium.api.utils.Utils;

import javax.annotation.Nullable;
import java.util.Set;

public final class Composition {
    private final Set<SubstanceStack> composition, possible;
    @Nullable
    private final Element element;
    private final String formula;

    public Composition(Element element) {
        this(ImmutableSet.of(), ImmutableSet.of(), element);
    }

    public Composition(Set<SubstanceStack> composition, Set<SubstanceStack> possible) {
        this(composition, possible, null);
    }

    private Composition(Set<SubstanceStack> composition, Set<SubstanceStack> possible, @Nullable Element element) {
        this.composition = composition;
        this.possible = possible;
        this.element = element;
        this.formula = initFormula();
    }

    private String initFormula() {
        StringBuilder formula = new StringBuilder();
        if (isNative())
            formula = new StringBuilder(element.getSymbol());
        else {
            for (SubstanceStack substanceStack : composition) {
                Composition subComposition = substanceStack.getSubstance().getComposition();
                if (substanceStack.getAmount() > 1) {
                    if (!subComposition.getFormula().isEmpty()) {
                        String inner = subComposition.getFormula();
                        if (subComposition.getFormula().length() > 2)
                            inner = "(" + inner + ")";
                        formula.append(inner).append(substanceStack.getAmount());
                    }
                } else formula.append(subComposition.getFormula());
            }
        }

        return Utils.numbersToDown(formula.toString());
    }

    public Set<SubstanceStack> getComposition() {
        return composition;
    }

    public Set<SubstanceStack> getPossible() {
        return possible;
    }

    @Nullable
    public Element getElement() {
        return element;
    }

    public String getFormula() {
        return formula;
    }

    public boolean isNative() {
        return element != null;
    }

    public boolean isComposite() {
        return !composition.isEmpty() || !possible.isEmpty();
    }
}
