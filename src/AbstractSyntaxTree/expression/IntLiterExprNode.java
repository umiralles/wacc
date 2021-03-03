package AbstractSyntaxTree.expression;

import InternalRepresentation.Enums.LdrType;
import InternalRepresentation.Enums.Register;
import InternalRepresentation.Instructions.LdrInstruction;
import InternalRepresentation.Instructions.MovInstruction;
import InternalRepresentation.InternalState;
import SemanticAnalysis.DataTypeId;
import SemanticAnalysis.DataTypes.BaseType;
import SemanticAnalysis.SymbolTable;
import java.util.List;

public class IntLiterExprNode extends ExpressionNode {

  /* value: int representing the value of this node's INT literal */
  private final int value;
  private SymbolTable currSymTable = null;

  public IntLiterExprNode(int line, int charPositionInLine, int value) {
    super(line, charPositionInLine);
    this.value = value;
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<String> errorMessages) {
    currSymTable = symbolTable;
  }

  @Override
  public void generateAssembly(InternalState internalState) {
    Register currDestination = internalState.peekFreeRegister();
    internalState.addInstruction(new LdrInstruction(LdrType.LDR, currDestination, value));
  }

  @Override
  public SymbolTable getCurrSymTable() {
    return currSymTable;
  }

  @Override
  public DataTypeId getType(SymbolTable symbolTable) {
    return new BaseType(BaseType.Type.INT);
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}