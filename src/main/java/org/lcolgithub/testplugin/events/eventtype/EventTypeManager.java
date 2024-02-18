package org.lcolgithub.testplugin.events.eventtype;

import org.bukkit.Material;

import java.util.*;


public class EventTypeManager {
    private static EventTypeManager instance;
    private Map<EventType, Set<Material>> eventGroupings = new HashMap<>();

    private EventTypeManager(){
        eventGroupings.put(EventType.MINING, new HashSet<>(
                Arrays.asList(
                        Material.STONE,
                        Material.COBBLESTONE,
                        Material.ANDESITE,
                        Material.MOSSY_COBBLESTONE
                )
        ));

        eventGroupings.put(EventType.DIGGING, new HashSet<>(
                Arrays.asList(
                        Material.GRAVEL,
                        Material.DIRT,
                        Material.GRASS_BLOCK,
                        Material.CLAY
                )
        ));

        eventGroupings.put(EventType.LOGGING, new HashSet<>(
                Arrays.asList(
                        Material.OAK_LOG,
                        Material.BIRCH_LOG,
                        Material.SPRUCE_LOG,
                        Material.DARK_OAK_LOG
                )
        ));
    }

    public static EventTypeManager getInstance(){
        if (instance == null){
            instance = new EventTypeManager();
        }
        return instance;
    }

    public EventType findType(Material material){
        for(Map.Entry<EventType, Set<Material>> entry : eventGroupings.entrySet()){
            if(entry.getValue().contains(material)){
                return entry.getKey();
            }
        }
        return EventType.UNKNOWN;
    }
}
