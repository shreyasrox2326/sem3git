import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    static Font UI_FONT;
    static String[] fontNames;
        public static void main(String[] args) {
            UI_FONT = new Font("Arial", Font.PLAIN, 14);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontNames = ge.getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            if (fontName.contains("Sans")) {
                UI_FONT = new Font(fontName, Font.PLAIN, 14);
                System.out.println("Selected UI Font: " + fontName); // Print the selected font name
                break; // Exit the loop after finding the first match
            }
        }
        // Fallback to a default font if no 'Sans' font is found
        

        SwingUtilities.invokeLater(() -> new TextEditor().setVisible(true));
    }
}

class TextEditor extends JFrame {
    // UI Customization Variables
    private static Color BACKGROUND_COLOR = new Color(240, 240, 240);
    private static Color ACCENT_COLOR = new Color(63, 81, 181);
    private static final Color DRAWING_BACKGROUND = Color.WHITE;

    private JTextPane textPane;
    private JFileChooser fileChooser;
    private DrawingPanel drawPanel;
    private JButton undoButton, redoButton, clearButton;
    private String currentShape = "Rectangle";

    public TextEditor() {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Advanced Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        Font UI_FONT = Main.UI_FONT;
        // Customize default UI
        UIManager.put("Button.font", UI_FONT);
        UIManager.put("Menu.font", UI_FONT);
        UIManager.put("MenuItem.font", UI_FONT);

        textPane = new JTextPane();

        textPane.setFont(new Font("Courier New", Font.PLAIN, 14)); // Set default font for text
        JScrollPane textScrollPane = new JScrollPane(textPane);
        textScrollPane.getViewport().setBackground(BACKGROUND_COLOR);

        fileChooser = new JFileChooser();

        JMenuBar menuBar = createMenuBar();
        setJMenuBar(menuBar);

        drawPanel = new DrawingPanel();
        drawPanel.setPreferredSize(new Dimension(400, 700));
        drawPanel.setBackground(DRAWING_BACKGROUND);

        // Create control panel for drawing
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(BACKGROUND_COLOR);

        undoButton = createStyledButton("Undo");
        redoButton = createStyledButton("Redo");
        clearButton = createStyledButton("Clear");

        undoButton.addActionListener(e -> drawPanel.undo());
        redoButton.addActionListener(e -> drawPanel.redo());
        clearButton.addActionListener(e -> drawPanel.clearShapes());

        controlPanel.add(undoButton);
        controlPanel.add(redoButton);
        controlPanel.add(clearButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(drawPanel, BorderLayout.CENTER);
        rightPanel.add(controlPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, textScrollPane, rightPanel);
        splitPane.setResizeWeight(0.7);

        add(splitPane, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(Main.UI_FONT);
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private void findAndReplace() {
        JDialog findReplaceDialog = new JDialog(this, "Find and Replace", true);
        findReplaceDialog.setLayout(new GridLayout(4, 2));

        JTextField findField = new JTextField(20);
        JTextField replaceField = new JTextField(20);
        JCheckBox replaceAllCheckbox = new JCheckBox("Replace All");

        findReplaceDialog.add(new JLabel("Find:"));
        findReplaceDialog.add(findField);
        findReplaceDialog.add(new JLabel("Replace with:"));
        findReplaceDialog.add(replaceField);
        findReplaceDialog.add(new JLabel(""));
        findReplaceDialog.add(replaceAllCheckbox);

        JButton replaceButton = new JButton("Replace");
        replaceButton.addActionListener(e -> {
            String findText = findField.getText();
            String replaceText = replaceField.getText();

            if (replaceAllCheckbox.isSelected()) {
                // Replace all occurrences
                String text = textPane.getText();
                text = text.replaceAll(findText, replaceText);
                textPane.setText(text);
            } else {
                // Find and replace first occurrence
                String text = textPane.getText();
                int index = text.indexOf(findText);
                if (index != -1) {
                    textPane.select(index, index + findText.length());
                    textPane.replaceSelection(replaceText);
                }
            }
        });

        findReplaceDialog.add(replaceButton);

        findReplaceDialog.setSize(300, 200);
        findReplaceDialog.setLocationRelativeTo(this);
        findReplaceDialog.setVisible(true);
    }

    private void showTextStatistics() {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        try {
            String selectedText = textPane.getDocument().getText(start, end - start);

            // Count words (split by whitespace)
            int wordCount = selectedText.trim().isEmpty() ? 0 : selectedText.trim().split("\\s+").length;
            int charCount = selectedText.length();

            // Create a status bar or dialog to show statistics
            JDialog statsDialog = new JDialog(this, "Text Statistics", true);
            statsDialog.setLayout(new GridLayout(2, 2));

            statsDialog.add(new JLabel("Total Words:"));
            statsDialog.add(new JLabel(String.valueOf(wordCount)));
            statsDialog.add(new JLabel("Total Characters:"));
            statsDialog.add(new JLabel(String.valueOf(charCount)));

            statsDialog.setSize(250, 100);
            statsDialog.setLocationRelativeTo(this);
            statsDialog.setVisible(true);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void changeFontType(String fontName) {
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attr, fontName);

        // If no text is selected, apply the font change to the entire document
        if (start == end) {
            doc.setCharacterAttributes(0, doc.getLength(), attr, false);
        } else {
            doc.setCharacterAttributes(start, end - start, attr, false);
        }

        // Debugging: Print the font name being set
        System.out.println("Font changed to: " + fontName);

        // Repaint the JTextPane to reflect the font change
        textPane.revalidate(); 

        textPane.repaint();
    }

    private void changeFontSize(int size) {
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        if (start == end)
            return;

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontSize(attr, size);
        doc.setCharacterAttributes(start, end - start, attr, false);
    }

    private void changeTextCase(boolean toUpperCase) {
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        if (start == end)
            return;

        try {
            String selectedText = textPane.getDocument().getText(start, end - start);
            String transformedText = toUpperCase ? selectedText.toUpperCase() : selectedText.toLowerCase();

            textPane.getDocument().remove(start, end - start);
            textPane.getDocument().insertString(start, transformedText, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // Rest of the previous implementation remains the same
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        newItem.addActionListener(e -> textPane.setText(""));
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);

        // Edit menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        cutItem.addActionListener(e -> textPane.cut());
        copyItem.addActionListener(e -> textPane.copy());
        pasteItem.addActionListener(e -> textPane.paste());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        menuBar.add(editMenu);

        // Add Find and Replace to Edit menu
        JMenuItem findReplaceItem = new JMenuItem("Find and Replace");
        findReplaceItem.addActionListener(e -> findAndReplace());
        editMenu.add(findReplaceItem);

        // Add Text Statistics to Edit menu
        JMenuItem textStatsItem = new JMenuItem("Text Statistics");
        textStatsItem.addActionListener(e -> showTextStatistics());
        editMenu.add(textStatsItem);

        // Format menu
        JMenu formatMenu = new JMenu("Format");
        JMenuItem boldItem = new JMenuItem("Bold");
        JMenuItem italicItem = new JMenuItem("Italic");
        JMenuItem underlineItem = new JMenuItem("Underline");
        JMenuItem strikethroughItem = new JMenuItem("Strikethrough");

        boldItem.addActionListener(e -> toggleStyle(StyleConstants.Bold));
        italicItem.addActionListener(e -> toggleStyle(StyleConstants.Italic));
        underlineItem.addActionListener(e -> toggleStyle(StyleConstants.Underline));
        strikethroughItem.addActionListener(e -> toggleStyle(StyleConstants.StrikeThrough));

        formatMenu.add(boldItem);
        formatMenu.add(italicItem);
        formatMenu.add(underlineItem);
        formatMenu.add(strikethroughItem);
        menuBar.add(formatMenu);

        // Alignment menu
        JMenu alignmentMenu = new JMenu("Alignment");
        JMenuItem leftAlign = new JMenuItem("Left");
        JMenuItem centerAlign = new JMenuItem("Center");
        JMenuItem rightAlign = new JMenuItem("Right");
        JMenuItem justifyAlign = new JMenuItem("Justify");

        leftAlign.addActionListener(e -> setAlignment(StyleConstants.ALIGN_LEFT));
        centerAlign.addActionListener(e -> setAlignment(StyleConstants.ALIGN_CENTER));
        rightAlign.addActionListener(e -> setAlignment(StyleConstants.ALIGN_RIGHT));
        justifyAlign.addActionListener(e -> setAlignment(StyleConstants.ALIGN_JUSTIFIED));

        alignmentMenu.add(leftAlign);
        alignmentMenu.add(centerAlign);
        alignmentMenu.add(rightAlign);
        alignmentMenu.add(justifyAlign);
        menuBar.add(alignmentMenu);

        // Shapes menu
        JMenu shapesMenu = new JMenu("Shapes");
        JMenuItem rectShape = new JMenuItem("Rectangle");
        JMenuItem ovalShape = new JMenuItem("Oval");
        JMenuItem lineShape = new JMenuItem("Line");
        JMenuItem triangleShape = new JMenuItem("Triangle");
        JMenuItem freehandShape = new JMenuItem("Freehand");

        rectShape.addActionListener(e -> currentShape = "Rectangle");
        ovalShape.addActionListener(e -> currentShape = "Oval");
        lineShape.addActionListener(e -> currentShape = "Line");
        triangleShape.addActionListener(e -> currentShape = "Triangle");
        freehandShape.addActionListener(e -> currentShape = "Freehand");

        shapesMenu.add(rectShape);
        shapesMenu.add(ovalShape);
        shapesMenu.add(lineShape);
        shapesMenu.add(triangleShape);
        shapesMenu.add(freehandShape);
        menuBar.add(shapesMenu);

        // Font Type Submenu
        JMenu fontMenu = new JMenu("Font");
        // String[] fontNames = { "Arial", "Verdana", "Times New Roman", "Courier New" };
        for (String fontName : Main.fontNames) {
            JMenuItem fontItem = new JMenuItem(fontName);
            fontItem.addActionListener(e -> changeFontType(fontName));
            fontMenu.add(fontItem);
        }

        // Font Size Submenu
        JMenu fontSizeMenu = new JMenu("Font Size");
        int[] fontSizes = { 8, 10, 12, 14, 16, 18, 20, 24 };
        for (int size : fontSizes) {
            JMenuItem sizeItem = new JMenuItem(String.valueOf(size));
            sizeItem.addActionListener(e -> changeFontSize(size));
            fontSizeMenu.add(sizeItem);
        }

        // Case Change Submenu
        JMenu caseMenu = new JMenu("Case");
        JMenuItem upperCaseItem = new JMenuItem("Uppercase");
        JMenuItem lowerCaseItem = new JMenuItem("Lowercase");
        upperCaseItem.addActionListener(e -> changeTextCase(true));
        lowerCaseItem.addActionListener(e -> changeTextCase(false));
        caseMenu.add(upperCaseItem);
        caseMenu.add(lowerCaseItem);

        formatMenu.add(fontMenu);
        formatMenu.add(fontSizeMenu);
        formatMenu.add(caseMenu);
        return menuBar;
    }

    private void openFile() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textPane.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textPane.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        }
    }

    private void toggleStyle(Object style) {
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();
        if (start == end)
            return;

        MutableAttributeSet attr = new SimpleAttributeSet();
        boolean current = StyleConstants.isBold(textPane.getCharacterAttributes())
                || StyleConstants.isItalic(textPane.getCharacterAttributes())
                || StyleConstants.isUnderline(textPane.getCharacterAttributes())
                || StyleConstants.isStrikeThrough(textPane.getCharacterAttributes());

        StyleConstants.setBold(attr, style == StyleConstants.Bold && !current);
        StyleConstants.setItalic(attr, style == StyleConstants.Italic && !current);
        StyleConstants.setUnderline(attr, style == StyleConstants.Underline && !current);
        StyleConstants.setStrikeThrough(attr, style == StyleConstants.StrikeThrough && !current);
        doc.setCharacterAttributes(start, end - start, attr, false);
    }

    private void setAlignment(int alignment) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, alignment);
        doc.setParagraphAttributes(0, doc.getLength(), attr, false);
    }

    class DrawingPanel extends JPanel {
        private List<DrawingShape> shapes = new ArrayList<>();
        private Stack<DrawingShape> undoStack = new Stack<>();
        private int startX, startY, endX, endY;
        private List<Point> freehandPoints = new ArrayList<>();

        public DrawingPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startX = e.getX();
                    startY = e.getY();
                    if ("Freehand".equals(currentShape)) {
                        freehandPoints.clear();
                        freehandPoints.add(new Point(startX, startY));
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    endX = e.getX();
                    endY = e.getY();
                    if (!"Freehand".equals(currentShape)) {
                        addShape();
                        repaint();
                    }
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if ("Freehand".equals(currentShape)) {
                        freehandPoints.add(new Point(e.getX(), e.getY()));
                        repaint();
                    } else {
                        endX = e.getX();
                        endY = e.getY();
                        repaint();
                    }
                }
            });
        }

        private void addShape() {
            DrawingShape shape = null;
            switch (currentShape) {
                case "Rectangle":
                    shape = new DrawingShape.Rectangle(startX, startY, endX, endY);
                    break;
                case "Oval":
                    shape = new DrawingShape.Oval(startX, startY, endX, endY);
                    break;
                case "Line":
                    shape = new DrawingShape.Line(startX, startY, endX, endY);
                    break;
                case "Triangle":
                    shape = new DrawingShape.Triangle(startX, startY, endX, endY);
                    break;
                case "Freehand":
                    shape = new DrawingShape.Freehand(new ArrayList<>(freehandPoints));
                    break;
            }

            if (shape != null) {
                shapes.add(shape);
                undoStack.clear(); // Clear redo stack when a new shape is drawn
            }
        }

        public void undo() {
            if (!shapes.isEmpty()) {
                undoStack.push(shapes.remove(shapes.size() - 1));
                repaint();
            }
        }

        public void redo() {
            if (!undoStack.isEmpty()) {
                shapes.add(undoStack.pop());
                repaint();
            }
        }

        public void clearShapes() {
            shapes.clear();
            undoStack.clear();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            // Draw existing shapes
            for (DrawingShape shape : shapes) {
                shape.draw(g);
            }

            // Draw current shape being drawn
            if (!shapes.isEmpty() || "Freehand".equals(currentShape)) {
                switch (currentShape) {
                    case "Rectangle":
                        g.drawRect(Math.min(startX, endX), Math.min(startY, endY),
                                Math.abs(startX - endX), Math.abs(startY - endY));
                        break;
                    case "Oval":
                        g.drawOval(Math.min(startX, endX), Math.min(startY, endY),
                                Math.abs(startX - endX), Math.abs(startY - endY));
                        break;
                    case "Line":
                        g.drawLine(startX, startY, endX, endY);
                        break;
                    case "Triangle":
                        int[] xPoints = { startX, (startX + endX) / 2, endX };
                        int[] yPoints = { endY, startY, endY };
                        g.drawPolygon(xPoints, yPoints, 3);
                        break;
                    case "Freehand":
                        for (int i = 1; i < freehandPoints.size(); i++) {
                            Point p1 = freehandPoints.get(i - 1);
                            Point p2 = freehandPoints.get(i);
                            g.drawLine(p1.x, p1.y, p2.x, p2.y);
                        }
                        break;
                }
            }
        }
    }

    abstract static class DrawingShape implements Serializable {
        private static final long serialVersionUID = 1L;

        abstract void draw(Graphics g);

        static class Rectangle extends DrawingShape {
            private static final long serialVersionUID = 1L;
            int x1, y1, x2, y2;

            Rectangle(int x1, int y1, int x2, int y2) {
                this.x1 = Math.min(x1, x2);
                this.y1 = Math.min(y1, y2);
                this.x2 = Math.max(x1, x2);
                this.y2 = Math.max(y1, y2);
            }

            @Override
            void draw(Graphics g) {
                g.drawRect(x1, y1, x2 - x1, y2 - y1);
            }
        }

        // do Similar modifications for other shape classes (Oval, Line, Triangle,
        // Freehand)

        // ... (implement Serializable with serialVersionUID)

        static class Oval extends DrawingShape {
            int x1, y1, x2, y2;

            Oval(int x1, int y1, int x2, int y2) {
                this.x1 = Math.min(x1, x2);
                this.y1 = Math.min(y1, y2);
                this.x2 = Math.max(x1, x2);
                this.y2 = Math.max(y1, y2);
            }

            @Override
            void draw(Graphics g) {
                g.drawOval(x1, y1, x2 - x1, y2 - y1);
            }
        }

        static class Line extends DrawingShape {
            int x1, y1, x2, y2;

            Line(int x1, int y1, int x2, int y2) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }

            @Override
            void draw(Graphics g) {
                g.drawLine(x1, y1, x2, y2);
            }
        }

        static class Triangle extends DrawingShape {
            int[] xPoints;
            int[] yPoints;

            Triangle(int x1, int y1, int x2, int y2) {
                xPoints = new int[] { x1, (x1 + x2) / 2, x2 };
                yPoints = new int[] { y2, y1, y2 };
            }

            @Override
            void draw(Graphics g) {
                g.drawPolygon(xPoints, yPoints, 3);
            }
        }

        static class Freehand extends DrawingShape {
            List<Point> points;

            Freehand(List<Point> points) {
                this.points = points;
            }

            @Override
            void draw(Graphics g) {
                for (int i = 1; i < points.size(); i++) {
                    Point p1 = points.get(i - 1);
                    Point p2 = points.get(i);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
    }

    // Method to change font dynamically
    public void setCustomFont(Font font) {
        textPane.setFont(font);
        UIManager.put("Button.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        SwingUtilities.updateComponentTreeUI(this);
    }

    // Method to change color scheme dynamically
    public void setColorScheme(Color backgroundColor, Color accentColor, Color textColor) {
        BACKGROUND_COLOR = backgroundColor;
        ACCENT_COLOR = accentColor;
        // Update UI components
        textPane.setBackground(backgroundColor);
        textPane.setForeground(textColor);
        drawPanel.setBackground(backgroundColor);

        undoButton.setBackground(accentColor);
        redoButton.setBackground(accentColor);
        clearButton.setBackground(accentColor);

        SwingUtilities.updateComponentTreeUI(this);
    }
}