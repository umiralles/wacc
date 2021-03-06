package AbstractSyntaxTree.statement;

import AbstractSyntaxTree.ASTNode;
import AbstractSyntaxTree.expression.ExpressionNode;
import InternalRepresentation.InternalState;
import SemanticAnalysis.SemanticError;
import SemanticAnalysis.SymbolTable;

import java.util.List;

public class PrintLineStatementNode extends StatementNode {

  /* expression:   ExpressionNode corresponding to the expression 'println' was called with */
  private final ExpressionNode expression;

  public PrintLineStatementNode(ExpressionNode expression) {
    this.expression = expression;
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<SemanticError> errorMessages,
      List<ASTNode> uncheckedNodes, boolean firstCheck) {
    /* Set the symbol table for this node's scope */
    setCurrSymTable(symbolTable);

    /* Recursively call semanticAnalysis on expression node */
    expression.semanticAnalysis(symbolTable, errorMessages, uncheckedNodes, firstCheck);
  }

  @Override
  public void generateAssembly(InternalState internalState) {
    internalState.getCodeGenVisitor().
        visitPrintLineStatementNode(internalState, expression, getCurrSymTable());
  }

}