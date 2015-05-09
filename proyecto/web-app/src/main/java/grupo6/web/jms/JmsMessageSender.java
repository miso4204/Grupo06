package grupo6.web.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Envía un mensaje JMS a la cola dessoft-queue.
 * El contenido del mensaje es un objeto que el 'receiver'
 * sabe interpretar. 
 * 
 * @author caespinosam
 *
 */
@Profile("pago_asincrono")
@Service
public class JmsMessageSender {

	/**
	 * Manejador de conexión a la cola.
	 */
	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * Envía el objeto mensaje a la cola.
	 * @param o el objeto a enviar como mensaje.
	 */	
	public void send(Object o) {
		System.out.println("Enviando a la cola JMS el objeto: " + o);
		jmsTemplate.convertAndSend(o);
	}

}
