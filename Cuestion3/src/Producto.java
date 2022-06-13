import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

public class Producto {


    private JPanel Main;
    private JLabel labelNombre;
    private JLabel labelFecha;
    private JLabel labelUnidades;
    private JLabel labelPrecio;
    private JLabel labelDispo;
    private JTextField txtNombre;
    private JTextField txtFecha;
    private JTextField txtUnidades;
    private JTextField txtPrecio;
    private JButton regBtn;
    private JTable tabla;
    private JButton updbtn;
    private JButton delBtn;
    private JButton seaBtn;
    private JTextField txtId;
    private JLabel txtTitulo;
    private JCheckBox chkDispo;
    private JButton btnShow;
    Connection con;
    PreparedStatement pst;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Producto");
        frame.setContentPane(new Producto().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //frame.setBounds(1920,1080,700,700);
    }
    public Producto() {
        Connection();
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre,fecha,mensaje;
                nombre= txtNombre.getText();
                mensaje="No tiene envase";
                if (txtFecha.getText().equals("")) {
                    fecha = mensaje;
                }else{
                    fecha=txtFecha.getText();
                }
                int dispo,unidades;
                unidades=Integer.parseInt(txtUnidades.getText());
                if(chkDispo.isSelected()){
                    dispo=1;
                }else{
                    dispo=0;
                }
                float precio=Float.parseFloat(txtPrecio.getText());
                try{
                    pst = con.prepareStatement("insert into productos values(default,?,?,?,?,?)");
                    pst.setString(1,nombre);
                    pst.setString(2,fecha);
                    pst.setInt(3,unidades);
                    pst.setFloat(4,precio);
                    pst.setInt(5,dispo);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Producto registrado");
                    cargarTabla();
                    txtNombre.setText("");
                    txtFecha.setText("");
                    txtUnidades.setText("");
                    txtPrecio.setText("");
                    txtId.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        );
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTabla();
            }
        });
        updbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre,fecha,mensaje,id,unidades,precio;
                nombre= txtNombre.getText();
                mensaje="No tiene envase";
                if (txtFecha.getText().equals("")) {
                    fecha = mensaje;
                }else{
                    fecha=txtFecha.getText();
                }
                unidades=txtUnidades.getText();
                id=txtId.getText();
                precio=txtPrecio.getText();
                int dispo;
                if(!Objects.equals(txtNombre.getText(), "")){
                    try {
                        pst=con.prepareStatement("update productos set nombre=? where idProducto=?");
                        pst.setString(1,nombre);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        cargarTabla();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(!Objects.equals(txtFecha.getText(), "")){
                    try {
                        pst=con.prepareStatement("update productos set fechaEnvasado=? where idProducto=?");
                        pst.setString(1,fecha);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        cargarTabla();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(!Objects.equals(txtUnidades.getText(), "")){
                    try {
                        pst=con.prepareStatement("update productos set unidades=? where idProducto=?");
                        pst.setString(1,unidades);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        cargarTabla();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(!Objects.equals(txtPrecio.getText(), "")){
                    try {
                        pst=con.prepareStatement("update productos set precio=? where idProducto=?");
                        pst.setString(1,precio);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        cargarTabla();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(chkDispo.isSelected()) {
                    dispo=1;
                    try {
                        pst=con.prepareStatement("update productos set disponible=? where idProducto=?");
                        pst.setInt(1,dispo);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        cargarTabla();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    dispo=0;
                    try {
                        pst=con.prepareStatement("update productos set disponible=? where idProducto=?");
                        pst.setInt(1,dispo);
                        pst.setString(2,id);
                        pst.executeUpdate();
                        cargarTabla();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                JOptionPane.showMessageDialog(null,"Producto actualizado");
                txtNombre.setText("");
                txtFecha.setText("");
                txtUnidades.setText("");
                txtPrecio.setText("");
                txtId.setText("");
                }
        });
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                id=Integer.parseInt(txtId.getText());
                try{
                    pst=con.prepareStatement("delete from productos where idProducto=?");
                    pst.setInt(1,id);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Producto borrado");
                    cargarTabla();
                    txtNombre.setText("");
                    txtFecha.setText("");
                    txtUnidades.setText("");
                    txtPrecio.setText("");
                    txtId.setText("");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        seaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id;
                id=txtId.getText();
                try{
                    pst=con.prepareStatement("select * from productos where idProducto=?");
                    pst.setString(1,id);
                    ResultSet rs = pst.executeQuery();
                    tabla.setModel(DbUtils.resultSetToTableModel(rs));
                    txtNombre.setText("");
                    txtFecha.setText("");
                    txtUnidades.setText("");
                    txtPrecio.setText("");
                    txtId.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    void cargarTabla() {
        try {
            pst = con.prepareStatement("select * from productos");
            ResultSet rs = pst.executeQuery();
            tabla.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    void Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();

        }
    }
}
