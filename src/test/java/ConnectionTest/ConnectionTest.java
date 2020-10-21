package ConnectionTest;

import br.com.rentcar.jdbc.ConnectionFactory;
import org.junit.Test;

public class ConnectionTest {

    @Test
    public void getConnection(){
        ConnectionFactory.getConnection();
    }
}
