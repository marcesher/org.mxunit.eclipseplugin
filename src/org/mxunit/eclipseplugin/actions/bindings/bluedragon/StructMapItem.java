/**
 * StructMapItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings.bluedragon;

public class StructMapItem  implements java.io.Serializable {
    private java.lang.Object key;

    private java.lang.Object val;

    public StructMapItem() {
    }

    public StructMapItem(
           java.lang.Object key,
           java.lang.Object val) {
           this.key = key;
           this.val = val;
    }


    /**
     * Gets the key value for this StructMapItem.
     * 
     * @return key
     */
    public java.lang.Object getKey() {
        return key;
    }


    /**
     * Sets the key value for this StructMapItem.
     * 
     * @param key
     */
    public void setKey(java.lang.Object key) {
        this.key = key;
    }


    /**
     * Gets the val value for this StructMapItem.
     * 
     * @return val
     */
    public java.lang.Object getVal() {
        return val;
    }


    /**
     * Sets the val value for this StructMapItem.
     * 
     * @param val
     */
    public void setVal(java.lang.Object val) {
        this.val = val;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StructMapItem)) return false;
        StructMapItem other = (StructMapItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.val==null && other.getVal()==null) || 
             (this.val!=null &&
              this.val.equals(other.getVal())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getVal() != null) {
            _hashCode += getVal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StructMapItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wstypes.newatlanta.com", "StructMapItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("", "key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("val");
        elemField.setXmlName(new javax.xml.namespace.QName("", "val"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
