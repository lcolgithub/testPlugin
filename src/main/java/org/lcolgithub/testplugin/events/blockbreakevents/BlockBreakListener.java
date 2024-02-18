package org.lcolgithub.testplugin.events.blockbreakevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.lcolgithub.testplugin.events.Event;
import org.lcolgithub.testplugin.events.blockbreakevents.BlockBreakEventFactory;


public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent blockBreakevent){
        BlockBreakEventFactory eventFactory = new BlockBreakEventFactory();
        Event event = eventFactory.getEvent(blockBreakevent);

        if(event != null)
            event.process();
    }
}
