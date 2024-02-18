package org.lcolgithub.testplugin.player;

import org.bukkit.event.block.BlockBreakEvent;
import org.lcolgithub.testplugin.events.eventtype.EventType;

public abstract class AbstractPlayer implements PlayerInterface{
    @Override
    public void gainExp(EventType skill, int experience) {

    }
}
