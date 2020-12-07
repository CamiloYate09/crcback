package co.gov.crcom.mycroft;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import co.gov.crcom.mycroft.schema.IServiceldap;
import co.gov.crcom.mycroft.schema.RespuestaDTO;
import co.gov.crcom.mycroft.schema.ServiceldapCRC;

public class TestWebServiceLdap {

	public static void main(String[] args) throws MalformedURLException {
		
		String endpoint = "http://vm-app3-tramites.crcom.gov.co/ldap/ServiceldapCRC.svc?wsdl";
		URL wsdlLocation = new URL(endpoint);
		QName serviceName = new QName("http://tempuri.org/", "ServiceldapCRC");
		
		ServiceldapCRC portNameService = new ServiceldapCRC(wsdlLocation, serviceName) ;
		
		IServiceldap service = portNameService.getBasicHttpBindingIServiceldap();
		
		BindingProvider bindingProvider = (BindingProvider) service;
		
		bindingProvider.getRequestContext().put("javax.xml.ws.service.endpoint.address", endpoint);
		
		RespuestaDTO respuestaLdap = service.autenticacion("informese", "!1nf0rm3s3IA");
		
		
		System.out.println(respuestaLdap.toString());

	}

}
