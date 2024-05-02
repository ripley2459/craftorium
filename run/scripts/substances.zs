#loader craftorium

import mods.craftorium.substance.Substances;
import mods.craftorium.substance.Builder;

var silicon = Substances.get("silicon");
var copper = Substances.get("copper");

var test = Substances.create("test_substance") // Starting the process
                     .composition([silicon, 3, "electrum", 1]) // Defining a composition
                     .possible([copper, 3, 20, "obsidian", 1, 5]) // And byproducts
                     .items(["ingot", "dust", "pearl"]) // Determines which items will be registered with this substance
                     .tools(["wrench"]) // Same for the tools
                     .tools(5.0f, 2.0f, 500, 3, 5) // Defines the tools properties
                     .style("metal") // Gives the substance a style
                     .shiny() // Makes it shiny (white layer over the texture)
                     .build(); // And finalizes the process