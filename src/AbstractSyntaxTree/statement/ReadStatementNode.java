package AbstractSyntaxTree.statement;

import AbstractSyntaxTree.assignment.AssignLHSNode;
import SemanticAnalysis.DataTypeId;
import SemanticAnalysis.DataTypes.ArrayType;
import SemanticAnalysis.DataTypes.BaseType;
import SemanticAnalysis.DataTypes.PairType;
import SemanticAnalysis.SymbolTable;
import java.util.List;

public class ReadStatementNode implements StatementNode {

  private final AssignLHSNode lhs;

  public ReadStatementNode(AssignLHSNode lhs) {
    this.lhs = lhs;
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<String> errorMessages) {

      lhs.semanticAnalysis(symbolTable, errorMessages);

    DataTypeId lhsType = lhs.getType(symbolTable);
    // TODO: BETTER ERROR MESSAGE
    if (!lhsType.equals(new BaseType(null, BaseType.Type.INT)) ||
            !lhsType.equals(new BaseType(null, BaseType.Type.CHAR))) {
      errorMessages.add("Standard input allows only INT and CHAR and not " + lhsType.toString());
    }
  }
}