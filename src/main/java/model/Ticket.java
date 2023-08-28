package model;

import exceptions.ArgumentException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter

@ToString
@EqualsAndHashCode

@Entity
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long Id;
    @Column(name = "created_at")
    String createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    Planet fromPlanet;
    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    Planet toPlanet;

    public Ticket() {
        ZoneId zone = ZoneId.of("GMT");
        ZonedDateTime time = ZonedDateTime.now(zone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        createdAt = time.format(formatter);
    }

    public void setClient(Client client) throws ArgumentException {
        if(client==null ) {
            throw new ArgumentException("Argument cannot be null");
        }
        if(client.getName()==null||client.getId()==0){
            throw new ArgumentException("Сlient name cannot be null");
        }
        this.client = client;
    }

    public void setFromPlanet(Planet fromPlanet) throws ArgumentException {
        if(fromPlanet == null){
            throw new ArgumentException("Argument cannot be null");
        }
        if (fromPlanet.getName()==null) {
            throw new ArgumentException("Planet name cannot be null");
        }
        if(fromPlanet.getId()==null) {
            throw new ArgumentException("Planet Id cannot be null");
        }
        this.fromPlanet = fromPlanet;
    }

    public void setToPlanet(Planet toPlanet) throws ArgumentException {
        if(toPlanet == null){
            throw new ArgumentException("Argument cannot be null");
        }
        if (toPlanet.getName()==null) {
            throw new ArgumentException("Planet name cannot be null");
        }
        if(toPlanet.getId()==null) {
            throw new ArgumentException("Planet Id cannot be null");
        }
        this.toPlanet = toPlanet;
    }
}
