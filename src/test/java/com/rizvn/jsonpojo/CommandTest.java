package com.rizvn.jsonpojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizvan.json.Command;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.util.Set;

/**
 * Created by Riz
 */
public class CommandTest {

  ObjectMapper mapper = new ObjectMapper();
  ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

  @Test
  public void testCommandBinding() throws Exception
  {
    Command addressCommand =  mapper.readValue(new File("src/test/resources/GetAddress.json"), Command.class);
    Assert.assertEquals("PR6 7EN", addressCommand.getGetAddress().getPostcode());

    Command supplierCommand =  mapper.readValue(new File("src/test/resources/GetSupplier.json"), Command.class);
    Assert.assertEquals("123", supplierCommand.getGetSupplier().getCustomerRef());
  }

  @Test
  public void testCommandBindingAndValidationSuccess() throws Exception
  {
    Command cmd =  mapper.readValue(new File("src/test/resources/GetAddress.json"), Command.class);
    Validator validator = validatorFactory.getValidator();

    Set<ConstraintViolation<Command>> validationResults = validator.validate(cmd);

    Assert.assertTrue("Expected no validation failures but found some",validationResults.isEmpty());
  }

  @Test
  public void testCommandBindingAndValidationFail() throws Exception
  {
    ObjectMapper mapper = new ObjectMapper();

    Command cmd =  mapper.readValue(new File("src/test/resources/GetSupplier.json"), Command.class);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Set<ConstraintViolation<Command>> validationResults = validator.validate(cmd);

    Assert.assertFalse("Expected no validation failures but found some",validationResults.isEmpty());
  }

}
