package org.lcolgithub.testplugin.events.blockbreakevents;

import org.bukkit.event.block.BlockBreakEvent;
import org.lcolgithub.testplugin.events.Event;
import org.lcolgithub.testplugin.events.blockbreakevents.digevent.DigEvent;
import org.lcolgithub.testplugin.events.blockbreakevents.logevent.LogEvent;
import org.lcolgithub.testplugin.events.blockbreakevents.mineevent.MineEvent;
import org.lcolgithub.testplugin.events.eventtype.EventType;
import org.lcolgithub.testplugin.events.eventtype.EventTypeManager;


public class BlockBreakEventFactory {

    public Event getEvent(BlockBreakEvent event){

        EventType type = EventTypeManager.getInstance().findType(event.getBlock().getType());

        switch(type){
            case MINING: return new MineEvent(event);
            case DIGGING: return new DigEvent(event);
            case LOGGING: return new LogEvent(event);
            default: return null;
        }
    }
}
