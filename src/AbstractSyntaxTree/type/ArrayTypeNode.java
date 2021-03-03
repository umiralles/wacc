package AbstractSyntaxTree.type;

import InternalRepresentation.InternalState;
import SemanticAnalysis.DataTypeId;
import SemanticAnalysis.DataTypes.ArrayType;
import SemanticAnalysis.Identifier;
import SemanticAnalysis.SymbolTable;
import java.util.List;

public class ArrayTypeNode implements TypeNode {

  /* type:  TypeNode of the corresponding type of elements the array contains */
  private final TypeNode type;
  private SymbolTable currSymTable = null;

  public ArrayTypeNode(TypeNode type) {
    this.type = type;
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<String> errorMessages) {
    currSymTable = symbolTable;

    /* Recursively call semanticAnalysis on type */
    type.semanticAnalysis(symbolTable, errorMessages);
  }

  @Override
  public void generateAssembly(InternalState internalState) {

  }

  @Override
  public SymbolTable getCurrSymTable() {
    return currSymTable;
  }

  @Override
  public Identifier getIdentifier(SymbolTable symbolTable) {
    return new ArrayType((DataTypeId) type.getIdentifier(symbolTable));
  }

  @Override
  public DataTypeId getType() {
    return new ArrayType(type.getType());
  }
}