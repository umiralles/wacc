package InternalRepresentation.Instructions;

import InternalRepresentation.InstructionPrinter;
import InternalRepresentation.Register;

public class LogicalInstruction implements Instruction{

    private final InstructionPrinter printer = new InstructionPrinter();
    private final LogicalOperation operation;
    private final Register destReg;
    private final Register operand1;
    private final Register operand2;

    public LogicalInstruction(LogicalOperation operation, Register destReg,
                              Register operand1, Register operand2) {
        this.operation = operation;
        this.destReg = destReg;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String writeInstruction() {
        return printer.printLogical(operation.name(), destReg, operand1, operand2);
    }

    public enum LogicalOperation {AND, EOR, ORR, BIC, MVN, TST}
}