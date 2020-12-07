
package co.gov.crcom.mycroft.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="autenticacionResult" type="{http://schemas.datacontract.org/2004/07/LDAP.DTO}RespuestaDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "autenticacionResult"
})
@XmlRootElement(name = "autenticacionResponse")
public class AutenticacionResponse {

    @XmlElement(nillable = true)
    protected RespuestaDTO autenticacionResult;

    /**
     * Obtiene el valor de la propiedad autenticacionResult.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaDTO }
     *     
     */
    public RespuestaDTO getAutenticacionResult() {
        return autenticacionResult;
    }

    /**
     * Define el valor de la propiedad autenticacionResult.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaDTO }
     *     
     */
    public void setAutenticacionResult(RespuestaDTO value) {
        this.autenticacionResult = value;
    }

}
