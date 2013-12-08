
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ari100tell on 06.10.13.
 */
public class MyFrame extends JFrame {
    //Объявление компонентов формы
    MyTableData myTableData = new MyTableData();
    MyTableModel myTableModel = new MyTableModel();
    int height = myTableData.getDataSize();
    private JTable myTable;
    private JButton buttonDefaultData;
    private JLabel labelResult;
    private JButton buttonFindResults;
    private JButton buttonUpdateTable;

    public MyFrame() {
        //Инициализация начальных значений для свойств формы и ее компонентов
        super("Crow calculator");
        labelResult = new JLabel("Label");
        buttonFindResults = new JButton("Find results  ");
        buttonDefaultData = new JButton("Load Default");
        buttonUpdateTable = new JButton("Update table");
        myTable = new JTable(myTableModel);
        //Установка размеров таблицы
        myTable.setSize(100, 100);
        // Размещение компонентов на форме с помощью Layout Manager

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(buttonDefaultData);
        buttonsPanel.add(buttonFindResults);
        buttonsPanel.add(buttonUpdateTable);

        final JPanel labelsPanel = new JPanel();
        labelsPanel.add(labelResult);

        final JPanel tablesPanel = new JPanel();
        //установка размеров прокручиваемой области просмотра
        myTable.setPreferredScrollableViewportSize(new Dimension(450,80));
        //Включение таблицы в состав панели содержимого
        JScrollPane scrollPane = new JScrollPane(myTable);
        tablesPanel.add(scrollPane);

        final JPanel allPanel = new JPanel();
        allPanel.add(tablesPanel);
        allPanel.add(buttonsPanel);
        allPanel.add(labelsPanel);

        //Вывод основной панели
        add(allPanel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);           //что делать при закрытии окна
        //Кнопка для обновления/очистки значений таблици
        buttonUpdateTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < height; j++) {
                        myTable.setValueAt(0, i, j);
                    }
                }

                for (int i = 0; i < height; i++) {
                    myTable.setValueAt(0, i, height);
                }
            }
        });

        //Кнопка для запуска вычисления результата
        buttonFindResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double det = processingMatrix(myTable);
                labelResult.setText(String.valueOf(det));
            }
        });

        //Кнопка для загрузки ячеек матрицы и других значений заданных по умолчанию
        buttonDefaultData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < height; j++) {
                        myTable.setValueAt(myTableData.getCellValue(i, j), i, j);
                    }
                }
                //цикл для внесения в таблицу свободных членов
                for (int i = 0; i < height; i++) {
                    myTable.setValueAt(myTableData.getFreeVariables(i), i, height);
                }
            }
        });
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        myFrame.pack();
        myFrame.setVisible(true);
    }

    public double processingMatrix(JTable myTable) {
        double[][] data = new double[height][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = (Double) myTable.getValueAt(i, j);
            }
        }
        Determinant determinant = new Determinant();
        return (determinant.calculateDeterminant(data));
    }
}
