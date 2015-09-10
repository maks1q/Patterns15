/*
 * Created by JFormDesigner on Tue Jul 01 18:14:40 NOVT 2014
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.Timer;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Daniil
 */
public class MainForm extends JFrame {

    // Переменные *начало*

    ArrayList<parcel> parcelExe;
    ArrayList<parcel> parcelDelay;

    city barnaul, vladivostok, kaliningrad, moscow, novosibirsk;
    air dair;
    auto dauto;
    train dtrain;
    stat info, tmpStat;

    int n, r;
    int f_start;
    int f_type;

    Random rnd = new Random(System.currentTimeMillis());

    // Переменные *конец*

    public MainForm() {
        initComponents();
    }

    public static void main(String[] args){
        new MainForm();
    }

    // Основная работа программы
    void actionOfCarrier() {

        // Инициализация моделей таблиц и их сортировка *начало*
        DefaultTableModel model1 = (DefaultTableModel)table1.getModel();
        DefaultTableModel model2 = (DefaultTableModel)table2.getModel();
        DefaultTableModel model3 = (DefaultTableModel)table3.getModel();
        DefaultTableModel model4 = (DefaultTableModel)table4.getModel();

        RowSorter<TableModel> sorter3 = new TableRowSorter<TableModel>(model3);
        table3.setRowSorter(sorter3);
        RowSorter<TableModel> sorter4 = new TableRowSorter<TableModel>(model4);
        table4.setRowSorter(sorter4);
        // Инициализация моделей таблиц и их сортировка *конец*

        parcel order = new parcel();        //
        city tempS = new city();            // Создание объектов - посылка, город отправления, город прибытия
        city tempF = new city();            //

        int fs = order.switchSettingOne(n);     //  Определение городов
        int ff = order.switchSettingTwo(fs);    //

        // Определение выбранных городов *начало*
        switch (fs) {
            case 0: tempS = barnaul;        break;
            case 1: tempS = vladivostok;    break;
            case 2: tempS = kaliningrad;    break;
            case 3: tempS = moscow;         break;
            case 4: tempS = novosibirsk;    break;
        }
        switch (ff) {
            case 0: tempF = barnaul;        break;
            case 1: tempF = vladivostok;    break;
            case 2: tempF = kaliningrad;    break;
            case 3: tempF = moscow;         break;
            case 4: tempF = novosibirsk;    break;
        }
        // Определение выбранных городов *конец*

        int distance = Math.abs(tempS.getPoint() - tempF.getPoint()+1);     // Вычисление расстояний между городами

        if (distance < 3 && order.getType().equals("Воздушный"))            //  Если расстояние малО, то автомобильный
            order.setType("Автомобильный");                                 //  тип доставки

        order.setPrice(distance+142);                                       // Установка стоимости

        // Заполнение характеристик доставки *начало*
        label11.setText(String.valueOf(order.getNumber()));                 // Номер заказа
        label12.setText(order.getStartCity());                              // Город отправления
        label14.setText(order.getFinishCity());                             // Город прибытие
        label16.setText(String.valueOf(order.getType()));                   // Тип доставки
        label18.setText(String.valueOf(order.getWeight()));                 // Вес
        label13.setText(tempS.getWeather());                                // Погода города отправления
        label15.setText(tempF.getWeather());                                // Погода города прибытия
        label19.setText(String.valueOf(order.getPrice()));                  // Стоимость доставки

        if (order.getType().equals("Воздушный") || order.getType().equals("Железнодорожный"))
            label17.setText("-");
        else {
            if (dauto.getHighway() == 0)                                    //
                label17.setText("Не имеются");                              // Определение наличия магистралей
            else                                                            //
                label17.setText("Имеются");
        }
        // Заполнение характеристик доставки *конец*

        // Определение времени доставки *начало*
        if (order.getType().equals("Воздушный")) {
            distance /= 2;
            dair.setSpeed(distance);
            order.setTime(dair.getSpeed());
            order.setTimeF(dair.getSpeed());
        } else {
            if (order.getType().equals("Автомобильный")) {

                dauto.setSpeed(distance);
                order.setTime(dauto.getSpeed());
                order.setTimeF(dauto.getSpeed());
            } else {
                distance /= 1.5;
                dtrain.setSpeed(distance);
                order.setTime(dtrain.getSpeed());
                order.setTimeF(dtrain.getSpeed());
            }
        }
        // Определение времени доставки *начало*

        // Определение задержки при неблагоприятных погодных условиях *начало*
        if (!tempF.getWeather().equals("Неблагоприятная")) {

            if (order.getType().equals("Воздушный")) {
                order.setTime(dair.getSpeed() + 2);
                order.setTimeF(dair.getSpeed() + 2);
            }
            else
                if (order.getType().equals("Автомобильный")) {
                    order.setTime(dauto.getSpeed() + 2);
                    order.setTimeF(dauto.getSpeed() + 2);
                }
                else {
                    order.setTime(dtrain.getSpeed() + 2);
                    order.setTimeF(dtrain.getSpeed() + 2);
                }
        // Определение задержки при неблагоприятных погодных условиях *конец*

            // Заполнение таблицы "Задерживаемые заказы" *начало*
            model4.addRow(new Object[]{
                    String.valueOf(order.getNumber()),
                    order.getFinishCity(),
                    order.getType(),
                    String.valueOf(order.getTime())
            });
            parcelDelay.add(order);
            // Заполнение таблицы "Задерживаемые заказы" *конец*
        }
        else {

			// Заполнение таблицы "Исполняемые заказы" *начало*
            model3.addRow(new Object[]{
                    String.valueOf(order.getNumber()),
                    order.getFinishCity(),
                    order.getType(),
                    String.valueOf(order.getTime())
            });
            parcelExe.add(order);
            // Заполнение таблицы "Исполняемые заказы" *конец*
        }

        // Перебор исполняемых заказов на завершенность *начало*
        for (parcel obj : parcelExe){

            if (obj.getPrice() != 0 && order.getNumber() != obj.getNumber()) {

                // Определение типа доставки и аварийности *начало*
                if (obj.getType().equals("Автомобильный")) {
                    f_type = 0;
                    r = rnd.nextInt(dauto.getFactor() * 25);
                }
                if (obj.getType().equals("Железнодорожный")) {
                    f_type = 1;
                    r = rnd.nextInt(dtrain.getFactor() * 25);
                }
                if (obj.getType().equals("Воздушный")) {
                    f_type = 2;
                    r = rnd.nextInt(dair.getFactor() * 25);
                }
                // Определение типа доставки и аварийности *конец*

                // Выбор города данной посылки *начало*
                city a = new city();
                if (obj.getStartCity().equals("Барнаул")) a = barnaul;
                if (obj.getStartCity().equals("Владивосток")) a = vladivostok;
                if (obj.getStartCity().equals("Калининград")) a = kaliningrad;
                if (obj.getStartCity().equals("Москва")) a = moscow;
                if (obj.getStartCity().equals("Новосибирск")) a = novosibirsk;
                // Выбор города данной посылки *конец*

                // Фактор аварийности. r=0, то случилась аварий и дальнейшая доставка данной посылки невозможна
                if (r == 0) {

                    // Установка потерь по типам и удаление записи из таблицы *начало*
                    info.setLoss(obj.getPrice());
                    if (f_type == 0) dauto.getStat().setLoss(obj.getPrice());
                    if (f_type == 1) dtrain.getStat().setLoss(obj.getPrice());
                    if (f_type == 2) dair.getStat().setLoss(obj.getPrice());
                    a.getStat().setLoss(obj.getPrice());

                    for (int i = 0; i < model3.getRowCount(); i++) {
                        if (String.valueOf(obj.getNumber()).equals(model3.getValueAt(i, 0)))
                            model3.removeRow(i);
                    }
                    // Установка потерь по типам и удаление записи из таблицы *конец*
                    obj.setPrice(0);
                }
                // Фактор аварийности. r!=0, то продолжаем доставку.
                else {
                    // Если посылка доставлена
                    if (obj.getTime() == 0) {

                        // Установка доходов по типам и удаление записи из таблицы *начало*
                        info.setProfit(obj.getPrice());

                        if (f_type == 0) {
                            dauto.getStat().setTimeS(obj.getTimeF());
                            dauto.getStat().setProfit(obj.getPrice());
                            dauto.getStat().setCounter(dauto.getStat().getCounter() + 1);
                        }
                        if (f_type == 1) {
                            dtrain.getStat().setTimeS(obj.getTimeF());
                            dtrain.getStat().setProfit(obj.getPrice());
                            dtrain.getStat().setCounter(dtrain.getStat().getCounter() + 1);
                        }
                        if (f_type == 2) {
                            dair.getStat().setTimeS(obj.getTimeF());
                            dair.getStat().setProfit(obj.getPrice());
                            dair.getStat().setCounter(dair.getStat().getCounter() + 1);
                        }

                        if (!(obj.getType().equals("Воздушный") || obj.getType().equals("Железнодорожный"))) {
                            if (dauto.getHighway() == 1) info.setProfitH(obj.getPrice());
                            else info.setProfitR(obj.getPrice());
                        }

                        for (int i = 0; i < model3.getRowCount(); i++) {
                            if (String.valueOf(obj.getNumber()).equals(model3.getValueAt(i, 0)))
                                model3.removeRow(i);

                        }

                        a.getStat().setTimeS(obj.getTimeF());
                        a.getStat().setCounter(a.getStat().getCounter() + 1);
                        a.getStat().setProfit(obj.getPrice());
                        // Установка доходов по типам и удаление записи из таблицы *конец*

                        obj.setPrice(0);
                    }
                }
                obj.setTime(obj.getTime() - 1); // Уменьшаем доставку посылки на 1ед.времени
            }
        }
        // Перебор исполняемых заказов на завершенность *конец*

        // Перебор задерживаемых заказов на завершенность *начало*
        for(parcel obj : parcelDelay){

            if (obj.getPrice() != 0 && order.getNumber() != obj.getNumber()) {

                // Определение типа доставки и аварийности *начало*
                if (obj.getType().equals("Автомобильный")) {
                    f_type = 0;
                    r = rnd.nextInt(dauto.getFactor() * 25);
                }
                if (obj.getType().equals("Железнодорожный")) {
                    f_type = 1;
                    r = rnd.nextInt(dtrain.getFactor() * 25);
                }
                if (obj.getType().equals("Воздушный")) {
                    f_type = 2;
                    r = rnd.nextInt(dair.getFactor() * 25);
                }
                // Определение типа доставки и аварийности *конец*

                // Выбор города данной посылки *начало*
                city a = new city();
                if (obj.getStartCity().equals("Барнаул")) a = barnaul;
                if (obj.getStartCity().equals("Владивосток")) a = vladivostok;
                if (obj.getStartCity().equals("Калининград")) a = kaliningrad;
                if (obj.getStartCity().equals("Москва")) a = moscow;
                if (obj.getStartCity().equals("Новосибирск")) a = novosibirsk;
                // Выбор города данной посылки *конец*

                // Фактор аварийности. r=0, то случилась аварий и дальнейшая доставка данной посылки невозможна
                if (r == 0) {

                    // Установка потерь по типам и удаление записи из таблицы *начало*
                    info.setLoss(obj.getPrice());
                    if (f_type == 0) dauto.getStat().setLoss(obj.getPrice());
                    if (f_type == 1) dtrain.getStat().setLoss(obj.getPrice());
                    if (f_type == 2) dair.getStat().setLoss(obj.getPrice());
                    a.getStat().setLoss(obj.getPrice());

                    for (int i = 0; i < model4.getRowCount(); i++) {
                        if (String.valueOf(obj.getNumber()).equals(model4.getValueAt(i, 0)))
                            model4.removeRow(i);
                    }
                    // Установка потерь по типам и удаление записи из таблицы *конец*
                    obj.setPrice(0);
                } else {

                    // Выбор города данной посылки *начало*
                    city c = new city();
                    if (obj.getFinishCity().equals("Барнаул")) c = barnaul;
                    if (obj.getFinishCity().equals("Владивосток")) c = vladivostok;
                    if (obj.getFinishCity().equals("Калининград")) c = kaliningrad;
                    if (obj.getFinishCity().equals("Москва")) c = moscow;
                    if (obj.getFinishCity().equals("Новосибирск")) c = novosibirsk;
                    // Выбор города данной посылки *конец*

                    // Фактор аварийности из-за погоды. r=0, то дальнейшая доставка невозможно
                    r = rnd.nextInt(9);
                    if (r == 0 && obj.getTime() < 6 && obj.getTime() > 1) {

                        // Установка потерь из-за погоды по типам и удаление записи из таблицы *начало*
                        info.setLossW(obj.getPrice());
                        info.setLoss(obj.getPrice());
                        a.getStat().setLoss(obj.getPrice());

                        for (int i = 0; i < model4.getRowCount(); i++) {
                            if (String.valueOf(obj.getNumber()).equals(model4.getValueAt(i, 0)))
                                model4.removeRow(i);
                        }
                        // Установка потерь из-за погоды по типам и удаление записи из таблицы *конец*
                        obj.setPrice(0);
                    }
                    // Фактор аварийности. r!=0, то продолжаем доставку.
                    else
                        // Установка доходов по типам и удаление записи из таблицы *начало*
                        if (obj.getTime() < 1 && c.getWeather().equals("Благоприятная")) {
                            info.setProfit(obj.getPrice());

                            if (f_type == 0) {
                                dauto.getStat().setTimeS(obj.getTimeF());
                                dauto.getStat().setProfit(obj.getPrice());
                                dauto.getStat().setCounter(dauto.getStat().getCounter() + 1);
                            }
                            if (f_type == 1) {
                                dtrain.getStat().setTimeS(obj.getTimeF());
                                dtrain.getStat().setProfit(obj.getPrice());
                                dtrain.getStat().setCounter(dtrain.getStat().getCounter() + 1);
                            }
                            if (f_type == 2) {
                                dair.getStat().setTimeS(obj.getTimeF());
                                dair.getStat().setProfit(obj.getPrice());
                                dair.getStat().setCounter(dair.getStat().getCounter() + 1);
                            }

                            if (!(obj.getType().equals("Воздушный") || order.getType().equals("Железнодорожный"))) {
                                if (dauto.getHighway() == 1) info.setProfitH(obj.getPrice());
                                else info.setProfitR(obj.getPrice());
                            }

                            for (int i = 0; i < model4.getRowCount(); i++) {
                                if (String.valueOf(obj.getNumber()).equals(model4.getValueAt(i, 0)))
                                    model4.removeRow(i);
                            }

                            a.getStat().setProfit(obj.getPrice());
                            a.getStat().setCounter(a.getStat().getCounter() + 1);
                            a.getStat().setTimeS(obj.getTimeF());

                            obj.setPrice(0);
                        } else if (obj.getTime() < 1) obj.setTime(obj.getTime() + 1);
                    // Установка доходов по типам и удаление записи из таблицы *конец*

                }
                obj.setTime(obj.getTime() - 1);            // Уменьшаем доставку посылки на 1ед.времени
            }
        }
        // Перебор задерживаемых заказов на завершенность *конец*

        // Вывод доходов и потерь *начало*
        label30.setText(String.valueOf(info.getProfitR()));
        label29.setText(String.valueOf(info.getProfitH()));

        label32.setText(String.valueOf(info.getProfit()));
        label31.setText(String.valueOf(info.getLossW()));
        label33.setText(String.valueOf(info.getLoss()));
        // Вывод доходов и потерь *конец*

        // Заполнение таблицы *Статистика по городам* *начало*
        model1.setValueAt(String.valueOf(barnaul.getStat().getProfit()),0,1);
        model1.setValueAt(String.valueOf(barnaul.getStat().getLoss()),0,2);
        model1.setValueAt(String.valueOf(barnaul.getStat().getTimeS()),0,3);

        model1.setValueAt(String.valueOf(vladivostok.getStat().getProfit()),1,1);
        model1.setValueAt(String.valueOf(vladivostok.getStat().getLoss()),1,2);
        model1.setValueAt(String.valueOf(vladivostok.getStat().getTimeS()),1,3);

        model1.setValueAt(String.valueOf(kaliningrad.getStat().getProfit()),2,1);
        model1.setValueAt(String.valueOf(kaliningrad.getStat().getLoss()),2,2);
        model1.setValueAt(String.valueOf(kaliningrad.getStat().getTimeS()),2,3);

        model1.setValueAt(String.valueOf(moscow.getStat().getProfit()),3,1);
        model1.setValueAt(String.valueOf(moscow.getStat().getLoss()),3,2);
        model1.setValueAt(String.valueOf(moscow.getStat().getTimeS()),3,3);

        model1.setValueAt(String.valueOf(novosibirsk.getStat().getProfit()),4,1);
        model1.setValueAt(String.valueOf(novosibirsk.getStat().getLoss()),4,2);
        model1.setValueAt(String.valueOf(novosibirsk.getStat().getTimeS()),4,3);
        // Заполнение таблицы *Статистика по городам* *конец*

        // Заполнение таблицы *Статистика по типу доставки* *начало*
        model2.setValueAt(String.valueOf(dauto.getStat().getProfit()),0,1);
        model2.setValueAt(String.valueOf(dauto.getStat().getLoss()),0,2);
        model2.setValueAt(String.valueOf(dauto.getStat().getTimeS()),0,3);

        model2.setValueAt(String.valueOf(dtrain.getStat().getProfit()),1,1);
        model2.setValueAt(String.valueOf(dtrain.getStat().getLoss()),1,2);
        model2.setValueAt(String.valueOf(dtrain.getStat().getTimeS()),1,3);

        model2.setValueAt(String.valueOf(dair.getStat().getProfit()),2,1);
        model2.setValueAt(String.valueOf(dair.getStat().getLoss()),2,2);
        model2.setValueAt(String.valueOf(dair.getStat().getTimeS()),2,3);
        // Заполнение таблицы *Статистика по типу доставки* *конец*

        n++;
  }

