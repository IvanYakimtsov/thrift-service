/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package by.bsuir.iit.aipos.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-03-16")
public class Header implements org.apache.thrift.TBase<Header, Header._Fields>, java.io.Serializable, Cloneable, Comparable<Header> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Header");

  private static final org.apache.thrift.protocol.TField PATTERN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("patternName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField AUTHOR_EMAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("authorEmail", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new HeaderStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new HeaderTupleSchemeFactory();

  public String patternName; // required
  public String authorEmail; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PATTERN_NAME((short)1, "patternName"),
    AUTHOR_EMAIL((short)2, "authorEmail");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // PATTERN_NAME
          return PATTERN_NAME;
        case 2: // AUTHOR_EMAIL
          return AUTHOR_EMAIL;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PATTERN_NAME, new org.apache.thrift.meta_data.FieldMetaData("patternName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AUTHOR_EMAIL, new org.apache.thrift.meta_data.FieldMetaData("authorEmail", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Header.class, metaDataMap);
  }

  public Header() {
  }

  public Header(
    String patternName,
    String authorEmail)
  {
    this();
    this.patternName = patternName;
    this.authorEmail = authorEmail;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Header(Header other) {
    if (other.isSetPatternName()) {
      this.patternName = other.patternName;
    }
    if (other.isSetAuthorEmail()) {
      this.authorEmail = other.authorEmail;
    }
  }

  public Header deepCopy() {
    return new Header(this);
  }

  @Override
  public void clear() {
    this.patternName = null;
    this.authorEmail = null;
  }

  public String getPatternName() {
    return this.patternName;
  }

  public Header setPatternName(String patternName) {
    this.patternName = patternName;
    return this;
  }

  public void unsetPatternName() {
    this.patternName = null;
  }

  /** Returns true if field patternName is set (has been assigned a value) and false otherwise */
  public boolean isSetPatternName() {
    return this.patternName != null;
  }

  public void setPatternNameIsSet(boolean value) {
    if (!value) {
      this.patternName = null;
    }
  }

  public String getAuthorEmail() {
    return this.authorEmail;
  }

  public Header setAuthorEmail(String authorEmail) {
    this.authorEmail = authorEmail;
    return this;
  }

  public void unsetAuthorEmail() {
    this.authorEmail = null;
  }

  /** Returns true if field authorEmail is set (has been assigned a value) and false otherwise */
  public boolean isSetAuthorEmail() {
    return this.authorEmail != null;
  }

  public void setAuthorEmailIsSet(boolean value) {
    if (!value) {
      this.authorEmail = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case PATTERN_NAME:
      if (value == null) {
        unsetPatternName();
      } else {
        setPatternName((String)value);
      }
      break;

    case AUTHOR_EMAIL:
      if (value == null) {
        unsetAuthorEmail();
      } else {
        setAuthorEmail((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case PATTERN_NAME:
      return getPatternName();

    case AUTHOR_EMAIL:
      return getAuthorEmail();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case PATTERN_NAME:
      return isSetPatternName();
    case AUTHOR_EMAIL:
      return isSetAuthorEmail();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Header)
      return this.equals((Header)that);
    return false;
  }

  public boolean equals(Header that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_patternName = true && this.isSetPatternName();
    boolean that_present_patternName = true && that.isSetPatternName();
    if (this_present_patternName || that_present_patternName) {
      if (!(this_present_patternName && that_present_patternName))
        return false;
      if (!this.patternName.equals(that.patternName))
        return false;
    }

    boolean this_present_authorEmail = true && this.isSetAuthorEmail();
    boolean that_present_authorEmail = true && that.isSetAuthorEmail();
    if (this_present_authorEmail || that_present_authorEmail) {
      if (!(this_present_authorEmail && that_present_authorEmail))
        return false;
      if (!this.authorEmail.equals(that.authorEmail))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetPatternName()) ? 131071 : 524287);
    if (isSetPatternName())
      hashCode = hashCode * 8191 + patternName.hashCode();

    hashCode = hashCode * 8191 + ((isSetAuthorEmail()) ? 131071 : 524287);
    if (isSetAuthorEmail())
      hashCode = hashCode * 8191 + authorEmail.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Header other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetPatternName()).compareTo(other.isSetPatternName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPatternName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.patternName, other.patternName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAuthorEmail()).compareTo(other.isSetAuthorEmail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAuthorEmail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.authorEmail, other.authorEmail);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Header(");
    boolean first = true;

    sb.append("patternName:");
    if (this.patternName == null) {
      sb.append("null");
    } else {
      sb.append(this.patternName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("authorEmail:");
    if (this.authorEmail == null) {
      sb.append("null");
    } else {
      sb.append(this.authorEmail);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class HeaderStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HeaderStandardScheme getScheme() {
      return new HeaderStandardScheme();
    }
  }

  private static class HeaderStandardScheme extends org.apache.thrift.scheme.StandardScheme<Header> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Header struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PATTERN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.patternName = iprot.readString();
              struct.setPatternNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // AUTHOR_EMAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.authorEmail = iprot.readString();
              struct.setAuthorEmailIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Header struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.patternName != null) {
        oprot.writeFieldBegin(PATTERN_NAME_FIELD_DESC);
        oprot.writeString(struct.patternName);
        oprot.writeFieldEnd();
      }
      if (struct.authorEmail != null) {
        oprot.writeFieldBegin(AUTHOR_EMAIL_FIELD_DESC);
        oprot.writeString(struct.authorEmail);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HeaderTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HeaderTupleScheme getScheme() {
      return new HeaderTupleScheme();
    }
  }

  private static class HeaderTupleScheme extends org.apache.thrift.scheme.TupleScheme<Header> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Header struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetPatternName()) {
        optionals.set(0);
      }
      if (struct.isSetAuthorEmail()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetPatternName()) {
        oprot.writeString(struct.patternName);
      }
      if (struct.isSetAuthorEmail()) {
        oprot.writeString(struct.authorEmail);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Header struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.patternName = iprot.readString();
        struct.setPatternNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.authorEmail = iprot.readString();
        struct.setAuthorEmailIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

