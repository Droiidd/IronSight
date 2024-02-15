# Iron Sight Planning Doc

## Table of Contents

---

### 1. [Game Explanation](#Gameplay)
### 2. [How to begin helping](#Setting-up-your-environment)
### 3. [Stylesheet](#Stylesheet )


### Precursor

---

This is developed currently for Minecraft version 1.20.4!
>For a more in depth feature breakdown, you can view our documentation 
> linked here. This document is general set up and maintanence.

>**Timeline:**
> >*Everything above the endpoint is included*
>
>- Refactor base game
>- Introduce Contract System
>  - Specically the combat contracts!
>- Add in Officer role
>
>**Endpoint A** (The ez goal) ===========
>
>- Add peacemaker contracts
>- Add the citadel combat contract
>- Finally make a texturepack!
>
>**Endpoint B** (The optimal goal) =======
>
>- Add in prison system for bounty
>- New peacemaker quests?
>- Small QOL updates
>
>**Endpoint C** (The stretch goal) ========

---

## Gameplay
#### [**Back to the top**](#Table-of-Contents)
Upon logging in it should be made very clear that towns are the **only** safe zone. A message saying "Talk to the contract manager" is stated on first join
Players will spawn with the worst pistol, some light armor and a couple heals / food
Immediately they are introduced to the main money maker mechanic: The contract manager.

Why get money if you can loot gear?
Money is to buy expensive items that aren't lootable. You can upgrade your player bank, team bank, or purchase new horses. These things cannot be done via looting. Additionally, weapons and armor can both be bought for a high dollar.

---

### Contracts

>**New Feature**
#### Main Jist
Aside from the player object and it's functionality, this is the main feature
of the game.

Contracts are the best way to make money in the game, and the most fun. 
In a nutshell,you can do any job do without a contract, but will ALWAYS make less money than doing a contract of the same job.

Contracts are split into two categories, peacemaker and combat. 
Combat quests involve killing things that may kill you. As you'd expect, 
the harder the mission, the rarer the gear.

Peacemaker contracts do not inately put you in danger, 
but are still not completely peaceful (Since it can be completed outside). 
These are significantly less risky quests that often do not give gear, 
but yield good money. You also get items useful for other things: 
Items used for potions, etc. Hence "peacemaker", you are makers of peace by
by not directly invoking violence.

By talking to a contract manager NPC (Or using a command?) you can view the
contracts menu. This will display 3 slots filled by 3 different contracts.
The slots are considered "easy" "Medium" and "Hard" contracts.
Contracts are ranked in difficulty 1-4. (Always 4 levels of rarity in 
everything:- weapons, armor, etc.) 

A contract slot will refresh it's offered contract
upon the player completing the previous contract, or it has been 1 hour since
the contract was offered.

In addition to the reward, upon completing a contract, a player also recieves contract XP.
It's split into two types of contractors: peacemakers and combat peeps.
Contract XP is used to level up your contractor level which earns you a small %
extra per contract in that category. So Level 2 combat contractor gets extra 10%
on combat contracts. Peacemakers level up slower than combat players, but their bonus at max level is
much higher.

Time completion bonus. Most of these contracts need to be completed in a certain 
amount of time.

Peacemaker quests always make more money than combat quests, 
but combat quests will always yield more rare gear.

A final but not necessary feature:
If a player is in a team and completes a contract, all other online players
will also get money: 50% of the total earned from contract completor.

Always return to the contract manager to get rewards??

#### Types of contracts:
Okay so here's the actual juice of the game.

>**PeaceMaker Contracts**

- **Fishing**: Every river has 3 generic fish, but specifc rivers throughout the map
are named, and have one or two dedicated rare fish. Fishing will often ask for 20
bass, or 10 trout, but when it asks for the rare fish, it pays huge.

- **Mining**: There's typical bulk orders for 40 unprocessed Iron ore, or gold even.
But on the occasion, someones asking for a jewel, or maybe some of the materials
you find inside those lucky geodes! Geologist NPCs can crack those open for ya.

- **Hunting**: This one is violent, but the animals won't fight back. Essentially similar to
how fishing has designated rivers, hunting has designated forests and fields.
Travel to these to find different groups of herding animals that spawn in the
area.

This is all I have for this one for now. Remember this is all for the base game
AKA what we know we can finish. Any extra stuff will be added in a later version

>**Combat Contracts**

- **Head Hunter**: Now these are usually easier combat missions. It gives a location
and a name. Travel to that location, find the NPC and kill him. Now if it was possible
It would be cool if you could also get player targets for a much higher difficulty.

- **Drug Runner**: For this you would harvest and process a load of drugs. The instructions
would tell you what field to get the drugs from and what base to process at. Rebel bases
are where you process drugs, and are usually pretty hostile areas, so naturally, the more you have
to process, the higher the payout (You have to last longer).
Rebel bases also naturally spawn bandit NPC's who patrol and attack players.

- **Stolen Item(s)**: Go to a location to find a stolen item (Maybe change to steal a
stolen item?)

