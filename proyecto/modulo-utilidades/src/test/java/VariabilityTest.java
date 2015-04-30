import static org.junit.Assert.*;
import grupo6.modulo.utilidades.FeaturesNames;
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
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.ECOTURISMO));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.SESSION));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.LOG_IN));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.LOG_OUT));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.UPDATE_PROFILE));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.CHANGE_PASSWORD));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.CREATE_ACCOUNT));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.SHOPPING_CART));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.PAY));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.CREDIT_CARD));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.PSE));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.REPORTS));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.SALES));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.REPORT_BY_LOCATION));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.REPORT_BY_PERIOD));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.ADMIN_MONEDA));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.DOLAR));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.PRODUCT));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.SEARCH));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.SEARCH_BY_LOCATION));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.SEARCH_BY_PRICE));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.SEARCH_BY_DATE));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.QUALITY_ATRIBUTES));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.CONFIGURABILITY));
		assertTrue("Feature no encontrado",Variability.isEnable(FeaturesNames.FUNCTIONALITY));
		assertFalse("Feature no encontrado",Variability.isEnable("NO DEBERIA ESTAR"));
	}
	
	@After
	public void tearDown() throws Exception {
	}

	

}
