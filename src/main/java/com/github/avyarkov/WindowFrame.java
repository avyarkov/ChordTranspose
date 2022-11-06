package com.github.avyarkov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

class WindowFrame extends JFrame implements ActionListener {
    static final int INF = 1_000_000;
    static final int topHeight = 40;
    static final int gap = 20;
    static final int margin = 20;

    static class MyNumberField extends JTextField {
        static final int maxLength = 2;
        static final int minValue = 0;
        static final int maxValue = 99;

        MyNumberField() {
            super("0");
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent ke) {
                    char inputChar = ke.getKeyChar();
                    if (!Character.isDigit(inputChar) || getText().length() >= maxLength ) {
                        ke.consume();
                    }
                }
            });
        }

        int getNumber() {
            if (getText().isEmpty()) {
                setText("0");
            }
            return Integer.parseInt(getText());
        }

        void setNumber(int number) {
            if (minValue <= number && number <= maxValue) {
                this.setText(Integer.toString(number));
            }
        }
    }

    static class MyIncrementButtonListener implements ActionListener {
        int increment;
        MyNumberField controlledNumberField;

        public MyIncrementButtonListener(MyNumberField controlledNumberField, int increment) {
            super();
            this.increment = increment;
            this.controlledNumberField = controlledNumberField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controlledNumberField.setNumber(controlledNumberField.getNumber() + increment);
        }
    }

    JComboBox<String> comboBox;
    MyNumberField inputNumberField;
    JButton minusButton;
    JButton plusButton;
    JButton transposeButton;
    JTextArea input;
    JScrollPane inputScroll;
    JTextArea output;
    JScrollPane outputScroll;

    WindowFrame() {
        super("ChordTranspose");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int w = gd.getDisplayMode().getWidth();
        int h = gd.getDisplayMode().getHeight();
        this.setSize(w * 5 / 8, h * 5 / 8);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/GuitarIcon.png");
        this.setIconImage(imageIcon.getImage());
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(INF, topHeight));
        top.setOpaque(false);

        JLabel label1 = new JLabel("Transpose");
        JLabel label2 = new JLabel("by");
        JLabel label3 = new JLabel("semitones    ");

        String[] options = {"up", "down"};
        comboBox = new JComboBox<>(options);
        comboBox.setFocusable(false);

        inputNumberField = new MyNumberField();
        inputNumberField.setPreferredSize(new Dimension(40, 25));

        minusButton = new JButton("-");
        minusButton.setFocusable(false);
        minusButton.addActionListener(new MyIncrementButtonListener(inputNumberField, -1));
        plusButton = new JButton("+");
        plusButton.setFocusable(false);
        plusButton.addActionListener(new MyIncrementButtonListener(inputNumberField, 1));

        transposeButton = new JButton("Transpose");
        transposeButton.setFocusable(false);
        transposeButton.addActionListener(this);

        top.add(label1);
        top.add(comboBox);
        top.add(label2);
        top.add(minusButton);
        top.add(inputNumberField);
        top.add(plusButton);
        top.add(label3);
        top.add(transposeButton);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(1, 2, gap, gap));
        center.setOpaque(false);

        input = new JTextArea("Input");
        inputScroll = new JScrollPane(input,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        output = new JTextArea("Output");
        output.setEditable(false);
        outputScroll = new JScrollPane(output,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        center.add(inputScroll);
        center.add(outputScroll);

        JPanel south = new JPanel();
        south.setPreferredSize(new Dimension(margin, margin));
        south.setOpaque(false);
        JPanel west = new JPanel();
        west.setPreferredSize(new Dimension(margin, margin));
        west.setOpaque(false);
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(margin, margin));
        east.setOpaque(false);
        this.add(top, BorderLayout.NORTH);
        this.add(south, BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
        this.add(east, BorderLayout.EAST);
        this.add(center);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == transposeButton) {
            String text = input.getText();
            // TODO
            output.setText(TextParser.replaceWords(text, Map.of("Em", "??")));
            input.setCaretPosition(0);
            output.setCaretPosition(0);
            var inputVertical = inputScroll.getVerticalScrollBar();
            inputVertical.setValue(inputVertical.getMinimum());
            var outputVertical = outputScroll.getVerticalScrollBar();
            outputVertical.setValue(inputVertical.getMinimum());
        }
    }
}
