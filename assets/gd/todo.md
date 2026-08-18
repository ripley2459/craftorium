# TODO

## Bloquant - le mod ne fonctionne pas seul en l'état

- [ ] **Aucune recette de synthèse d'alliage (élément → alliage).** `Substances.java` déclare des
  `.composition()` (ex. `BRONZE = COPPER,3+TIN,1` `:259-265`, `STEEL = IRON,1+CARBON,3` `:266-270`)
  mais `RecipesHandler.java:526-582` n'exploite cette composition que dans le sens inverse
  (dust composé → dusts composants, via électrolyse). Aucune recette Mixer/Foundry ne va
  élément → alliage. Conséquence : STEEL, BRONZE, HSLA_STEEL, STAINLESS_STEEL, TUNGSTEN_STEEL,
  SUPER_ALLOY, OSMIRIDIUM, YBCO, GRAPHENE, HASTE_ALLOY, NICHROME, RTM_ALLOY ne sont crafters par
  rien. Comme `TIER_ONE.pack(STEEL, BRONZE, COPPER...)` (`Substances.java:1152`) exige ces alliages
  pour fabriquer moteurs/pistons/machines, aucune machine Tier 1 n'est actuellement constructible
  sans autre mod. **Priorité n°1.**

- [ ] **Impasses totales de matière première.** `RUTHENIUM`, `YTTRIUM`, `EUROPIUM`, `SAMARIUM`
  (`Substances.java` ~`:750-755` et zone YBCO) n'ont ni `veinMember()`, ni composition, et
  n'apparaissent dans aucun vein de `Veins.java`. Bloque définitivement `RTM_ALLOY` et `YBCO`,
  indépendamment du point précédent. Ajouter des veins/sources ou une composition de décomposition.
  **Précision (audit du 2026-08-15) :** une source existe pourtant dans le code pour trois des
  quatre - `common/world/MoreVeins.java` déclare `.possible(YTTRIUM, EUROPIUM, SAMARIUM, ...)` sur
  un vein `fluorite` (actif seulement avec NuclearCraft, cf. `SubstancesNuclearCraft.java:70`) -
  mais elle est **doublement inatteignable** : `MoreVeins.init()` n'est appelé nulle part dans tout
  le mod (classe morte), et les 4 `new VeinBuilder(...)` du fichier (dont `fluorite`) sont de toute
  façon commentés. Donc même avec NuclearCraft installé, `RTM_ALLOY`/`YBCO` restent bloqués - il ne
  suffit pas de "décommenter", il faut aussi appeler `MoreVeins.init()` quelque part dans
  l'initialisation. `RUTHENIUM` en revanche n'apparaît nulle part, même dans ce fichier mort - vrai
  cul-de-sac dans tous les cas.

## Design - formule Tier ↔ Durée ↔ RF (consolide vitesse cassée + absence de formule + coût RF figé)

Analyse du 2026-08-14 : ces trois points sont un seul et même problème mécanique. Vérifié dans
`RecipeProcessor.java` + `EnergyInventory.java`.

**Mécanique réelle actuelle** (`RecipeProcessor.java:161,153-154`) :
`progressMax = round(duration / tier.getSpeed())` puis `perTick = energyIn / progressMax`, donc
`perTick(tier) = E × speed(tier) / D`. La recette ne peut tourner que si
`perTick(tier) ≤ capacity(tier)` (sinon blocage permanent en `NOPOWER`,
`RecipeProcessor.java:98-104`) où `capacity(tier) = capacitéSlot × tier.getEnergyBuffer()`
(`EnergyInventory.java:34`). **Ce verrou "impossible sur Tier 1" existe déjà mécaniquement**, il
n'est simplement jamais exploité volontairement.

**Bug confirmé** (`Substances.java:1145-1188`) : `speed`/`energyStorage`/`fluidStorage` valent
exactement **12/8/8 pour Tier 1 ET pour Tier 4** - copier-coller, T1 dupliquant T4 mot pour mot. Le
reste (T2=3/2/2, T3=6/4/4, T5=24/16/16) est une progression propre en puissances de 2 (×1.5 pour la
vitesse). Fix trivial : remettre T1 en tête de la vraie progression
(`speed`: 1.5/3/6/12/24 - `energyBuffer`/`fluidStorage`: 1/2/4/8/16).

**Piège mathématique identifié** : si `speed(tier)` et `energyBuffer(tier)` progressent au même
rythme (×2/tier, quasi le cas actuel), alors `perTick(tier)/capacity(tier)` reste **constant** quel
que soit le tier - une recette qui dépasse la capacité du Tier 1 la dépassera dans les mêmes
proportions au Tier 5. Le tier n'accélère alors que le craft, il ne débloque jamais rien. Autre
fait : `RecipeMap.getRecipe()` (`RecipeMap.java:95-108`) ne regarde jamais le tier de la machine -
seul ce calcul d'énergie peut bloquer une recette, il n'y a aucun garde-fou explicite du type
"nécessite Tier 3+" (à part le cas isolé `RecipesHandler.java:767`,
`tier.getMachines().contains(ELECTROLYZER)`).

**Plan en 3 étapes :**

- [ ] **Fix immédiat** : corriger la duplication T1/T4 ci-dessus (`speed` 1.5/3/6/12/24,
  `energyBuffer`/`fluidStorage` 1/2/4/8/16). Gain immédiat, aucun risque.

- [ ] **Ajouter un champ `minimumTier` explicite sur `MachineRecipe`**, vérifié dans
  `RecipeMap.isRecipeValid`. Palier dur et lisible (JEI : "Nécessite Tier 3+") plutôt qu'un blocage
  énergétique opaque qui, comme démontré ci-dessus, ne fonctionne pas si buffer et vitesse scalent
  au même rythme. Nécessite aussi de faire grimper `energyBuffer` plus vite que `speed` par tier
  (ex. buffer ×4/tier, vitesse ×2/tier) pour que le calcul d'énergie serve de filet de sécurité
  cohérent sous le tier minimum.

