package cnam.net.swingCommon;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

///////////////////////////////////////////////////////////
/////// Permet de changer le backroud de la Jtable Produit
///////////////////////////////////////////////////////////
public class TableCellRendererProduit extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final Color BleuC = new Color(126, 168, 252);

	public Component getTableCellRendererComponent(
			JTable table,
			java.lang.Object value,
			boolean isSelected,
			boolean hasFocus,
			int row,
			int column) {

		Color c=new Color(1f,0f,0f,.3f );
		Color c2=new Color(1f,0f,0f,.7f );
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (table.getModel().getValueAt(row, 3).equals("A contrôler")) {
			if( !isSelected ) {
				setBackground(c);
			}
			else {
				setBackground(c2);
			}
		}
		else {
			if( !isSelected ) {
				setBackground(Color.white);
			} else {
				setBackground(BleuC);
			}
		}
		return this;
	}
}