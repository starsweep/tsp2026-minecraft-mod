Untitled RPG Mod
=======

### Features
Currently has a menu that you can open with `M`, and the option to rebind the key

### Planned Features
- Skill tree with 4 paths, each having 2 smaller paths. Skills unlockable via EXP, and expand upon existing mechanics.
- Potion flask item, imbuable with a potion and refillable with water. Less effective than an equivalent standard potion. Comes in splash and lingering variant.
- Cauldron brewing, able to make weaker potions early game.
- Lectern enchanting, no lapis needed, marginally buffed by bookshelves. Meant to make enchantment accessible early game.
- Disenchanting table, takes EXP to put enchantment from item onto book.
- No permanent tool breaking, when durability runs out the tool will enter an unusable "broken" state.
- Repairing at an anvil has no EXP cost.
- New bosses in Nether and trial chambers, rework existing bosses to be more rewarding and engaging
- More direct story content, findable from various loot tables.

Dev information
=======

### Dependencies
- Minecraft 26.3
- Gradle 9.8.0
- JDK 25

### Building

Build can be run with `./gradlew build`

A development environment can be entered with `./gradlew runClient`

On Microsoft Windows, replace `./gradlew` with `gradlew.bat`

Github Builds and Releases
=======

Builds are automatically run upon having content pushed to the Github repository. Every build is saved as an artifact under the actions tab of the Github repo.

A release can be published to the releases category of the Github page instead of just being run in actions by pushing to main with a `v` tag. For example,

```
git tag v1.0.1
git push origin v1.0.1
```

This should only be used for pushes to github that are finalized polished versions that contain code that the end user should be seeing in use.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
