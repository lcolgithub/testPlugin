package org.lcolgithub.testplugin.player;

import org.bson.Document;
import org.lcolgithub.testplugin.dal.DAL;
import org.lcolgithub.testplugin.events.eventtype.EventType;

import java.util.HashMap;
import java.util.Map;

public class PlugPlayer extends AbstractPlayer{
    String uuid;
    int blocksBroken;
    Map<EventType, Integer> experienceLevels;

    public PlugPlayer(String uuid) {
        this.uuid = uuid;
        this.blocksBroken = 0;
        this.experienceLevels = new HashMap<>();
        experienceLevels.put(EventType.MINING, 0);
        experienceLevels.put(EventType.DIGGING, 0);
        experienceLevels.put(EventType.LOGGING, 0);
    }

    private PlugPlayer(PlayerBuilder builder){
        this.uuid = builder.uuid;
        this.blocksBroken = builder.blocksBroken;
        this.experienceLevels = new HashMap<>();
        experienceLevels.put(EventType.MINING, builder.miningLevel);
        experienceLevels.put(EventType.DIGGING, builder.diggingLevel);
        experienceLevels.put(EventType.LOGGING, builder.loggingLevel);
    }

    public String getUuid(){
        return uuid;
    }

    public void gainExp(EventType skill, int experience){
        int currentExp = experienceLevels.get(skill);
        experienceLevels.put(skill, currentExp + experience);
    }

    public Document toDocument(){
        Document doc = new Document();
        doc.put("uuid", uuid);
        doc.put("blocksBroken", blocksBroken);
        doc.put("miningLevel", experienceLevels.get(EventType.MINING));
        doc.put("loggingLevel", experienceLevels.get(EventType.LOGGING));
        doc.put("diggingLevel", experienceLevels.get(EventType.DIGGING));
        return doc;
    }

    public void update(PlugPlayer player){
        DAL.updatePlayer(player);
    }

    public static PlugPlayer fromDocument(Document doc){
        String uuid = doc.getString("uuid");

        int blocksBroken = doc.getInteger("blocksBroken");
        int miningLevel = doc.getInteger("miningLevel");
        int diggingLevel = doc.getInteger("diggingLevel");
        int loggingLevel = doc.getInteger("loggingLevel");

        return new PlayerBuilder(uuid)
                .setBlocksBroken(blocksBroken)
                .setMiningLevel(miningLevel)
                .setDiggingLevel(diggingLevel)
                .setLoggingLevel(loggingLevel)
                .build();
    }

    public static class PlayerBuilder{
        String uuid;
        int blocksBroken;
        int miningLevel;
        int diggingLevel;
        int loggingLevel;


        public PlayerBuilder(String uuid){
            this.uuid = uuid;
        }


        public PlayerBuilder setBlocksBroken(int blocksBroken){
            this.blocksBroken = blocksBroken;
            return this;
        }

        public PlayerBuilder setMiningLevel(int miningLevel){
            this.miningLevel = miningLevel;
            return this;
        }

        public PlayerBuilder setDiggingLevel(int diggingLevel){
            this.diggingLevel = diggingLevel;
            return this;
        }

        public PlayerBuilder setLoggingLevel(int loggingLevel){
            this.loggingLevel = loggingLevel;
            return this;
        }

        public PlugPlayer build(){
            return new PlugPlayer(this);
        }
    }
}
