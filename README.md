# Cauldron Dyeing
![GitHub license](https://img.shields.io/github/license/tibinonest/cauldron-dyeing)
![GitHub actions](https://img.shields.io/github/actions/workflow/status/tibinonest/cauldron-dyeing/build-gradle.yml?branch=1.19.3)
![GitHub release](https://img.shields.io/github/v/release/tibinonest/cauldron-dyeing?display_name=tag&include_prereleases&sort=semver)

Bring Bedrock's cauldron-based armor dyeing to Java Edition.

---

This mod adds Bedrock's ability to dye leather armor or leather horse armor with cauldrons to Java Edition.
The usage is the same as in Bedrock, right-clicking on a water cauldron with dye will dye the water; adding dye multiple times will blend the dye same as in a crafting table.
When you right-click on the dyed water cauldron with a dyeable item, it will add the current color in the cauldron to the item.
Right-clicking on an un-dyed water cauldron with a dyed item to remove its color will still work.

![dyed water](https://cdn.tibinonest.me/images/dyed-water.jpg)

## Usage
Cauldron Dyeing requires Fabric or Quilt loader and [FAPI](https://modrinth.com/mod/fabric-api) or [QFAPI](https://modrinth.com/mod/qsl).
The latest release is available on the [Modrinth](https://modrinth.com/mod/cauldron-dyeing) or [Github releases](https://github.com/TibiNonEst/cauldron-dyeing/releases).
To install, just drag the mod jar into your mods folder along with either FAPI or QFAPI.

### Building
Building the mod requires JDK 17+ on your system. To build, simply run `./gradlew build` and the artifacts will be available in `build/libs`.

## Compatibility
Cauldron Dyeing is ensured to be compatible with (Quilted) Fabric API, CaffeineMC mods, and Iris, if any incompatibility is found please report it on the [issue tracker](https://github.com/TibiNonEst/cauldron-dyeing/issues).

**Note:** The current method of displaying dyed water is not compatible with shaders that customize water color such as Complementary's Realistic-ish and RTX-ish settings.

## Issues
Report any bugs, crashes, or other issues that may arise on the [issue tracker](https://github.com/TibiNonEst/cauldron-dyeing/issues).

## License
Cauldron Dyeing is licensed under the GNU LGPLv3, see the [full license](https://github.com/TibiNonEst/cauldron-dyeing/blob/main/LICENSE).
