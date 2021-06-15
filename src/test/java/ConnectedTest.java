import org.junit.Test;

import static org.junit.Assert.assertTrue;

class ConnectedTest {
    @Test
    public void TestValidateArgsEmptyList() {
        String[] args = {""};
        assertTrue(!Connected.validateArgs(args));
    }

    @Test
    public void TestValidateArgsNoFileName() {
        String[] args = {"  ", "Delhi", "Pune"};
        assertTrue(!Connected.validateArgs(args));
    }

    @Test
    public void TestValidateArgsInvalidCityName() {
        String[] args = {"rrs", " ", " "};
        assertTrue(!Connected.validateArgs(args));
    }


}