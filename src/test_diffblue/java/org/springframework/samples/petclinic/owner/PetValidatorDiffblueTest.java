package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

class PetValidatorDiffblueTest {
  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(LocalDate.of(1970, 1, 1));
    pet.setId(1);
    pet.setName("Bella");
    pet.setType(type);

    // Act
    petValidator.validate(pet, new BindException(pet, "org.springframework.samples.petclinic.owner.Pet"));

    // Assert that nothing has changed
    assertEquals("1970-01-01", pet.getBirthDate().toString());
    assertFalse(pet.isNew());
    assertTrue(pet.getVisits().isEmpty());
    assertSame(type, pet.getType());
    assertEquals("Bella", pet.getName());
  }

  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate2() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(null);
    pet.setId(1);
    pet.setName("Bella");
    pet.setType(type);
    BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

    // Act
    petValidator.validate(pet, errors);

    // Assert
    assertEquals(
        "org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
            + "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'birthDate': rejected"
            + " value [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.birthDate,required"
            + ".birthDate,required.java.time.LocalDate,required]; arguments []; default message [required]",
        errors.getLocalizedMessage());
    BindingResult bindingResult = errors.getBindingResult();
    assertEquals(1, bindingResult.getErrorCount());
    assertSame(errors.getPropertyEditorRegistry(), ((BeanPropertyBindingResult) bindingResult).getPropertyAccessor());
  }

  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate3() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(LocalDate.of(1970, 1, 1));
    pet.setId(null);
    pet.setName("Bella");
    pet.setType(type);

    // Act
    petValidator.validate(pet, new BindException(pet, "org.springframework.samples.petclinic.owner.Pet"));

    // Assert that nothing has changed
    assertEquals("1970-01-01", pet.getBirthDate().toString());
    assertTrue(pet.isNew());
    assertTrue(pet.getVisits().isEmpty());
    assertSame(type, pet.getType());
    assertEquals("Bella", pet.getName());
  }

  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate4() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(LocalDate.of(1970, 1, 1));
    pet.setId(1);
    pet.setName(null);
    pet.setType(type);
    BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

    // Act
    petValidator.validate(pet, errors);

    // Assert
    assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
        + "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'name': rejected value"
        + " [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.name,required.name,required"
        + ".java.lang.String,required]; arguments []; default message [required]", errors.getLocalizedMessage());
    BindingResult bindingResult = errors.getBindingResult();
    assertEquals(1, bindingResult.getErrorCount());
    assertSame(errors.getPropertyEditorRegistry(), ((BeanPropertyBindingResult) bindingResult).getPropertyAccessor());
  }

  /**
  * Method under test: {@link PetValidator#supports(Class)}
  */
  @Test
  void testSupports() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    // Act and Assert
    assertFalse(petValidator.supports(Object.class));
  }

  /**
   * Method under test: {@link PetValidator#supports(Class)}
   */
  @Test
  void testSupports2() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    // Act and Assert
    assertTrue(petValidator.supports(Pet.class));
  }
}

