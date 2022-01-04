# Cauldron Dyeing
![GitHub license](https://img.shields.io/github/license/tibinonest/cauldron-dyeing)
![GitHub actions](https://img.shields.io/github/workflow/status/tibinonest/cauldron-dyeing/gradle-ci)
![GitHub release](https://img.shields.io/github/v/release/tibinonest/cauldron-dyeing?display_name=tag&include_prereleases&sort=semver)

Bring Bedrock's cauldron-based armor dyeing to Java Edition.

:warning: This mod is still in alpha stage, it may be unstable.

---

This mod adds Bedrock's ability to dye leather armor or leather horse armor with cauldrons to Java Edition.
The usage is the same as in Bedrock, right-clicking on a water cauldron with dye will dye the water; adding dye multiple times will blend the dye same as in a crafting table.
When you right-click on the dyed water cauldron with a dyeable item, it will add the current color in the cauldron to the item.
Right-clicking on an un-dyed water cauldron with a dyed item to remove its color will still work.

### Build
Building the mod requires JDK 17+ on your system. To build, simply run `./gradlew build` and the artifacts will be available in `build/libs`.

### License
Cauldron Dyeing is licensed under the GNU LGPLv3, see the [full license](https://github.com/TibiNonEst/cauldron-dyeing/blob/main/LICENSE).
