package com.project.ase_project.unit.model.ddragon.champion;

import com.project.ase_project.model.ddragon.champion.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageTest {

    static Image ViImage_1;
    static Image ViImage_2;

    @BeforeAll
    public static void setUp() {
        ViImage_1 = new Image("Vi.png", "champion4.png", "champion", 336, 96, 48, 48);
        ViImage_2 = new Image("Vi.png", "champion4.png", "champion", 336, 96, 48, 48);
    }

    @Test
    public void testGettersAndSetters() {
        Image imageTest = new Image();
        imageTest.setFullImage(ViImage_1.getFullImage());
        imageTest.setSprite(ViImage_1.getSprite());
        imageTest.setImageGroup(ViImage_1.getImageGroup());
        imageTest.setX(ViImage_1.getX());
        imageTest.setY(ViImage_1.getY());
        imageTest.setW(ViImage_1.getW());
        imageTest.setH(ViImage_1.getH());

        assertEquals(ViImage_1.getFullImage(), imageTest.getFullImage());
        assertEquals(ViImage_1.getSprite(), imageTest.getSprite());
        assertEquals(ViImage_1.getImageGroup(), imageTest.getImageGroup());
        assertEquals(ViImage_1.getX(), imageTest.getX());
        assertEquals(ViImage_1.getY(), imageTest.getY());
        assertEquals(ViImage_1.getW(), imageTest.getW());
        assertEquals(ViImage_1.getH(), imageTest.getH());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(ViImage_1.equals(ViImage_2) && ViImage_2.equals(ViImage_1));
        assertEquals(ViImage_1.hashCode(), ViImage_2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Image(fullImage=Vi.png, sprite=champion4.png, " +
                "imageGroup=champion, x=336, y=96, w=48, h=48)";
        assertEquals(expected, ViImage_1.toString());
    }

    @Test
    public void testBuilder() {
        Image imageTest = Image.builder()
                .fullImage(ViImage_1.getFullImage())
                .sprite(ViImage_1.getSprite())
                .imageGroup(ViImage_1.getImageGroup())
                .x(ViImage_1.getX())
                .y(ViImage_1.getY())
                .w(ViImage_1.getW())
                .h(ViImage_1.getH())
                .build();

        assertEquals(ViImage_1.getFullImage(), imageTest.getFullImage());
        assertEquals(ViImage_1.getSprite(), imageTest.getSprite());
        assertEquals(ViImage_1.getImageGroup(), imageTest.getImageGroup());
        assertEquals(ViImage_1.getX(), imageTest.getX());
        assertEquals(ViImage_1.getY(), imageTest.getY());
        assertEquals(ViImage_1.getW(), imageTest.getW());
        assertEquals(ViImage_1.getH(), imageTest.getH());
    }
}
