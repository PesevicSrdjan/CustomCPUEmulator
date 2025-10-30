package org.unibl.etf.ar.instruction;



import java.util.HashMap;

import org.unibl.etf.ar.processor.Processor;

public abstract class Instruction 
{
	protected String operation;

	public Instruction()
	{
		super();
	}
	
	public Instruction(String operation)
	{
		this.operation = operation;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public abstract void execute(Processor processor);
	public abstract Opcode getOpcode();
	public abstract void writeToMemory(HashMap<Long, Byte> memory, long pc);
	public abstract int getSize();
	
}
