/**
 * StructMap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings.bluedragon;



public class StructMap  implements java.io.Serializable {
    private org.mxunit.eclipseplugin.actions.bindings.bluedragon.StructMapItem[] item;

    public StructMap() {
    }

    public StructMap(
           org.mxunit.eclipseplugin.actions.bindings.bluedragon.StructMapItem[] item) {
           this.item = item;
    }


    /**
     * Gets the item value for this StructMap.
     * 
     * @return item
     */
    public org.mxunit.eclipseplugin.actions.bindings.bluedragon.StructMapItem[] getItem() {
        return item;
    }


    /**
     * Sets the item value for this StructMap.
     * 
     * @param item
     */
    public void setItem(org.mxunit.eclipseplugin.actions.bindings.bluedragon.StructMapItem[] item) {
        this.item = item;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StructMap)) return false;
        StructMap other = (StructMap) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.item==null && other.getItem()==null) || 
             (this.item!=null &&
              java.util.Arrays.equals(this.item, other.getItem())));
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
        if (getItem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getItem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getItem(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StructMap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wstypes.newatlanta.com", "StructMap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("item");
        elemField.setXmlName(new javax.xml.namespace.QName("", "item"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wstypes.newatlanta.com", "StructMapItem"));
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
