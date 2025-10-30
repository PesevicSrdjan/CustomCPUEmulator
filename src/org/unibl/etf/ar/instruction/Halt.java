package org.unibl.etf.ar.instruction;

import java.util.HashMap;

import org.unibl.etf.ar.processor.Processor;

public class Halt extends Instruction
{
	
	public Halt()
	{
		super();
	}

	@Override
	public void execute(Processor processor)
	{
		processor.setHalted();
	}
	
	@Override
	public void writeToMemory(HashMap<Long, Byte> memory, long pc)
	{
		memory.put(pc++, getOpcode().getCode());
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	@Override
	public Opcode getOpcode() 
	{ 
		return Opcode.HALT; 
	}
}
