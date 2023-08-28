package model;

import exceptions.ArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private  Client client;
    @BeforeEach
    private void getClient() {

        client= new Client();
    }

    @Test
    void setName_ArgumentNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> client.setName(null));
    }


    @Test
    void setName_ArgumentLessTwoSimbol_ThrowException(){
        assertThrows(ArgumentException.class, () -> client.setName("G"));
    }

    @Test
    void setName_ArgumentMore500Simbol_ThrowException(){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<600;i++){
            stringBuilder.append("G");
        }
        assertThrows(ArgumentException.class, () -> client.setName(stringBuilder.toString()));
    }

    @Test
    void setName_ArgumentValid_PositiveResult() throws ArgumentException {
        client.setName("Ganna");
        assertEquals("Ganna", client.getName());

    }

    @Test
    void setId_SetIdLessZero_ThrowException(){
        assertThrows(ArgumentException.class, () -> client.setId(-1));
    }

    @Test
    void setId_ArgumentValid_PositiveResult() throws ArgumentException {
        client.setId(6);
        assertEquals(6, client.getId());

    }
}