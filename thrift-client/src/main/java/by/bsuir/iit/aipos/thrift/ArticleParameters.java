/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package by.bsuir.iit.aipos.thrift;


public enum ArticleParameters implements org.apache.thrift.TEnum {
  NAME(0),
  PHOTO(1),
  BODY(2);

  private final int value;

  private ArticleParameters(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static ArticleParameters findByValue(int value) { 
    switch (value) {
      case 0:
        return NAME;
      case 1:
        return PHOTO;
      case 2:
        return BODY;
      default:
        return null;
    }
  }
}
