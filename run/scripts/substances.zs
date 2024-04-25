#norun
#loader craftorium

import mods.craftorium.substance.Substances;
import mods.craftorium.substance.Builder;

// Create

var silicon = Substances.get("silicon");
var copper = Substances.get("copper");

var test = Builder.createSubstance("test_substance")
                  .composition([silicon, 3, "electrum", 1])
                  .possible([copper, 3, 20, "obsidian", 1, 5])
                  .items(["ingot", "dust", "pearl"])
                  .tools(["wrench"])
                  .tools(5.0f, 2.0f, 500, 3, 5)
                  .style("metal")
                  .shiny()
                  .build();