/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TechnicalServices;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcleking
 */
public class Utility {
    
    // Fonction provenant de : http://stackoverflow.com/questions/4765469/how-to-retrieve-jtable-data-as-an-array
    public static Object[][] getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = dtm.getValueAt(i,j);
        return tableData;
    }
}
