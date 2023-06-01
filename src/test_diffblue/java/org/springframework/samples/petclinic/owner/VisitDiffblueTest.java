package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class VisitDiffblueTest {
  /**
  * Methods under test: 
  * 
  * <ul>
  *   <li>default or parameterless constructor of {@link Visit}
  *   <li>{@link Visit#setDate(LocalDate)}
  *   <li>{@link Visit#setDescription(String)}
  *   <li>{@link Visit#getDate()}
  *   <li>{@link Visit#getDescription()}
  * </ul>
  */
  @Test
  void testConstructor() {
    // Arrange and Act
    Visit actualVisit = new Visit();
    LocalDate date = LocalDate.of(1970, 1, 1);
    actualVisit.setDate(date);
    actualVisit.setDescription("The characteristics of someone or something");

    // Assert
    assertSame(date, actualVisit.getDate());
    assertEquals("The characteristics of someone or something", actualVisit.getDescription());
  }

  /**
   * Method under test: default or parameterless constructor of {@link Visit}
   */
  @Test
  void testConstructor2() {
    // Arrange and Act
    Visit actualVisit = new Visit();

    // Assert
    assertTrue(actualVisit.isNew());
    assertNull(actualVisit.getId());
    assertNull(actualVisit.getDescription());
  }
}

