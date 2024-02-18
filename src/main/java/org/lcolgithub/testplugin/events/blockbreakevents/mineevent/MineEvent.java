package org.lcolgithub.testplugin.events.blockbreakevents.mineevent;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.lcolgithub.testplugin.dal.DAL;
import org.lcolgithub.testplugin.events.Event;
import org.lcolgithub.testplugin.events.eventtype.EventType;
import org.lcolgithub.testplugin.player.PlugPlayer;

import java.util.Random;

public class MineEvent implements Event {

    private Player player;
    private ItemStack itemInHand;
    private Block block;

    public MineEvent(org.bukkit.event.block.BlockBreakEvent event){
        this.player = event.getPlayer();
        this.block = event.getBlock();
        this.itemInHand = event.getPlayer().getPlayer().getInventory().getItemInMainHand();
    }

    public void process(){
        PlugPlayer p = getPlayer(player.getUniqueId().toString());
        p.gainExp(EventType.MINING, 1);
        randomEvent();
    }

    @Override
    public void randomEvent() {
        if(rand.nextInt(1000) > 900) {
            player.sendMessage("A diamond dropped!");
            player.getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND));
        }
    }

    private PlugPlayer getPlayer(String uuid){
        return DAL.getPlayer(uuid);
    }
}
