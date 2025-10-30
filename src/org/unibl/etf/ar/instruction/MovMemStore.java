package org.unibl.etf.ar.instruction;

import java.util.HashMap;

import org.unibl.etf.ar.processor.Processor;

public class MovMemStore extends Instruction 
{
	private long address;
	private int reg;
	
	public MovMemStore(long address, int reg)
	{
		this.address = address;
		this.reg = reg;
	}

	@Override
	public void execute(Processor processor)
	{
		HashMap<Long, Byte> memory = processor.getMemory();
	    
		long value = processor.getRegisters()[reg];
	    memory.put(address, (byte)value);
	}
	
	@Override
	public void writeToMemory(HashMap<Long, Byte> memory, long pc)
	{
		memory.put(pc++, getOpcode().getCode());
        memory.put(pc++, (byte)address);
        memory.put(pc++, (byte)reg);
	}
	
	
	@Override
	public int getSize() {
		return 3;
	}
	@Override
	public Opcode getOpcode() 
	{ 
		return Opcode.MOV_MEM_STORE; 
	}

	public int getReg() {
		return reg;
	}
	
	public long getAddress() {
		return address;
	}
	

}
