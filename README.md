# West3.0# 3.0  Planning Doc

### Precursor: 

---
For any feature exact %, rates, values or anything of the sort will be fully
detailed in the "Feature Breakdown" Document.

I broke down a bunch of large features for the game and spaced it out into a couple different
"endpoints", where in theory we could stop the project there and it would still feel like a complete
game and offer a lot of content.

Timeline:

- Refactor base game
- Introduce Contract System
  - Specically the combat contracts!
- Add in Officer role

**Endpoint A** (The ez goal) ===========

- Add peacemaker contracts
- Add the citadel combat contract
- Finally make a texturepack!

**Endpoint B** (The optimal goal) =======

- Add in prison system for bounty
- New peacemaker quests?
- Small QOL updates

**Endpoint C** (The stretch goal) ========

---

## Gameplay: 

Upon logging in it should be made very clear that towns are the **only** safe zone. A message saying "Talk to the contract manager" is stated on first join
Players will spawn with the worst pistol, some light armor and a couple heals / food
Immediately they are introduced to the main money maker mechanic: The contract manager.

Why get money if you can loot gear?
Money is to buy expensive items that aren't lootable. You can upgrade your player bank, team bank, or purchase new horses. These things cannot be done via looting. Additionally, weapons and armor can both be bought for a high dollar.

---

## Contracts

**New Feature**
### Main Jist
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

### Types of contracts:
Okay so here's the actual juice of the game.

**PeaceMaker**

Fishing: Every river has 3 generic fish, but specifc rivers throughout the map
are named, and have one or two dedicated rare fish. Fishing will often ask for 20
bass, or 10 trout, but when it asks for the rare fish, it pays huge.

Mining: There's typical bulk orders for 40 unprocessed Iron ore, or gold even.
But on the occasion, someones asking for a jewel, or maybe some of the materials
you find inside those lucky geodes! Geologist NPCs can crack those open for ya.

Hunting: This one is violent, but the animals won't fight back. Essentially similar to
how fishing has designated rivers, hunting has designated forests and fields.
Travel to these to find different groups of herding animals that spawn in the
area.

This is all I have for this one for now. Remember this is all for the base game
AKA what we know we can finish. Any extra stuff will be added in a later version

**Combat**

Head Hunter: Now these are usually easier combat missions. It gives a location
and a name. Travel to that location, find the NPC and kill him. Now if it was possible
It would be cool if you could also get player targets for a much higher difficulty.

Drug Runner: For this you would harvest and process a load of drugs. The instructions
would tell you what field to get the drugs from and what base to process at. Rebel bases
are where you process drugs, and are usually pretty hostile areas, so naturally, the more you have
to process, the higher the payout (You have to last longer).
Rebel bases also naturally spawn bandit NPC's who patrol and attack players.

Stolen Item(s): Go to a location to find a stolen item (Maybe change to steal a
stolen item?)

Oil Field: Oil field is a higher stakes, and is in general a harder mission.
At oil field there are a bunch of gaurds, A timed locked chest will spawn at one
of the towers. You have to kill your way towards the towers and start the timer.
When the timer starts, waves of stronger NPC's will come, and an alert to the entire
server will trigger saying "This team is taking Oil Field!", leaving it up to other players
When a player accepts the quest to take oil, they are given a crate key. If you are not
actively on an oil contract and try to open the crate with the key it breaks. The key
however, this is ONLY to start the timer!!! This is to avoid random players from starting the event
it needs to be only on someone who is doing an oil contract. Any player however,
can get the loot.

Optional:
Citadel: I quite like this one too. Essentiallty the citadel is a huge military outpost.
It remains locked and requires a key to enter. When the gates open though, they are public
to all for 10 minutes. That's because the player has 10 minutes to clear the citadel and find the
chest with the key out. Once the key bearer leaves the citadel,/opens the exit it announces that you
have 2 minutes to find your way out. The gate closes after 2 minutes and anyone inside the
citadel is killed (To avoid players camping chest respawns)
The citadel is structured like a maze and when you enter, the door is sealed behind you.
There are multiple loot rooms within the citadel, 5 to be exact. Each one has a single
chest, this is where you can find an exit key. If you find one, you must run to the exit
and leave.

## Misc

---

Ok thats all the jobs there are, if you're now sitting here wondering, well how do I find anything?
You always spawn with a compass, this is your navigator. It can take you to the clostest
shop, take you to any river, mine, camp or town. It can even track rogue players.

Theres a command to whistle for your horses, that makes getting around quicker.
Between towns, theres quite a distance. For that kind of journey consider taking
the train, or a ferry.

Horses can also be bought, as yours may die if you're not careful. Or maybe you
just want an upgrade. Purchase up to 3 horses, the quicker the horse, the more
you pay.

NPCs in the town sell general goods and weapons / armor for high dollar. There's
a bank for your money, and in the same building a bank for your items. Item bank
can be upgraded for a really high dollar. This adds more slots.

Foraging flowers around the map can make useful potions for getting jobs done
quicker. Potions for double the ore mined, or quicker pickaxe speed, etc.

Officers: Players all have an option to join the side of the law. With this, they spawn
with a basic gear set, and if they rank up, can buy upgrades to their guns and armor.
Ranking up can b done by completing contracts, or killing rogue players.
You can talk to the chief of police for promotions.

Guns :- Guns are split into 3 types within 3 categories. You have rifles, pistols
and shotguns. Which are either legal, illegal, or Officer. This makes certain
guns exclusive to Bandits and or Officers. And within Officers, there are guns exclusive
to certain levels.

Bounty is a metric that is calculated by how many evil deeds you do. For instance,
killing players increases bounty, doing the hardest combat contracts give bounty.
Bounty is used to determine how worth it is to kill someone. A 10k bounty is a 5k
bonus check to whoever claims your head.

