package fr.cyrilneveu.craftorium.api.recipe.process;

import fr.cyrilneveu.craftorium.api.substance.Substance;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class AProcess {
    protected final String name;
    protected final List<Consumer<Substance>> recipes = new ArrayList<>();
    protected boolean closed = false;

    public AProcess(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected abstract void addRecipes();

    public final void registerRecipes(Substance substance) {
        if (!closed) {
            addRecipes();
            closed = true;
        }

        recipes.forEach(r -> r.accept(substance));
    }
}
