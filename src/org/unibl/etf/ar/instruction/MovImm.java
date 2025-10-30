package org.unibl.etf.ar.instruction;

import java.util.HashMap;

import org.unibl.etf.ar.processor.Processor;

public class MovImm extends Instruction
{
	private int reg;
	private byte value;
	
	public MovImm(int reg, byte value)
	{
		this.reg = reg;
		this.value = value;
	}

	@Override
	public void execute(Processor processor)
	{
		processor.getRegisters()[reg] = value;
	}
	
	@Override
	public void writeToMemory(HashMap<Long, Byte> memory, long pc)
	{
		memory.put(pc++, getOpcode().getCode());
        memory.put(pc++, (byte)reg);
        memory.put(pc++, value);
	}
	@Override
	public Opcode getOpcode() 
	{ 
		return Opcode.MOV_IMM; 
	}

	public int getReg() {
		return reg;
	}
	
	@Override
	public int getSize() {
		return 3;
	}

	public byte getValue() {
		return value;
	}
}
