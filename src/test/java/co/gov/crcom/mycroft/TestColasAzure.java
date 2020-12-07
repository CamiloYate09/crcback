package co.gov.crcom.mycroft;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.ServiceBusReceiverException;

public class TestColasAzure {
	
	public static void main(String[] args) throws InterruptedException {
		
		String urlConexion = "Endpoint=sb://busmessagingcrc.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=lWca+LtxlROET6TNU//sYSocOm9/2Frhh/6lxiG0eHA=";
		String nombreCola = "queuecrc";
		
		System.out.println(recibirMensaje(urlConexion, nombreCola));
	}
	
	
	static List<String> recibirMensaje(String urlConexion, String nombreCola) throws InterruptedException {

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

	    TimeUnit.SECONDS.sleep(5);
	    
	    System.out.println("Stopping and closing the processor");
	    processorClient.close();   
	    
	    return mensajes;
	}

}
