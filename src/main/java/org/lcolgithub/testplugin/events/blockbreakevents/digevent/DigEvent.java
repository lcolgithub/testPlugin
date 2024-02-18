package org.lcolgithub.testplugin.events.blockbreakevents.digevent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.lcolgithub.testplugin.dal.DAL;
import org.lcolgithub.testplugin.events.Event;
import org.lcolgithub.testplugin.events.eventtype.EventType;
import org.lcolgithub.testplugin.player.PlugPlayer;

public class DigEvent implements Event {

    private Player player;
    private ItemStack itemInHand;
    private Block block;

    public DigEvent(org.bukkit.event.block.BlockBreakEvent event){
        this.player = event.getPlayer();
        this.block = event.getBlock();
        this.itemInHand = event.getPlayer().getPlayer().getInventory().getItemInMainHand();
    }

    public void process(){
        PlugPlayer p = getPlayer(player.getUniqueId().toString());
        p.gainExp(EventType.DIGGING, 1);
    }

    @Override
    public void randomEvent() {

    }

    private PlugPlayer getPlayer(String uuid) {
        return DAL.getPlayer(uuid);
    }
}
