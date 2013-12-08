
/**
 * Created by Ari100tell on 22.09.13.
 */

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;

public class MyTableModel extends AbstractTableModel implements Serializable {
    //  private int height=3;

    final private int NUMBER_FREE_VARIABLES = 1;


    MyTableData myTableData = new MyTableData();
    private int height = myTableData.getDataSize();
    private int width = height + NUMBER_FREE_VARIABLES;
    private String[] columnNames = new String[width];//Названия колонок
    private double[][] data = new double[height][width];//myTableData.getData();


    @Override
    public int getColumnCount() {             //Выдает количество колонок
        return width;
    }
      /* @Override
    public String getColumnName(int col) {    //Выдает название колонки
        return columnNames[col];
    }   */


    @Override
    public String getColumnName(int c) {
        if (c == getColumnCount() - 1) {
            return "Свободные члены";
        } else
            return "Коэффициент " + c;

    }

    @Override
    public int getRowCount() {                 //Выдает количество строк
        return height;
    }

    @Override
    public Double getValueAt(int row, int col) {  //Выдает значение ячейки
        return data[row][col];
    }

    /*public Class getColumnClass(int col) {       //Возвращает класс колонки
        return data[0][col].getClass();
    }     */
    @Override
    public boolean isCellEditable(int row, int col) {     //Возвращает, можно
        // if (col >= 0)                                      // ли редактировать ячейку
        return true;
        // else
        //   return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {    //Установка нового значения
        Object oldValue = getValueAt(row, col);
        double k = (Double) oldValue;
        boolean errorSetValue = false;
        // do
        try {
            k = Double.parseDouble(value.toString());
            errorSetValue = false;
        } catch (Exception error) {
            k = Double.parseDouble(oldValue.toString());
        }
        data[row][col] = k;
        fireTableCellUpdated(row, col);
    }


}