    // Включение/выключение работы программы
    void On_Of(){

        if (f_start == 1) {
            button1.setText("Продолжить");
            f_start = 0;
            timer.stop();
        }
        else {
            button1.setText("Остановить");
            f_start = 1;
            timer.start();
        }
    }

    // Установка параметров для городов
    void setSettingCity(){

        barnaul = new city(8, "Барнаул", "Благоприятная", tmpStat);
        vladivostok = new city(12, "Владивосток", "Благоприятная", tmpStat);
        kaliningrad = new city(0, "Калининград", "Благоприятная", tmpStat);
        moscow = new city(2, "Москва", "Благоприятная", tmpStat);
        novosibirsk = new city(7, "Новосибирск", "Благоприятная", tmpStat);

        setSettingCityWeather();
    }

    // Установка параметров по типоам доставки
    void setSettingType(){

        dair = new air(tmpStat);
        dauto = new auto(tmpStat);
        dtrain = new train(tmpStat);
    }

    // Установка погоды в городах
    void setSettingCityWeather(){

        barnaul.switchSettingWeather();
        vladivostok.switchSettingWeather();
        kaliningrad.switchSettingWeather();
        moscow.switchSettingWeather();
        novosibirsk.switchSettingWeather();
    }

    // Инициализация при запуске программы
    private void thisWindowOpened(WindowEvent e) {

        n = 1;
        f_start = 0;
        tmpStat = new stat();
        info = new stat();

        setSettingType();
        setSettingCity();

        parcelExe = new ArrayList <parcel>();
        parcelDelay = new ArrayList <parcel>();
    }

