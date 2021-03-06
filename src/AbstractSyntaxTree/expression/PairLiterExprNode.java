package AbstractSyntaxTree.expression;

import AbstractSyntaxTree.ASTNode;
import InternalRepresentation.InternalState;
import SemanticAnalysis.DataTypeId;
import SemanticAnalysis.DataTypes.PairType;
import SemanticAnalysis.SemanticError;
import SemanticAnalysis.SymbolTable;

import java.util.List;

public class PairLiterExprNode extends ExpressionNode {

  public PairLiterExprNode(int line, int charPositionInLine) {
    super(line, charPositionInLine);
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<SemanticError> errorMessages,
      List<ASTNode> uncheckedNodes, boolean firstCheck) {
    /* Set the symbol table for this node's scope */
    setCurrSymTable(symbolTable);
  }

  @Override
  public void generateAssembly(InternalState internalState) {
    internalState.getCodeGenVisitor().visitPairLiterExprNode(internalState);
  }

  @Override
  public DataTypeId getType(SymbolTable symbolTable) {
    return new PairType(null, null);
  }

  @Override
  public String toString() {
    return "null";
  }
}