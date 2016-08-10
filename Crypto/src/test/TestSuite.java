package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   DESDataDecrypterTest.class,
   DESDataEncrypterTest.class,
   DESKeyGeneratorTest.class,
   MongoDBClientTest.class
})

public class TestSuite {

}
