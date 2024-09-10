package fr.cyrilneveu.craftorium.common.recipe;

import fr.cyrilneveu.craftorium.api.recipe.process.AProcess;
import fr.cyrilneveu.craftorium.api.utils.Registry;
import fr.cyrilneveu.craftorium.common.recipe.process.DefaultProcess;
import fr.cyrilneveu.craftorium.common.recipe.process.FlintProcess;
import fr.cyrilneveu.craftorium.common.recipe.process.WoodProcess;

public final class Processes {
    public static final Registry<String, AProcess> PROCESSES_REGISTRY = new Registry<>();

    public static final DefaultProcess DEFAULT_PROCESS = new DefaultProcess("default_process");
    public static final FlintProcess FLINT_PROCESS = new FlintProcess("flint_process");
    public static final WoodProcess WOOD_PROCESS = new WoodProcess();
}