- [ ] **Formule de coût basée sur la complexité de la substance**, calculable depuis l'arbre de
  `.composition()` déjà présent dans `Substances.java` (pas de tag manuel) :
  `C(substance) = 0` si élément brut/`veinMember()`, sinon `1 + max(C(composant))` - ex.
  STEEL/BRONZE/HASTE_ALLOY = 1, SUPER_ALLOY (composé de HASTE_ALLOY) = 2. Puis pour une recette de
  synthèse de complexité `C` : `duration(C) = D0 × α^C` (grind de base à vitesse Tier 1),
  `énergieTotale(C) = E0 × β^C` avec `β > α` (le coût énergétique grimpe plus vite que la durée),
  `minimumTier(C)` réutilisant le mapping déjà existant dans les `.pack()` de tiers
  (STEEL/BRONZE→T1, RTM_ALLOY→T3, SUPER_ALLOY/YBCO→T5). Résultat : sous le tier minimum →
  infaisable (palier dur) ; au tier minimum → faisable mais très lent (grind) ; au-dessus →
  nettement plus rapide, incitant à monter en tier même après déblocage.

## Important - équilibrage et personnalisation

- [ ] **`Tier` et `Machine` non exposés à CraftTweaker** (pas de `TierCT`/`MachineCT`). Impossible de
  retoucher vitesse/énergie d'un tier ou d'ajouter une machine depuis un pack. Le pattern
  Builder-CT existant (Substance/Vein/Recipe) est réutilisable sans refonte pour combler ça.

- [ ] **Modifier une recette existante via CT repose sur un mécanisme fragile non prévu à cet
  usage** : le nom de recette est régénéré automatiquement à partir des ingrédients
  (`MachineRecipeBuilder.java:126-129`), et le garde-fou anti-doublon est commenté
  (`RecipeMap.java:89`). Ça marche par coïncidence de nom, pas par API dédiée - prévoir un vrai
  `getRecipe(name)` + setters ou un `editRecipe` explicite.

## Refonte technique - `Machine`/`MachineBuilder` trop couplés à JEI et au GUI

Analyse du 2026-08-15. Aujourd'hui `Machine` **est** implicitement "un bloc avec RecipeMap + GUI
JEI" : impossible de déclarer une machine purement mécanique (convoyeur, capteur, tuyau, bloc de
stockage passif) sans traîner tout le bagage JEI/écran, et impossible de rendre JEI facultatif
pour un type de machine donné sans casser la construction. Deux couplages précis :

- [ ] **`MachineJeiData` est construit sans condition dans `MachineBuilder.build()`**
  (`MachineBuilder.java:113`, `new Machine(..., new MachineJeiData(map.getName(), arrowJEI,
  slotJEI))`), alors que `map` est `@Nullable` (`:37`) et que `verify()` (`:120-144`) ne valide
  `arrowJEI`/le nombre de slots que **si** `map != null` - sans jamais imposer `map != null` en
  premier lieu. Résultat : le seul cas non testé aujourd'hui (une machine sans `.processor(...)`)
  fait un NPE sur `map.getName()` à la construction. Les 10 machines actuelles
  (`Machines.java:39-160`) ont toutes un processor donc ça ne s'est jamais vu, mais l'API interdit
  de fait toute machine sans RecipeMap/JEI. `MachineJeiData` (package
  `common.integration.jei.recipe.machine`) est en plus importé directement par `Machine.java:13`
  et `MachineBuilder.java:14` - une classe API cœur dépend d'un package d'intégration optionnelle.
  **Fix** : sortir `MachineJeiData` de `Machine`/`MachineBuilder` (le calculer à la demande côté
  `JEIPlugin`/`MachineRecipeCategory` à partir de `Machine` + `RecipeMap`, pas le stocker dessus),
  et rendre `map`/`arrowJEI` réellement optionnels (une machine peut exposer 0 recette).

- [ ] **`JEIPlugin` suppose partout que toute `Machine` enregistrée a une `RecipeMap`**, pas
  seulement à un endroit : `registerCategories` (`JEIPlugin.java:27`) construit une
  `MachineRecipeCategory` pour **chaque** machine de `MACHINES_REGISTRY` sans condition, et le
  constructeur de `MachineRecipeCategory` (`MachineRecipeCategory.java:32`) déréférence
  `machine.getJeiData()` immédiatement (`getMinimalSize()`/`getOffset()`) ; `register`
  (`JEIPlugin.java:44-46`) fait de même via `MACHINE_MAPS_REGISTRY.get(machine.getJeiData().getMap())`.
  Les deux plantent dès qu'une machine sans processor existera. **Fix** : sauter l'enregistrement
  JEI (catégorie + recettes + catalyst) pour toute machine dont `getJeiData()`/RecipeMap est absent,
  aux deux endroits.

