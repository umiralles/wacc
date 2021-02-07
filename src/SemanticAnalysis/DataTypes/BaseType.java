package SemanticAnalysis.DataTypes;

import AbstractSyntaxTree.ASTNode;
import SemanticAnalysis.DataTypeId;

public class BaseType extends DataTypeId {

  private final Type type;

  public BaseType(ASTNode node, Type type) {
    super(node);
    this.type = type;
  }

  public Type getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof BaseType)) {
      return false;
    }

    BaseType baseType = (BaseType) o;
    return baseType.getType() == type;
  }

  @Override
  public String toString() {
    return type.toString().toLowerCase();
  }

  public enum Type {INT, BOOL, CHAR}
}