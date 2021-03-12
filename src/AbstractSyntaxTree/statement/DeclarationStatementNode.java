package AbstractSyntaxTree.statement;

import AbstractSyntaxTree.assignment.AssignRHSNode;
import AbstractSyntaxTree.assignment.FuncCallNode;
import AbstractSyntaxTree.expression.IdentifierNode;
import AbstractSyntaxTree.type.TypeNode;
import InternalRepresentation.InternalState;
import SemanticAnalysis.DataTypeId;
import SemanticAnalysis.OverloadFuncId;
import SemanticAnalysis.SymbolTable;
import SemanticAnalysis.VariableId;

import java.util.List;

public class DeclarationStatementNode extends StatementNode {

  /* type:         TypeNode corresponding to the type of the new variable
   * identifier:   Name identifier given to the new variable
   * assignment:   AssignRHSNode corresponding to the value assigned to the new variable */
  private final TypeNode type;
  private final IdentifierNode identifier;
  private final AssignRHSNode assignment;

  public DeclarationStatementNode(TypeNode type, IdentifierNode identifier,
      AssignRHSNode assignment) {
    this.type = type;
    this.identifier = identifier;
    this.assignment = assignment;
  }

  @Override
  public void semanticAnalysis(SymbolTable symbolTable, List<String> errorMessages) {
    /* Set the symbol table for this node's scope */
    setCurrSymTable(symbolTable);

    /* Check whether identifier has been previously declared as another variable in the current scope.
     * If not, add a new VariableId to the symbol table under identifier */
    if (symbolTable.lookup(identifier.getIdentifier()) != null) {
      errorMessages.add(identifier.getLine() + ":" + identifier.getCharPositionInLine()
          + " Identifier '" + identifier.getIdentifier()
          + "' has already been declared in the same scope.");

    } else {
      symbolTable.add(identifier.getIdentifier(),
          new VariableId(identifier, type.getType()));
    }

    /* Check that the expected (declared) type and the type of assignment
     * can be resolved and match */
    DataTypeId declaredType = type.getType();
    DataTypeId assignedType = null;

    List<DataTypeId> returnTypes;

    if(assignment instanceof FuncCallNode
        && ((FuncCallNode) assignment).getIdentifier(symbolTable) instanceof OverloadFuncId) {
      returnTypes = ((FuncCallNode) assignment).getOverloadType(symbolTable);
      for(DataTypeId returnType : returnTypes) {
        if(returnType == null) {
          continue;
        }

        if(returnType.equals(declaredType)) {
          assignedType = returnType;
          break;
        }
      }

      if(assignedType == null) {
        errorMessages.add(assignment.getLine() + ":" + assignment.getCharPositionInLine()
            + " RHS type does not match LHS type for assignment.'"
            + " Expected: " + declaredType + ". Could not find matching return type"
            + " in overloaded functions.");
        return;
      }
    } else {
      assignedType = assignment.getType(symbolTable);
    }

    if (declaredType == null) {
      errorMessages.add(assignment.getLine() + ":" + assignment.getCharPositionInLine()
          + " Could not resolve type of '" + identifier.getIdentifier() + "'.");

    } else if (assignedType == null) {
      errorMessages.add(assignment.getLine() + ":" + assignment.getCharPositionInLine()
          + " Could not resolve type of '" + assignment.toString() + "'."
          + " Expected: " + declaredType);

    } else if (!declaredType.equals(assignedType) && !stringToCharArray(declaredType,
        assignedType)) {
      errorMessages.add(assignment.getLine() + ":" + assignment.getCharPositionInLine()
          + " Assignment type does not match declared type for '"
          + identifier.getIdentifier() + "'."
          + " Expected: " + declaredType + " Actual: " + assignedType);
    }

    /* Recursively call semanticAnalysis on stored nodes */
    identifier.semanticAnalysis(symbolTable, errorMessages);
    assignment.semanticAnalysis(symbolTable, errorMessages);
  }

  @Override
  public void generateAssembly(InternalState internalState) {
    internalState.getCodeGenVisitor().visitDeclarationStatementNode(
        internalState, assignment, type, identifier, getCurrSymTable());
  }
}