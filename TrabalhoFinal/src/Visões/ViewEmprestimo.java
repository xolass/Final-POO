package Visões;

import Controles.ControleEmprestimo;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

public class ViewEmprestimo implements ActionListener {
    
    ControleEmprestimo cE;
    JPanel main = new JPanel(new FlowLayout(1, 5000, 8));

    JTextField tIsbn = new JTextField(16);
    JTextField tId = new JTextField(16);
    JTextField tData = new JTextField(16);
    JButton empBtn = new JButton("Emprestar");
    JButton devBtn = new JButton("Devolver");
    public ViewEmprestimo() {
        cE = new ControleEmprestimo(this);
        
    }

    public JPanel ViewEmprestimo() {

        JLabel isbn = new JLabel("ISBN");
        JLabel id = new JLabel("ID do cliente ");
        JLabel data = new JLabel("Data de Emprestimo");
        JLabel blank = new JLabel(" ");

        main.add(isbn);
        main.add(tIsbn);
        main.add(id);
        main.add(tId);
        main.add(data);
        main.add(tData);
        main.add(blank);
        main.add(empBtn);

        empBtn.addActionListener(this);
        main.setSize(400, 400);

        return main;

    }

    public JPanel ViewDevolucao() {
        JLabel isbn = new JLabel("ISBN do exemplar emprestado");
        JLabel id = new JLabel("ID do cliente ");
        JLabel blank = new JLabel(" ");

        main.add(id);
        main.add(tId);
        main.add(isbn);
        main.add(tIsbn);
        main.add(blank);
        main.add(devBtn);

        devBtn.addActionListener(this);

        main.setSize(400, 400);
        
        return main;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == empBtn)
        {
            try
            {
                int id = Integer.parseInt(tIsbn.getText());
                int isbn = Integer.parseInt(tIsbn.getText());

                Date data = cE.checkDate(tData.getText());
                cE.cadastraExemplar(id, isbn, data);

            } catch (Exception x)
            {
                JOptionPane.showMessageDialog(main, "ID e ISBN devem ser numeros inteiros");
            }
        } else if (e.getSource() == devBtn)
        {
            try
            {
                cE.checkIsbn(tIsbn.getText());

                try
                {
                    cE.checkID(tId.getText());

                    try
                    {
                        cE.checkIsbn(tIsbn.getText());
                        try
                        {
                            cE.devolveExemplar(tIsbn.getText(),tId.getText());
                            JOptionPane.showMessageDialog(main,"Devolução Registrada");
                        }catch(Exception x)
                        {
                            JOptionPane.showMessageDialog(main,"Devolução Falhou");
                        }
                        
                    } catch (Exception x)
                    {
                        JOptionPane.showMessageDialog(main, "ISBN não cadastrado");
                    }
                    
                } catch (Exception x)
                {
                    JOptionPane.showMessageDialog(main, "Cliente não cadastrado");
                }
                    
            } catch (Exception x)
            {
                JOptionPane.showMessageDialog(main, "Exemplar não cadastrado");
            }

        }

    }

    public void showMessageError() {

        JOptionPane.showMessageDialog(null, "O campo data deve estar no formato dd/mm/aaaa");
    }

}
