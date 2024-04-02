package com.project.ase_project.unit.model.ddragon.champion;

import com.project.ase_project.model.ddragon.champion.Champion;
import com.project.ase_project.model.ddragon.champion.Image;
import com.project.ase_project.model.ddragon.maps.LOLMap;
import com.project.ase_project.model.ddragon.queue.LOLQueue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChampionTest {
    static Champion champion1;
    static Champion champion2;

    @BeforeAll
    public static void setUp(){
        champion1 = new Champion("Annie", 1, "Annie", new Image("Annie.png", "champion0.png", "champion", 336, 0,48,48));
        champion2 = new Champion("Annie", 1, "Annie", new Image("Annie.png", "champion0.png", "champion", 336, 0,48,48));
    }


    @Test
    public void testGettersAndSetters() {
        Champion championTest = new Champion();
        championTest.setChampionKey(champion1.getChampionKey());
        championTest.setId(champion1.getId());
        championTest.setImage(champion1.getImage());
        championTest.setName(champion1.getName());

        assertEquals(champion1.getChampionKey(), championTest.getChampionKey());
        assertEquals(champion1.getId(), championTest.getId());
        assertEquals(champion1.getName(), championTest.getName());
        assertEquals(champion1.getImage(), championTest.getImage());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(champion1.equals(champion2) && champion2.equals(champion1));
        assertEquals(champion1.hashCode(), champion2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Champion(id=Annie, championKey=1, name=Annie, image=Image(fullImage=Annie.png, sprite=champion0.png, imageGroup=champion, x=336, y=0, w=48, h=48))";
        assertEquals(expected, champion1.toString());
    }

    @Test
    public void testBuilder() {
        Champion championTest = Champion.builder()
                .championKey(champion1.getChampionKey())
                .id(champion1.getId())
                .image(champion1.getImage())
                .name(champion1.getName())
                .build();

        assertEquals(champion1.getChampionKey(), championTest.getChampionKey());
        assertEquals(champion1.getId(), championTest.getId());
        assertEquals(champion1.getName(), championTest.getName());
        assertEquals(champion1.getImage(), championTest.getImage());
    }
}
