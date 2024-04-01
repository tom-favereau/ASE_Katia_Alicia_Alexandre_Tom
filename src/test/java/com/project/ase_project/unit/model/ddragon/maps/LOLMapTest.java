package com.project.ase_project.unit.model.ddragon.maps;

import com.project.ase_project.model.ddragon.maps.LOLMap;
import com.project.ase_project.model.ddragon.queue.LOLQueue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LOLMapTest {

    static LOLMap LOLMap1;
    static LOLMap LOLMap2;

    @BeforeAll
    public static void setUp(){
        LOLMap1 = new LOLMap(1,"Summoner's Rift","Original Summer variant");
        LOLMap2 = new LOLMap(1,"Summoner's Rift","Original Summer variant");
    }


    @Test
    public void testGettersAndSetters() {
        LOLMap LOLMapTest = new LOLMap();
        LOLMapTest.setMapId(LOLMap1.getMapId());
        LOLMapTest.setMapName(LOLMap1.getMapName());
        LOLMapTest.setNotes(LOLMap1.getNotes());

        assertEquals(LOLMap1.getMapId(), LOLMapTest.getMapId());
        assertEquals(LOLMap1.getMapName(), LOLMapTest.getMapName());
        assertEquals(LOLMap1.getNotes(), LOLMapTest.getNotes());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(LOLMap1.equals(LOLMap2) && LOLMap2.equals(LOLMap1));
        assertEquals(LOLMap1.hashCode(), LOLMap2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "LOLMap(mapId=1, mapName=Summoner's Rift, notes=Original Summer variant)";
        assertEquals(expected, LOLMap1.toString());
    }

    @Test
    public void testBuilder() {
        LOLMap LOLMapTest = LOLMap.builder()
                .mapId(LOLMap1.getMapId())
                .mapName(LOLMap1.getMapName())
                .notes(LOLMap1.getNotes())
                .build();

        assertEquals(LOLMap1.getMapId(), LOLMapTest.getMapId());
        assertEquals(LOLMap1.getMapName(), LOLMapTest.getMapName());
        assertEquals(LOLMap1.getNotes(), LOLMapTest.getNotes());
    }
}
