package yfu.practice.springboot.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CommonUtilsTest {

	@Test
	void testCamel2UpperCase() {
		try {
            Assertions.assertEquals("YFU_NAME", CommonUtils.camel2UpperCase("YFU_NAME"));
            Assertions.assertEquals("YFU_NAME", CommonUtils.camel2UpperCase("_YFU_NAME"));
            Assertions.assertEquals("YFU_NAME", CommonUtils.camel2UpperCase("yfuName"));
            Assertions.assertEquals("YFU_NAME", CommonUtils.camel2UpperCase("YfuName"));
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e);
        }
	}

	@Test
	void testCamelize() {
		try {
            Assertions.assertEquals("yfuName", CommonUtils.camelize("YFU_NAME", true));
            Assertions.assertEquals("yfuName", CommonUtils.camelize("_YFU_NAME", true));
            Assertions.assertEquals("yfuname", CommonUtils.camelize("yfuName", true));
            Assertions.assertEquals("yfuname", CommonUtils.camelize("YfuName", true));
            
            Assertions.assertEquals("YfuName", CommonUtils.camelize("YFU_NAME", false));
            Assertions.assertEquals("YfuName", CommonUtils.camelize("_YFU_NAME", false));
            Assertions.assertEquals("Yfuname", CommonUtils.camelize("yfuName", false));
            Assertions.assertEquals("Yfuname", CommonUtils.camelize("YfuName", false));
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e);
        }
	}
}