- **Oil Field**: Oil field is a higher stakes, and is in general a harder mission.
At oil field there are a bunch of gaurds, A timed locked chest will spawn at one
of the towers. You have to kill your way towards the towers and start the timer.
When the timer starts, waves of stronger NPC's will come, and an alert to the entire
server will trigger saying "This team is taking Oil Field!", leaving it up to other players
When a player accepts the quest to take oil, they are given a crate key. If you are not
actively on an oil contract and try to open the crate with the key it breaks. The key
however, this is ONLY to start the timer!!! This is to avoid random players from starting the event
it needs to be only on someone who is doing an oil contract. Any player however,
can get the loot.

_Optional_:
- **Citadel**: I quite like this one too. Essentiallty the citadel is a huge military outpost.
It remains locked and requires a key to enter. When the gates open though, they are public
to all for 10 minutes. That's because the player has 10 minutes to clear the citadel and find the
chest with the key out. Once the key bearer leaves the citadel,/opens the exit it announces that you
have 2 minutes to find your way out. The gate closes after 2 minutes and anyone inside the
citadel is killed (To avoid players camping chest respawns)
The citadel is structured like a maze and when you enter, the door is sealed behind you.
There are multiple loot rooms within the citadel, 5 to be exact. Each one has a single
chest, this is where you can find an exit key. If you find one, you must run to the exit
and leave.

#### Misc

Ok thats all the jobs there are, if you're now sitting here wondering, well how do I find anything?
You always spawn with a compass, this is your navigator. It can take you to the clostest
shop, take you to any river, mine, camp or town. It can even track rogue players.
- Fast travelling is done at conductors or ferry captains where you can buy a ticket to ride to another city.
- A player carries a wallet with him at all times, this stores the amount of gold he has on hand. If the player dies,
they will drop all the gold in their wallet. They can deposit their gold in the bank to keep it safe in town.

- **Horses** Theres a command to whistle for your horses, that makes getting around quicker.
Between towns, theres quite a distance. For that kind of journey consider taking
the train, or a ferry.

  - Horses can also be bought, as yours may die if you're not careful. Or maybe you
  just want an upgrade. Purchase up to 3 horses, the quicker the horse, the more
  you pay.

- **NPCs** in the town sell general goods and weapons / armor for high dollar. There's
a bank for your money, and in the same building a bank for your items. Item bank
can be upgraded for a really high dollar. This adds more slots.

- **Foraging** flowers around the map can make useful potions for getting jobs done
quicker. Potions for double the ore mined, or quicker pickaxe speed, etc.

- **Officers**: Players all have an option to join the side of the law. With this, they spawn
with a basic gear set, and if they rank up, can buy upgrades to their guns and armor.
Ranking up can b done by completing contracts, or killing rogue players.
You can talk to the chief of police for promotions.

- **Guns** :- Guns are split into 3 types within 3 categories. You have rifles, pistols
and shotguns. Which are either legal, illegal, or Officer. This makes certain
guns exclusive to Bandits and or Officers. And within Officers, there are guns exclusive
to certain levels.

- **Bounty** is a metric that is calculated by how many evil deeds you do. For instance,
killing players increases bounty, doing the hardest combat contracts give bounty.
Bounty is used to determine how worth it is to kill someone. 
  - A 10k bounty is a 5k
  bonus check to whoever claims your head.

- **Rogue / Wanted system**
Going rogue, or when a player is wanted is when a player who is not a officer attacks another player who is **not** already rogue/wanted.
These results in an immediate bounty increase as well as messages the entire server that the player has gone rogue! 
  - Rogue players are trackable by
  close by players. If sheriffs kill rogue players, they gain extra sheriff XP to rank up for "Taking out criminals". 

## Setting up your environment

---
### [**Back to the top**](#Table-of-Contents)

Download the following apps:

- Filezilla (Connects to the server to send and take files) :: https://filezilla-project.org/download.php
- Intelliji (Best for minecraft plugins) 
- Java SDK 17

Before we run any code, let's set up something on Intelliji.
1. First you'll need the base plugin, add a plugin called "Minecraft development".

   1. Restart your ide

1. To get the code up and running, clone the git repo into an appropriate location.
Then, open the repo in intelliji. Let it download all the extra packages.

   1. Then next to your
   run and debug option, click the 3 dots. Edit your configurations, add new configuration for gradle.

   1. Inside the run box, type "build", inside the Gradle project box, type the exact 
   file path to the Intelliji project (It should be .../IronSight/IronSight)

1. To make sure it's set up correctly, open up the builds package, then the libs package.
Delete the current IronSight snapshot, then run your build. If it builds correctly,
a new snapshot should appear.
1. To test your changes on the plugin, you have two options. Either connect to the production
server via filezilla and add the snapshot.java file to the plugins folder, then reload.
 
Or create your own local server for development (recommended not necessary).
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
majority of any text should be gray. In the case a message has an important word / key word ("Bleeding, Combat blocked, Rogue, etc.)
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
 