- [ ] **Une "Machine" n'aura pas forcément de `RecipeMap` non plus** - pas seulement pas de JEI.
  `MachineBuilder.processor(...)` (`:93-98`) est la seule méthode qui renseigne `map`, ajoute le
  `RecipeProcessor` comme `IMachineBehaviour` et positionne `arrowJEI` ; ne pas l'appeler doit être
  un mode de construction supporté (capteur, convoyeur, câble, trieur, bloc de stockage passif -
  aucun de ces cas n'a de recette à traiter). Aujourd'hui c'est impossible à isoler du GUI/de JEI :
  `verify()` (`MachineBuilder.java:120-144`) ne fait ses checks (`arrowJEI`, quotas de slots) que
  `if (map != null)`, donc l'absence de `map` est déjà tolérée à ce niveau - seul `build()`
  (`:113`, `map.getName()`) empêche concrètement ce cas de marcher. **Fix** : une fois `build()`
  corrigé (point ci-dessus) et le GUI découplé du RecipeMap (`Machine.hasGui()` ne doit pas
  dépendre de `map != null` mais de widgets/slots présents), `MachineBuilder` sans `.processor(...)`
  doit produire une `Machine` valide : pas de `RecipeProcessor`, pas de barre de progression, pas
  d'entrée JEI, et un GUI optionnel selon que des slots/widgets ont été déclarés par ailleurs
  (`itemInput`/`fluidOutput`/...).

- [ ] **Une "Machine" est actuellement toujours un GUI.** `MachineTile` construit
  systématiquement un `Screen` paresseux (`MachineTile.java:34`,
  `new CustomLazy<>(() -> machine.getScreen(this, tier), false)`) et `MachineBlock.onBlockActivated`
  ouvre inconditionnellement un GUI au clic droit (`MachineBlock.java:105-108`,
  `playerIn.openGui(...)` dès que la tile est une `MachineTile` et que le joueur ne sneak pas). Il
  n'existe aucun chemin pour une tile "machine" sans écran (capteur, convoyeur, câble, tuyau,
  réservoir passif) : même une machine qui n'aurait besoin d'aucune interaction traînerait un
  `Screen`/`Container`/`MachineContainer` vides. **Fix** : un flag ou une interface (ex.
  `Machine.hasGui()`, vrai par défaut si `widgets`/`map` non vides) que `MachineBlock` consulte
  avant `openGui` (sinon `onBlockActivated` retourne `false` et laisse la logique custom du bloc
  gérer l'interaction), et que `MachineTile.getScreen()` retourne `null` proprement au lieu de
  construire un `Screen` coquille vide.

- [ ] **Conséquence pratique attendue** : une fois ces points traités, trois axes deviennent
  indépendants - RecipeMap (oui/non), GUI (oui/non), JEI (oui/non tant qu'il y a une RecipeMap) -
  et toutes les combinaisons utiles sont possibles : machine avec `RecipeMap` mais sans JEI (dev/
  debug, ou masquer une chaîne interne), machine avec GUI mais sans `RecipeMap` (simple stockage/
  trieur), machine sans GUI ni `RecipeMap` (capteur/convoyeur/câble). Aucun des 10 `MachineBuilder`
  existants (`Machines.java`) n'a besoin de changer : ils gardent `.processor(...)` et récupèrent
  JEI comme avant, seuls les chemins "sans" doivent devenir possibles.

## Refonte technique - `FlowController` : contrôle I/O par slot, pas par machine

Analyse du 2026-08-15. Objectif exprimé : granularité par slot (item, fluide ou énergie) au lieu
d'un réglage global par face de machine. Un bouton "edit flow" n'apparaît que si la machine a au
moins un slot ; une fois actif, cliquer sur un slot ouvre un popup - même disposition/textures que
le panneau actuel à 6 faces - mais qui ne configure que ce slot précis.

- [ ] **Aujourd'hui `FlowController` est unique par machine et partagé entre tous les
  inventaires**, pas seulement "global" au sens conceptuel : `flowMap` est une simple
  `Map<EnumFacing, ESlotFlow>` de 6 entrées (`FlowController.java:35,40-43`), et `ItemInventory`
  *et* `FluidInventory` retrouvent la **même** instance via
  `Utils.first(owner.getBehaviours(), b -> b instanceof FlowController)`
  (`ItemInventory.java:44`, `FluidInventory.java:44`) puis appellent
  `flowController.canInput(side)`/`canOutput(side)` (`ItemInventory.java:275-281`,
  `FluidInventory.java:71,85,101`). Donc régler "NORD = INPUT" aujourd'hui ouvre en input **tous**
  les slots item et fluide côté nord en même temps - impossible de dire "nord : input pour le
  fluide seulement". `EnergyInventory` ne consulte `FlowController` **nulle part**
  (`EnergyInventory.java:38-39,52-53,86-89` : `hasCapability`/`receiveEnergy`/`extractEnergy`
  toujours ouverts, sans check de face) - l'énergie n'est pas juste "au niveau machine", elle n'est
  actuellement pas filtrable du tout.

- [ ] **Le GUI actuel est un unique panneau statique par machine**, pas un popup par slot :
  `FlowController.pushWidgets` (`FlowController.java:113-126`) pousse toujours les 6
  `AButton.ButtonFlowControl` (un par face, disposées en croix) dans l'onglet gauche de la machine
  entière, sans lien avec un `ItemSlotData`/`FluidSlotData`/`EnergySlotData` précis. Chaque bouton
  cycle `ESlotFlow` au clic gauche/droit (`AButton.java:120-132`, `next()`/`previous()`).

- [ ] **Modèle de données à changer** : remplacer le `Map<EnumFacing, ESlotFlow>` unique par une
  map indexée par slot, ex. `Map<SlotKey, Map<EnumFacing, ESlotFlow>>` où `SlotKey` combine le type
  d'inventaire (ITEM/FLUID/ENERGY) et l'index - `ItemInventory` et `FluidInventory` ont chacun leur
  propre espace d'index (`getSlotsData().get(i).getIndex()`), donc un simple `int` ne suffit pas à
  distinguer un slot item 0 d'un slot fluide 0. Attention à ne pas confondre avec `ASlotData.flow`
  (`ASlotData.java:8,16-22`) : ce champ définit si un slot est structurellement INPUT/OUTPUT/FREE
  (posé une fois par le `MachineBuilder` via `itemInput`/`itemOutput`/...), c'est une autre notion
  que le réglage runtime par face qu'on veut rendre configurable ici - les deux coexistent (un
  slot marqué OUTPUT à la construction n'a par exemple pas besoin d'un sens INPUT dans son popup).
  `EnergyInventory` doit gagner une vraie logique de filtrage par face (aujourd'hui absente, cf.
  point 1) pour que son inclusion dans "edit flow" ait un sens.

- [ ] **`hasCapability` au niveau de l'inventaire entier doit passer d'un lookup unique à un
  OU logique sur les slots** : `ItemInventory.hasCapability`/`getCapability`
  (`ItemInventory.java:54-66`) et `FluidInventory.hasCapability`/`getCapability`
  (`FluidInventory.java:168-180`) décident aujourd'hui d'exposer la capability pour toute une face
  via `flowController.canConnect(facing)` sur la map unique. Avec un réglage par slot, exposer la
  capability sur une face donnée doit devenir "au moins un slot de ce type autorise input ou output
  sur cette face" (les checks fins par slot dans `canInput(slot)`/`canOutput(slot)` restent le
  vrai filtre, ce niveau ne fait que décider si la capability existe du tout sur cette face).

