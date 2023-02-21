package main;

import java.io.Serializable;

public class Mark implements Serializable {
	public boolean atteandanse=false;
	public int mark=0;

	public Mark() {

	}
	

	@Override
	public String toString() {
		return atteandanse + " " + mark;
	}
}
