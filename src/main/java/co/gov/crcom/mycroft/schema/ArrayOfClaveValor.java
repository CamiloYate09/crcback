
package co.gov.crcom.mycroft.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfClaveValor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfClaveValor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClaveValor" type="{http://schemas.datacontract.org/2004/07/LDAP.MINTIC.DTO}ClaveValor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfClaveValor", namespace = "http://schemas.datacontract.org/2004/07/LDAP.MINTIC.DTO", propOrder = {
    "claveValor"
})
public class ArrayOfClaveValor {

    @XmlElement(name = "ClaveValor", nillable = true)
    protected List<ClaveValor> claveValor;

    /**
     * Gets the value of the claveValor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the claveValor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClaveValor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClaveValor }
     * 
     * 
     */
    public List<ClaveValor> getClaveValor() {
        if (claveValor == null) {
            claveValor = new ArrayList<ClaveValor>();
        }
        return this.claveValor;
    }

}
