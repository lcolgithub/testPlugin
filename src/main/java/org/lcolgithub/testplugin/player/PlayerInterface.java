package org.lcolgithub.testplugin.player;

import org.bukkit.event.block.BlockBreakEvent;
import org.lcolgithub.testplugin.events.eventtype.EventType;

public interface PlayerInterface {

    void gainExp(EventType skill, int experience);
}
