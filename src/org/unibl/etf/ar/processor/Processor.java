package org.unibl.etf.ar.processor;

import java.util.HashMap;

import java.util.List;

import org.unibl.etf.ar.instruction.*;


public class Processor 
{
	private long registers[] = new long[4];
	private long programCounter;
	private HashMap<Long, Byte> memory = new HashMap<>();
	private HashMap<Long, Byte> dataMemory = new HashMap<>();
		
	private boolean halted = false;
	
	public Processor()
	{
		super();
	}
	
	public HashMap<Long, Byte> getMemorySnapshot() 
	{
	    return new HashMap<>(memory);
	}
	
	public void loadProgram(List<Instruction> instructions)
	{
		long pc = 0;
		
		for(Instruction instruction : instructions)
		{
			instruction.writeToMemory(memory, pc);
			pc += instruction.getSize();
		}
		
		System.out.println(getMemorySnapshot());
		this.programCounter = 0;
	}
	
	public void run()
	{
	    while(!halted) 
	    {
	    	Instruction instruction = fetchAndDecode();
	        execute(instruction);
	    }
	}
	
	private Instruction fetchAndDecode()
	{
		Byte opcodeByte = memory.get(programCounter++);

		Opcode op = Opcode.values()[opcodeByte];
		
		Instruction instruction = InstructionFactory.create(op, memory, programCounter);
		programCounter += instruction.getSize() - 1;
		return instruction;
	}
	
	private void execute(Instruction instruction)
	{
		instruction.execute(this);
	}

	public long getRegister(int index) {
	    return registers[index];
	}

	public long[] getRegisters() {
	    return registers;
	}

	public long getProgramCounter() {
	    return programCounter;
	}
	
	public HashMap<Long, Byte> getMemory() {
		return memory;
	}

	public void setMemory(HashMap<Long, Byte> memory) {
		this.memory = memory;
	}

	public boolean isHalted() {
	    return halted;
	}
	
	public void setHalted()
	{
		halted = true;
	}
	public HashMap<Long, Byte> getDataMemory() {
	    return dataMemory;
	}
}
