package org.unibl.etf.ar.main;

import org.unibl.etf.ar.loader.Loader;
import org.unibl.etf.ar.processor.Processor;

public class Main 
{

	public static void main(String[] args) 
	{
		Processor processor = new Processor();
		processor.loadProgram(Loader.loadFile());
		
		processor.run();
		
		
		System.out.println("Registers:");
		long[] regs = processor.getRegisters();
		for (int i = 0; i < regs.length; i++) {
		    System.out.println("R" + i + " = " + regs[i]);
		}

		System.out.println("PC = " + processor.getProgramCounter());
		System.out.println("Halted = " + processor.isHalted());

	}

}
