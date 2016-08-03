package com.rizvn.jsonpojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizvan.json.Address;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Riz
 */
public class JsonAddressTest {

  @Test
  public void testAddressValidMapping() throws Exception{
    ObjectMapper mapper = new ObjectMapper();
    Address address = (Address) mapper.readValue(
                        "{" +
                        "\"street-addreaa\":\"10 Some Street\", " +
                        "\"locality\":\"Lancashire\", " +
                        "\"region\": \"North West\", " +
                        "\"country-name\": \"UK\"" +
                        "}",
                        Address.class);

    Assert.assertEquals(address.getLocality(), "Lancashire");
    Assert.assertEquals(address.getRegion(), "North West");
    Assert.assertEquals(address.getCountryName(), "UK");
  }

  @Test
  public void testValidAddress() throws Exception{

    //1. First marshall the object
    ObjectMapper mapper = new ObjectMapper();
    Address address = (Address) mapper.readValue(
    "{" +
    "\"street-addreaa\":\"10 Some Street\", " +
    "\"locality\":\"Lancashire\", " +
    "\"region\": \"North West\", " +
    "\"country-name\": \"UK\"" +
    "}",
    Address.class);

    //2. Validate the object using jsr303 (implemented by hibernate validator)
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    //3. Perform validation
    Set<ConstraintViolation<Address>> validationResults = validator.validate(address);

    Assert.assertTrue("Expected no validation failures but found some",validationResults.isEmpty());
  }

  @Test
  public void testInvalidAddress() throws Exception{

    //1. First marshall the object
    ObjectMapper mapper = new ObjectMapper();
    Address address = (Address) mapper.readValue(
    "{" +
    "\"region\": \"North West\", " +
    "\"country-name\": \"UK\"" +
    "}",
    Address.class);

    //2. Validate the object using jsr303 (implemented by hibernate validator)
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    //3. Perform validation
    Set<ConstraintViolation<Address>> validationResults = validator.validate(address);

    Assert.assertFalse("Expected validation failures but none were found", validationResults.isEmpty());

    validationResults.forEach(violation -> System.out.println(violation.getPropertyPath().toString() + " - " +violation.getMessage()));
  }
}
