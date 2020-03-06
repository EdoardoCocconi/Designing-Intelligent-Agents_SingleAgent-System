package uk.ac.nott.cs.g53dia.agent;

import uk.ac.nott.cs.g53dia.library.LitterAgent;
import uk.ac.nott.cs.g53dia.library.Point;


public abstract class Sensor {

    protected DemoLitterAgent agent;


    public Sensor(LitterAgent agent) {
        this.agent = (DemoLitterAgent)agent;
    }


    public abstract Point readSensor(ExploredMap exploredMap);

}
