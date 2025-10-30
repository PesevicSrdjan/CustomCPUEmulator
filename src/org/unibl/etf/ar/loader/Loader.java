	package org.unibl.etf.ar.loader;
	
	import java.io.BufferedReader;

	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;

import org.unibl.etf.ar.instruction.Add;
import org.unibl.etf.ar.instruction.Halt;
import org.unibl.etf.ar.instruction.Instruction;
import org.unibl.etf.ar.instruction.MovImm;
import org.unibl.etf.ar.instruction.MovMemLoad;
import org.unibl.etf.ar.instruction.MovMemStore;
import org.unibl.etf.ar.instruction.MovReg;
	
	public class Loader 
	{
		public static final String PATH_STRING = System.getProperty("user.dir") + 
											File.separator + "resources" + File.separator + "program.txt";
		
		
		public static List<Instruction> loadFile()
		{
			List<Instruction> instructions = new ArrayList<>();
			
			File file = new File(PATH_STRING);
			
			try(BufferedReader br = new BufferedReader(new FileReader(file)))
			{
				String line;
				while((line = br.readLine()) != null)
				{
					line = line.replace(",", "");
					String elems[] = line.split(" ");
					instructions.add(parseInstruction(elems));
				}
				
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
			
			return instructions;
		}
		
		private static Instruction parseInstruction(String[] elems) 
		{
		    String instr = elems[0].toUpperCase();
	
		    switch(instr) 
		    {
		        case "MOV":
		        	return parseMov(elems[1], elems[2]);
		        case "ADD":
		        case "SUB":
		        case "MUL":
		        case "DIV":
		        case "AND":
		        case "OR":
		        case "XOR":
		            return parseArithmetic(instr, elems);
	
		       /* case "OUT":
		        case "IN":
		        case "NOT":
		            int r = Integer.parseInt(elems[1].substring(1));
		            return new Instruction(Instruction.InstructionType.valueOf(instr), r);*/
	
		        case "HALT":
		            return new Halt();
	
		       /* // Grananja (JMP, JE, JNE, …)
		        case "JMP":
		        case "JE":
		        case "JNE":
		        case "JGE":
		        case "JL":
		        case "CMP":
		            int regOrAddr = Integer.parseInt(elems[1].substring(1)); // ili parse adresu
		            // zavisno od toga da li je registar ili adresa
		            return new Instruction(Instruction.InstructionType.valueOf(instr), regOrAddr); */
		    }
	
		    throw new RuntimeException("Nepoznata instrukcija: " + instr);
		}
		
		
		
		private static Instruction parseMov(String op1, String op2) 
		{
		    if(op1.startsWith("R") && !op2.startsWith("R") && !op2.startsWith("0x")) 
		    {
		        int reg = Integer.parseInt(op1.substring(1));
		        byte value = Byte.parseByte(op2);
		        return new MovImm(reg, value);
		    }
		    else if(op1.startsWith("R") && op2.startsWith("R")) 
		    {
		        int reg1 = Integer.parseInt(op1.substring(1));
		        int reg2 = Integer.parseInt(op2.substring(1));
		        return new MovReg(reg1, reg2);
		    }
		    else if(op1.startsWith("R") && op2.startsWith("0x")) 
		    {
		        int reg = Integer.parseInt(op1.substring(1));
		        long addr = Long.parseLong(op2.substring(2), 16);
		        return new MovMemLoad(reg, addr);
		    }
		    else if(op1.startsWith("0x") && op2.startsWith("R")) 
		    {
		        long addr = Long.parseLong(op1.substring(2), 16);
		        int reg = Integer.parseInt(op2.substring(1));
		        return new MovMemStore(addr, reg);
		    }
		    throw new RuntimeException("Nepoznata MOV kombinacija: " + op1 + ", " + op2);
		}
		
		private static Instruction parseArithmetic(String instr, String[] elems) 
		{
		    int reg1 = Integer.parseInt(elems[1].substring(1));
		    int reg2 = Integer.parseInt(elems[2].substring(1));
		    
		    switch(instr) 
		    {
		        case "ADD": return new Add(reg1, reg2);
		        // case "SUB": return new Sub(reg1, reg2);
		        //case "MUL": return new Mul(reg1, reg2);
		        //case "DIV": return new Div(reg1, reg2);
		        //case "AND": return new And(reg1, reg2);
		        //case "OR":  return new Or(reg1, reg2);
		        //case "XOR": return new Xor(reg1, reg2);
		    }
		    throw new RuntimeException("Nepoznata aritmetička instrukcija: " + instr);
		}


	}
