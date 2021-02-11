package AbstractSyntaxTree.type;

import SemanticAnalysis.DataTypeId;
import SemanticAnalysis.DataTypes.BaseType;
import SemanticAnalysis.DataTypes.BaseType.Type;
import SemanticAnalysis.Identifier;
import SemanticAnalysis.SymbolTable;
import java.util.List;

public class BaseTypeNode implements TypeNode {

  private final Type baseType;

  public BaseTypeNode(Type baseType) {
    this.baseType = baseType;
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<String> errorMessages) {
  }

  @Override
  public Identifier getIdentifier(SymbolTable parentSymbolTable) {
    return parentSymbolTable.lookupAll(baseType.toString().toLowerCase());
  }

  @Override
  public DataTypeId getType() {
    return new BaseType(baseType);
  }
}