- [ ] **Le protocole de sync widget actuel ne supporte pas un popup conditionnel.**
  `MachineTile.screen` est un `CustomLazy<Screen>` construit une seule fois
  (`MachineTile.java:34`, `false` = pas de refresh) à partir d'une liste de widgets figée pour
  toute la durée de vie de la tile (`Machine.getScreen`, `Machine.java:79-92`). La resync
  client→serveur actuelle (`MachineScreen.mouseClicked`, `:64-75`) sérialise **tous** les widgets
  de l'écran dans l'ordre (`CMachinePacket.toBytes`, `CMachinePacket.java:47-50`,
  `widget.toBytes(buf)` pour chaque widget) et le serveur relit dans l'ordre des **behaviours**
  (`CMachinePacket.process`, `:26-38`, `behaviour.fromBytes(data)`) - un protocole positionnel qui
  suppose un nombre et un ordre de widgets fixes. Un popup qui n'existe que pendant le mode "edit
  flow", pour le seul slot cliqué, ne rentre pas dans ce moule. **Fix** : ne pas pousser le popup
  comme widget permanent de l'écran - le gérer comme un overlay purement client dans
  `MachineScreen` (état "popup ouvert pour le slot X", dessiné/cliqué en plus de la liste de
  widgets figée), et lui donner un packet dédié explicite `(type de slot, index de slot, face,
  nouvel ESlotFlow)` plutôt que de piggyback sur la resync générique par ordre.

- [ ] **Bouton "edit flow" unique par machine, conditionné à l'existence d'au moins un slot** :
  actuellement le tab `FlowController` est ajouté par tout `MachineBuilder.flowControlled()`
  (`MachineBuilder.java:88-91`) indépendamment du nombre de slots réels. Une fois le modèle par
  slot en place, le bouton "edit flow" doit être calculé depuis l'écran assemblé (au moins un
  `ItemSlotData`/`FluidSlotData`/`EnergySlotData` poussé par `ItemInventory`/`FluidInventory`/
  `EnergyInventory`, `Machine.java:84-85`) plutôt que d'exister indépendamment de ce qui a
  réellement été construit.

- [ ] **Réutilisation prévue** : le rendu du popup (croix de 6 boutons + textures
  `FLOW_ICON`/`FLOW_LOCK`/`FLOW_FREE`/`FLOW_INPUT`/`FLOW_OUTPUT`, `AButton.java:106-168`) reste
  identique visuellement au panneau actuel - seule sa cible change (la map d'un slot précis au
  lieu de la map unique de la machine). Pas besoin de nouvel assets/textures.

## Audit technique élargi (2026-08-15) - bugs de fond et dette technique

Revue systématique du reste de la codebase (au-delà des refontes ciblées ci-dessus), à la
recherche de bugs et de dette qui gêneraient de futures features. Classé par sous-système, sévérité
décroissante à l'intérieur de chaque section.

### Moteur de recettes (`RecipeProcessor`/`RecipeMap`) - le cluster le plus critique

Deux audits indépendants (comportement des machines et matching de recettes) sont tombés
séparément sur les deux mêmes bugs de `RecipeProcessor.java` - double confirmation, pas une
coïncidence d'analyse.

- [ ] **Fuite d'énergie en boucle quand la sortie est pleine.** `RecipeProcessor.java:84-104` :
  quand `progress == progressMax` mais `insertOutputs()` échoue (inventaire de sortie plein), rien
  ne fige l'état ni ne repasse la machine dans un état "bloqué" - `progress` reste à `progressMax`,
  `recipe` n'est pas nullifié. Au tick suivant, `progress <= progressMax` (égalité) est encore vrai
  → `extractEnergy(...)` est rappelé, on retente `insertOutputs()`, etc. Une machine dont la sortie
  sature consomme indéfiniment du RF sans produire un seul item de plus, sans aucun signal au
  joueur (le commentaire `// progress--; // TODO ?` ligne 99 montre que ce cas n'a jamais été
  traité).

- [ ] **Boucle potentiellement infinie dans `consumeInputs()`, gel du thread serveur entier.**
  `RecipeProcessor.java:114-146` : `while (asked > 0) { for (slot...) { ... } }` sans aucune garde
  de sortie ni compteur d'itérations. Si le `for` se termine sans que `asked` retombe à 0 (cas
  rendu possible par le bug suivant), la boucle ne progresse plus et tourne indéfiniment - un gel
  total du serveur, pas seulement de la machine.

- [ ] **Cause probable de la boucle infinie : double-comptage dans `RecipeMap.checkItems`.**
  `RecipeMap.java:30-50` vérifie chaque `OreStack` requis indépendamment contre la liste complète
  des `ItemStack` disponibles, sans jamais décrémenter le pool entre deux exigences différentes.
  Une recette à deux entrées qui matchent le même stack physique (tag OreDict large + tag étroit
  se recouvrant, ex. `dustAny` + `dustIron` sur un seul dust présent) est validée comme suffisante
  alors qu'il n'y a qu'un seul exemplaire réel - `isRecipeValid` retourne vrai, puis
  `consumeInputs()` ne trouve jamais la 2ᵉ unité demandée et boucle indéfiniment (bug précédent).
  Le mécanisme catalyseur/`itemNotConsumed` (`MachineRecipeBuilder.java:67-70`) est exposé au même
  défaut : un catalyseur peut "satisfaire" simultanément son propre slot et un autre input
  consommable qui matche le même tag.

