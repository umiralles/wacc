package SemanticAnalysis.DataTypes;

import SemanticAnalysis.DataTypeId;

public class BaseType extends DataTypeId {

  /* INT_BYTES_SIZE: size of an in in bytes */
  private static final int INT_BYTES_SIZE = 4;
  /* BOOL_BYTES_SIZE: size of a bool in bytes */
  private static final int BOOL_BYTES_SIZE = 1;
  /* CHAR_BYTES_SIZE: size of a char in bytes */
  private static final int CHAR_BYTES_SIZE = 1;
  /* ADDRESS_BYTES_SIZE: size of an address in bytes */
  private static final int ADDRESS_BYTES_SIZE = 4;

  /* type: BaseType.Type enum corresponding to the base type this represents */
  private final Type type;

  public BaseType(Type type) {
    this.type = type;
  }

  public Type getBaseType() {
    return type;
  }

  /* BaseTypes are equal if their type is the same */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof BaseType)) {
      return false;
    }

    BaseType baseType = (BaseType) o;
    return baseType.getBaseType() == type;
  }

  @Override
  public int hashCode() {
    return type.ordinal();
  }

  @Override
  public String toString() {
    return type.toString();
  }

  /* Returns corresponding size of type in bytes */
  @Override
  public int getSize() {
    switch (type) {
      case INT:
        return INT_BYTES_SIZE;
      case BOOL:
        return BOOL_BYTES_SIZE;
      case CHAR:
        return CHAR_BYTES_SIZE;
      case STRING:
        return ADDRESS_BYTES_SIZE;
      default:
        return 0;
    }
  }

  public enum Type {INT, BOOL, CHAR, STRING}
}
