package beans;

import executers.BaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;


public class Beans {

    @Autowired
   public BaseExecutor baseExecutor = new BaseExecutor();

}
