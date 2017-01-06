package com.rizvn.jsonpojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizvan.json.Command;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

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

}
