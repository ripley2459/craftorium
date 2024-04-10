package fr.cyrilneveu.craftorium.api.substance.property;

import fr.cyrilneveu.craftorium.api.substance.Element;
import fr.cyrilneveu.craftorium.api.substance.SubstanceStack;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public final class Composition {
    private final Set<SubstanceStack> composition, possible;
    @Nullable
    private final Element element;

    public Composition(Element element) {
        this(new HashSet<>(), new HashSet<>(), element);
    }

    public Composition(Set<SubstanceStack> composition, Set<SubstanceStack> possible) {
        this(composition, possible, null);
    }

    private Composition(Set<SubstanceStack> composition, Set<SubstanceStack> possible, @Nullable Element element) {
        this.composition = composition;
        this.possible = possible;
        this.element = element;
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
}
