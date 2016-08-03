package com.rizvn.jsonpojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizvan.json.Address;
import com.rizvan.json.Student;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.FileInputStream;
import java.util.Set;

/**
 * Created by Riz
 */
public class StudentTest {

  @Test
  public void testValidStudentRecord() throws Exception{

    ObjectMapper mapper = new ObjectMapper();
    Student student = mapper.readValue(new FileInputStream("src/test/resources/ValidStudent.json"), Student.class);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    //3. Perform validation
    Set<ConstraintViolation<Student>> validationResults = validator.validate(student);

    Assert.assertTrue("Expected no validation failures but found some",validationResults.isEmpty());
  }

  @Test
  public void testInvalidStudent() throws Exception{

    ObjectMapper mapper = new ObjectMapper();
    Student student = mapper.readValue(new FileInputStream("src/test/resources/InvalidStudent.json"), Student.class);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<Student>> validationResults = validator.validate(student);

    Assert.assertFalse("Expected validation failures but none were found", validationResults.isEmpty());
    validationResults.forEach(violation -> System.out.println(violation.getPropertyPath().toString() + " - " +violation.getMessage()));
  }
}
