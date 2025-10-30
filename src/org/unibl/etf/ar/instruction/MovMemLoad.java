package org.unibl.etf.ar.instruction;

import java.util.HashMap;

import org.unibl.etf.ar.processor.Processor;


public class MovMemLoad extends Instruction
{
	private int reg;
	private long address;
	
	public MovMemLoad(int reg, long address)
	{
		this.reg = reg;
		this.address = address;
	}

	@Override
	public void execute(Processor processor)
	{
		HashMap<Long, Byte> memory = processor.getMemory();
	    
	    byte value = memory.getOrDefault(address, (byte)0);
	    
	    processor.getRegisters()[reg] = value;
	}
	
	@Override
	public void writeToMemory(HashMap<Long, Byte> memory, long pc)
	{
		memory.put(pc++, getOpcode().getCode());
        memory.put(pc++, (byte)reg);
        memory.put(pc++, (byte)address);
	}
	@Override
	public Opcode getOpcode() 
	{ 
		return Opcode.MOV_MEM_LOAD; 
	}
	@Override
	public int getSize() {
		return 3;
	}
		
	public int getReg() {
		return reg;
	}
	
	public long getAddress() {
		return address;
	}
}
