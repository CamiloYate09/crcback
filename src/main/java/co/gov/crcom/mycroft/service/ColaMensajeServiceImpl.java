package co.gov.crcom.mycroft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.ServiceBusReceiverException;
import com.azure.messaging.servicebus.ServiceBusSenderClient;

@Service
public class ColaMensajeServiceImpl implements ColaMensajeService {

	/**
	 * @see ColaMensajeService#enviarMensaje(String, String, String)
	 */
	@Override
	public void enviarMensaje(String urlConexion, String nombreCola, String mensaje) {

		// crear un cliente de remitente de Service Bus para la cola
		ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
				.connectionString(urlConexion)
				.sender()
				.queueName(nombreCola)
				.buildClient();

		// enviar un mensaje a la cola
		senderClient.sendMessage(new ServiceBusMessage(mensaje));

		// cerramos el cliente.
		senderClient.close();
	}

	/**
	 * @see ColaMensajeService#recibirMensaje(String, String)
	 */
	@Override
	public List<String> recibirMensaje(String urlConexion, String nombreCola) {

		List<String> mensajes = new ArrayList<>();
		
		// consumidor que procesa un solo mensaje recibido de Service Bus
	    Consumer<ServiceBusReceivedMessageContext> messageProcessor = context -> {
	        ServiceBusReceivedMessage message = context.getMessage();
	        System.out.println("Mensaje recibido: " + message.getBody().toString());
	        mensajes.add(message.getBody().toString());
	    };

	    // maneja cualquier error que ocurra al recibir mensajes
	    Consumer<Throwable> errorHandler = throwable -> {
	        System.out.println("Error al recibir mensajes: " + throwable.getMessage());
	        if (throwable instanceof ServiceBusReceiverException) {
	            ServiceBusReceiverException serviceBusReceiverException = (ServiceBusReceiverException) throwable;
	            System.out.println("Error source: " + serviceBusReceiverException.getErrorSource());
	        }
	    };

	    // crear una instancia del procesador a través de ServiceBusClientBuilder
	    ServiceBusProcessorClient processorClient = new ServiceBusClientBuilder()
	        .connectionString(urlConexion)
	        .processor()
	        .queueName(nombreCola)
	        .processMessage(messageProcessor)
	        .processError(errorHandler)
	        .buildProcessorClient();

	    System.out.println("Starting the processor");
	    processorClient.start();

	    System.out.println("Stopping and closing the processor");
	    processorClient.close();   
	    
	    return mensajes;
	}

}
