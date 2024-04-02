package com.project.ase_project.unit.model.ddragon.queue;

import com.project.ase_project.model.clean.grade.Grade;
import com.project.ase_project.model.ddragon.champion.Image;
import com.project.ase_project.model.ddragon.queue.LOLQueue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LOLQueueTest {

    static LOLQueue LOLQueue1;
    static LOLQueue LOLQueue2;

    @BeforeAll
    public static void setUp(){
        LOLQueue1 = new LOLQueue(0, "Custom games", null);
        LOLQueue2 = new LOLQueue(0, "Custom games", null);



    }

    @Test
    public void testGettersAndSetters() {
        LOLQueue LOLQueueTest = new LOLQueue();
        LOLQueueTest.setDescription(LOLQueue1.getDescription());
        LOLQueueTest.setQueueId(LOLQueue1.getQueueId());
        LOLQueueTest.setMap(LOLQueue1.getMap());

        assertEquals(LOLQueue1.getDescription(), LOLQueueTest.getDescription());
        assertEquals(LOLQueue1.getQueueId(), LOLQueueTest.getQueueId());
        assertEquals(LOLQueue1.getMap(), LOLQueueTest.getMap());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(LOLQueue1.equals(LOLQueue2) && LOLQueue2.equals(LOLQueue1));
        assertEquals(LOLQueue1.hashCode(), LOLQueue2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "LOLQueue(queueId=0, map=Custom games, description=null)";
        assertEquals(expected, LOLQueue1.toString());
    }

    @Test
    public void testBuilder() {
        LOLQueue LOLQueueTest = LOLQueue.builder()
                .queueId(LOLQueue1.getQueueId())
                .map(LOLQueue1.getMap())
                .description(LOLQueue1.getDescription())
                .build();

        assertEquals(LOLQueue1.getDescription(), LOLQueueTest.getDescription());
        assertEquals(LOLQueue1.getQueueId(), LOLQueueTest.getQueueId());
        assertEquals(LOLQueue1.getMap(), LOLQueueTest.getMap());
    }
}
