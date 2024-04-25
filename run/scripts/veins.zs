#norun
#loader craftorium

import mods.craftorium.vein.Veins;
import mods.craftorium.substance.Substances;
import mods.craftorium.vein.Builder;

var newVein = Builder.createVein("test",
                      1, 50,
                      5, 5,
                      1000,
                      0,
                      [Substances.get("iron"), 3, "copper", 3, "lead", 3])
                     .build();