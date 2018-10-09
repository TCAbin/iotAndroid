import cn.com.gree.utils.IOTUtils.DataCollector;
import org.junit.Test;

import java.util.Map;

public class iotInterfaceTest {



    @Test
    public void test() throws Exception {
//        String id = "199dfa18-f05f-4ff4-a0cb-34b418e002ad";
        String id = "906203a2-5987-46bc-b224-71b7fff677e2";
        Map<String,String> map = DataCollector.getRemoteData(id,DataCollector.getToken());
        System.out.println(1);
    }
}
