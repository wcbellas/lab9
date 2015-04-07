package com.mycompany.lab9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class urlReader extends JFrame implements ActionListener {

    JTextField input1, input2, intervalI;
    JButton exitButton, goButton;
    JFrame frame;
    JLabel intro, stock1, stock2, intervalL, stock1LOut, stock2LOut, stock1Out, stock2Out, avg1, avg2, avg1Out, avg2Out;
    JPanel stockPanel, stockOut, avg;
    float gAvg1, gAvg2;
    int totalIntervals;

    public urlReader() {
        frame = new JFrame("Stock Viewer");
        intro = new JLabel("<html>This Project will display sock information and some way to select stock symbols and time intervals<br>stock symbols and current retrieved values<br>stock symbols and average from the previous readings</html>", SwingConstants.CENTER);
        stock1 = new JLabel("Enter Stock #1:");
        stock2 = new JLabel("Enter Stock #2:");
        intervalL = new JLabel("Enter Interval (in seconds):");
        input1 = new JTextField(4);
        input2 = new JTextField(4);
        intervalI = new JTextField(20);
        stockPanel = new JPanel();
        goButton = new JButton("Go!");
        goButton.addActionListener(this);
        stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.PAGE_AXIS));
        stockPanel.add(stock1);
        stockPanel.add(input1);
        stockPanel.add(stock2);
        stockPanel.add(input2);
        stockPanel.add(intervalL);
        stockPanel.add(intervalI);
        stockPanel.add(goButton);
        frame.add(stockPanel, BorderLayout.LINE_START);
        stock1LOut = new JLabel("Stock 1 Price:");
        stock2LOut = new JLabel("Stock 2 Price:");
        stock1Out = new JLabel("$$$$$");
        stock1Out.setBackground(Color.BLACK);
        stock1Out.setOpaque(true);
        stock2Out = new JLabel("$$$$$");
        stock2Out.setBackground(Color.BLACK);
        stock2Out.setOpaque(true);
        stockOut = new JPanel();
        stockOut.setLayout(new BoxLayout(stockOut, BoxLayout.PAGE_AXIS));
        stockOut.add(stock1LOut);
        stockOut.add(stock1Out);
        stockOut.add(stock2LOut);
        stockOut.add(stock2Out);
        frame.add(stockOut, BorderLayout.CENTER);
        avg1 = new JLabel("Average for Stock 1(past 10 intervals):");
        avg2 = new JLabel("Average for Stock 2(past 10 intervals):");
        avg1Out = new JLabel("$$$$$$$$");
        avg2Out = new JLabel("$$$$$$$$");
        avg = new JPanel();
        avg.setLayout(new BoxLayout(avg, BoxLayout.PAGE_AXIS));
        avg.add(avg1);
        avg.add(avg1Out);
        avg.add(avg2);
        avg.add(avg2Out);
        frame.add(avg, BorderLayout.LINE_END);
        frame.add(intro, BorderLayout.PAGE_START);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        frame.add(exitButton, BorderLayout.PAGE_END);
        frame.setSize(700, 500);
        frame.setVisible(true);
        gAvg1 = 0;
        gAvg2 = 0;
        totalIntervals = 0;
    }

    public static void main(String[] args) throws Exception {

        urlReader mainWindow = new urlReader();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            dispose();
            System.exit(0);
        }
        if (e.getSource() == goButton) {
            try {
                loopOut();
            } catch (Exception ex) {
                System.out.println("Not Valid Input");
            }
        }
    }

    public void loopOut() throws Exception {
        try {
            for (int i = 1; i <= 10; i++) {
                Timer t = new Timer((Integer.parseInt(intervalI.getText()) * 1000) * i, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            goButton.setEnabled(false);
                            URL yahoofinance;
                            yahoofinance = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + input1.getText() + "+" + input2.getText() + "&f=hg");
                            URLConnection yc = yahoofinance.openConnection();
                            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                            String inputLine, totalLine = "";
                            while ((inputLine = in.readLine()) != null) {
                                totalLine += inputLine;
                                totalLine += "|";
                            }
                            in.close();
                            float stNum1 = Float.parseFloat(totalLine.substring(0, totalLine.indexOf(',')));
                            float stNum2 = Float.parseFloat(totalLine.substring(totalLine.indexOf('|') + 1, totalLine.indexOf(',', totalLine.indexOf('|'))));
                            stock1Out.setText("$" + Float.toString(stNum1));
                            stock2Out.setText("$" + Float.toString(stNum2));
                            gAvg1 = gAvg1 + stNum1;
                            gAvg2 = gAvg2 + stNum2;
                            totalIntervals++;
                            avg1Out.setText("$" + Float.toString(gAvg1 / totalIntervals));
                            avg2Out.setText("$" + Float.toString(gAvg2 / totalIntervals));

                            if (stNum1 > gAvg1 / totalIntervals) {
                                stock1Out.setForeground(Color.GREEN);
                            } else if (stNum1 == gAvg1 / totalIntervals) {
                                stock1Out.setForeground(Color.YELLOW);
                            } else {
                                stock1Out.setForeground(Color.RED);
                            }

                            if (stNum2 > gAvg2 / totalIntervals) {
                                stock2Out.setForeground(Color.GREEN);
                            } else if (stNum2 == gAvg2 / totalIntervals) {
                                stock2Out.setForeground(Color.YELLOW);
                            } else {
                                stock2Out.setForeground(Color.RED);
                            }

                        } catch (IOException ex) {
                            System.out.println("oops");
                        }
                    }
                });
                t.setRepeats(false);
                t.start();
            }
            Timer disabler = new Timer((Integer.parseInt(intervalI.getText()) * 10000), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goButton.setEnabled(true);
                }
            });
            disabler.setRepeats(false);
            disabler.start();
        } catch (Exception e) {
            System.out.println("Not Valid Input");
        }
    }
}
