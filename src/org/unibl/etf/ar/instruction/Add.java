package org.unibl.etf.ar.instruction;

import org.unibl.etf.ar.processor.Processor;

public class Add extends ArithmeticInstruction
{
	public Add(int reg1, int reg2) {
        super(reg1, reg2);
    }

    @Override
    public Opcode getOpcode() {
        return Opcode.ADD;
    }

    @Override
    public void execute(Processor processor) {
        processor.getRegisters()[reg1] += processor.getRegisters()[reg2];
    }

}
