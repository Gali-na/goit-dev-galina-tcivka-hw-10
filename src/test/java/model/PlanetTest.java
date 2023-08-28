package model;

import exceptions.ArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {
    private  Planet planet;
    @BeforeEach
    private void getPlane() {

        planet= new Planet();
    }

    @Test
    void setId_ArgumentNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> planet.setId(null));
    }

    @Test
    void setId_ArgumentLessTwoSimbol_ThrowException(){
        assertThrows(ArgumentException.class, () -> planet.setId("G"));
    }

    @Test
    void setId_ArgumentLessMoreSimbol_ThrowException(){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<60;i++){
            stringBuilder.append("G");
        }
        assertThrows(ArgumentException.class, () -> planet.setId(stringBuilder.toString()));
    }
    @Test
    void setId_ArgumentConsistSmallSimbol_ThrowException(){
        assertThrows(ArgumentException.class, () -> planet.setId("hhh"));
    }
    @Test
    void setId_ArgumentValid_PositiveResult() throws ArgumentException {
         planet.setId("GND");
        assertEquals("GND", planet.getId());
    }

    @Test
    void  setName_ArgumentNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> planet.setName(null));
    }

    @Test
    void  setName_ArgumentLessOneSimbol_ThrowException(){
        assertThrows(ArgumentException.class, () -> planet.setName(""));
    }

    @Test
    void  setName_ArgumentMore500Simbol_ThrowException(){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<600;i++){
            stringBuilder.append("G");
        }
        assertThrows(ArgumentException.class, () -> planet.setName(stringBuilder.toString()));
    }
    @Test
    void setName_ArgumentValid_PositiveResult() throws ArgumentException {
        planet.setName("NewPlanet");
        assertEquals("NewPlanet", planet.getName());
    }
}
