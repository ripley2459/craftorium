package fr.cyrilneveu.craftorium.api.recipe;

import fr.cyrilneveu.craftorium.api.substance.Substance;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static fr.cyrilneveu.craftorium.common.recipe.Processes.PROCESSES_REGISTRY;

public abstract class AProcess {
    protected final String name;
    protected final List<Consumer<Substance>> recipes = new ArrayList<>();
    protected boolean closed = false;

    public AProcess(String name) {
        this.name = name;
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
