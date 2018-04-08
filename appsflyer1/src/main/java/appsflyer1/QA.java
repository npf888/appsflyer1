package appsflyer1;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class QA {

	public static void main(String[] args){
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao UserDao = (UserDao) ctx.getBean("userDao");
		System.out.println("");*/
		 // Create a logger by the name of class Log4jTest.  
        Logger logger = Logger.getLogger(QA.class);  
  
        // Use the default configuration.  
        BasicConfigurator.configure();  
  
        // Set the logger level to Level.INFO  
        logger.setLevel(Level.INFO);  
  
        // This request will be disabled since Level.DEBUG < Level.INFO.  
        logger.debug("This is debug.");  
  
        // These requests will be enabled.  
        logger.info("This is an info.");  
        logger.warn("This is a warning.");  
        logger.error("This is an error.");  
        logger.fatal("This is a fatal error.");  
	}
}
