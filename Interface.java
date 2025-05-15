import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Interface {
    public static void main(String[] args) {
        ArrayList<Produto> produtos = new ArrayList<>();
        Font bold = new Font("Bold", Font.BOLD, 20);

        JFrame frame1 = new JFrame("Cadastro de Produtos");

        JFrame frame2 = new JFrame("Listagem de Produtos");

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel labelNome = new JLabel("Nome:");
        CreateLabel(labelNome, 50,0,100,100, panel1);

        TextField fieldNome = new TextField();
        CreateTextField(fieldNome, 150, 35, 300, 30, bold, panel1);

        JLabel labelPreco = new JLabel("Preço:");
        CreateLabel(labelPreco, 50,50,100,100, panel1);

        TextField fieldPreco = new TextField();
        CreateTextField(fieldPreco, 150, 85, 300, 30, bold, panel1);

        JLabel labelQuantidade = new JLabel("Quantidade:");
        CreateLabel(labelQuantidade, 50,100,100,100, panel1);

        TextField fieldQuantidade = new TextField();
        CreateTextField(fieldQuantidade, 150, 135, 300, 30, bold, panel1);

        JLabel labelConfirmacao = new JLabel();
        CreateLabel(labelConfirmacao, 50, 160, 400,40, panel1);
        labelConfirmacao.setHorizontalAlignment(SwingConstants.CENTER);
        labelConfirmacao.setVisible(false);

        JLabel labelListagem = new JLabel();
        CreateLabel(labelListagem, 50, 50, 500, 200, panel2);

        String [] colunas = {"Nome", "Preço", "Quantidade"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        JButton botaoCadastrar = new JButton("Cadastrar");
        CreateButton(botaoCadastrar, 50,200,100,40, panel1);
        botaoCadastrar.addActionListener(_ -> {
            try {
                if (Float.parseFloat(fieldPreco.getText()) <= 0) {
                    throw new Exception("O valor do preço não pode ser menor ou igual a 0...");
                }
                if (Integer.parseInt(fieldQuantidade.getText()) < 0) {
                    throw new Exception("A quantidade não pode ser negativa...");
                }

                Produto produto = new Produto(
                        fieldNome.getText(),
                        Float.parseFloat(fieldPreco.getText()),
                        Integer.parseInt(fieldQuantidade.getText())
                );
                model.setRowCount(0);
                produtos.add(produto);
                for (Produto prod : produtos){
                    Object [] linha = {
                            prod.nome, prod.preco, prod.quantidade
                    };
                    model.addRow(linha);
                }

                labelConfirmacao.setText("Produto cadastrado!");
                labelConfirmacao.setVisible(true);
            }
            catch (NumberFormatException e){
                labelConfirmacao.setText("Dados inseridos incorretamente, tente novamente...");
                labelConfirmacao.setVisible(true);
            }
            catch (Exception e){
                labelConfirmacao.setText(e.getMessage());
                labelConfirmacao.setVisible(true);
            }


        });

        JButton botaoListar = new JButton("Listar");
        CreateButton(botaoListar, 200,200,100,40, panel1);
        botaoListar.addActionListener(_ -> {
            JTable tabela = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(tabela);
            scrollPane.setBounds(0,0,800,300);
            panel2.add(scrollPane, BorderLayout.CENTER);
            frame1.setVisible(false);
            frame2.setVisible(true);
        });

        JButton botaoLimpar = new JButton("Limpar");
        CreateButton(botaoLimpar, 350,200,100,40, panel1);
        botaoLimpar.addActionListener(_ -> {
            fieldNome.setText("");
            fieldPreco.setText("");
            fieldQuantidade.setText("");
        });

        JButton botaoVoltar = new JButton("Voltar");
        CreateButton(botaoVoltar, 350,310, 100, 40 , panel2);
        botaoVoltar.addActionListener(_ -> {
            frame1.setVisible(true);
            frame2.setVisible(false);
        });

        frame1.setSize(500, 300);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel1);

        frame2.setSize(800, 400);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(false);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.add(panel2);
    }

    public static void CreateTextField(@NotNull TextField var, int x, int y, int width, int height, Font font, @NotNull JPanel panel){
        var.setBounds(x, y, width, height);
        var.setFont(font);
        panel.add(var);
    }

    public static void CreateLabel(@NotNull JLabel var, int x, int y, int width, int height, @NotNull JPanel panel){
        var.setBounds(x, y, width, height);
        panel.add(var);
    }

    public static void CreateButton(@NotNull JButton var, int x, int y, int width, int height, @NotNull JPanel panel){
        var.setBounds(x, y, width, height);
        panel.add(var);
    }
}
