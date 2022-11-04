package com.github.avyarkov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowFrame extends JFrame implements ActionListener {
    static final int INF = 1_000_000;
    static final int topHeight = 50;
    static final int gap = 20;
    static final int margin = 20;

    JButton button;
    JTextArea input;
    JTextArea output;

    public WindowFrame() {
        super("ChordTranspose");
        this.setSize(800, 600);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        input.setLineWrap(true);
        input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        output = new JTextArea("Output");
        output.setEditable(false);
        output.setLineWrap(true);
        output.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        center.add(input);
        center.add(output);

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
            output.setText(text);
        }
    }
}