- [ ] **Division entière qui neutralise le coût énergétique annoncé.** `RecipeProcessor.java:153-
  154` : `recipe.getEnergyIn() / progressMax` tronque à 0 dès que `energyIn < progressMax` (recette
  peu coûteuse mais longue). `getEnergyRequired()` renvoie alors 0, `extractEnergy(0, false)` ne
  retire rien, et la machine crafte à l'infini pour 0 RF réellement consommé tant qu'il reste ne
  serait-ce que 1 RF dans le buffer (`update()` exige juste `energyStored > 0`). Pas un simple
  arrondi - un contournement total du coût énergétique pour toute recette à faible énergie/longue
  durée, un profil qu'encouragerait justement la formule de coût par complexité proposée plus haut
  (grind long à tier minimum).

- [ ] **Recette 0 input / 0 output constructible sans erreur, et prioritaire si enregistrée en
  premier.** `MachineRecipeBuilder.java:126-129` + `WeightedList.put` (ignore silencieusement
  `weight <= 0`) : un builder sans aucun `consumeItem`/`produceItem` (ou tous à chance 0) produit
  une `MachineRecipe` valide (nom généré `"to"`), et `isRecipeValid` pour cette recette est
  **toujours vrai** dès que la `configuration` correspond. Insérée avant les vraies recettes (le
  registre respecte l'ordre d'insertion), elle consomme l'énergie par défaut sans jamais rien
  produire et rend injoignables toutes les recettes suivantes de même `configuration`.

- [ ] **Collision confirmée et déjà active entre deux `CONFIGURATION_*` de `Maps.java`.**
  `CONFIGURATION_CASTING_MELTING = 1` et `CONFIGURATION_CASTING_FORM_INGOT = 1` (`Maps.java:26-
  27`) partagent la même valeur et sont toutes deux utilisées sur la RecipeMap `CASTING`
  (`RecipesHandler.java:436` vs recettes de coulée en lingot). Comme `isRecipeValid` n'exige pas
  que les listes non requises soient vides côté machine, un Foundry avec à la fois un item en
  entrée et un fluide résiduel en cuve peut matcher l'une ou l'autre recette selon l'ordre
  d'insertion - comportement non déterministe côté joueur, à corriger indépendamment de tout ajout
  de nouvelle configuration (`CONFIGURATION_CASTING_FORM_BOULE`/`CONFIGURATION_CASTING_ANNEAL`
  proposées plus haut devront choisir des valeurs libres, pas juste "la suivante").

- [ ] **Nommage de recette auto-généré n'encode ni `configuration`, ni énergie, ni durée**
  (`Utils.generateRecipeName`, seuls items/fluides in/out entrent dans le nom) - déjà signalé plus
  haut pour l'édition CT, mais la conséquence directe est que **deux recettes aux mêmes
  ingrédients/sorties mais à `configuration` ou coûts différents s'écrasent silencieusement** dans
  le registre (garde-fou anti-doublon commenté, `RecipeMap.java:89`, et le booléen de retour de
  `Registry.put` jamais vérifié par aucun appelant - cf. section Fondations transverses). Impossible
  de déclarer deux variantes (mode A / mode B) sur les mêmes ingrédients sans écrasement.

- [ ] **Balayage linéaire O(n) exécuté chaque tick pour toute machine idle.**
  `RecipeProcessor.java:105-109` + `RecipeMap.getRecipe` (`:104-120`) : tant que `recipe == null`
  (machine vide/en attente), `setRecipe()` est rappelé sans condition à 20 Hz et réitère toute la
  `Registry` de la map, sans index par item ni dirty-flag sur changement d'inventaire. Coût qui
  grossira linéairement avec chaque recette ajoutée (alliages, wafers... cf. sections précédentes)
  multiplié par le nombre de machines idle en jeu.

### Faille réseau - `CMachinePacket`/`SMachinePacket` ne valident ni l'identité ni les bornes

- [ ] **Aucune vérification que le paquet cible bien le container actuellement ouvert par le
  joueur.** `CMachinePacket.java:27` et l'équivalent côté S→C ne testent que
  `player.openContainer instanceof MachineContainer`, jamais que ce container précis correspond à
  la tile pour laquelle le paquet a été construit (aucune position/ID transmis). Scénario
  atteignable sans triche : le joueur clique dans le GUI de la machine A, le ferme aussitôt et
  ouvre celui de la machine B avant l'arrivée du paquet réseau (latence) → le serveur applique
  aveuglément les octets de A aux behaviours de B dans l'ordre, corrompant des champs sans rapport
  (ex. un `configuration` d'une machine appliqué ailleurs sur une autre).

- [ ] **Aucune borne vérifiée avant lecture du `ByteBuf`.** `CMachinePacket.process()`
  (`:30-31`) ne vérifie jamais `data.isReadable(n)` avant que les behaviours lisent
  (`readInt()`/`readByte()`), sans try/catch autour. Un paquet tronqué/malformé (client modifié, ou
  bug de sérialisation futur) lève une `IndexOutOfBoundsException` **après** que les behaviours
  précédents dans l'ordre ont déjà été mutés mais **avant** `tile.markDirty()`/
  `notifyBlockUpdate()` - la tile finit dans un état partiellement appliqué et non notifié, sans
  erreur visible côté client.

- [ ] **Le garde-fou de distance existant (`MachineContainer.canInteractWith`, ≤64 blocs +
  `!isInvalid()`) n'est jamais appelé sur le chemin des paquets custom.** Il ne protège que
  l'ouverture vanilla du container, pas `CMachinePacket.process()`/`SMachinePacket.process()`. Un
  client resté connecté sans fermeture propre du GUI (téléportation, déco/reco) peut continuer à
  modifier une machine à distance arbitraire ou invalide, sans contrôle serveur à ce niveau.

- [ ] **`RecipeProcessor.fromBytes` applique la valeur client sans clamp.**
  `RecipeProcessor.java:266-268` : `configuration = buf.readInt()` sans borner à
  `[MACHINE_CONFIGURATION_MIN, MACHINE_CONFIGURATION_MAX]`, contrairement au clic client normal qui
  clampe (`AButton.java:70-72`). Inoffensif aujourd'hui (`RecipeMap.getRecipe` ne fait qu'une
  égalité), mais aucune défense en profondeur si `configuration` sert un jour d'index.

