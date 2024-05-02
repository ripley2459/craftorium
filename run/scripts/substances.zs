#norun
#loader craftorium

import mods.craftorium.substance.Substances;
import mods.craftorium.substance.Builder;

// Create

var silicon = Substances.get("silicon");
var copper = Substances.get("copper");

var test = Substances.create("test_substance") // Starting the process
                     .composition([silicon, 3, "electrum", 1]) // Defines a composition
                     .possible([copper, 3, 20, "obsidian", 1, 5]) // And byproducts
                     .items(["ingot", "dust", "pearl"]) // Determines which items will be registered with this substance
                     .tools(["wrench"]) // Same for the tools
                     .tools(5.0f, 2.0f, 500, 3, 5) // Defines the tools properties
                     .style("metal") // Gives the substance a style
                     .shiny() // Makes it shiny (white layer over the texture)
                     .build(); // And finalizes the process

// Modify

var diamond = Substances.modify("diamond"); // Getting the builder

diamond.possible(["uranium", 3, 20]) // Uranium become a diamond's byproduct
       .blocks([]) // Removing every blocks from the blocks pool
       .veinMember() // Diamond is a vein member, without this call the game will crash
       .color(0xFFc6eb34) // Changing the color
       .shiny() // removing the shiny effect
       .build(); // Rebuild the substance