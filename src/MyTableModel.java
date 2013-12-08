
/**
 * Класс в котором реализована модель для создания таблици JTable и работы с ней
 *
 * Created by Ari100tell on 22.09.13.
 */

import org.jetbrains.annotations.NotNull;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {

    final private int NUMBER_FREE_VARIABLES = 1;
    MyTableData myTableData = new MyTableData();
    private int height = myTableData.getDataSize();
    private int width = height + NUMBER_FREE_VARIABLES;
    private double[][] data = new double[height][width];

    @Override
    public int getColumnCount() {             //Выдает количество колонок
        return width;
    }

    @NotNull
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

    @Override
    public boolean isCellEditable(int row, int col) {     //Возвращает, можно ли редактировать ячейку
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {    //Установка нового значения
        Object oldValue = getValueAt(row, col);
        double k;
        try {
            k = Double.parseDouble(value.toString());
        } catch (Exception error) {
            k = Double.parseDouble(oldValue.toString());
        }
        data[row][col] = k;
        fireTableCellUpdated(row, col);
    }
}
