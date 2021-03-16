/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt;

import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnSave1;

    @FXML
    private Button btnSave2;

    @FXML
    private Button btnRelacionar;

    @FXML
    private ComboBox<Nodo> nodo2;

    @FXML
    private ComboBox<Nodo> nodo1;

    @FXML
    private ComboBox<String> sigma;

    @FXML
    private TextField textAccion;

    @FXML
    private TextField labelLetra;

    @FXML
    private TableColumn<String, Nodo> nodoDestinoCol;

    @FXML
    private TableView<Nodo> nodoTable;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<Nodo> estadoInicial;

    @FXML
    private ComboBox<Nodo> eFinal;

    @FXML
    private Button Save;

    @FXML
    private TextField labelEtiqueta;

    @FXML
    private TextField inString;

    @FXML
    private TableColumn<String, Nodo> nodoAccionCol;

    @FXML
    private TableColumn<String, Nodo> nodoCol;

    @FXML
    private TableView<Nodo> infoNodoTable;

    @FXML
    private TableColumn<String, String> nodoDestinoCol1;

    @FXML
    private TableView<String> infoNodoTable1;

    Alert error = new Alert(Alert.AlertType.ERROR);
    Alert message = new Alert(Alert.AlertType.INFORMATION);

    private Maquina maquina;
    private boolean flag = false;
    private ObservableList<Nodo> nodos;
    private ObservableList<String> s;
    private ObservableList<String> accion;
    private ObservableList<Nodo> destino;
    private Nodo aux;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Nodo nodo = this.estadoInicial.getSelectionModel().getSelectedItem();
        Nodo nodo2 = this.eFinal.getSelectionModel().getSelectedItem();
        if (!"".equals(inString.getText()) && flag) {
            if (maquina.validator() && maquina.getGrafo().init(nodo, nodo2)) {
                maquina.setCinta(inString.getText());
                maquina.proceso();
                this.message.setHeaderText(maquina.getResultado());
                this.message.setTitle("Resultado");
                this.message.showAndWait();
                inString.clear();
                inString.setText(maquina.imprimirCinta());
                System.out.println(maquina.getCinta());
                flag = false;
            } else {
                this.error.setHeaderText("Error al activar la maquina");
                this.error.setTitle("Error");
                this.error.setContentText("Verifique que ha seleccionado el estado inicial");
                this.error.showAndWait();
            }
        } else {
            this.error.setHeaderText("Error al activar la maquina");
            this.error.setTitle("Error");
            this.error.setContentText("Verifique que las especificaciones esten llenas");
            this.error.showAndWait();
        }
    }

    @FXML
    private void handleClickTextField() {
        if (!flag) {
            flag = true;
            inString.clear();
        }
    }

    @FXML
    private void handleClickNodeArea() {
        Nodo nodo = new Nodo(labelEtiqueta.getText());
        boolean flag2 = false;
        for (Nodo nodo3 : nodos) {
            if (nodo.getEtiqueta().compareToIgnoreCase(nodo3.getEtiqueta()) == 0) {
                flag2 = true;
            }
        }

        if (!flag2) {
            this.nodos.add(nodo);
            this.nodoTable.setItems(nodos);
            this.nodo1.setItems(nodos);
            this.nodo2.setItems(nodos);
            this.estadoInicial.setItems(nodos);
            this.eFinal.setItems(nodos);
            maquina.getGrafo().addNode(nodo);
            maquina.getGrafo().verNodos();
            labelEtiqueta.clear();
            this.message.setHeaderText("El nodo ha sido creado y añadido");
            this.error.setTitle("Info");
            this.error.setContentText("Todo ok :3");
            this.error.showAndWait();
        } else {
            this.error.setHeaderText("Error al crear el nodo");
            this.error.setTitle("Error");
            this.error.setContentText("Este nodo ya existe");
            this.error.showAndWait();
        }
    }

    @FXML
    private void elementSelected() {

        Nodo nodo = this.nodoTable.getSelectionModel().getSelectedItem();
        if (nodo != null) {
            System.out.println("Pasar informacion a la tabla");
            System.out.println(nodo.getEtiqueta() + ":" + nodos.indexOf(nodo));
            System.out.println(nodo.especificaciones());
            System.out.println(nodo.table().keySet() + " ***********");
            System.out.println(nodo.table().values());
            if (!this.aux.getEtiqueta().equals(nodo.getEtiqueta())) {
                for (Nodo aux : nodo.table().values()) {
                    destino.add(aux);
                }
                for (String accion : nodo.table().keySet()) {
                    this.accion.add(accion);
                }
                infoNodoTable.setItems(destino);
                infoNodoTable1.setItems(accion);
                this.aux = nodo;
            } else {
                destino.clear();
                accion.clear();
            }

        }
    }

    @FXML
    private void handleClickSigmaArea() {
        if (!"".equals(this.labelLetra.getText())) {
            if (!s.contains(labelLetra.getText())) {
                maquina.addletter(labelLetra.getText());
                s.add(labelLetra.getText());
                labelLetra.clear();
                this.sigma.setItems(s);
                this.message.setHeaderText("El caracter ha sido añadido");
                this.message.setTitle("Info");
                this.message.setContentText("Todo ok :3");
                this.message.showAndWait();
            } else {
                this.error.setHeaderText("Error en la accion");
                this.error.setTitle("Error");
                this.error.setContentText("Este elemento ya existe");
                this.error.showAndWait();
                labelLetra.clear();
            }
        } else {
            this.error.setHeaderText("Error en la accion");
            this.error.setTitle("Error");
            this.error.setContentText("Debe escribir algo antes de hacer eso");
            this.error.showAndWait();
        }
    }

    @FXML
    private void nodeJoin(ActionEvent event) {
        Nodo aux = nodo1.getSelectionModel().getSelectedItem();
        Nodo aux2 = nodo2.getSelectionModel().getSelectedItem();

        if (aux != null && aux2 != null) {
            if (!"".equals(this.textAccion.getText())) {
                maquina.getGrafo().getNodos().get(nodos.indexOf(aux)).addAccion(textAccion.getText());
                maquina.getGrafo().getNodos().get(nodos.indexOf(aux)).setTransicion(textAccion.getText(), nodos.get(nodos.indexOf(aux2)));

            } else {
                this.error.setHeaderText("Error al crear union");
                this.error.setTitle("Error");
                this.error.setContentText("Debe rellenar la accion");
                this.error.showAndWait();
            }
        } else {
            this.error.setHeaderText("Error al crear union");
            this.error.setTitle("Error");
            this.error.setContentText("Debe seleccionar 2 nodos");
            this.error.showAndWait();
        }
    }

    @FXML
    public void SaveObject() {
        String fileName = "data.bin";
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(maquina);
            //os.writeObject(nodos);
            //os.writeObject(s);
            os.close();
            System.out.println("write successful");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nodoCol.setCellValueFactory(new PropertyValueFactory("etiqueta"));
        this.nodoDestinoCol1.setCellValueFactory(new PropertyValueFactory("conjuntoAcciones"));
        this.nodoDestinoCol.setCellValueFactory(new PropertyValueFactory("etiqueta"));

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("data.bin"));
            this.maquina = (Maquina) is.readObject();
            nodos = FXCollections.observableArrayList(maquina.getGrafo().getNodos());
            s = FXCollections.observableArrayList(maquina.getSigma());
            this.aux = maquina.getGrafo().getNodos().get(0);
            this.sigma.setItems(s);
            this.estadoInicial.setItems(nodos);
            this.eFinal.setItems(nodos);
            this.nodoTable.setItems(nodos);
            this.nodo1.setItems(nodos);
            this.nodo2.setItems(nodos);
            accion = FXCollections.observableArrayList();
            destino = FXCollections.observableArrayList();
            is.close();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hay que guardar el archivo");
            maquina = new Maquina();
            nodos = FXCollections.observableArrayList();
            s = FXCollections.observableArrayList();
            accion = FXCollections.observableArrayList();
            destino = FXCollections.observableArrayList();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
