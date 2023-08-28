package model;

import exceptions.ArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketTest {

    private  Ticket ticket;
    @BeforeEach
    private void getTicket() {

        ticket= new Ticket();
    }

    @Test
    void setClient_ArgumentNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> ticket.setClient(null));
    }
    @Test
    void setClient_SetClientNameNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> ticket.setClient(new Client()));
    }

    @Test
    void setClient_SetClientValidName_ThrowException() throws ArgumentException {
        Client client = new Client();
        client.setName("newClient");
        assertThrows(ArgumentException.class, () -> ticket.setClient(new Client()));
    }
    @Test
    void setFromPlanet_ArgumentNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> ticket.setFromPlanet(null));
    }
    @Test
    void setFromPlanet_PlanetNameNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> ticket.setFromPlanet(new Planet()));
    }
    @Test
    void setFromPlanet_PlanetIdNull_ThrowException() throws ArgumentException {
        Planet planet = new Planet();
        planet.setName("Mars2");
        assertThrows(ArgumentException.class, () -> ticket.setFromPlanet( planet));
    }

    @Test
    void setFromPlanet_PlanetValid_PositiveResult() throws ArgumentException {
        Planet planet = new Planet();
        planet.setName("Mars2");
        planet.setId("MAR");
        ticket.setFromPlanet(planet);
        assertEquals(ticket.getFromPlanet().getName(),planet.getName());
        assertEquals(ticket.getFromPlanet().getId(),planet.getId());

    }

    @Test
    void setToPlanet() {
    }
    @Test
    void setToPlanet_ArgumentNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> ticket.setToPlanet(null));
    }
    @Test
    void setToPlanet_PlanetNameNull_ThrowException(){
        assertThrows(ArgumentException.class, () -> ticket.setToPlanet((new Planet())));
    }

    @Test
    void setToPlanet_PlanetIdNull_ThrowException() throws ArgumentException {
        Planet planet = new Planet();
        planet.setName("Mars2");
        assertThrows(ArgumentException.class, () -> ticket.setToPlanet( planet));
    }
    @Test
    void setToPlanet_PlanetValid_PositiveResult() throws ArgumentException {
        Planet planet = new Planet();
        planet.setName("Mars4");
        planet.setId("MAR4");
        ticket.setToPlanet(planet);
        assertEquals(ticket.getToPlanet().getName(),planet.getName());
        assertEquals(ticket.getToPlanet().getId(),planet.getId());
    }

}