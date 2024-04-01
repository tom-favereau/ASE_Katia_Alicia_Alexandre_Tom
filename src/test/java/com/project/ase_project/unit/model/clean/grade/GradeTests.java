package com.project.ase_project.unit.model.clean.grade;

import com.project.ase_project.model.clean.grade.Grade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GradeTests {
    static Grade grade;
    static Grade grade2;

    @BeforeAll
    public static void setUp() {
        grade = new Grade("1", "Test", 4.5f, 10);
        grade2 = new Grade("1", "Test", 4.5f, 10);
    }

    @Test
    public void testGettersAndSetters() {
        Grade gradeTest = new Grade();
        gradeTest.setId(grade.getId());
        gradeTest.setSummonerName(grade.getSummonerName());
        gradeTest.setAverage(grade.getAverage());
        gradeTest.setCardinal(grade.getCardinal());

        assertEquals(grade.getId(), gradeTest.getId());
        assertEquals(grade.getSummonerName(), gradeTest.getSummonerName());
        assertEquals(grade.getAverage(), gradeTest.getAverage());
        assertEquals(grade.getCardinal(), gradeTest.getCardinal());
    }

    @Test
    public void testEqualsAndHashCode() {
        assertTrue(grade.equals(grade2) && grade2.equals(grade));
        assertEquals(grade.hashCode(), grade2.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Grade(id=1, summonerName=Test, average=4.5, cardinal=10)";
        assertEquals(expected, grade.toString());
    }

    @Test
    public void testBuilder() {
        Grade gradeTest = Grade.builder()
                .id(grade.getId())
                .summonerName(grade.getSummonerName())
                .average(grade.getAverage())
                .cardinal(grade.getCardinal())
                .build();

        assertEquals(grade.getId(), gradeTest.getId());
        assertEquals(grade.getSummonerName(), gradeTest.getSummonerName());
        assertEquals(grade.getAverage(), gradeTest.getAverage());
        assertEquals(grade.getCardinal(), gradeTest.getCardinal());
    }
}