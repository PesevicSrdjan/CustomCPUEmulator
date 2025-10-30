package org.unibl.etf.ar.instruction;

import java.util.HashMap;

import org.unibl.etf.ar.processor.Processor;

public class InstructionFactory 
{
	public static Instruction create(Opcode op, HashMap<Long, Byte> memory, long pc) 
	{
        switch(op) 
        {
            case MOV_IMM: 
            {
                int reg = memory.get(pc);
                byte val = memory.get(pc + 1);
                return new MovImm(reg, val);
            }
            case MOV_REG: 
            {
                int reg1 = memory.get(pc);
                int reg2 = memory.get(pc + 1);
                return new MovReg(reg1, reg2);
            }
            case MOV_MEM_LOAD:
            {
            	int reg = memory.get(pc);
            	long address = memory.get(pc + 1);
            	return new MovMemLoad(reg, address);
            }
            case MOV_MEM_STORE:
            {
            	long address = memory.get(pc);
            	int reg = memory.get(pc + 1);
            	return new MovMemStore(address, reg);
            }
            case ADD:
            {
            	int reg1 = memory.get(pc);
            	int reg2 = memory.get(pc + 1);
            	return new Add(reg1, reg2);
            	
            }
            /*case SUB:
            {
            	int reg1 = memory.get(pc);
            	int reg2 = memory.get(pc + 1);
            	return new SUB(reg1, reg2);
            }*/
            case HALT:
                return new Halt();
        }
        throw new RuntimeException("Nepoznata instrukcija: " + op);
    }
	
	
	

}
