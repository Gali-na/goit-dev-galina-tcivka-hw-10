package model;

import exceptions.ArgumentException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@ToString
@NoArgsConstructor
@Entity
public class Planet {
   @Id
   private String id;
   private String name;


   public void setId(String id) throws ArgumentException{
      if (id == null) {
         throw new ArgumentException("Argument cannot be null");
      }
      if (id.length() > 50 || id.length() < 2) {
         throw new ArgumentException("The id is incorrect, the number of characters in" +
                 " the id must not be less than 2 or more than 50");
      }

      Pattern pattern = Pattern.compile("^[A-Z0-9]+");
      Matcher matcher = pattern.matcher(id);

      if (matcher.matches()) {
         this.id = id;
      }
      else {
         throw new ArgumentException("planet identifier is row that is composed exclusively " +
                 "of Latin letters " +
                 "at the upper register and numbers. For example, MARS, VEN");
      }


   }

   public void setName(String name) throws ArgumentException {
      if (name == null) {
         throw new ArgumentException("Argument cannot be null");
      }
      if (name.length() > 500 || name.length() < 1) {
         throw new ArgumentException ("The name is incorrect, the number of characters in" +
                 " the name must not be less than 1 or more than 500");
      }

      this.name = name;
   }
}


