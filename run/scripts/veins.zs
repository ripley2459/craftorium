#norun
#loader craftorium

import mods.craftorium.vein.Veins;
import mods.craftorium.substance.Substances;
import mods.craftorium.vein.Builder;

// Create

var newVein = Veins.create("test", // ID
                      1, 50, // Y
                      5, 5, // Sizes
                      1000, // Chance to spawn
                      0, // The dimension id
                      [Substances.get("iron"), 3, "copper", 3, "lead", 3]) // The composition
                     .build(); // Finalizes the process

// Modify

Veins.modify("stibnite") // Get the vein to modify
     .y(0, 100) // Changes it spawn range
     .size(8, 8) // Its size
     .chance(1000) // And its chance to spawn
     .build(); // Rebuilds the vein