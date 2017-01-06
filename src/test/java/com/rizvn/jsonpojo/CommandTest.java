package com.rizvn.jsonpojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizvan.json.Command;
import com.rizvan.json.Student;
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

  @Test
  public void testCommandBinding() throws Exception
  {
    ObjectMapper mapper = new ObjectMapper();

    Command addressCommand =  mapper.readValue(new File("src/test/resources/GetAddress.json"), Command.class);
    Assert.assertEquals(addressCommand.getGetAddress().getRequest().getPostcode(), "PR6 7EN");

    Command supplierCommand =  mapper.readValue(new File("src/test/resources/GetSupplier.json"), Command.class);
    Assert.assertEquals(supplierCommand.getGetSupplier().getRequest().getCustomerRef(), "123");
  }

  @Test
  public void testCommandBindingAndValidationSuccess() throws Exception
  {
    ObjectMapper mapper = new ObjectMapper();

    Command cmd =  mapper.readValue(new File("src/test/resources/GetAddress.json"), Command.class);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

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

    //5. validations
    Set<ConstraintViolation<Command>> validationResults = validator.validate(cmd);

    Assert.assertFalse("Expected no validation failures but found some",validationResults.isEmpty());
  }

}
