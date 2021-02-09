package SemanticAnalysis.DataTypes;

import AbstractSyntaxTree.ASTNode;
import SemanticAnalysis.DataTypeId;

public class PairType extends DataTypeId {

  private final DataTypeId fstType;
  private final DataTypeId sndType;

  public PairType(ASTNode node, DataTypeId fstType, DataTypeId sndType) {
    super(node);
    this.fstType = fstType;
    this.sndType = sndType;
  }

  public DataTypeId getFstType() {
    return fstType;
  }

  public DataTypeId getSndType() {
    return sndType;
  }

  private boolean equalsPairElem(DataTypeId el1, DataTypeId el2) {
    return el1 == null || el2 == null || el1.equals(el2);
  }

  @Override
  public String toString() {
    return "pair(" + fstType.toString() + ", " + sndType.toString() + ")";
  }

  @Override
  public boolean equals(DataTypeId object) {

    if (object instanceof PairType) {
      PairType pairObject = (PairType) object;
      return equalsPairElem(pairObject.getFstType(), this.fstType) &&
              equalsPairElem(pairObject.getSndType(), this.sndType);

    }

    return false;
  }
}