import javax.swing.*;

class MyImageIcon extends ImageIcon{
	
	private String name;
	
	public MyImageIcon(String filename, String imageName){
		super(filename);
		name = imageName;
	}
	
	@Override
	public boolean equals(Object other){
		MyImageIcon otherImage = (MyImageIcon)other;
		if(other == null) return false;
		return name.equals(otherImage.name);
	}
	
}
