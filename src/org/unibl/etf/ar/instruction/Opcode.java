package org.unibl.etf.ar.instruction;

public enum Opcode 
{
    MOV_IMM,
    MOV_REG,
    MOV_MEM_LOAD,
    MOV_MEM_STORE,
    
    ADD,
    SUB,
    AND,
    OR,
    XOR,
    MUL,
    DIV,
    
    
    HALT;
	
    public byte getCode() 
    { 
    	return (byte)this.ordinal(); 
    }
}


