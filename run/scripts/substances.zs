#loader craftorium

import mods.craftorium.substance.Substances;
import mods.craftorium.substance.Substance; // Not required here
import mods.craftorium.substance.Builder;

var silicon as Substance = Substances.get("silicon");
var copper = Substances.get("copper");

var test = Substances.create("test_substance") // Starting the process
                     .composition([silicon, 3, "electrum", 1]) // Defining a composition
                     .possible([copper, 3, 20, "obsidian", 1, 5]) // And byproducts
                     .packageMetalExtended() // Adds a lot of things
                     .tools([]) // Remove every tools
                     .tools(["wrench"]) // But re-add the wrench
                     .tools(5.0f, 2.0f, 500, 3, 5) // Defines the tools properties
                     .style("metal") // Gives the substance a style
                     .shiny() // Makes it shiny (white layer over the texture)
                     .build(); // And finalizes the process

// Looping throught the substances registry
for s in Substances.getAll() {
    print(" - " + s.getName());
}