    // Нажатие на кнопку *начать*
    private void button1MouseClicked(MouseEvent e) {

        On_Of();
    }

    // Нажатие на кнопку *пошагово*
    private void button2MouseClicked(MouseEvent e) {

        setSettingCityWeather();
        actionOfCarrier();
    }

    // Действие таймера
    Timer timer = new javax.swing.Timer( 10, new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            setSettingCityWeather();
            actionOfCarrier();
        }
    } );

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        label21 = new JLabel();
        label22 = new JLabel();
        label24 = new JLabel();
        label25 = new JLabel();
        label26 = new JLabel();
        label27 = new JLabel();
        label28 = new JLabel();
        label29 = new JLabel();
        label30 = new JLabel();
        label31 = new JLabel();
        label32 = new JLabel();
        label33 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        scrollPane3 = new JScrollPane();
        table3 = new JTable();
        scrollPane4 = new JScrollPane();
        table4 = new JTable();
        label23 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setVisible(true);
        setTitle("<<Summer Practice>> \u0422\u0440\u0430\u043d\u0441\u043f\u043e\u0440\u0442\u043d\u043e\u0435 \u0410\u0433\u0435\u043d\u0442\u0441\u0442\u0432\u043e");
        setResizable(false);
        setMinimumSize(new Dimension(950, 550));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
            panel1.setMaximumSize(new Dimension(286, 429));
            panel1.setPreferredSize(new Dimension(286, 429));
            panel1.setEnabled(false);

            //---- label2 ----
            label2.setText("\u0417\u0430\u044f\u0432\u043a\u0430 \u2116");
            label2.setFont(new Font("Arial", label2.getFont().getStyle(), label2.getFont().getSize() + 1));

            //---- label3 ----
            label3.setText("\u041c\u0435\u0441\u0442\u043e \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u044f:");
            label3.setFont(new Font("Arial", label3.getFont().getStyle() | Font.ITALIC, label3.getFont().getSize() + 1));

            //---- label4 ----
            label4.setText("\u041c\u0435\u0441\u0442\u043e \u043f\u0440\u0438\u0431\u044b\u0442\u0438\u044f:");
            label4.setFont(new Font("Arial", label4.getFont().getStyle() | Font.ITALIC, label4.getFont().getSize() + 1));

            //---- label5 ----
            label5.setText("\u041f\u043e\u0433\u043e\u0434\u0430:");
            label5.setFont(new Font("Arial", label5.getFont().getStyle() | Font.ITALIC, label5.getFont().getSize() + 1));

            //---- label6 ----
            label6.setText("\u041f\u043e\u0433\u043e\u0434\u0430:");
            label6.setFont(new Font("Arial", label6.getFont().getStyle() | Font.ITALIC, label6.getFont().getSize() + 1));

            //---- label7 ----
            label7.setText("\u0421\u043f\u043e\u0441\u043e\u0431 \u0442\u0440\u0430\u043d\u0441\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u043a\u0438:");
            label7.setFont(new Font("Arial", label7.getFont().getStyle() | Font.ITALIC, label7.getFont().getSize() + 1));

            //---- label8 ----
            label8.setText("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u043d\u044b\u0435 \u043c\u0430\u0433\u0438\u0441\u0442\u0440\u0430\u043b\u0438:");
            label8.setFont(new Font("Arial", label8.getFont().getStyle() | Font.ITALIC, label8.getFont().getSize() + 1));

            //---- label9 ----
            label9.setText("\u041c\u0430\u0441\u0441\u0430 \u0433\u0440\u0443\u0437\u0430:");
            label9.setFont(new Font("Arial", label9.getFont().getStyle() | Font.ITALIC, label9.getFont().getSize() + 1));

            //---- label10 ----
            label10.setText("\u0421\u0442\u043e\u0438\u043c\u043e\u0441\u0442\u044c:");
            label10.setFont(new Font("Arial", label10.getFont().getStyle() | Font.ITALIC, label10.getFont().getSize() + 1));

            //---- label11 ----
            label11.setText("0");

            //---- label12 ----
            label12.setText("0");

            //---- label13 ----
            label13.setText("0");

            //---- label14 ----
            label14.setText("0");

            //---- label15 ----
            label15.setText("0");

            //---- label16 ----
            label16.setText("0");

            //---- label17 ----
            label17.setText("0");

            //---- label18 ----
            label18.setText("0");

            //---- label19 ----
            label19.setText("0");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(label3)
                            .addComponent(label5)
                            .addComponent(label4)
                            .addComponent(label6)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7)
                            .addComponent(label9)
                            .addComponent(label10)
                            .addComponent(label2))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label12)
                            .addComponent(label13)
                            .addComponent(label14)
                            .addComponent(label15)
                            .addComponent(label16)
                            .addComponent(label17)
                            .addComponent(label18)
                            .addComponent(label19)
                            .addComponent(label11, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label11))
                        .addGap(30, 30, 30)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label12))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label13))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label14))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label15))
                        .addGap(41, 41, 41)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label16)
                            .addComponent(label7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label17)
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label18)
                            .addComponent(label9, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label19)
                            .addComponent(label10, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
            );
        }

        //---- label20 ----
        label20.setText("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043a\u0430 \u043f\u043e \u0433\u043e\u0440\u043e\u0434\u0430\u043c");
        label20.setFont(label20.getFont().deriveFont(Font.PLAIN, label20.getFont().getSize() + 5f));

        //---- label21 ----
        label21.setText("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043a\u0430 \u043f\u043e \u0441\u043f\u043e\u0441\u043e\u0431\u0443 \u0434\u043e\u0441\u0442\u0430\u0432\u043a\u0438");
        label21.setFont(label21.getFont().deriveFont(Font.PLAIN, label21.getFont().getSize() + 5f));

        //---- label22 ----
        label22.setText("\u0418\u0441\u043f\u043e\u043b\u043d\u044f\u0435\u043c\u044b\u0435 \u0437\u0430\u043a\u0430\u0437\u044b");
        label22.setFont(label22.getFont().deriveFont(Font.PLAIN, label22.getFont().getSize() + 4f));

        //---- label24 ----
        label24.setText("\u0414\u043e\u0445\u043e\u0434 \u043d\u0430 \u043c\u0430\u0433\u0438\u0441\u0442\u0440\u0430\u043b\u044f\u0445:");
        label24.setFont(new Font("Arial", label24.getFont().getStyle() | Font.ITALIC, label24.getFont().getSize() + 1));

        //---- label25 ----
        label25.setText("\u0414\u043e\u0445\u043e\u0434 \u043d\u0430 \u043e\u0431\u044b\u0447\u043d\u044b\u0445 \u0434\u043e\u0440\u043e\u0433\u0430\u0445:");
        label25.setFont(new Font("Arial", label25.getFont().getStyle() | Font.ITALIC, label25.getFont().getSize() + 1));

        //---- label26 ----
        label26.setText("\u041f\u043e\u0442\u0435\u0440\u0438 \u0438\u0437-\u0437\u0430 \u043f\u043e\u0433\u043e\u0434\u044b:");
        label26.setFont(new Font("Arial", label26.getFont().getStyle() | Font.ITALIC, label26.getFont().getSize() + 1));

        //---- label27 ----
        label27.setText("\u041e\u0431\u0449\u0438\u0435 \u043f\u043e\u0442\u0435\u0440\u0438:");
        label27.setFont(new Font("Arial", label27.getFont().getStyle() | Font.ITALIC, label27.getFont().getSize() + 1));

        //---- label28 ----
        label28.setText("\u041e\u0431\u0449\u0438\u0439 \u0434\u043e\u0445\u043e\u0434:");
        label28.setFont(new Font("Arial", label28.getFont().getStyle() | Font.ITALIC, label28.getFont().getSize() + 1));

        //---- label29 ----
        label29.setText("0");

        //---- label30 ----
        label30.setText("0");

        //---- label31 ----
        label31.setText("0");

        //---- label32 ----
        label32.setText("0");

        //---- label33 ----
        label33.setText("0");

        //======== scrollPane1 ========
        {
            scrollPane1.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

            //---- table1 ----
            table1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            table1.setColumnSelectionAllowed(true);
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {"BRN", "0", "0", "0"},
                    {"VDK", "0", "0", "0"},
                    {"KND", "0", "0", "0"},
                    {"MSK", "0", "0", "0"},
                    {"NSK", "0", "0", "0"},
                },
                new String[] {
                    "\u0413\u043e\u0440\u043e\u0434", "\u041f\u0440\u0438\u0431\u044b\u043b\u044c", "\u041f\u043e\u0442\u0435\u0440\u0438", "\u0412\u0440\u0435\u043c\u044f \u0434\u043e\u0441\u0442\u0430\u0432\u043a\u0438"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = table1.getColumnModel();
                cm.getColumn(0).setResizable(false);
            }
            table1.setBorder(null);
            table1.setEnabled(false);
            scrollPane1.setViewportView(table1);
        }

        //======== scrollPane2 ========
        {
            scrollPane2.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

            //---- table2 ----
            table2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            table2.setColumnSelectionAllowed(true);
            table2.setModel(new DefaultTableModel(
                new Object[][] {
                    {"\u0410\u0432\u0442\u043e\u043c\u043e\u0431\u0438\u043b\u044c\u043d\u044b\u0439", "0", "0", "0"},
                    {"\u0416\u0435\u043b\u0435\u0437\u043d\u043e\u0434\u043e\u0440\u043e\u0436\u043d\u044b\u0439", "0", "0", "0"},
                    {"\u0412\u043e\u0437\u0434\u0443\u0448\u043d\u044b\u0439", "0", "0", "0"},
                },
                new String[] {
                    "\u0413\u043e\u0440\u043e\u0434", "\u041f\u0440\u0438\u0431\u044b\u043b\u044c", "\u041f\u043e\u0442\u0435\u0440\u0438", "\u0412\u0440\u0435\u043c\u044f \u0434\u043e\u0441\u0442\u0430\u0432\u043a\u0438"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            {
                TableColumnModel cm = table2.getColumnModel();
                cm.getColumn(0).setResizable(false);
            }
            table2.setBorder(null);
            table2.setEnabled(false);
            scrollPane2.setViewportView(table2);
        }

        //======== scrollPane3 ========
        {

            //---- table3 ----
            table3.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u2116", "\u0413\u043e\u0440\u043e\u0434 \u043f\u0440\u0438\u0431\u044b\u0442\u0438\u044f", "\u0422\u0438\u043f \u0434\u043e\u0441\u0442\u0430\u0432\u043a\u0438", "\u0412\u0440\u0435\u043c\u044f \u0434\u043e\u0441\u0442\u0430\u0432\u043a\u0438"
                }
            ));
            scrollPane3.setViewportView(table3);
        }

        //======== scrollPane4 ========
        {

            //---- table4 ----
            table4.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u2116", "\u0413\u043e\u0440\u043e\u0434 \u043f\u0440\u0438\u0431\u044b\u0442\u0438\u044f", "\u0422\u0438\u043f \u0434\u043e\u0441\u0442\u0430\u0432\u043a\u0438", "\u0412\u0440\u0435\u043c\u044f \u0434\u043e\u0441\u0442\u0430\u0432\u043a\u0438"
                }
            ));
            scrollPane4.setViewportView(table4);
        }

        //---- label23 ----
        label23.setText("\u0417\u0430\u0434\u0435\u0440\u0436\u0438\u0432\u0430\u0435\u043c\u044b\u0435 \u0437\u0430\u043a\u0430\u0437\u044b");
        label23.setFont(label23.getFont().deriveFont(Font.PLAIN, label23.getFont().getSize() + 4f));

        //---- button1 ----
        button1.setText("\u041d\u0430\u0447\u0430\u0442\u044c");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });

        //---- button2 ----
        button2.setText("\u041f\u043e\u0448\u0430\u0433\u043e\u0432\u043e");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });

        //---- label1 ----
        label1.setText("\u0425\u0430\u0440\u0430\u043a\u0442\u0435\u0440\u0438\u0441\u0442\u0438\u043a\u0438 \u0414\u043e\u0441\u0442\u0430\u0432\u043a\u0438");
        label1.setFont(label1.getFont().deriveFont(Font.PLAIN, label1.getFont().getSize() + 5f));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label22))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label23))
                            .addGap(0, 53, Short.MAX_VALUE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label20)
                                .addComponent(label21)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(54, 54, 54)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label27)
                                        .addComponent(label28)
                                        .addComponent(label26))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(label32, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(label33, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE))
                                        .addComponent(label31, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(label25)
                                        .addComponent(label24))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label29, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                        .addComponent(label30, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))))
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap(430, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                    .addGap(173, 173, 173)
                    .addComponent(button2, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                    .addGap(84, 84, 84))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label20, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label24, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label29))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label25, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label30))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label26, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label31)))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(label21, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label27, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label33))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label32)
                                        .addComponent(label28, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(label23, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(label22, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addGap(28, 28, 28)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1)
                                .addComponent(button2)))
                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 458, GroupLayout.PREFERRED_SIZE))
                    .addGap(5, 5, 5))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JLabel label21;
    private JLabel label22;
    private JLabel label24;
    private JLabel label25;
    private JLabel label26;
    private JLabel label27;
    private JLabel label28;
    private JLabel label29;
    private JLabel label30;
    private JLabel label31;
    private JLabel label32;
    private JLabel label33;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JScrollPane scrollPane3;
    private JTable table3;
    private JScrollPane scrollPane4;
    private JTable table4;
    private JLabel label23;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
        // JFormDesigner - End of variables declaration  //GEN-END:variables
}
