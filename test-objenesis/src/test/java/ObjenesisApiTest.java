import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

public class ObjenesisApiTest {

    @Test
    void shouldCreateObject() {
        ObjenesisStd std = new ObjenesisStd();
        ObjectInstantiator<DataObject> instantiator = std.getInstantiatorOf(DataObject.class);
        DataObject dataObject = instantiator.newInstance();
        dataObject.field = "teste";
        Assertions.assertNotNull(dataObject);
        Assertions.assertEquals("teste", dataObject.field);
    }
}