- [ ] **Protocole non typé, non versionné, et mono-tile câblé partout.** Deux types de message
  seulement (`NetManager.java`), sans discriminant d'action ni ID stable (compteur
  auto-incrémenté dépendant de l'ordre d'enregistrement). `CMachinePacket`/`SMachinePacket` ne
  portent qu'un seul `MachineTile instigator`. Conséquence pour l'avenir : le popup FlowController
  par slot (section précédente) devra soit s'insérer dans le flux positionnel existant soit
  ajouter un type de message en dur ; une future machine multibloc (contrôleur + parties)
  nécessiterait un protocole entièrement nouveau, pas une extension de celui-ci.

- [ ] **`SMachinePacket` renvoie l'état complet, sans diff, à chaque tick de container, à chaque
  joueur.** `MachineContainer.detectAndSendChanges()` (`:26-38`) resérialise l'intégralité des
  behaviours (tanks, énergie, progression - ex. nom de fluide en UTF8 + 2 int par tank,
  `FluidInventory.toBytes`) 20×/s par joueur/machine ouverte, même à l'arrêt total. Bande passante
  ∝ joueurs × machines ouvertes × taille d'état, sans plafond - à surveiller pour toute machine à
  beaucoup de tanks/slots.

### GUI/widgets (`mui`) - bugs concrets, au-delà du protocole déjà documenté

- [ ] **`Text.getRealPosition()` ignore l'offset parent - texte mal placé sur toute l'UI.**
  `api/mui/Text.java:40-47` : les deux `.add(parentPosition)` sont **commentés**
  (`return getPosition().add(offset);//.add(parentPosition);`), contrairement à
  `AWidget.getRealPosition()` utilisé partout ailleurs. Tout `Text` (le label "Flow controller" de
  `FlowController.java:116`, les labels génériques de `MachineBuilder.text()`) est positionné en
  coordonnées locales pures, jamais recalé sur `(getGuiLeft(), getGuiTop())` - décalage visuel vers
  le coin de la fenêtre, jamais corrigé au resize puisque `parentPosition` change mais n'est tout
  simplement jamais lu par ce widget.

- [ ] **Confirmation concrète du bug EST/OUEST dans le protocole positionnel de
  `FlowController`.** `FlowController.pushWidgets()` (`:117-122`) pousse les boutons dans l'ordre
  DOWN, UP, NORTH, SOUTH, **EAST, WEST** ; `FlowController.fromBytes()` (`:69-72`) relit dans
  l'ordre `EnumFacing.values()` = DOWN, UP, NORTH, SOUTH, **WEST, EAST**. Le nombre d'octets
  coïncide (6=6, aucune désync globale détectable) mais la sémantique est inversée : cliquer sur le
  bouton EST du panneau modifie en réalité le flow OUEST côté serveur, et inversement. C'est une
  manifestation concrète et vérifiée du risque déjà pointé en général sur ce protocole positionnel
  (section FlowController ci-dessus) - à corriger dans la même passe que la refonte par slot, en
  utilisant un identifiant de face explicite dans le paquet plutôt qu'un ordre positionnel implicite.

- [ ] **Aucune consommation/priorité de clic entre widgets superposés.**
  `MachineScreen.mouseClicked()` (`:64-75`) itère tous les widgets sans `break`/early-return ni
  notion de z-order ; deux widgets actifs aux mêmes coordonnées réagissent tous les deux au même
  clic. `ATabGroup.onMouseClicked` retourne toujours `false` même en basculant un onglet - aucun
  mécanisme n'existe pour signaler qu'un clic a déjà été "traité".

- [ ] **Tooltips recalculés sans cache à ~60 fps sur tous les widgets**, même souris immobile
  (`MachineScreen.drawScreen():56-60`) - chaque implémentation alloue une nouvelle `List` et refait
  des lookups (ex. lookup `SUBSTANCES_REGISTRY` dans `ASlot.FluidSlot.getTooltips`). Passera mal à
  l'échelle avec une future liste scrollable de nombreuses entrées.

- [ ] **`ATabGroup.init()` appelle `child.init()` deux fois par passage, fragile au resize** -
  une fois avec une position calculée avant que `setTabsOffset()` ait tourné (valeur périmée), une
  fois avec le `parentPosition` brut de l'écran ; ça ne fonctionne que parce que le second appel
  écrase le premier. Un futur refactor qui "nettoierait" cet appel apparemment redondant casserait
  silencieusement le placement des enfants d'onglet.

- [ ] **Aucun canal serveur→client granulaire par widget, aucun support clavier/focus, aucun
  scissor/clip.** Toute mise à jour visuelle pilotée serveur passe par le resync NBT complet de la
  tile ; `AWidget`/`AButton` n'exposent ni `keyTyped` ni notion de focus ; pas de helper
  `glScissor` dans `RenderUtils`. Trois obstacles concrets pour des features déjà envisagées
  ailleurs dans ce document : une jauge qui se redessine en direct, un widget de renommage/
  recherche, une liste scrollable.

### Substances/Tier/Worldgen - bugs de données et de performance

