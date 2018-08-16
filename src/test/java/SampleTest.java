import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SampleTest {
	@Test
	public void simpleTest() {
		int a=1;
		int b=2;
		assertEquals(3,a + b);
	}
	@Test
	public void simpleTestFail() {
		int a=1;
		int b=2;
		assertEquals(3,a + b);
	}
}