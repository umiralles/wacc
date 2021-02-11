package AbstractSyntaxTree;

import AbstractSyntaxTree.expression.IdentifierNode;
import AbstractSyntaxTree.statement.StatementNode;
import AbstractSyntaxTree.type.FunctionNode;
import SemanticAnalysis.FunctionId;
import SemanticAnalysis.SymbolTable;
import java.util.ArrayList;
import java.util.List;

public class ProgramNode implements ASTNode {

    private final StatementNode statementNode;
    private final List<FunctionNode> functionNodes;
    private final List<String> syntaxErrors;


    public ProgramNode(StatementNode statementNode, List<FunctionNode> functionNodes) {
        this.statementNode = statementNode;
        this.functionNodes = functionNodes;
        syntaxErrors = new ArrayList<>();
    }

    @Override
    public void semanticAnalysis(SymbolTable topSymbolTable, List<String> errorMessages) {
        // go through list of nodes, for each new function create a symbol table
        // and perform the semantic analysis on it, passing the newly created sym table

        for (FunctionNode func : functionNodes) {
            func.setCurrSymTable(new SymbolTable(topSymbolTable));

            // check if declared
            // if not add to top table
            if (topSymbolTable.lookupAll(func.getName()) != null) {
                // function is defined - add error message and exit
                IdentifierNode id = func.getIdentifierNode();
                errorMessages.add(id.getLine() + ":" + id.getCharPositionInLine()
                    + "Attempt at redefining already existing function " + func.getName());

            } else {
                FunctionId identifier = (FunctionId) func.getIdentifier(func.getCurrSymTable());
                topSymbolTable.add(func.getName(), identifier);
            }
        }

        for (FunctionNode func : functionNodes) {
            func.semanticAnalysis(func.getCurrSymTable(), errorMessages);
        }

        //do semantic analysis on the statement node with new scope
        statementNode.semanticAnalysis(topSymbolTable, errorMessages);
    }

    public List<String> checkSyntaxErrors() {
        String error;
        for (FunctionNode f : functionNodes) {
            error = f.checkSyntaxErrors();
            if (!error.isEmpty()) {
                syntaxErrors.add(error);
            }
        }
        return syntaxErrors;
    }
}