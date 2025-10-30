package org.unibl.etf.ar.instruction;

import java.util.HashMap;

public abstract class ArithmeticInstruction extends Instruction
{
	protected int reg1, reg2;
	
	public ArithmeticInstruction(int reg1, int reg2) {
        this.reg1 = reg1;
        this.reg2 = reg2;
    }

    @Override
    public void writeToMemory(HashMap<Long, Byte> memory, long pc) {
        memory.put(pc++, getOpcode().getCode());
        memory.put(pc++, (byte)reg1);
        memory.put(pc++, (byte)reg2);
    }

    public int getReg1() { 
    	return reg1; 
    }
    
    @Override
	public int getSize() {
		return 3;
	}
    
    public int getReg2() { 
    	return reg2; 
    }
}
