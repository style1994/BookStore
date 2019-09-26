package view;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MyGridBagLayout  
{
	private static GridBagConstraints constraints = new GridBagConstraints();
	
	static {
		constraints.fill = GridBagConstraints.NONE;
		constraints.weightx = 0;
		constraints.weighty =0;
	}
			
	//用來取得GridBagConstraints 在取得前會進行一些通用設定
	public static GridBagConstraints getGridBagConstraints(int gridx,int gridy,int gridwidht,int gridheight,int anchor) {
		
		
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidht;
		constraints.gridheight = gridheight;
		constraints.anchor = anchor;
		constraints.insets = new Insets(5, 5, 5, 5);
		
		return constraints;
	}
	
}
