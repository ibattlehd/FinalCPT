import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class character{
	
	// Properties
	int intPower;
	boolean blnDead;
	boolean blnSpecialPiece;
	boolean blnSpecialAbility;
	int intY;
	int intX;
	
	// Methods
	public int moveUp(){
		intY = intY - 1;
		return intY;
	}
	public int moveDown(){
		intY = intY + 1;
		return intY;
	}
	public int moveRight(){
		intX = intX + 1;
		return intX;
	}
	public int moveLeft(){
		intX = intX - 1;
		return intX;
	}
	
	public void die(){
		blnDead = true;
	}
	
	// Constructor
	public character(boolean blnDead, int intPower, boolean blnSpecialPiece, boolean blnSpecialAbility, int intRow, int intCol){
		this.blnDead = blnDead;
		this.intPower = intPower;
		this.blnSpecialPiece = blnSpecialPiece;
		this.blnSpecialAbility = blnSpecialAbility;
		this.intY = intRow;
		this.intX = intCol;
	}

}
