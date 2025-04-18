# Iron Sight Planning Doc

[![Build](https://github.com/Droiidd/IronSight/actions/workflows/gradle.yml/badge.svg)](https://github.com/Droiidd/IronSight/actions/workflows/gradle.yml)

## Table of Contents

---

### 2. [How to begin helping](#Setting-up-your-environment)

### 3. [Stylesheet](#Stylesheet )

### Precursor

---

This is developed currently for Minecraft version 1.20.4!
> For a more in depth feature breakdown, you can view our documentation
> linked here. This document is general set up and maintanence.

## Setting up your environment

---

> This source code generates a Minecraft java edition plugin.jar file, which if added to your server enabled the IronSight Gamemode.
> This is developed alongside the IronSightWeapons Plugin, which is a dependent weapon plugin.
>
> > **Dev Notes:**
> The project currently is developed using Gradle as the compiler. If you don't have an IDE with everything downloaded, I suggest using Intelliji.
> The project is native to intelliji.

### If you are developing on Intelliji, follow these steps:

1. Install a plugin called "Minecraft development".

   1. Restart your ide

1. Open the project in Intelliji
   1. Let it download all the extra packages. Restart IDE if needed

   1. Click the three dots next to the debug option on your top taskbar. 
   2. Edit your configurations, add new configuration for gradle
   1. Inside the text box for the run field, type "build"
   2. Inside the Gradle project box, type the exact
      file path to the Intelliji project 
      3. Ex. (It should be .../IronSight/IronSight)
   4. Click apply + ok button 
1. Then Run the created gradle configuration

Once you have the repository opened, dependencies downloaded and configurations added, you can verify everything up correct by the following:
### Verification
1. In the file explorer for the project, open up the projects `build` file
2. then the `libs` file
3. Delete the current IronSight Snapshot.jar file (if there is one)
4. Then, run the program again
5. A new build should appear in the libs folder

You can drag and drop that Snapshot.jar file into any Server.Plugins folder and thats how you test your changes
## Other setup

---
Download the following apps:

- Filezilla (Connects to the server to send and take files) :: https://filezilla-project.org/download.php
- Intelliji (Best for minecraft plugins)
- Java SDK 17

Create your own local server for development (recommended not necessary).

1. For this, download a minecraft server from here https://www.minecraft.net/en-us/download/server
   and put it in a folder.

1. Run the jar file, this will begin building the server. After a few seconds it will stop.
   Open the Eula file it creates, and change the eula to true.
   Run the server again.

1. You will also need a spigot runnable, this can be found here: https://getbukkit.org
   This is what allows you to run our spigot plugin.

1. Drag the java file into your servers folder and run the jar.
   To start the server now, you just need to run the spigot.jar

1. To connect to the server, just open your minecraft multiplayer,
   add server, IP = "localhost"

1. To add plugins to the server, simply add them to the plugins
   folder.

## Stylesheet

---

### [**Back to the top**](#Table-of-Contents)

There needs to be consistency between color choices in different scenarios, UI choices,
text word choice, build style etc.

### Text

Text is a little tricky because it's more specific. In most general cases, the main
majority of any text should be gray. In the case a message has an important word / key word ("Bleeding, Combat blocked,
Rogue, etc.)
, these keywords are colored separately to highlight the important part of the message. Since most
alerts occur during combat, the goal is to make it very easy to know what type of alert it is instantly.
All other words are grayed out as usual.

In-game message/alert/UI keyword text color:

- General : Gray
- Danger Alert : Red
- Anything money related (Numbers exclusively) : Gold (followed by a g)
- Good alert : Green
- Anything wanted / rogue related : Dark red
- Town related : yellow
- Tips: Aqua
- Server broadcast : Dark red "Alert" + blue

### Items

All items will have the following consistent styling:

1. Item name is white (Or red if illegal)
2. First line of lore / description is item rarity (Common, uncommon etc.)
3. Second line is "Officer" tag if officer, Otherwise it's item description
4. Third line is description if officer, otherwise blank

### Locations

All locations have both a welcome message, and a title displayed at the top of
a players HUD. In the event new locations are added, they should be consistent with their
type.

Location Title color based on type:

- Illegal : Red
- Town : Magenta / Pink
- River : Aqua / Blue
- Wilderness : Green
- Special area : Yellow
### [**Back to the top**](#Table-of-Contents)
 