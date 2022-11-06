package com.github.avyarkov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

class WindowFrame extends JFrame implements ActionListener {
    static final int INF = 1_000_000;
    static final int topHeight = 50;
    static final int gap = 20;
    static final int margin = 20;

    JButton button;
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

        button = new JButton("Transpose");
        button.setFocusable(false);
        button.addActionListener(this);

        top.add(button);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(1, 2, gap, gap));
        center.setOpaque(false);

        input = new JTextArea("Input");
//        input.setLineWrap(true);
        inputScroll = new JScrollPane(input,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        inputScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        output = new JTextArea("Output");
        output.setEditable(false);
//        output.setLineWrap(true);
        outputScroll = new JScrollPane(output,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        outputScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

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
        if (source == button) {
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
