package ic.doc.semantic_analysis;

public class FunctionId extends Identifier {

  private final DataTypeId returnType;
  private final ParameterId[] params;
  private final SymbolTable parentTable;

  public FunctionId(DataTypeId returnType, ParameterId[] params, SymbolTable parentTable) {
    super();
    this.returnType = returnType;
    this.params = params;
    this.parentTable = parentTable;
  }

  public DataTypeId getReturnType() {
    return returnType;
  }

  public ParameterId[] getParams() {
    return params;
  }

  public SymbolTable getParentTable() {
    return parentTable;
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();

    string.append(returnType.toString());
    string.append(" <identifier>(");

    for (int i = 0; i < params.length; i++) {
      string.append(params[i].toString());

      if (i != params.length - 1) {
        string.append(", ");
      }
    }

    return string.toString();
  }
}
