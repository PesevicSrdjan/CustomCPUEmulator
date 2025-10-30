package org.unibl.etf.ar.instruction;

import java.util.HashMap;

import org.unibl.etf.ar.processor.Processor;

public class MovReg extends Instruction
{
	private int reg1;
	private int reg2;
	
	public MovReg(int reg1, int reg2)
	{
		this.reg1 = reg1;
		this.reg2 = reg2;
	}

	@Override
	public void execute(Processor processor)
	{
		processor.getRegisters()[reg1] = processor.getRegister(reg2);
	}
	
	@Override
	public void writeToMemory(HashMap<Long, Byte> memory, long pc)
	{
		memory.put(pc++, getOpcode().getCode());
        memory.put(pc++, (byte)reg1);
        memory.put(pc++, (byte)reg2);
	}
	
	@Override
	public Opcode getOpcode() 
	{ 
		return Opcode.MOV_REG; 
	}
	
	@Override
	public int getSize() {
		return 3;
	}

	public int getReg1() {
		return reg1;
	}
	
	public int getReg2() {
		return reg2;
	}

}
