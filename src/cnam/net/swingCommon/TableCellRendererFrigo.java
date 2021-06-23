package cnam.net.swingCommon;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

///////////////////////////////////////////////////////////
/////// Permet de changer le backroud de la Jtable Frigo
///////////////////////////////////////////////////////////
public class TableCellRendererFrigo extends DefaultTableCellRenderer {
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

		Calendar dateActuelleCalendar=Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		java.util.Date date = null;
		try {
			date = dateFormat.parse(String.valueOf(table.getValueAt(row, 5)));
		} catch (ParseException e) {

		}
		Calendar dateCalendar= Calendar.getInstance();
		try {
		dateCalendar.setTime(date);
		if (dateCalendar.get(Calendar.DAY_OF_YEAR) <dateActuelleCalendar.get(Calendar.DAY_OF_YEAR)||dateCalendar.get(Calendar.YEAR) !=dateActuelleCalendar.get(Calendar.YEAR)||(dateActuelleCalendar.get(Calendar.HOUR_OF_DAY)>12&&dateCalendar.get(Calendar.HOUR_OF_DAY)<13)) {
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
		} catch (Exception e) {
			if( !isSelected ) {
				setBackground(Color.white);
			} else {
				setBackground(BleuC);
			}
		}
		return this;
	}
}