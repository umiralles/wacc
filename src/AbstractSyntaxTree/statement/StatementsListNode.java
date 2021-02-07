package AbstractSyntaxTree.statement;

import java.util.List;

public class StatementsListNode extends StatementNode {
    private final List<StatementNode> statements;

    public StatementsListNode(List<StatementNode> statements){
      this.statements = statements;
    }

  public boolean hasReturnStatement() {
    return statements.stream().anyMatch(StatementNode::hasReturnStatement);
  }
}