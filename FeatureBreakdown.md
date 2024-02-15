# Feature Breakdown

---

This document will give you a look into roughly how each feature is going to work
in-game.
## Main Classes
### WestPlayer class
Keeps track of all west 3.0 player stats such as bank account amount, item bank content,
List of their horses, bounty etc.
Variables:
- UUID :: playerId
- double :: bank
- double :: wallet
- bool :: isBleeding
- bool :: isLegsBroke
- bool :: isWanted
- bool :: isOfficer
- Officer :: officerType
- int :: bounty
- String :: teamName?
- int :: cmbtContractLvl
- int :: pceContractLvl
- int :: cmbtXP
- int :: pceXP
- int :: wantedKills
- List<Item Stack> :: itemBank
- List<Horse> :: horses

Getters and Setters

### Horse class
Horses can be summoned from anywhere and take 1 minute to arrive. The worse horse
can store 1 item, better horses have more slots, or they are very fast. The most slots
a horse will have is 7 (Maybe less). A player can have 3 horses. When you shift right
click your horse, it opens an inventory. Here you can send it back to the stable,
view its inventory, or exit the menu. If your horse dies, you lose it permanently and
have to buy a new one.

- UUID :: horseId
- string :: horseName
- HorseType (Enum) :: horseType
- color :: horseColor
- pattern :: horsePattern
- List<ItemStack> :: saddleInventory

Getters and Setters

Commands
- call //Calls horse to location (Opens menu to choose horses)
- gethorse (ADMIN COMMAND)

### Location class

Locations can give characteristics to certain areas, and help with events. They can
be used to determine if a player is inside them, then disable their PvP to create
a safezone, or spawn enemies if its in a PvP location, or fish special fish if at a river
etc. This is all achieved with safezones

- string :: locName
- WestLocation (Enum) :: locType
- double :: x1, y1, x2, y2
- Bossbar (*minecraft object*) :: locDisplay
- String :: welcomeMessage
- boolean :: isTown

Methods
- boolean :: isPlayerInside(Player p)
- void :: displayLocTitle()
- void :: displayWelcomeMessage()
- boolean :: isTown()

### Team class
Teams can have up to 3 players. You'll be able to online teammates. If you complete
a contract, your teammates also get cut - 30% of your total earnings.
Team names cannot be longer than 6 Letters.

- string :: teamName
- int :: playerCount
- string :: teamId
- string :: teamLeaderId
- List<Player> :: onlinePlayers
- List<ItemStack> :: teamBank

Methods
- Getters and setters
- boolean isLeader(string playerId)

Commands

``/team create {teamName}``

``/team leave`` :: (Last to leave disbands)

``/team invite {player}``

``/team accept`` :: Accept an invite

``/team info`` :: Displayes team info

``/team info {player}`` :: Displays other players team info

``/team help``

``/team list`` :: Lists all teams

### Officer interface / class 

Officers are going to be a main class that can be reused for 3 subclass officers. A Deputy, Sheriff and Marshall. 
The only different between the 3, is going to be their display name, and OffixcerTypeID, which differentiates them in hierarchal order.
So 1 is the sheriff, which is the lowest, 2 is deputy, 3 is marshall the highest. This way we can easily
differentiate between teh ranks when we need to know if the player is able to use a certain item, or get a certain
contract.

- String :: officerDisplayName
- Enum :: officerTypeId
- int :: xp

### Scoreboard

A scoreboard is going to be essentially a big display of all your rankings and statistics to easily keep track of 
everything. You'll use getter methods to grab the team name, officer role, bank, wallet etc. (If applicable). Then display
it all on the scoreboard and refresh every tick thd player is in the game.

### Western Item

These are just easy to create item objects. We'll likely have a util class where we create a list of all of the items
and use this to create them. It will need a lore line for its description, also it's rarity status. All items are plain
white text for names.
Test