- [ ] **Double enregistrement OreDictionary pour tout item de tier.** `ACommonProxy.java:153`
  enregistre déjà l'ore dict de chaque item de tier (`BATTERY`, `MOTOR`, `PISTON`...) en itérant
  `TIERS_REGISTRY` ; `ACommonProxy.java:155-158` refait exactement la même chose en itérant
  `SUBSTANCES_REGISTRY`, car `SubstanceBuilder.build()` insère un `Tier` dans les **deux**
  registres. Les 5 tiers × 11 items + 1 bloc sont enregistrés deux fois sous le même nom OreDict
  avec le même ItemStack - JEI/Ore Dictionary browser affiche des doublons, tout mod tiers itérant
  `OreDictionary.getOres(name)` reçoit une liste dupliquée.

- [ ] **`SubstanceBuilder.simultaneousRecipe()`/`additionalChance()` : clamp inversé, bombe à
  retardement actuellement invisible.** `SubstanceBuilder.java:422-430` : `Math.min(1,
  simultaneous)` et `Math.min(0, chance)` plafonnent systématiquement à 1/0 au lieu d'imposer un
  plancher (l'intention du nom, comparer au pattern correct `Math.max(0, energy)` de
  `MachineRecipeBuilder.java:43`). Mort aujourd'hui (aucun appel dans `Substances.java`), mais
  cassera silencieusement (toujours `simultaneous=0/1`, `chance=0` peu importe la valeur passée)
  dès la première utilisation par un futur contributeur.

- [ ] **Éléments mal classés `EGroup.HALOGEN`.** OXYGEN (`Substances.java:791`), HYDROGEN
  (`:1103`), NITROGEN (`:1109`) sont tagués `HALOGEN` (réutilisation copier-coller de
  `.packageHalogen()`) - seuls F et Cl en sont de vrais. Sans effet aujourd'hui (`getGroup()` non
  lu par la logique de jeu), mais bombe à retardement pour un futur système de groupement
  auto-généré (JEI, wiki).

- [ ] **`packageNobleGas()` : point d'extension jamais exercé, 0 gaz noble défini dans tout le
  mod** (base + intégrations) - plus précis que le constat déjà noté plus haut ("gaz nobles sans
  forme solide") : ici l'élément lui-même n'existe même pas, juste une méthode jamais testée en
  pratique.

- [ ] **`Veins.getVein()` recalcule tout à chaque tentative de génération, sans cache par
  dimension.** `Veins.java:138-143` : jusqu'à `veinDensity` fois par chunk (8 par défaut), la
  méthode reconstruit une `WeightedList` en refiltrant l'intégralité du registre de veins (~55)
  par dimension, avec un `collect(Collectors.toMap(...))` intermédiaire dont le résultat n'est
  utilisé que pour un `forEach` immédiat (inutile, un simple `filter().forEach()` suffirait). Coût
  qui grossira linéairement avec chaque vein ajouté, y compris via CraftTweaker.

- [ ] **`VeinGeneration.CustomPredicate.apply()` : scan linéaire de `StoneTypes` par bloc
  candidat testé.** Pour chaque bloc testé dans la génération d'un vein (jusqu'à des centaines par
  vein), on réitère tout `STONES_REGISTRY` sans structure de lookup par `IBlockState`. Anodin avec
  6 `StoneType` actuels, coût `O(blocsTestés × nbStoneTypes)` qui grossira avec tout ajout futur
  (marbre, basalte, pierre d'un autre mod).

- [ ] **`SubstanceBuilder.build()` n'empêche pas une substance "fantôme" sans élément ni
  composition.** La précondition (`:460-463`) vérifie `element`/`composition`/`possible` mais
  `composition` par défaut est un `LinkedHashSet` vide non-null - un builder qui n'appelle ni
  `.element(...)` ni `.composition(...)` passe la validation silencieusement, sans crash ni
  message. Aucune occurrence actuelle, mais rien n'empêche un futur copier-coller incomplet de
  créer une 5ᵉ impasse totale sans même le signal (élément déclaré) qui a permis de repérer les 4
  impasses déjà connues.

- [ ] **Correction à une hypothèse précédente : CraftTweaker expose déjà `Vein`** (`VeinBuilderCT`/
  `VeinCT`/`VeinsCT` couvrent y/size/chance/dimension/composition) - le vrai manque reste
  uniquement `Tier`/`Machine` comme déjà noté plus haut, pas les veins.

### Fondations transverses - `Registry`, `CustomLazy`, config, tooltips

- [ ] **`Registry.put()` ignore silencieusement les collisions de nom, partout dans le mod.**
  `api/utils/Registry.java:16-20` retourne `false` si la clé existait déjà, mais **aucun** des
  ~25 points d'appel ne vérifie ce retour (`ItemBuilder`, `BlockBuilder`, `FluidBuilder`,
  `MachineBuilder.java:115`, `SubstanceBuilder.java:486-487,492`, `Machines.java:183-184`...).
  Deux enregistrements sous le même nom (copier-coller, ou deux intégrations définissant la même
  substance) écrasent silencieusement l'ancien sans log ni exception - le premier objet Java reste
  référencé ailleurs (champ statique direct) pointant vers quelque chose qui n'est plus dans le
  registre. C'est aussi la cause directe de la collision de nom de recette déjà signalée plus haut
  (section moteur de recettes).

- [ ] **`CustomLazy` : une exception dans le supplier corrompt le cache en `null` pour
  toujours.** `api/utils/CustomLazy.java:22-26` pose `initialized = true` **avant** d'appeler
  `supplier.get()` ; si le supplier lève, l'exception remonte une fois (correct) mais tous les
  appels suivants sautent le recalcul et renvoient `null` en cache, sans jamais réévaluer le
  `Preconditions.checkArgument(canBeNull || value != null)`. Utilisé par
  `MachineTile.java:34` pour l'écran (`() -> machine.getScreen(this, tier)`) - si `getScreen()`
  lève une fois (ex. le NPE `map.getName()` déjà documenté plus haut pour une machine sans
  processor), la tile renvoie silencieusement `null` pour son écran à tout jamais, masquant le bug
  originel derrière un simple "le GUI ne s'ouvre pas". Absence totale de synchronisation en plus
  (`initialized`/`value` ni `volatile` ni protégés) - deux threads concurrents peuvent tous deux
  invoquer le supplier.

