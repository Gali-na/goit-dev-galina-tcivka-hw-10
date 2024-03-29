package model;

import exceptions.ArgumentException;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Table(name = "client")
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
    private long Id;
   @Column(name = "client_name")
    private  String name;

    public void setName(String name) throws ArgumentException {
        if (name == null) {
            throw new ArgumentException("Argument cannot be null");
        }
        if (name.length() > 200 || name.length() < 2) {
            throw new ArgumentException("The name is incorrect, the number of characters in" +
                    " the name must not be less than 2 or more than 1000");
        }
        this.name = name;
    }

    public void setId(long id) throws ArgumentException {
        if (id <0) {
            throw new ArgumentException("Argument cannot be null");
        }
        Id = id;
    }
}
