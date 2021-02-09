package SemanticAnalysis.DataTypes;

import AbstractSyntaxTree.ASTNode;
import SemanticAnalysis.DataTypeId;

public class ArrayType extends DataTypeId {

  private int size = -1;
  private final DataTypeId arrayType;

  public ArrayType(ASTNode node, DataTypeId arrayType) {
    super(node);
    this.arrayType = arrayType;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) { this.size = size;}

  public DataTypeId getArrayType() {
    return arrayType;
  }

  @Override
  public String toString() {
    return arrayType.toString() + "[]";
  }

  @Override
  public boolean equals(DataTypeId object) {
    // might be a bit confusing to have both the type and the field be called array type?

    if (this.arrayType == null) {
      return true;
    }

    if (object instanceof ArrayType) {
      ArrayType arrayObject = (ArrayType) object;
      return arrayObject.getArrayType() == null ||  arrayObject.getArrayType().equals(this.arrayType);
    }
    return false;
  }
}
