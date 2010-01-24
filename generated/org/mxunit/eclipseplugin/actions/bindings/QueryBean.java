/**
 * QueryBean.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mxunit.eclipseplugin.actions.bindings;

public class QueryBean  implements java.io.Serializable {
    private java.lang.String[] columnList;

    private java.lang.Object[][] data;

    public QueryBean() {
    }

    public QueryBean(
           java.lang.String[] columnList,
           java.lang.Object[][] data) {
           this.columnList = columnList;
           this.data = data;
    }


    /**
     * Gets the columnList value for this QueryBean.
     * 
     * @return columnList
     */
    public java.lang.String[] getColumnList() {
        return columnList;
    }


    /**
     * Sets the columnList value for this QueryBean.
     * 
     * @param columnList
     */
    public void setColumnList(java.lang.String[] columnList) {
        this.columnList = columnList;
    }


    /**
     * Gets the data value for this QueryBean.
     * 
     * @return data
     */
    public java.lang.Object[][] getData() {
        return data;
    }


    /**
     * Sets the data value for this QueryBean.
     * 
     * @param data
     */
    public void setData(java.lang.Object[][] data) {
        this.data = data;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryBean)) return false;
        QueryBean other = (QueryBean) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.columnList==null && other.getColumnList()==null) || 
             (this.columnList!=null &&
              java.util.Arrays.equals(this.columnList, other.getColumnList()))) &&
            ((this.data==null && other.getData()==null) || 
             (this.data!=null &&
              java.util.Arrays.equals(this.data, other.getData())));
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
        if (getColumnList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColumnList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColumnList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getData(), i);
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
        new org.apache.axis.description.TypeDesc(QueryBean.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://rpc.xml.coldfusion", "QueryBean"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columnList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "columnList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("", "data"));
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
