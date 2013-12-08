
/**
 * Класс, который реализует интерфейс программы и вычисления,
 * связанные с решением системы линейных однородных уравнений
 * методом Крамера
 *
 * Created by Ari100tell on 06.10.13.
 */

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    MyTableData myTableData = new MyTableData();
    MyTableModel myTableModel = new MyTableModel();
    int height = myTableData.getDataSize();
    private JTable myTable;

    public MainGUI() {
        //Инициализация начальных значений для свойств формы и ее компонентов
        super("Crow calculator");
        JLabel labelResult = new JLabel("Ответ:");
        JButton buttonFindResults = new JButton("Find results  ");
        JButton buttonDefaultData = new JButton("Load Default");
        JButton buttonUpdateTable = new JButton("Update table");
        final DefaultListModel<String> resultListModel = new DefaultListModel<String>();
        JList<String> resultList = new JList<String>(resultListModel);
        resultList.setModel(resultListModel);
        resultList.setSelectedIndex(0);
        resultList.setFocusable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultList);
        resultScrollPane.setViewportView(resultList);
        resultList.setSize(200,100);
        resultScrollPane.setSize(300,100);
        myTable = new JTable(myTableModel);

        // Размещение компонентов на форме с помощью Layout Manager
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(buttonDefaultData);
        buttonsPanel.add(buttonFindResults);
        buttonsPanel.add(buttonUpdateTable);

        final JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.add(labelResult);
        labelsPanel.add(resultScrollPane);

        final JPanel tablesPanel = new JPanel();
        //установка размеров прокручиваемой области просмотра
        myTable.setPreferredScrollableViewportSize(new Dimension(400,80));
        //Включение таблицы в состав панели содержимого
        JScrollPane scrollPane = new JScrollPane(myTable);
        tablesPanel.add(scrollPane);
        JPanel allPanel = new JPanel();
        allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
        allPanel.add(buttonsPanel);
        allPanel.add(tablesPanel);
        allPanel.add(labelsPanel);
        //Вывод основной панели
        add(allPanel);

        //Кнопка для обновления/очистки значений таблици
        buttonUpdateTable.addActionListener(new ActionListener() {
            public void actionPerformed(@NotNull ActionEvent event) {
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < height; j++) {
                        myTable.setValueAt(0, i, j);
                    }
                }
                for (int i = 0; i < height; i++) {
                    myTable.setValueAt(0, i, height);
                }
                resultListModel.clear();
            }
        });

        //Кнопка для запуска вычисления результата
        buttonFindResults.addActionListener(new ActionListener() {
            public void actionPerformed(@NotNull ActionEvent event) {
                double[] det = processingMatrix(myTable);
                resultListModel.addElement("\t Результат решения системы линейных уравнений Методом Крамера");
                for(int i=0;i<det.length;i++){ 
                resultListModel.addElement("x" + i + "=" + det[i]);
                }
            }
        });

        //Кнопка для загрузки ячеек матрицы и других значений заданных по умолчанию
        buttonDefaultData.addActionListener(new ActionListener() {
            public void actionPerformed(@NotNull ActionEvent event) {
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
        MainGUI mainGUI = new MainGUI();

        mainGUI.pack();
        mainGUI.setSize(450,400);
        mainGUI.setVisible(true);
        mainGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public double[][] updateMatrix(JTable myTable) {
        double[][] data = new double[height][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                data[i][j] = (Double) myTable.getValueAt(i, j);
            }
        }
        return data;
    }
    public double[] processingMatrix(JTable myTable) {
        double[] solution = new double[height];
        double[][] data;
        data = updateMatrix(myTable);
        Determinant determinant = new Determinant();
        double basicDeterminant=(determinant.calculateDeterminant(data));
        for (int i = 0; i < height; i++) {
            data = updateMatrix(myTable);
            for (int j = 0; j < height; j++) {
                data[j][i] = (Double)myTable.getValueAt(j, height);
            }
            double intermediateDeterminant = (determinant.calculateDeterminant(data));
            solution[i]=intermediateDeterminant/basicDeterminant;
        }
        return solution;
    }
}
