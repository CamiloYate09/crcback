package co.gov.crcom.mycroft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import co.gov.crcom.mycroft.config.SecurityConfig;
import co.gov.crcom.mycroft.exception.NegocioException;
import co.gov.crcom.mycroft.model.entity.Parametro;
import co.gov.crcom.mycroft.model.request.LoginRequest;
import co.gov.crcom.mycroft.model.response.LoginResponse;
import co.gov.crcom.mycroft.repository.ParametroRepository;
import co.gov.crcom.mycroft.schema.IServiceldap;
import co.gov.crcom.mycroft.schema.RespuestaDTO;
import co.gov.crcom.mycroft.schema.ServiceldapCRC;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static co.gov.crcom.mycroft.model.entity.Parametro.*;
import static co.gov.crcom.mycroft.util.ErrorMessageUtil.*;
import static co.gov.crcom.mycroft.util.InfoMessageUtil.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

@Service
public class LoginServiceImpl implements LoginService {

	/**
	 * Servicios para acceder a la base de datos.
	 */
	@Autowired
	private ParametroRepository mParametroRepository;

	//Constantes
	private static final String ROL_CONSULTA = "ROLE_CONSULTA";
	private static final String ROL_CARGA = "ROLE_CARGA";
	private static final String ROL_HISTORIA = "ROLE_HISTORIA";

	/**
	 * @see LoginService#autenticarUsuario(LoginRequest)
	 */
	@Override
	public ResponseEntity<LoginResponse> autenticarUsuario(LoginRequest request) {

		LoginResponse response = new LoginResponse();

		try {

			// Validamos que la petición tenga los parámetros indicados.
			if (request == null) {
				response.setEstado(ERR_LOGIN_001);
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
			}

			// FIXME: Impl Temporal.
			List<String> roles = new ArrayList<>();
			if ("usuario1".equals(request.getUser())) {
				roles.add(ROL_CONSULTA);
			} else if ("usuario2".equals(request.getUser())) {
				roles.add(ROL_CARGA);
			} else if ("usuario3".equals(request.getUser())) {
				roles.add(ROL_HISTORIA);
			} else if ("usuario4".equals(request.getUser())) {
				roles.add(ROL_CONSULTA);
				roles.add(ROL_CARGA);
				roles.add(ROL_HISTORIA);
			} else {
				response.setEstado(ERR_LOGIN_001);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
			
			//Invocamos servicio de LDAP:
			boolean autenticado = autenticarLdap(request.getUser(), request.getPassword());
			
			if(Boolean.TRUE.equals(autenticado)) {
				// Si el usuario existe retornamos mensaje exitoso.
				response.setEstado(MSG_LOGIN_001);
				response.setJwt(generarJwt(request.getUser(), roles));
				response.setRoles(roles);
				response.setLoginExitoso(Boolean.TRUE);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
			else {
				response.setEstado(ERR_LOGIN_001);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}

		} catch (NegocioException e) {
			response.setEstado(e.getMensaje());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		} catch (Exception e) {
			response.setEstado(ERR_LOGIN_001);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}
	
	private boolean autenticarLdap(String usuario, String contrasena) throws MalformedURLException, NegocioException {

		// 1. Consultamos el parámetro del endpoint para consumir el LDAP de Informese.
		String parEndpointLdap = "UNO";
//				mParametroRepository.findFirstByIdParametro(PAR_EXPIRACION_JWT);
		if (parEndpointLdap != null) {

			/*
			// Configuramos la URL y el QName del servicio
			URL wsdlLocation = new URL(parEndpointLdap.getValor());
			QName serviceName = new QName("http://tempuri.org/", "ServiceldapCRC");
			ServiceldapCRC portNameService = new ServiceldapCRC(wsdlLocation, serviceName);

			// Obtenemos una instancia del servicio
			IServiceldap service = portNameService.getBasicHttpBindingIServiceldap();

			// Configuramos los bindings de la petición
			BindingProvider bindingProvider = (BindingProvider) service;
			bindingProvider.getRequestContext().put("javax.xml.ws.service.endpoint.address",
					parEndpointLdap.getValor());

			// Invocamos el servicio.
			RespuestaDTO respuestaLdap = service.autenticacion(usuario, contrasena);
			
			//Validamos la respuesta del LDAP:
			if (respuestaLdap == null || respuestaLdap.getUsuarioldap() == null
					|| respuestaLdap.getUsuarioldap().getUsuario() == null) {
				throw new NegocioException(ERR_LOGIN_001);
			}
			*/
			
			//Si existe el usuario retornamos verdadero.
			return Boolean.TRUE;

		} else {
			throw new NegocioException(ERR_LOGIN_002);
		}
	}
	

	/**
	 * Generamos un JWT para un usuario y sus roles.
	 * @param mUsuario usuario que se logueo.
	 * @param roles roles asignados al usuario.
	 * @return
	 */
	private String generarJwt(String mUsuario, List<String> roles) {

		//1. Consultamos el parámetro de expiración del JWT, sino asignamos 1 hora.
		Parametro parExpiracionJwt = mParametroRepository.findFirstByIdParametro(PAR_EXPIRACION_JWT);
		Long expiracionJwt = 3600000L;
		if (parExpiracionJwt != null) {
			expiracionJwt = Long.parseLong(parExpiracionJwt.getValor());
		}

		//2. Parametrizamos los roles relacionados al usuario.
		String authorityString = roles.stream().map(String::valueOf).collect(Collectors.joining(","));
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);		

		//3. Construimos el JWT.
		String token = Jwts.builder().setId(SecurityConfig.JWT_KEY)
				.setSubject(mUsuario)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiracionJwt))
				.signWith(SignatureAlgorithm.HS512, SecurityConfig.JWT_KEY.getBytes()).compact();

		//4. Retornamos el jwt con el prefijo Bearer.
		return "Bearer " + token;
	}

}