- [ ] **`RecipeProcessor` déréférence `itemInventory`/`fluidInventory` sans garde alors qu'ils
  sont `@Nullable` et qu'une machine 100% fluide (0 slot item) est mathématiquement permise par
  `MachineBuilder.verify()`.** `consumeInputs()`/`insertOutputs()`/`pushOutputs()` appellent
  `itemInventory.startSimulation()`/`getSlotsData()` sans `!= null`. Latent aujourd'hui (aucune des
  10 `RecipeMap` n'a `itemsIn=0`), mais devient actif dès la première machine purement fluide -
  exactement le genre de machine que permettrait la refonte Machine/RecipeMap optionnels décrite
  plus haut. À corriger dans la même passe.

- [ ] **`Settings.java` : commentaires de config faux, aucune validation de range.**
  `machineBaseStorage` commenté "Default: 5000" pour une valeur réelle de `50000` ;
  `batteryBaseTransfer` commenté "Default: 5000" pour une valeur réelle de `500`
  (`Settings.java:32-33,38-39`). Aucun `@Config.RangeInt`/`RangeDouble` sur ces champs ni sur
  `veinDensity` - rien n'empêche une valeur négative/nulle saisie via le GUI de config, cassant
  silencieusement la génération de veines ou le stockage/transfert d'énergie.

- [ ] **Incohérence de localisation des tooltips entre bloc et item.**
  `CustomItem.addInformation` traduit chaque clé via `Utils.localise(t)` ; l'équivalent bloc
  (`CustomBlock.CustomItemBlock.addInformation`) ajoute les tooltips **sans localisation**. Tout
  usage de l'API publique `BlockBuilder.tooltips(...)` avec des clés de traduction (convention
  utilisée partout ailleurs) affichera la clé brute au lieu du texte traduit.

- [ ] **`FlowController.cachedFacing` n'est jamais invalidé après une rotation externe au
  mécanisme vanilla.** Mémorisé une seule fois pour la durée de vie de la tile ; si un wrench tiers
  modifie la blockstate `FACING` directement, `alignFacing()` continue silencieusement à utiliser
  l'ancienne orientation, désalignant l'I/O par face sans rechargement de chunk.

### Intégrations mods tiers - régression active et bugs de validation CT

- [ ] **Régression active non commitée : les overrides Ice and Fire sont désactivés.**
  `iceandfire/SubstancesIceAndFire.java:19,26,32` - les trois
  `.overrides(INGOT, "iceandfire:dragonsteel_*_ingot")` sont commentés (modification en cours dans
  l'arbre de travail, cf. `git status`/`git diff`). Sans cet override, Craftorium crée ses **propres**
  items `ice_dragonsteel`/`fire_dragonsteel`/`lightning_dragonsteel` totalement déconnectés des
  vrais lingots Ice and Fire : un joueur avec ce mod installé se retrouve avec deux lingots
  "dragonsteel" différents, incompatibles entre les recettes des deux mods - régression
  fonctionnelle immédiate si commité tel quel. À vérifier avant tout commit de ce fichier.

- [ ] **`enderio/EnderioEndergy.java:40-43` - même schéma d'impasse de synthèse que
  `Substances.java`, mais localisé dans une intégration.** `MELODIC_ALLOY` a sa
  `.composition(END_STEEL, 1, POPPED_CHORUS, 1)` commentée (donc sans composition), alors que
  `STELLAR_ALLOY` (`:44-47`) compose à partir de `MELODIC_ALLOY` - impasse invisible à un audit qui
  ne porterait que sur `Substances.java`.

- [ ] **`crafttweaker/MachineRecipeBuilderCT.java` : `consumeFluid`/`produceFluid` ne valident
  pas le nom de fluide, contrairement à `consumeItem`.** `FluidRegistry.getFluidStack(fluid,
  amount)` renvoie `null` sur un nom inexistant, stocké tel quel ; `build()` plante alors en NPE
  dans `Utils.generateRecipeName` (`.getFluid().getName()` sur `null`). Un script CT avec une
  faute de frappe sur un nom de fluide plante au chargement sans message exploitable - à comparer
  avec `consumeItem(IIngredient)` qui valide et logue proprement via `CraftTweakerAPI.logError`.

- [ ] **`crafttweaker/RecipeMapsCT.java`/`VeinsCT.java` : `.get(name)` ne vérifie pas le retour
  du registre avant de l'envelopper.** `REGISTRY.get(name)` peut renvoyer `null`, enveloppé
  directement dans `new RecipeMapsCT(null)`/`new VeinCT(null)` - `mods.craftorium.recipe.Maps.
  get("typo").addRecipe(...)` fait un NPE au lieu d'un message clair, même défaut d'absence de
  garde-fou que le point précédent.

- [ ] **`crafttweaker/SubstanceBuilderCT.java:41-44` - `element(...)` lève une exception non
  rattrapée sur un groupe invalide.** `Element.EGroup.valueOf(group.toUpperCase())` sans
  try/catch : une faute de frappe interrompt le chargement du script entier, incohérent avec
  `sound(String)` juste à côté qui gère les valeurs invalides via `switch`/`default` +
  `CraftTweakerAPI.logError` sans crash.

- [ ] **`jei/TabMover.getGuiExtraAreas` recrée listes et `Rectangle` à chaque frame sans cache**,
  tant qu'un GUI de machine est ouvert - travail/allocation répétés inutilement au lieu d'être
  recalculés seulement quand les widgets changent.