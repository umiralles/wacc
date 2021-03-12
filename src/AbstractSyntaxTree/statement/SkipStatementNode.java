package AbstractSyntaxTree.statement;

import InternalRepresentation.InternalState;
import SemanticAnalysis.SemanticError;
import SemanticAnalysis.SymbolTable;

import java.util.List;

public class SkipStatementNode extends StatementNode {

  public String toString() {
    return "skip";
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<SemanticError> errorMessages) {
    /* Set the symbol table for this node's scope */
    setCurrSymTable(symbolTable);
  }

  @Override
  public void generateAssembly(InternalState internalState) {
  }

}
