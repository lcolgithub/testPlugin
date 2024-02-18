package org.lcolgithub.testplugin.events;

import java.util.Random;

public interface Event {
    Random rand = new Random();
    void process();
    void randomEvent();
}
