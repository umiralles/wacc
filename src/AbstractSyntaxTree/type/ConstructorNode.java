package AbstractSyntaxTree.type;

import AbstractSyntaxTree.expression.IdentifierNode;
import AbstractSyntaxTree.statement.StatementNode;
import InternalRepresentation.InternalState;
import SemanticAnalysis.DataTypeId;
import SemanticAnalysis.Identifier;
import SemanticAnalysis.SymbolTable;
import java.util.List;

public class ConstructorNode implements TypeNode{

  private IdentifierNode name;
  private ParamListNode parameters;
  private StatementNode bodyStatement;

  public ConstructorNode(IdentifierNode name, ParamListNode parameters,
      StatementNode bodyStatement) {
    this.name = name;
    this.parameters = parameters;
    this.bodyStatement = bodyStatement;
  }

  @Override
  public Identifier getIdentifier(SymbolTable symbolTable) {
    return null;
  }

  @Override
  public DataTypeId getType() {
    return null;
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<String> errorMessages) {

  }

  @Override
  public void generateAssembly(InternalState internalState) {

  }

  @Override
  public void setCurrSymTable(SymbolTable currSymTable) {

  }

  @Override
  public SymbolTable getCurrSymTable() {
    return null;
  }
}
