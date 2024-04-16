package droidco.west3.ironsight.Bandit.Commands;

import droidco.west3.ironsight.Bandit.Bandit;
import droidco.west3.ironsight.Globals.Utils.BanditUtils;
import droidco.west3.ironsight.Globals.Utils.GlobalUtils;
import droidco.west3.ironsight.Items.CustomItem;
import droidco.west3.ironsight.Items.Potions.CustomPotion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AdminCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player p){
            Bandit iP = Bandit.getPlayer(p);
            if(strings.length == 0){
                //Player entered commands with no args
                //show options
                p.sendMessage("Iron Sight Command options:");
                p.sendMessage("/dropgold *amount*");

            }
            else{
                switch(strings[0].toLowerCase()){
                    case "gold":
                        if(strings.length == 3){
                            switch(strings[1]){
                                case "add":
                                    Bandit iPlayer =  Bandit.getPlayer(p);
                                    double amount = GlobalUtils.checkStrToDErrMsg(strings[2], p);
                                    iPlayer.updateBank(amount);
                                    p.sendMessage("Gold added to account.");
                                    break;
                                case "remove":

                                    break;
                            }
                        }
                        break;
                    case "save":
                        if(strings[1].equalsIgnoreCase("player")){
                            //SAVE PLAYER HERE
                            System.out.println("Player data successfully saved");
                        }
                        else{
                            p.sendMessage("Incorrect usage, try /is save player");
                        }
                        break;
                    case "bounty":
                        iP.setBounty(100);
                        break;
                    case "bounty2":
                        iP.setBounty(1);
                        break;
                    case "kit":
                        switch (strings[1].toLowerCase()){
                            case "bandit" -> {
                                ItemStack morphFull = CustomPotion.getCustomPotion("Morphine").getItemStack();
                                morphFull.setAmount(64);
                                ItemStack whisFull = CustomPotion.getCustomPotion("Whiskey").getItemStack();
                                whisFull.setAmount(64);
                                ItemStack medFull = CustomPotion.getCustomPotion("Medicine").getItemStack();
                                medFull.setAmount(64);
                                ItemStack salmon = CustomItem.getCustomItem("Smoked Salmon").getItemStack();
                                salmon.setAmount(64);
                                ItemStack shotgun = CustomItem.getCustomItem("Shotgun Ammo").getItemStack();
                                shotgun.setAmount(64);
                                ItemStack pistol = CustomItem.getCustomItem("Pistol Ammo").getItemStack();
                                pistol.setAmount(64);
                                ItemStack rifle = CustomItem.getCustomItem("Rifle Ammo").getItemStack();
                                rifle.setAmount(64);
                                p.getInventory().addItem(morphFull);
                                p.getInventory().addItem(medFull);
                                p.getInventory().addItem(whisFull);
                                p.getInventory().addItem(salmon);
                                p.getInventory().addItem(shotgun);
                                p.getInventory().addItem(pistol);
                                p.getInventory().addItem(rifle);
                                p.getInventory().setBoots(CustomItem.getCustomItem("Journeymen Boots").getItemStack());
                                p.getInventory().setLeggings(CustomItem.getCustomItem("Journeymen Pants").getItemStack());
                                p.getInventory().setChestplate(CustomItem.getCustomItem("Journeymen Duster").getItemStack());
                                p.getInventory().setHelmet(CustomItem.getCustomItem("Journeymen Hat").getItemStack());
                                BanditUtils.getFirearm(p,"winchesterillegal");
                                BanditUtils.getFirearm(p,"sharps");
                                BanditUtils.getFirearm(p,"henry");
                                BanditUtils.getFirearm(p,"sawed");
                                BanditUtils.getFirearm(p,"navy1851");

                            }
                            case "marshall" -> {
                                ItemStack morphFull = CustomPotion.getCustomPotion("Morphine").getItemStack();
                                morphFull.setAmount(64);
                                ItemStack whisFull = CustomPotion.getCustomPotion("Whiskey").getItemStack();
                                whisFull.setAmount(64);
                                ItemStack medFull = CustomPotion.getCustomPotion("Medicine").getItemStack();
                                medFull.setAmount(64);
                                ItemStack salmon = CustomItem.getCustomItem("Smoked Salmon").getItemStack();
                                salmon.setAmount(64);
                                ItemStack shotgun = CustomItem.getCustomItem("Shotgun Ammo").getItemStack();
                                shotgun.setAmount(64);
                                ItemStack pistol = CustomItem.getCustomItem("Pistol Ammo").getItemStack();
                                pistol.setAmount(64);
                                ItemStack rifle = CustomItem.getCustomItem("Rifle Ammo").getItemStack();
                                rifle.setAmount(64);
                                p.getInventory().addItem(morphFull);
                                p.getInventory().addItem(medFull);
                                p.getInventory().addItem(whisFull);
                                p.getInventory().addItem(salmon);
                                p.getInventory().addItem(shotgun);
                                p.getInventory().addItem(pistol);
                                p.getInventory().addItem(rifle);
                                p.getInventory().setBoots(CustomItem.getCustomItem("Marshall Boots").getItemStack());
                                p.getInventory().setLeggings(CustomItem.getCustomItem("Marshall Pants").getItemStack());
                                p.getInventory().setChestplate(CustomItem.getCustomItem("Marshall Jacket").getItemStack());
                                p.getInventory().setHelmet(CustomItem.getCustomItem("Marshall Hat").getItemStack());
                                BanditUtils.getFirearm(p,"springfield");
                                BanditUtils.getFirearm(p,"double");
                                BanditUtils.getFirearm(p,"Maynard");
                                BanditUtils.getFirearm(p,"sharps");
                            }
                            default -> {
                                p.sendMessage(ChatColor.RED+"Incorrect usage. "+ChatColor.GRAY+"Try /is kit *kitName*");
                            }


                        }

                }
            }



        }



        return false;
    }


}
