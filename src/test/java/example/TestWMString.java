package example;

import com.wm.app.b2b.client.Context;
import com.wm.app.b2b.client.ServiceException;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class TestWMString {
	
	private static final String INPUT_LABEL = "inString";//Input variable name matching the service
    private static final String OUTPUT_LABEL = "value";//Output variable name matching the service
 
    //The Context is the variable we will connect to the server with and invoke services through.
    private static Context context = new Context();
 
    public TestWMString() {
    }
 
    /**
     * The Context is expensive to create and so is done through a method annotated @BeforClass.
     * 
     * @throws ServiceException 
     */
    @BeforeClass
    public static void setup() throws ServiceException {
        String server = "localhost:6666";
        String username = "Administrator";
        String password = "manage";
 
        context.connect(server, username, password);
    }
 
    /**
     * We must remember to disconnect the Context from the server once we have completed our unit tests.
     * 
     * @throws ServiceException 
     */
    @AfterClass
    public static void teardown() throws ServiceException {
        context.disconnect();
    }
 
    /**
     * This method handles the details of calling the service.
     * 
     * @param inputString
     * @return
     * @throws ServiceException 
     */
    private int invokeStringLengthService(String inputString) throws ServiceException {
        IData input = IDataFactory.create();//IData objects are data structures used to store input and output variables for service invocation.
        IDataCursor cursor = input.getCursor();//The cursor is used to traverse and manipulate an IData object.
        IDataUtil.put(cursor, INPUT_LABEL, inputString);//IDataUtil makes it easy to manipulate add and find variables in an IData object. Here we're adding an input variable called 'inString'
 
        IData output = context.invoke("pub.string", "length", input);//This invokes the service on the server. We pass in the package and name of the service as well as our input variables.
 
        IDataCursor outputCursor = output.getCursor();
        return IDataUtil.getInt(outputCursor, OUTPUT_LABEL, -1);//We retrieve the output variable from the IData object as an int. Various convenience methods are available to get other types of objects.
    }
 
    /**
     * Our unit test that double checks Software AG's math.
     * 
     * @throws ServiceException 
     */
    @Test
    public void testStringLength() throws ServiceException {
        int length = invokeStringLengthService("12345");
        assertEquals(5, length);
    }

}
