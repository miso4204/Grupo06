import static org.junit.Assert.*;
import grupo6.modulo.utilidades.Feature;
import grupo6.modulo.utilidades.Variability;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class VariabilityTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsEnable() {
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.ECOTURISMO));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.SESSION));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.LOG_IN));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.LOG_OUT));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.UPDATE_PROFILE));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.CHANGE_PASSWORD));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.CREATE_ACCOUNT));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.SHOPPING_CART));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.PAY));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.CREDIT_CARD));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.PSE));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.REPORTS));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.SALES));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.REPORT_BY_LOCATION));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.REPORT_BY_PERIOD));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.ADMIN_MONEDA));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.DOLAR));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.PRODUCT));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.SEARCH));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.SEARCH_BY_LOCATION));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.SEARCH_BY_PRICE));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.SEARCH_BY_DATE));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.QUALITY_ATRIBUTES));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.CONFIGURABILITY));
		assertTrue("Feature no encontrado",Variability.isEnable(Feature.FUNCTIONALITY));
		assertFalse("Feature no encontrado",Variability.isEnable("NO DEBERIA ESTAR"));
	}
	
	@After
	public void tearDown() throws Exception {
	}

	

}
