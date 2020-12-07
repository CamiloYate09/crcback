
package co.gov.crcom.mycroft.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para RespuestaDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RespuestaDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuarioldap" type="{http://schemas.datacontract.org/2004/07/LDAP.DTO}UsuarioDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaDTO", namespace = "http://schemas.datacontract.org/2004/07/LDAP.DTO", propOrder = {
    "codigo",
    "descripcion",
    "usuarioldap"
})
public class RespuestaDTO {

    @XmlElement(nillable = true)
    protected String codigo;
    @XmlElement(nillable = true)
    protected String descripcion;
    @XmlElement(nillable = true)
    protected UsuarioDTO usuarioldap;

    /**
     * Obtiene el valor de la propiedad codigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Define el valor de la propiedad codigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioldap.
     * 
     * @return
     *     possible object is
     *     {@link UsuarioDTO }
     *     
     */
    public UsuarioDTO getUsuarioldap() {
        return usuarioldap;
    }

    /**
     * Define el valor de la propiedad usuarioldap.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuarioDTO }
     *     
     */
    public void setUsuarioldap(UsuarioDTO value) {
        this.usuarioldap = value;
    }

}
